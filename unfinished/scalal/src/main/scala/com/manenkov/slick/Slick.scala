package com.manenkov.slick

import com.manenkov.slick.domain.{Coffee, Supplier}
import com.manenkov.slick.migration.DatabaseMigration
import slick.jdbc.PostgresProfile.api._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Slick extends App {

  val lines = new ArrayBuffer[Any]()
  val schema = Schema
  val suppliers = schema.suppliers
  val coffees = schema.coffees

  DatabaseMigration.migrate()

  val setup = DBIO.seq(

    // Insert some suppliers
    suppliers += Supplier(101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
    suppliers += Supplier(49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
    suppliers += Supplier(150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"),
    // Equivalent SQL code:
    // insert into SUPPLIERS(SUP_ID, SUP_NAME, STREET, CITY, STATE, ZIP) values (?,?,?,?,?,?)

    // Insert some coffees (using JDBC's batch insert feature, if supported by the DB)
    coffees ++= Seq(
      Coffee("Colombian", 101, 7.99, 0, 0),
      Coffee("French_Roast", 49, 8.99, 0, 0),
      Coffee("Espresso", 150, 9.99, 0, 0),
      Coffee("Colombian_Decaf", 101, 8.99, 0, 0),
      Coffee("French_Roast_Decaf", 49, 9.99, 0, 0)
    )
    // Equivalent SQL code:
    // insert into COFFEES(COF_NAME, SUP_ID, PRICE, SALES, TOTAL) values (?,?,?,?,?)
  )
  val db = Database.forConfig("mydb")

  def println(s: Any) = lines += s
  try {
    val setupFuture = db.run(setup)
    Await.result(setupFuture, Duration.Inf)
    lines.foreach(Predef.println _)
  } finally db.close
}
