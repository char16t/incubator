package com.manenkov.jsonrpc

import org.scalatest.FunSuite
import spray.json._

class ResponsesSerializationTest extends FunSuite with JsonSupport {
  test("Simple valid response serialized correctly") {
    val response = SingleResponse(
      JsString("2.0"),
      Option(JsString("result")),
      None,
      JsNumber(1),
    )
    assert(response.toJson.convertTo[SingleResponse] == response)
  }
  test("Valid batch response serialized correctly") {
    val response = BatchResponse(
      Vector(SingleResponse(
        JsString("2.0"),
        Option(JsString("result1")),
        None,
        JsNumber(1),
      ),
        SingleResponse(
          JsString("2.0"),
          Option(JsString("result2")),
          None,
          JsNumber(2),
        )
      ))
    assert(response.toJson.convertTo[BatchResponse] == response)
  }
}
