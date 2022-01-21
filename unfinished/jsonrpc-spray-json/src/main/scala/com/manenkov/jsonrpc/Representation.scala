package com.manenkov.jsonrpc

import spray.json._

sealed trait Request

sealed trait SingleRequest

final case class DefaultRequest(
  jsonrpc: JsString,
  method: JsString,
  params: JsValue,
  id: JsValue,
) extends SingleRequest {
  require(jsonrpc.value == "2.0")
  require(!method.value.startsWith("rpc\u002E"))
  require(!method.value.startsWith("rpc."))
  require(params match {
    case p: JsArray => true
    case p: JsObject => true
    case _ => false
  })
  require(id match {
    case requestId: JsNumber => requestId.value.isValidInt
    case requestId: JsArray => false
    case requestId: JsObject => false
    case _ => true
  })
}

final case class Notification(
  jsonrpc: JsString,
  method: JsString,
  params: JsValue,
) extends SingleRequest {
  require(jsonrpc.value == "2.0")
  require(!method.value.startsWith("rpc\u002E"))
  require(!method.value.startsWith("rpc."))
  require(params match {
    case p: JsArray => true
    case p: JsObject => true
    case _ => false
  })
}

final case class BatchRequest(
  requests: Vector[SingleRequest],
) extends Request

sealed trait Response

final case class SingleResponse(
  jsonrpc: JsString,
  result: Option[JsValue],
  error: Option[Error],
  id: JsValue,
) extends Response {
  require(jsonrpc.value == "2.0")
  require(result match {
    case None => error.isDefined
    case _ => true
  })
  require(error match {
    case None => result.isDefined
    case _ => true
  })
  require(id match {
    case requestId: JsNumber => requestId.value.isValidInt
    case requestId: JsArray => false
    case requestId: JsObject => false
    case _ => true
  })
}

final case class BatchResponse(
  responses: Vector[SingleResponse],
) extends Response

final case class Error(
  code: JsNumber,
  message: JsString,
  data: Option[JsValue],
) {
  require(code.value % 10 == 0)
  require(code.value >= -32768 && code.value <= -32000)
}
