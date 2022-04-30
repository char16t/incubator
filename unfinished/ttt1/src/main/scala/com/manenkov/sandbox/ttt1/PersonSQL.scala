package com.manenkov.sandbox.ttt1

import doobie._
import doobie.implicits._

object PersonSQL {
  def drop: doobie.Update0 =
    sql"""
    DROP TABLE IF EXISTS person
  """.update

  def create: doobie.Update0 =
    sql"""
    CREATE TABLE person (
      id   BIGSERIAL,
      name VARCHAR  NOT NULL UNIQUE,
      age  SMALLINT
    )
  """.update

  def insert(name: String, age: Option[Short]): doobie.Update0 =
    sql"insert into person (name, age) values ($name, $age)".update

  def select: doobie.Query0[Person] =
    sql"select id, name, age from person".query[Person]

  def update(name: String): doobie.Update0 =
    sql"update person set age = 15 where name =$name".update
}
