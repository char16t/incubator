package com.manenkov.sandbox.zio

import zio._
import zio.test._
import zio.test.Assertion._

object MainTest extends ZIOSpecDefault {
  override def spec = suite("Application Tests")(
    test("Simple run") {
      assert(2 == 2)(isTrue)
    }
  )
}
