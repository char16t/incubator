package com.manenkov.jsonrpc

import org.scalatest.FunSuite
import spray.json._

class ResponsesValidationTest extends FunSuite {
  test("A response with JSON-RPC version 1.0 is not created") {
    assertThrows[java.lang.IllegalArgumentException](
      SingleResponse(
        JsString("1.0"),
        Option(JsArray(Vector(
          JsNumber(42),
          JsNumber(43),
        ))),
        None,
        JsNumber(1),
      )
    )
  }
  test("A response with JSON-RPC version 2.1 is not created") {
    assertThrows[java.lang.IllegalArgumentException](
      SingleResponse(
        JsString("2.1"),
        Option(JsArray(Vector(
          JsNumber(42),
          JsNumber(43),
        ))),
        None,
        JsNumber(1),
      )
    )
  }
  test("A response with not integer id is not created") {
    assertThrows[java.lang.IllegalArgumentException](
      SingleResponse(
        JsString("2.0"),
        Option(JsArray(Vector(
          JsNumber(42),
          JsNumber(43),
        ))),
        None,
        JsNumber(1.1),
      )
    )
  }
  test("A response without result and without error is not created") {
    assertThrows[java.lang.IllegalArgumentException](
      SingleResponse(
        JsString("1.0"),
        None,
        None,
        JsNumber(1),
      )
    )
  }
  test("A response with result and with error is not created") {
    assertThrows[java.lang.IllegalArgumentException](
      SingleResponse(
        JsString("1.0"),
        Option(JsArray(Vector(
          JsNumber(42),
          JsNumber(43),
        ))),
        Option(Error(
          JsNumber(35),
          JsString("Error"),
          None,
        )),
        JsNumber(1),
      )
    )
  }
}
