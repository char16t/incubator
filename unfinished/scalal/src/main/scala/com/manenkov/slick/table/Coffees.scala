package com.manenkov.slick.table

import com.manenkov.slick.Schema
import com.manenkov.slick.domain.Coffee
import slick.jdbc.PostgresProfile.api._

class Coffees(tag: Tag) extends Table[Coffee](tag, "COFFEES") {
  def name = column[String]("COF_NAME", O.PrimaryKey)
  def supID = column[Int]("SUP_ID")
  def price = column[Double]("PRICE")
  def sales = column[Int]("SALES")
  def total = column[Int]("TOTAL")
  def * = (name, supID, price, sales, total) <> (Coffee.tupled, Coffee.unapply)

  def supplier = foreignKey("SUP_FK", supID, Schema.suppliers)(_.id)
}
