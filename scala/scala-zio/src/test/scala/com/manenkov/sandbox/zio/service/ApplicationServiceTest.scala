package com.manenkov.sandbox.zio.service

import zio._
import zio.test._
import zio.test.Assertion._

class ApplicationServiceTest extends ZIOSpecDefault {
  override def spec = suite("Application Tests")(
    test("Simple run") {
      assert(3 == 3)(isTrue)
    }
  )
}
