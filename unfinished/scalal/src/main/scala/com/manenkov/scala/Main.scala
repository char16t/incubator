package com.manenkov.scala

import java.io.{File, PrintWriter}

object Main extends App {

  implicit def intToRational(x: Int): Rational = new Rational(x)

  val r = new Rational(4, 6)
  private val r2: Rational = 2 * r
  println(r2)
  println(r2.numer)
  println(r2.denom)

  // Custom control structure
  def withPrintWriter(op: PrintWriter => Unit): Unit = {
    val writer = new PrintWriter(new File("date.txt"))
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  withPrintWriter { writer =>
    writer.println(new java.util.Date)
  }
}
