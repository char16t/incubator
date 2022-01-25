package com.manenkov

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

import org.scalatest._

import scala.io.{BufferedSource, Source}

class MainSpec extends FlatSpec with Matchers {

  def lines(strs: String*): String = strs.map(line).mkString("")

  def line(str: String): String =
    str + sys.props("line.separator")

  "Main" should "works" in {
    val input: BufferedSource = Source.fromInputStream(new ByteArrayInputStream("hello".getBytes()))
    val output = new ByteArrayOutputStream()
    val errors = new ByteArrayOutputStream()
    val args = Array[String]("--key", "value")

    Main(input, new PrintStream(output), new PrintStream(errors), args).execute()

    output.toString shouldBe lines("hello", "--key,value", "Юникод")
    errors.toString shouldBe lines("Something went wrong")
  }
}
