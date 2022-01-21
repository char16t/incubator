package com.manenkov.scala

package object scala {

  sealed abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  class Example {
    def method(): Unit = {
      val e = Number(42)
      val dawn = Person("Dawn")
      val a = Array(Person("Dan"), Person("Elijah"))
    }
  }

  // https://www.oreilly.com/library/view/scala-cookbook/9781449340292/ch06s09.html
  class Person {
    var name: String = _
  }

  object Person {
    def apply(name: String): Person = {
      var p = new Person
      p.name = name
      p
    }
  }

}
