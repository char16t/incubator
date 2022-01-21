package com.manenkov.slick.migration

import com.manenkov.slick.Slick.lines
import com.manenkov.slick.migration.step.{Step1, Step2}
import slick.jdbc.PostgresProfile.api._
import slick.migration.api.ReversibleMigrationSeq

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object DatabaseMigration {
  private val migration: ReversibleMigrationSeq =
    Step1.migrate() &
    Step2.migrate()

  def migrate() = {
    val db = Database.forConfig("mydb")
    try {
      val setupFuture = db.run(migration())
      Await.result(setupFuture, Duration.Inf)
    } finally db.close
  }
}
