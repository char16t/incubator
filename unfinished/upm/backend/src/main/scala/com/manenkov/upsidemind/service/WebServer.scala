package com.manenkov.upsidemind.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContextExecutor

object WebServer extends App with JsonSupport {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  val route =
    path("json-rpc") {
      post {
        entity(as[Request]) { request =>
          complete(StatusCodes.OK, Response(request.params.sum.toString, request.id))
        }
      }
    }
  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)
}
