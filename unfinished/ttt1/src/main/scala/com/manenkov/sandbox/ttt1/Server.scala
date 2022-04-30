package com.manenkov.sandbox.ttt1

import cats.effect._
import cats.implicits._

object Server extends IOApp.Simple {

  val run: IO[Unit] =
    DatabaseConfig.transactor.use { xa =>
      for {
        _ <- DatabaseConfig.initializeDb
        drop <- PersonRepository(xa).drop
        create <- PersonRepository(xa).create
        insert <- PersonRepository(xa).insert("Ivan", Option(12))
        sel1 <- PersonRepository(xa).select
        update <- PersonRepository(xa).update("Ivan")
        sel2 <- PersonRepository(xa).select
        _ <- IO.println(
          s"""
             |drop:   $drop
             |create: $create
             |insert: $insert
             |select: $sel1
             |update: $update
             |select: $sel2
             |""".stripMargin)
      } yield ExitCode.Success
    }
}
