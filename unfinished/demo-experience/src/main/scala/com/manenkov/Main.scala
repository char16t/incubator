package com.manenkov

import java.io.{InputStream, OutputStream, PrintStream}

import scala.io.{BufferedSource, Source}

case class Main(input: BufferedSource, output: PrintStream, errors: PrintStream, args: Array[String]) {
  def execute(): Unit = {
    input.getLines.foreach(output.println)
    output.println(args.mkString(","))
    output.println("Юникод")
    errors.println("Something went wrong")
  }
}

object Main {
  def main(args: Array[String]): Unit = Main(
    Source.fromInputStream(System.in),
    new PrintStream(System.out),
    new PrintStream(System.err),
    Array[String]()
  ).execute()

  def apply(
    input: BufferedSource,
    output: PrintStream,
    errors: PrintStream,
    args: Array[String]
  ): Main = new Main(input, output, errors, args)
}
