package com.manenkov.jsonrpc

import spray.json._

trait JsonSupport extends DefaultJsonProtocol {

  implicit object ErrorFormat extends RootJsonFormat[Error] {
    override def read(json: JsValue): Error = json match {
      case JsObject(fields) if fields.size == 2 => Error(
        fields("code").asInstanceOf[JsNumber],
        fields("message").asInstanceOf[JsString],
        None,
      )
      case JsObject(fields) if fields.size == 3 => Error(
        fields("code").asInstanceOf[JsNumber],
        fields("message").asInstanceOf[JsString],
        Option(fields("data")),
      )
      case _ => throw DeserializationException("Object with 2 or 3 fields expected")
    }

    override def write(obj: Error): JsValue = obj match {
      case o: Error if o.data.isDefined => JsObject(
        "code" -> obj.code,
        "message" -> obj.message,
        "data" -> obj.data.get,
      )
      case o: Error if o.data.isEmpty => JsObject(
        "code" -> obj.code,
        "message" -> obj.message,
      )
    }
  }

  implicit object SingleResponseFormat extends RootJsonFormat[SingleResponse] {
    override def read(json: JsValue): SingleResponse = json match {
      case JsObject(fields) if fields.contains("result") => SingleResponse(
        fields("jsonrpc").asInstanceOf[JsString],
        Option(fields("result")),
        None,
        fields("id"),
      )
      case JsObject(fields) if fields.contains("error") => SingleResponse(
        fields("jsonrpc").asInstanceOf[JsString],
        None,
        Option(fields("error").convertTo[Error]),
        fields("id"),
      )
      case _ => throw DeserializationException("Response should contains 'result' or 'error' field")
    }

    override def write(obj: SingleResponse): JsValue = obj match {
      case o if o.result.isDefined => JsObject(
        "jsonrpc" -> obj.jsonrpc,
        "result" -> obj.result.get,
        "id" -> obj.id,
      )
      case o if o.error.isDefined => JsObject(
        "jsonrpc" -> obj.jsonrpc,
        "error" -> obj.error.get.toJson,
        "id" -> obj.id,
      )
      case _ => throw DeserializationException("Either result or error should be defined")
    }
  }

  implicit object BatchResponseFormat extends RootJsonFormat[BatchResponse] {
    override def read(json: JsValue): BatchResponse = json match {
      case JsArray(elements) => BatchResponse(elements.map(_.convertTo[SingleResponse]))
      case _ => throw DeserializationException("List of requests expected")
    }

    override def write(obj: BatchResponse): JsValue = obj.responses.toJson
  }

  implicit object defaultRequestFormat extends RootJsonFormat[DefaultRequest] {
    override def read(json: JsValue): DefaultRequest = json match {
      case JsObject(fields) if fields.size == 4 => DefaultRequest(
        fields("jsonrpc").asInstanceOf[JsString],
        fields("method").asInstanceOf[JsString],
        fields("params"),
        fields("id"),
      )
      case _ => throw DeserializationException("4 fields expected")
    }

    override def write(obj: DefaultRequest): JsValue = JsObject(
      "jsonrpc" -> obj.jsonrpc,
      "method" -> obj.method,
      "params" -> obj.params,
      "id" -> obj.id,
    )
  }

  implicit object notificationFormat extends RootJsonFormat[Notification] {
    override def read(json: JsValue): Notification = json match {
      case JsObject(fields) if fields.size == 3 => Notification(
        fields("jsonrpc").asInstanceOf[JsString],
        fields("method").asInstanceOf[JsString],
        fields("params"),
      )
      case _ => throw DeserializationException("3 fields expected")
    }

    override def write(obj: Notification): JsValue = JsObject(
      "jsonrpc" -> obj.jsonrpc,
      "method" -> obj.method,
      "params" -> obj.params,
    )
  }

  implicit object singleRequestFormat extends RootJsonFormat[SingleRequest] {
    override def read(json: JsValue): SingleRequest = json match {
      case JsObject(fields) if fields.size == 3 => notificationFormat.read(json)
      case JsObject(fields) if fields.size == 4 => defaultRequestFormat.read(json)
      case _ => throw DeserializationException("Object with 3 or 4 fields expected")
    }

    override def write(obj: SingleRequest): JsValue = obj match {
      case o: DefaultRequest => defaultRequestFormat.write(o)
      case o: Notification => notificationFormat.write(o)
      case _ => throw DeserializationException("DefaultRequest or Notification type expected")
    }
  }

  implicit object BatchRequestFormat extends RootJsonFormat[BatchRequest] {
    override def read(json: JsValue): BatchRequest = json match {
      case JsArray(elements) => BatchRequest(elements.map(_.convertTo[SingleRequest]))
      case _ => throw DeserializationException("List of requests expected")
    }

    override def write(obj: BatchRequest): JsValue = obj.requests.toJson
  }

}
