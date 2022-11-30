package com.manenkov.sandbox.zio

import com.manenkov.sandbox.zio.service._
import zio._

object Main extends ZIOAppDefault {

  val application = for {
    _ <- ApplicationService.sayHello()
  } yield ()

  val run = application.provideLayer(ApplicationService.layer)
}
