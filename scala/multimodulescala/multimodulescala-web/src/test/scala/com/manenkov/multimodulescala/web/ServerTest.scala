package com.manenkov.multimodulescala.web

import org.scalatest.funsuite.AnyFunSuite

class ServerTest extends AnyFunSuite {
  test("Server.m(2, 3) == 5") {
    assert(Server.m(2, 3) == 5)
  }
}
