package com.manenkov.jsonrpc

import org.scalatest.FunSuite
import spray.json._

class RequestsValidationTest extends FunSuite {
  test("A request with JSON-RPC version 1.0 is not created") {
    assertThrows[java.lang.IllegalArgumentException](
      DefaultRequest(
        JsString("1.0"),
        JsString("subtract"),
        JsArray(Vector(
          JsNumber(42),
          JsNumber(43),
        )),
        JsNumber(1),
      )
    )
  }
  test("A request with JSON-RPC version 2.1 is not created") {
    assertThrows[java.lang.IllegalArgumentException](
      DefaultRequest(
        JsString("2.1"),
        JsString("subtract"),
        JsArray(Vector(
          JsNumber(42),
          JsNumber(43),
        )),
        JsNumber(1),
      )
    )
  }
  test("A request with rpc.* method is not created") {
    assertThrows[java.lang.IllegalArgumentException](
      DefaultRequest(
        JsString("2.0"),
        JsString("rpc.reserved"),
        JsArray(Vector(
          JsNumber(42),
          JsNumber(43),
        )),
        JsNumber(1),
      )
    )
    assertThrows[java.lang.IllegalArgumentException](
      DefaultRequest(
        JsString("2.0"),
        JsString("rpc\u002Ereserved"),
        JsArray(Vector(
          JsNumber(42),
          JsNumber(43),
        )),
        JsNumber(1),
      )
    )
  }
  test("A request with not integer id is not created") {
    assertThrows[java.lang.IllegalArgumentException](
      DefaultRequest(
        JsString("2.1"),
        JsString("subtract"),
        JsArray(Vector(
          JsNumber(42),
          JsNumber(43),
        )),
        JsNumber(12.3),
      )
    )
  }
}
