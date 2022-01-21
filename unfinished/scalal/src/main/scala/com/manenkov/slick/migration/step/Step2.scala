package com.manenkov.slick.migration.step

import com.manenkov.slick.Schema
import com.manenkov.slick.migration.MigrationStep
import slick.migration.api.{PostgresDialect, ReversibleMigration, TableMigration}

object Step2 extends MigrationStep {

  implicit val dialect = new PostgresDialect

  override def migrate(): ReversibleMigration = {
    TableMigration(Schema.coffees)
      .create
      .addColumns(
        _.name,
        _.supID,
        _.price,
        _.sales,
        _.total
      )
  }
}
