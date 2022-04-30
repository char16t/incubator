package com.manenkov.sandbox

import cats.effect.IO
import doobie.util.transactor.Transactor
import doobie.util.transactor.Transactor.Aux

import cats.effect.unsafe.implicits.global

package object ttt1 {
  val transactor = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost:5432/person",
    "postgres",
    "1"
  )
  def initializedTransactor: IO[Aux[IO, Unit]] =
    for {
      _ <- DatabaseConfig.initializeDb
    } yield transactor
  lazy val testTransactor = initializedTransactor.unsafeRunSync()
}
