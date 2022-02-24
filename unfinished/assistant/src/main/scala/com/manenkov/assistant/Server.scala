package com.manenkov.assistant

import java.io.{FileInputStream, InputStream}
import java.security.{KeyStore, SecureRandom}
import java.time.{LocalDateTime, LocalTime, ZoneId, ZonedDateTime}

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.DebuggingDirectives
import akka.http.scaladsl.{ConnectionContext, Http, HttpsConnectionContext}
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.manenkov.assistant.TrelloActor.{AddNowCard, DailyLists, DeleteNowCard}
import javax.net.ssl.{KeyManagerFactory, SSLContext, TrustManagerFactory}
import play.api.libs.json.Json

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.language.postfixOps

object Server extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(1 minute)

  val key = "SECRET_KEY"
  val token = "SECRET_TOKEN"
  val playgroundBoardId = "SECRET_ID_1"
  val mainBoardId = "SECRET_ID_2"

  val trello = system.actorOf(TrelloActor.props(key, token), "trello-actor")
  val interval = 24.hours
  val things: List[Thing] = List(
    //    Thing("Thing 1", (22, 21), (22, 22)),
    //    Thing("Thing 2", (22, 22), (22, 23)),
    //    Thing("Thing 3", (22, 23), (22, 24)),
  )

  // Manual HTTPS configuration

  val password: Array[Char] = "pwd".toCharArray // do not store passwords in code, read them from somewhere safe!

  val ks: KeyStore = KeyStore.getInstance("PKCS12")

  val keystore: InputStream = new FileInputStream("/etc/letsencrypt/live/assistant.manenkov.com/keystore.p12")

  require(keystore != null, "Keystore required!")
  ks.load(keystore, password)

  val keyManagerFactory: KeyManagerFactory = KeyManagerFactory.getInstance("SunX509")
  keyManagerFactory.init(ks, password)

  val tmf: TrustManagerFactory = TrustManagerFactory.getInstance("SunX509")
  tmf.init(ks)

  val sslContext: SSLContext = SSLContext.getInstance("TLS")
  sslContext.init(keyManagerFactory.getKeyManagers, tmf.getTrustManagers, new SecureRandom)
  val https: HttpsConnectionContext = ConnectionContext.https(sslContext)

  // Server
  val route = pathSingleSlash {
    get {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "He's just a robot!"))
    }
  } ~
    path("trello-webhooks") {
      get {
        complete(StatusCodes.OK)
      } ~
        post {
          entity(as[String]) { hook =>
            parameters('check.?) {
              case Some(check) =>
                complete(StatusCodes.OK, s"Check is: $check")
              case None =>
                onSuccess(AssistantDatabase.insertHook(LocalDateTime.now(), Json.parse(hook))) {
                  complete(StatusCodes.OK)
                }
            }
          }
        }
    }
  val clientRouteLogged = DebuggingDirectives.logRequestResult("Trello Hooks REST", Logging.InfoLevel)(route)
  val bindingFuture1 = Http().bindAndHandle(clientRouteLogged, "0.0.0.0", 443, connectionContext = https)
  val bindingFuture2 = Http().bindAndHandle(clientRouteLogged, "0.0.0.0", 80)

  def delay(hours: Int, minutes: Int) = {
    val moscowTimeZone = ZoneId.of("Europe/Moscow")
    val time = LocalTime.of(hours, minutes).toSecondOfDay
    val now = ZonedDateTime.now().withZoneSameInstant(moscowTimeZone).toLocalTime.toSecondOfDay
    val fullDay = 60 * 60 * 24
    val difference = time - now
    if (difference < 0) {
      fullDay + difference
    } else {
      time - now
    }
  }.seconds

  system.scheduler.schedule(delay(0, 1), interval)(listsMaintenance)

  def listsMaintenance = {
    try {
      val res2 = trello ? DailyLists(mainBoardId)
      Await.result(res2, timeout.duration)
      println(res2)
    }
  }

  def addThing(title: String): Unit = {
    trello ! AddNowCard(title)
  }

  for (thing <- things) {
    system.scheduler.schedule(delay(thing.start._1, thing.start._2), interval)(addThing(thing.title))
    system.scheduler.schedule(delay(thing.end._1, thing.end._2), interval)(delThing(thing.title))
  }

  def delThing(title: String): Unit = {
    trello ! DeleteNowCard(title)
  }

  // Test "Now cards"
  case class Thing(title: String, start: (Int, Int), end: (Int, Int))

}
