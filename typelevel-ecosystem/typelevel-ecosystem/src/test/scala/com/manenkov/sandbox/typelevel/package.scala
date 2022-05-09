package com.manenkov.sandbox

import cats.effect.{IO, Resource}
import doobie.util.transactor.Transactor
import doobie.util.transactor.Transactor.Aux
import cats.effect.unsafe.implicits.global

package object typelevel {

  val driverClassName = "org.postgresql.Driver"
  val jdbcUrl = "jdbc:postgresql://localhost:5432/pgdb"
  val dbUser = "pguser"
  val dbPassword = "pgpassword"
  val connectionPoolSize = 32

  val transactor = Transactor.fromDriverManager[IO](
    driverClassName,
    jdbcUrl,
    dbUser,
    dbPassword
  )

  val dbConfig: DatabaseConfig = DatabaseConfig(
    url                 = jdbcUrl,
    user                = Some(dbUser),
    password            = Some(dbPassword.toCharArray),
    migrationsTable     = "flyway",
    migrationsLocations = List("db")
  )

  def initializedTransactor: IO[Aux[IO, Unit]] =
    for {
      _ <- Main.migrateDb(dbConfig).use(IO.delay)
    } yield transactor
  lazy val testTransactor = initializedTransactor.unsafeRunSync()

}
