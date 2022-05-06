package com.manenkov.sandbox.typelevel

import cats.effect.{IO, SyncIO}
import munit.{CatsEffectSuite, ScalaCheckEffectSuite}
import org.scalacheck.effect.PropF
import weaver.*

class ExampleSuite extends CatsEffectSuite with ScalaCheckEffectSuite {
  test("make sure IO computes the right result") {
    IO.pure(1).map(_ + 2) flatMap { result =>
      IO(assertEquals(result, 3))
    }
  }

  test("first PropF test") {
    PropF.forAllF { (a: String, b: String) =>
      IO(a).map(_ + b).map(res => assert(res == (a ++ b)))
    }
  }
}
