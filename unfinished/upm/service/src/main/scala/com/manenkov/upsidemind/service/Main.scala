package com.manenkov.upsidemind.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.{Await, ExecutionContextExecutor}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.duration.Duration

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  val route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    }
  val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 8080)
  SlickPGUtils.createDb("database", 5432, "mydb", "postgres", "postgres")
  val db = Database.forConfig("mydb")
  try {
    // Definition of the SUPPLIERS table
    class Suppliers(tag: Tag) extends Table[(Int, String, String, String, String, String)](tag, "SUPPLIERS") {
      def id = column[Int]("SUP_ID", O.PrimaryKey) // This is the primary key column
      def name = column[String]("SUP_NAME")
      def street = column[String]("STREET")
      def city = column[String]("CITY")
      def state = column[String]("STATE")
      def zip = column[String]("ZIP")
      // Every table needs a * projection with the same type as the table's type parameter
      def * = (id, name, street, city, state, zip)
    }
    val suppliers = TableQuery[Suppliers]
    val setup = DBIO.seq(
      suppliers.schema.create,
      suppliers += (101, "Acme, Inc.",      "99 Market Street", "Groundsville", "CA", "95199"),
      suppliers += ( 49, "Superior Coffee", "1 Party Place",    "Mendocino",    "CA", "95460"),
      suppliers += (150, "The High Ground", "100 Coffee Lane",  "Meadows",      "CA", "93966"),
    )
    db.run(setup).onComplete { r =>
      println(r)
    }
  } finally db.close
}
