package com.manenkov.sandbox.zio.service

import zio._

import java.io.IOException

class ApplicationService {

  def sayHello(): IO[IOException, Unit] =
    Console.printLine("Hello, ZIO!")
}

object ApplicationService {

  def sayHello(): ZIO[ApplicationService, IOException, Unit] =
    ZIO.serviceWithZIO[ApplicationService](_.sayHello())

  val live: ZLayer[Any, IOException, ApplicationService] =
    ZLayer.fromZIO(ZIO.succeed(new ApplicationService()))

  val layer: ZLayer[Scope, Throwable, ApplicationService] =
    ZLayer.makeSome[Scope, ApplicationService](
      ApplicationService.live
    )
}
