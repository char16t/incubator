package com.manenkov.multimodulescala.core

import org.scalatest.funsuite.AnyFunSuite

class HelloTest extends AnyFunSuite {
  test("Hello.plus(2, 2) == 4") {
    assert(Hello.plus(2, 2) == 4)
  }
}
