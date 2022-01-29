package com.manenkov.upsidemind.service

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

object JsonRpc {
  import UnionType._
  case class Params [A : (UNil Or Map[String, Int] Or List[Int])#check](a: A)
}

trait JsonSupport extends SprayJsonSupport {
  import DefaultJsonProtocol._
  implicit val requestFormat: RootJsonFormat[Request] = jsonFormat4(Request)
  implicit val responseFormat: RootJsonFormat[Response] = jsonFormat2(Response)
}
