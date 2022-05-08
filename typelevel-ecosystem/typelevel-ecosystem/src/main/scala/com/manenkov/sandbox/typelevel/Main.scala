package com.manenkov.sandbox.typelevel

import cats.*
import cats.syntax.all.*
import cats.effect.*
import cats.effect.syntax.all.*
import org.typelevel.log4cats.syntax.*
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import com.github.eikek.calev.CalEvent
import eu.timepit.fs2cron.Scheduler
import eu.timepit.fs2cron.calev.CalevScheduler
import fs2.Stream

import java.time.LocalTime
import scala.concurrent.duration.*

object Main extends IOApp.Simple {

  implicit def logger[F[_] : Sync]: Logger[F] = Slf4jLogger.getLogger[F]

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

  def task1[F[_]: Sync]: F[Unit] =
    Sync[F].delay(println(LocalTime.now.toString + " task 1"))

  def task2[F[_]: Sync]: F[Unit] =
      Sync[F].delay(println(LocalTime.now.toString + " task 2"))

  def calevScheduledTasks[F[_] : Temporal : Sync]: Stream[F, Unit] = calevScheduler[F].schedule(List(
    oddSeconds -> Stream.eval {task1[F]},
    everyFourSeconds -> Stream.eval {task2[F]},
  ))

  val run: IO[Unit] = for {
    _ <- say3[IO].flatMap(IO.println)
    _ <- calevScheduledTasks[IO].compile.drain
  } yield ()
}
