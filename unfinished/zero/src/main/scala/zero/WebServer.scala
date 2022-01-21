package zero

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.RejectionHandler
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}

object WebServer extends App with RequestTimeout {
  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit def myRejectionHandler =
    RejectionHandler.newBuilder()
      .handleNotFound {
        complete(StatusCodes.NotFound, "Not here!")
      }
      .result()
  val api = new RestApi(system, requestTimeout(config)).routes
  val bindingFuture = Http().bindAndHandle(api, host, port)
}

trait RequestTimeout {
  import scala.concurrent.duration._
  def requestTimeout(config: Config): Timeout = {
    val t = config.getString("akka.http.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}
