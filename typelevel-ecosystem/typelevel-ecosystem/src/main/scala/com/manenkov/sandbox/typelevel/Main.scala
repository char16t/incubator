package com.manenkov.sandbox.typelevel

import cats.*
import cats.syntax.all.*
import cats.effect.*
import cats.effect.syntax.all.*
import org.typelevel.log4cats.syntax.*
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

import java.time.LocalTime
import scala.concurrent.duration._

object Main extends IOApp.Simple {

  implicit def logger[F[_]: Sync]: Logger[F] = Slf4jLogger.getLogger[F]

  def say1[F[_]: Sync: Logger]: F[String] = for {
    _ <- debug">> say1"
    r <- Sync[F].delay("Hello, ")
    _ <- debug"<< say1 = $r"
  } yield r
  def say2[F[_]: Sync: Logger]: F[String] = for {
    _ <- debug">> say2"
    r <- Sync[F].delay("Cats!")
    _ <- debug"<< say2 = $r"
  } yield r
  def say3[F[_]: Sync: Logger]: F[String] = for {
    a <- say1[F]
    _ <- info"Value of A is \"$a\""
    b <- say2[F]
    _ <- info"Value of B is \"$b\""
    c <- (a + b).pure[F]
    _ <- info"Value of C is \"$c\""
  } yield c

  val run = say3[IO].flatMap(IO.println)
}
