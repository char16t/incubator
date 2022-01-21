package com.manenkov.slick

import com.manenkov.slick.table.{Coffees, Suppliers}
import slick.lifted.TableQuery

object Schema {
  val suppliers = TableQuery[Suppliers]
  val coffees = TableQuery[Coffees]
}
