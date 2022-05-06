package com.manenkov.sandbox.typelevel

import com.manenkov.sandbox.typelevel.Main
import cats.effect.{ExitCode, IO, Sync, SyncIO}
import munit.{CatsEffectSuite, ScalaCheckEffectSuite}
import org.scalacheck.effect.PropF
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import weaver.*

class MainSuite extends CatsEffectSuite with ScalaCheckEffectSuite {

  implicit def logger[F[_]: Sync]: Logger[F] = Slf4jLogger.getLogger[F]
  
  test("make sure IO computes the right result") {
    Main.say3[IO].map(it => assertEquals(it, "Hello, Cats!"))
  }
}
