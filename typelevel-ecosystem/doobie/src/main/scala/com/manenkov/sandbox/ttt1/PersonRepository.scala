package com.manenkov.sandbox.ttt1

import cats.effect._
import doobie._
import doobie.implicits._

class PersonRepository(val xa: Transactor[IO]) {
  def drop: IO[Int] = PersonSQL.drop.run.transact(xa)
  def create: IO[Int] = PersonSQL.create.run.transact(xa)
  def insert(name: String, age: Option[Short]): IO[Int] = PersonSQL.insert(name, age).run.transact(xa)
  def select: IO[List[Person]] = PersonSQL.select.to[List].transact(xa)
  def update(name: String): IO[Int] = PersonSQL.update(name).run.transact(xa)
}

object PersonRepository {
  def apply(xa: Transactor[IO]) = new PersonRepository(xa)
}
