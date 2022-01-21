package com.manenkov.slick.migration

import slick.migration.api.ReversibleMigration

trait MigrationStep {
  def migrate(): ReversibleMigration
}
