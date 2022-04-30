package com.manenkov.sandbox.ttt1

import _root_.munit._
import cats.effect.IO
import doobie.Transactor

class PersonSQLTest extends FunSuite with doobie.munit.IOChecker {

  override val transactor: Transactor[IO] = testTransactor

  test("Check drop") {
    check(PersonSQL.drop)
  }

  test("Check create") {
    check(PersonSQL.create)
  }

  test("Check insert 1") {
    check(PersonSQL.insert("Ivan", Option(1)))
  }

  test("Check insert 2") {
    check(PersonSQL.insert("Ivan", None))
  }

  test("Check select") {
    check(PersonSQL.select)
  }

  test("Check update") {
    check(PersonSQL.update("Ivan"))
  }
}
