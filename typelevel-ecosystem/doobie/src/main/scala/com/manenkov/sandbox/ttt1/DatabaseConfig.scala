package com.manenkov.sandbox.ttt1

import cats.effect.{IO, Resource}
import doobie.ExecutionContexts
import doobie.hikari.HikariTransactor
import org.flywaydb.core.Flyway

object DatabaseConfig {
  val transactor: Resource[IO, HikariTransactor[IO]] =
    for {
      ce <- ExecutionContexts.fixedThreadPool[IO](32)
      xa <- HikariTransactor.newHikariTransactor[IO](
        "org.postgresql.Driver",
        "jdbc:postgresql://localhost:5432/person",
        "postgres",
        "1",
        ce
      )
    } yield xa

  def initializeDb: IO[Unit] =
    IO.delay {
      val fw: Flyway =
        Flyway
          .configure()
          .dataSource("jdbc:postgresql://localhost:5432/person", "postgres", "1")
          .load()
      fw.migrate()
    }.as(())
}
