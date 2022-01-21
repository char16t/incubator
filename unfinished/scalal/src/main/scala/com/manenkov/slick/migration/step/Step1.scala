package com.manenkov.slick.migration.step

import com.manenkov.slick.Schema
import com.manenkov.slick.migration.MigrationStep
import com.manenkov.slick.table.Suppliers
import slick.jdbc.PostgresProfile.api._
import slick.migration.api.{PostgresDialect, ReversibleMigration, ReversibleTableMigration, TableMigration}


object Step1 extends MigrationStep {

  implicit val dialect = new PostgresDialect

  override def migrate(): ReversibleMigration = {
    TableMigration(Schema.suppliers)
      .create
      .addColumns(
        _.id,
        _.name,
        _.street,
        _.city,
        _.state,
        _.zip,
      )
  }
}
