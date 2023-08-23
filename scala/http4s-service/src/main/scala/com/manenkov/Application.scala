package com.manenkov;

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.ember.server.EmberServerBuilder
import com.comcast.ip4s._

object Application extends IOApp {
    def run(args: List[String]) = {
        for {
            _ <- 
                EmberServerBuilder.default[IO]
                    .withHost(ipv4"0.0.0.0")
                    .withPort(port"8080")
                    .build
        } yield ()
    }.useForever
}
