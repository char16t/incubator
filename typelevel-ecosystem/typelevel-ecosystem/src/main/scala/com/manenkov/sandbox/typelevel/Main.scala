package com.manenkov.sandbox.typelevel

import cats.*
import cats.implicits.*
import cats.syntax.all.*
import cats.effect.*
import cats.effect.syntax.all.*
import org.typelevel.log4cats.syntax.*
import fly4s.implicits.*
import fly4s.core.*
import fly4s.core.data.*
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import com.github.eikek.calev.CalEvent
import com.manenkov.sandbox.typelevel.Main.{dbConfig, migrateDb}
import doobie.hikari.HikariTransactor
import doobie.*
import eu.timepit.fs2cron.Scheduler
import eu.timepit.fs2cron.calev.CalevScheduler
import fs2.Stream

import java.time.LocalTime
import scala.concurrent.duration.*

case class DatabaseConfig(
                           url: String,
                           user: Option[String],
                           password: Option[Array[Char]],
                           migrationsTable: String,
                           migrationsLocations: List[String]
                         )

object Main extends IOApp.Simple {

  implicit def logger[F[_] : Sync]: Logger[F] = Slf4jLogger.getLogger[F]

  val driverClassName = "org.postgresql.Driver"
  val jdbcUrl = "jdbc:postgresql://localhost:5432/pgdb"
  val dbUser = "pguser"
  val dbPassword = "pgpassword"
  val connectionPoolSize = 32

  val transactor: Resource[IO, HikariTransactor[IO]] =
    for {
      ce <- ExecutionContexts.fixedThreadPool[IO](connectionPoolSize)
      xa <- HikariTransactor.newHikariTransactor[IO](
        driverClassName,
        jdbcUrl,
        dbUser,
        dbPassword,
        ce
      )
    } yield xa

  val dbConfig: DatabaseConfig = DatabaseConfig(
    url                 = jdbcUrl,
    user                = Some(dbUser),
    password            = Some(dbPassword.toCharArray),
    migrationsTable     = "flyway",
    migrationsLocations = List("db")
  )

  def migrateDb(dbConfig: DatabaseConfig): Resource[IO, MigrateResult] =
    Fly4s.make[IO](
      url                 = dbConfig.url,
      user                = dbConfig.user,
      password            = dbConfig.password,
      config = Fly4sConfig(
        table     = dbConfig.migrationsTable,
        locations = Location.of(dbConfig.migrationsLocations)
      )
    ).evalMap(_.migrate)

  def say1[F[_] : Sync : Logger]: F[String] = for {
    _ <- debug">> say1"
    r <- Sync[F].delay("Hello, ")
    _ <- debug"<< say1 = $r"
  } yield r

  def say2[F[_] : Sync : Logger]: F[String] = for {
    _ <- debug">> say2"
    r <- Sync[F].delay("Cats!")
    _ <- debug"<< say2 = $r"
  } yield r

  def say3[F[_] : Sync : Logger]: F[String] = for {
    a <- say1[F]
    _ <- info"Value of A is \"$a\""
    b <- say2[F]
    _ <- info"Value of B is \"$b\""
    c <- (a + b).pure[F]
    _ <- info"Value of C is \"$c\""
  } yield c

  val oddSeconds: CalEvent = CalEvent.unsafe("*-*-* *:*:1/2")
  val everyFourSeconds: CalEvent = CalEvent.unsafe("*-*-* *:*:0/4")

  def calevScheduler[F[_] : Temporal : Sync]: Scheduler[F, CalEvent] = CalevScheduler.systemDefault[F]

  def task1[F[_]](implicit S: Sync[F]): F[Unit] = S.delay {
    println(LocalTime.now.toString + " task 1")
  }.as(())

  def task2[F[_]](implicit S: Sync[F]): F[Unit] = S.delay {
    println(LocalTime.now.toString + " task 2")
  }.as(())

  def calevScheduledTasks[F[_] : Temporal : Sync]: Stream[F, Unit] = calevScheduler[F].schedule(List(
    oddSeconds -> Stream.eval {task1[F]},
    everyFourSeconds -> Stream.eval {task2[F]},
  ))

  val run: IO[Unit] = {
    val migrationResult = migrateDb(dbConfig).use(r =>
      IO.delay(s"""
        |Migration result:
        |=======================
        |Success:                ${r.success}
        |Database:               ${r.database}
        |Operation:              ${r.operation}
        |Schema name:            ${r.schemaName}
        |Initial schema version: ${r.initialSchemaVersion}
        |Target schema version:  ${r.targetSchemaVersion}
        |""".stripMargin))

    val r = transactor.use { xa =>
      for {
        drop <- PersonRepository(xa).drop
        create <- PersonRepository(xa).create
        insert <- PersonRepository(xa).insert("Ivan", Option(12))
        sel1 <- PersonRepository(xa).select
        update <- PersonRepository(xa).update("Ivan")
        sel2 <- PersonRepository(xa).select
        r <- IO.delay(s"""
                |drop:   $drop
                |create: $create
                |insert: $insert
                |select: $sel1
                |update: $update
                |select: $sel2
                |""".stripMargin)
      } yield r
    }

    for {
      _ <- say3[IO].flatMap(IO.println)
      _ <- migrationResult.flatMap(IO.println)
      _ <- r.flatMap(IO.println)
      _ <- calevScheduledTasks[IO].compile.drain
    } yield ()
  }
}
