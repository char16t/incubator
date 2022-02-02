package com.manenkov

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor

object Main extends App with JsonSupport with CORSHandler {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  val route =
    corsHandler(path("plan") {
      post {
        complete(Response(123456, "Do it!"))
      }
    })
  Http().bindAndHandle(route, "localhost", 8080)
}
