/**
build.sbt:

scalaVersion := "3.1.3"
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core"           % "2.8.0",
  "org.typelevel" %% "cats-effect"         % "3.3.13",

  "co.fs2"        %% "fs2-core"             % "3.2.8",
  "co.fs2"        %% "fs2-io"               % "3.2.8",
  "co.fs2"        %% "fs2-reactive-streams" % "3.2.8",
  "co.fs2"        %% "fs2-scodec"           % "3.2.8",

  "org.http4s"    %% "http4s-ember-server" % "0.23.12",
  "org.http4s"    %% "http4s-dsl"          % "0.23.12",
  "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "1.0.1",
)
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked"
)
*/
package com.manenkov.sandbox

import cats.*
import cats.data.*
import cats.syntax.all.*
import cats.effect.{ExitCode, IO, IOApp, Sync, Resource, Async}
import fs2.{Pure, Stream}
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server
import com.comcast.ip4s.*
import org.http4s.HttpRoutes
import org.http4s.HttpApp
import org.http4s.dsl.Http4sDsl
import sttp.tapir._
import sttp.tapir.server.http4s.Http4sServerInterpreter

object Main extends IOApp.Simple {

  val tapir1 = endpoint.get.in("hello").in(query[String]("name")).out(stringBody)
  val helloWorldRoutes: HttpRoutes[IO] =
    Http4sServerInterpreter[IO]().toRoutes(tapir1.serverLogic(name => IO(s"Hello, $name!".asRight[Unit])))

//  val dsl = new Http4sDsl[IO]{}
//  import dsl._
//  val routes1: HttpRoutes[IO] = HttpRoutes.of[IO] {
//    case GET -> Root => Ok("Success")
//  }
//  val routes2: HttpRoutes[IO] = HttpRoutes.of[IO] {
//    case GET -> Root / "bbb" => for {
//      greeting <- service2
//      resp <- Ok(s"Success $greeting")
//    } yield resp
//  }

  val httpApp = (/*routes1 <+> routes2 <+> */helloWorldRoutes).orNotFound

  val server: Stream[IO, Server] = Stream.resource(
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(httpApp)
      .build >> Resource.eval(Async[IO].never)
  )

  val run: IO[Unit] = for {
    exitCode <- server.compile.drain
  } yield exitCode
}
