package com.manenkov.slick.table

import com.manenkov.slick.domain.Supplier
import slick.jdbc.PostgresProfile.api._

class Suppliers(tag: Tag) extends Table[Supplier](tag, "SUPPLIERS") {
  def id = column[Int]("SUP_ID", O.PrimaryKey)
  def name = column[String]("SUP_NAME")
  def street = column[String]("STREET")
  def city = column[String]("CITY")
  def state = column[String]("STATE")
  def zip = column[String]("ZIP")
  def * = (id, name, street, city, state, zip) <> (Supplier.tupled, Supplier.unapply)
}
