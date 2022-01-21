package com.manenkov.jsonrpc

import org.scalatest.FunSuite
import spray.json._

class RequestsSerializationTest extends FunSuite with JsonSupport {
  test("Simple valid request serialized correctly") {
    val request = DefaultRequest(
      JsString("2.0"),
      JsString("subtract"),
      JsObject(
        "aaa" -> JsNumber(42),
        "bbb" -> JsNumber(43),
      ),
      JsNumber(1),
    )
    assert(request.toJson.convertTo[DefaultRequest] == request)
  }
  test("Simple valid notification serialized correctly") {
    val request = Notification(
      JsString("2.0"),
      JsString("subtract"),
      JsObject(
        "aaa" -> JsNumber(42),
        "bbb" -> JsNumber(43),
      ),
    )
    assert(request.toJson.convertTo[Notification] == request)
  }
  test("Simple valid batch request serialized correctly") {
    val request = BatchRequest(
      Vector(DefaultRequest(
        JsString("2.0"),
        JsString("subtract"),
        JsObject(
          "aaa" -> JsNumber(42),
          "bbb" -> JsNumber(43),
        ),
        JsNumber(1),
      ),
        Notification(
          JsString("2.0"),
          JsString("subtract"),
          JsObject(
            "aaa" -> JsNumber(42),
            "bbb" -> JsNumber(43),
          ),
        )
      ))
    assert(request.toJson.convertTo[BatchRequest] == request)
  }
}
