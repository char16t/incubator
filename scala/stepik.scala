package com.manenkov

import scala.io.StdIn
import scala.math.BigDecimal.RoundingMode.HALF_UP

object Main {
  def main(args: Array[String]): Unit = {
    println(s"Hi, ${StdIn.readLine}!")
  }

  def normalDistribution(mu: Double, sigma: Double, x: Double): Double = {
    Math.pow(Math.E, -(Math.pow(x - mu, 2) / (2 * Math.pow(sigma, 2)))) / (sigma * Math.sqrt(2 * Math.PI))
  }

  def crispsWeight(weight: BigDecimal, potatoWaterRatio: Double, crispsWaterRatio: Double): BigDecimal = {
    val result = weight * (1 - potatoWaterRatio) / (1 - crispsWaterRatio)
    result.setScale(5, HALF_UP)
  }

  def numberOfOnes1(number: String): Int = {
    BigInt(number).bitLength
  }

  def numberOfOnes2(number: String): Int = {
    number
      .toInt
      .toBinaryString
      .count(_ == '1')
  }

  def numberOfOnes3(number: String): Int = {
    var result = 0
    for (c <- number.toInt.toBinaryString)
      if (c == '1') {
        result += 1
      }
    result
  }

  def isCapital(word: String, pos: Int): Boolean = {
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ" contains word.charAt(pos)
  }

  def partialReverse(startIndex: Int, endIndex: Int, str: String): String = {
    val prefix = str.take(startIndex)
    val mid = str.slice(startIndex, endIndex).reverse
    val postfix = str.drop(endIndex)
    prefix + mid + postfix
  }

  def isSnakeCase(str: String): Boolean = {
    str.matches("^[a-z]([a-z]+_?[a-z]+)*[a-z]?$")
  }

  def fibs(num: Int): Int = {
    if (num == 1) 1
    else if (num == 2) 1
    else fibs(num - 1) + fibs(num - 2)
  }

  def sendGift(currentAmount: Int, gift: Int): Int = {
    if (currentAmount >= 500)
      currentAmount + gift
    else
      currentAmount
  }

  def searchInArray(cond: Int => Boolean, array: List[Int]): List[Int] = {
    array.filter(cond)
  }
  val searchOdd: List[Int] => List[Int] = searchInArray((_: Int) % 2 == 1, _: List[Int])

  @scala.annotation.tailrec
  def fibs(n: Int, currentNumber: Int = 1, f1: BigInt = 0, f2: BigInt = 1): BigInt = {
    if (n == currentNumber)
      f2
    else {
      fibs(n, currentNumber + 1, f2, f1 + f2)
    }
  }

  def middle[A: Ordering](data: Iterable[A]): A = {
    if (data.size % 2 == 0)
      data.slice(data.size / 2, data.size / 2 + 2).max
    else
      data.slice(data.size / 2, data.size / 2 + 1).head
  }

  def pairs(n: Int): Unit = {
    for {
      i <- 1 until n
      j <- 1 until n
    } {
      val gcd = BigInt(i) gcd BigInt(j)
      if(gcd == 1)
        println(s"$i $j")
    }
  }

  case class Pet(name: String, says: String)
  def detectAnimal(pet: Pet): String = {
    val robotSays = ".*[1-9].*".r
    pet match {
      case Pet(_, "meow"|"nya") => "cat"
      case Pet("Rex", _) => "dog"
      case Pet(_, robotSays()) => "robot"
      case _ => "unknown"
    }
  }

  def nameAndDomain(input: List[String]): String = {
    val regex1 = "([a-zA-Z]+) \\w+@(\\w+\\.\\w+)".r
    val regex2 = "([a-zA-Z]+)".r
    val regex3 = "\\w+@(\\w+\\.\\w+)".r
    input match {
      case List(regex1(name, domain), rest@_*) => s"$name $domain"
      case List(regex2(name), regex3(domain), rest@_*) => s"$name $domain"
      case _ => "invalid"
    }
  }

  val log: PartialFunction[Double, Double] = {
    case x if x > 0 => Math.log(x)
    case Double.NaN => Math.log(Double.NaN)
  }

  case class Jar(name: String, value: Int, price: Double)
  val discount: PartialFunction[Jar, String] = {
    case Jar(art, volume, price) if volume >= 5 && volume <= 10 => s"$art ${price * 0.05}"
    case Jar(art, volume, price) if volume > 10 => s"$art ${price * 0.1}"
  }

  val nameAndPopulation: (String, Long) = "Moscow" -> 12
  //nameAndPopulation.swap

  def swap3(tuple: (Int, Int, Int)): (Int, Int, Int) = Tuple3(tuple._3, tuple._2, tuple._1)

  def foo(list: List[Int]): Int = list.find(_ % 3 == 0).map(_ * 2).getOrElse(0)

  // Дана некоторая функция bar: Int => Option[Int] и опциональное значение x: Option[Int].
  // Примените bar к содержимому x так, чтобы получилось Option[Int].
  val x: Option[Int] = Some(1)
  val bar: Int => Option[Int] = x => Option(x)
  //x.flatMap(bar)

  // todo: fix me
  def fracE(p: (Int, Int), isRes: Boolean = false): Either[String, (Int, Int)] = p match {
    case (_, b) if b == 0 => Left("Zero divisor")
    case (a, b) if a > b => Left("Invalid input")
    case f => Right(f)
  }

  def middle[T](data: Iterable[T]): T = {
    data.splitAt(data.size / 2)._2.head
  }

  def divide(p: (Int, Int))( q: (Int, Int)): Either[String, (Int, Int)] = {
    if (q._1 == 0 || q._2 == 0 || p._2 == 0)
      return Left("Zero divisor")
    if (Math.abs(p._1) >= Math.abs(p._2) || Math.abs(q._1) >= Math.abs(q._2))
      return Left("Invalid input")
    val s = (p._1 * q._2, p._2 * q._1)
    if (Math.abs(s._1) >= Math.abs(s._2))
      return Left("Improper result")
    val d = BigInt(s._1).gcd(BigInt(s._2)).toInt
    Right((s._1 / d, s._2 / d))
  }

  def flatten(options: List[Option[Int]]): List[Int] =
    options.collect { case e if e.nonEmpty => e.get }

  def splitList(ints: List[Int]) = {
    val booleanToInts: Map[Boolean, List[Int]] = ints.groupBy(_ == 0)
    println(booleanToInts(true).mkString(", "))
    println(booleanToInts(false).mkString(", "))
  }

  @scala.annotation.tailrec
  def kOrder(list: List[Int], k: Int): Int = list match {
    case head :: tail =>
      val left = tail.filter(_ <= head)
      left.length match {
        case len if len + 1 == k => head
        case len if len + 1 > k => kOrder(left, k)
        case len if len + 1 < k => kOrder(tail.filter(_ > head), k - len - 1)
      }
    case Nil => 0
  }

  val list = List(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150)
  lazy val listOps1 = list
    .takeWhile(_ < 100)
    .collect { case x if x % 4 == 0 => x / 4 }
    .init
    .map(println)

  lazy val unit: Int =
    LazyList.continually(StdIn.readLine)
      .takeWhile(_ != "END")
      .grouped(2).flatMap(_.drop(1))      // drop every n-th: .grouped(n).flatMap(_.take(n - 1))
      .map(_.toInt * 2)
      .sum
  //.foreach(line => println("read " + line)
  //println(unit)

  lazy val points: List[Int] = List(1, 3)
  lazy val chr1: List[Char] = List('x', 'x', 'x', 'x', 'x')
  lazy val chr2: List[Char] = List('y', 'y', 'y', 'y', 'y')

  lazy val crossover =
    (0 +: points :+ chr1.length)
      .sliding(2)
      .zipWithIndex
      .foldLeft(List[Char]())((acc, range) => {
        val start = range._1(0)
        val end = range._1(1)
        val seq = if (range._2 % 2 == 0) chr1 else chr2
        acc ++ seq.slice(start, end)
      })
  lazy val crossover2 = (0 +: points :+ chr1.length)
    .sliding(2)
    .zipWithIndex
    .foldLeft(List[Char]())((acc, range) => {
      val start = range._1(0)
      val end = range._1(1)
      val seq = if (range._2 % 2 == 0) chr2 else chr1
      acc ++ seq.slice(start, end)
    })
  println(crossover.mkString(""))
  println(crossover2.mkString(""))

  type Point = (Int, Int)
  type Field = Vector[Vector[Boolean]]
  type Ship = List[Point]
  type Fleet = Map[String, Ship]
  type Game = (Field, Fleet)

  def validateShip(ship: Ship): Boolean = {
    if (ship.length > 4 || ship.length < 1)
      false
    else
      ship.forall(p => p._1 >= 0 && p._1 <= 9 && p._2 >= 0 && p._2 <= 9) &&
        (ship == List.tabulate(ship.length)(i => (ship.head._1, ship.head._2 + i)) ||
          ship == List.tabulate(ship.length)(i => (ship.head._1 + i, ship.head._2)))
  }

  def validatePosition(ship: Ship, field: Field): Boolean = {
    !ship
      .flatMap(p => List(
        (p._1 - 1, p._2 - 1), (p._1 - 1, p._2), (p._1 - 1, p._2 + 1),
        (p._1, p._2 - 1), (p._1, p._2), (p._1, p._2 + 1),
        (p._1 + 1, p._2 - 1), (p._1 + 1, p._2), (p._1 + 1, p._2 + 1)))
      .distinct
      .filter(p => p._1 >= 0 && p._1 <= 9 && p._2 >= 0 && p._2 <= 9)
      .exists(p => field(p._1)(p._2))
  }

  abstract class DiffList[A](calculate: List[A] => List[A]) {
    def prepend(s: List[A]): DiffList[A]

    def append(s: List[A]): DiffList[A]

    def result: List[A]
  }

  final class DiffListImpl[A](listFunc: List[A] => List[A]) extends DiffList[A](listFunc) {
    def prepend(s: List[A]): DiffListImpl[A] = {
      val f: List[A] => List[A] = list => s ::: listFunc(list)
      new DiffListImpl[A](f)
    }

    def append(s: List[A]): DiffListImpl[A] = {
      val f: List[A] => List[A] = list => listFunc(s ::: list)
      new DiffListImpl[A](f)
    }

    def result: List[A] = {
      listFunc(List.empty)
    }
  }

  lazy val printProducts: (List[Int], List[Int], List[Int]) => Unit = (l1: List[Int], l2: List[Int], l3: List[Int]) => {
    val value: Seq[Int] = for {
      x <- l1
      y <- l2
      z <- l3 if x * y == z
    } yield z
    for (v <- value) {
      println(v)
    }
  }

  class Waiter(val name: String, val food: List[String]) {
    def giveMe(f: String): Waiter = {
      new Waiter(name, f :: food)
    }
    def complete(): List[String] = {
      println(s"My name $name")
      food.reverse
    }
  }
  trait StringProcessor {
    def process(input: String): String
  }

  val tokenDeleter = new StringProcessor {
    override def process(input: String): String = input.replaceAll("\\pP", "")
  }

  val shortener = new StringProcessor {
    override def process(input: String): String = if (input.length > 20) s"${input.take(20)}..." else input
  }

  val toLowerConvertor = new StringProcessor {
    override def process(input: String): String = input.toLowerCase
  }

  val transform: String => String = (toLowerConvertor.process _).andThen(tokenDeleter.process).andThen(shortener.process)

  trait Animal {
    val sound: String
    def voice: Unit = println("voice: " + sound)
  }

  class Cat extends Animal {
    override val sound: String = "Meow!"
  }

  class Dog extends Animal {
    override val sound: String = "Woof!"
  }

  class Fish extends Animal {
    override val sound: String = ""
    override def voice: Unit = println("Fishes are silent!")
  }

  class AAA()
  case class BBB() extends AAA

  trait AbstractBank {
    def a: Char
    def b: Char
    def c: Char
    def d: Char
    def e: Char
    def f: Char
    def issueCredit: Unit
  }

  trait BankA extends AbstractBank {
    override val b = 'T'
    override val d = 'R'
    override val f = 'I'
  }

  trait BankB extends AbstractBank {
    override val a = 'E'
    override val f = 'D'
  }

  trait BankC extends AbstractBank {
    override val b = 'C'
    override val d = 'D'
  }

  trait BankD extends AbstractBank {
    override val b = 'C'
    override val c = 'R'
    override val d = 'E'
  }

  trait BankE extends AbstractBank {
    override val b = 'C'
    override val a = 'I'
    override val e = 'T'
  }

  class CreditBank extends BankD with BankB with BankE {
    def issueCredit = println(""  + b + c + d + f + a + e) //for example: a + b + c + d + e + f
  }

  trait Marker[T >: Null <: AnyRef]

  //case object A extends Marker[Any]
  case object B extends Marker[AnyRef]
  //case object C extends Marker[Int]
  case object D extends Marker[String]
  case object E extends Marker[Null]
  //case object F extends Marker[Nothing]

  case class Pair[T <% Ordered[T]](first: T, second: T) {
    def smaller =
      if (first < second) first
      else second
  }

  trait Coll[+A] {
    def apply(i: Int): A
    def headOption: Option[A]
    def tail: Coll[A]
    def map[B](f: A => B): Coll[B]
  }

  trait Printer[-A] {
    def print(a: A): String
    def printList(as: List[A]): String
    def prefixed(s: String): Printer[A]
    def contramap[B](f: B => A): Printer[B]
  }

  class A (val value: String)

  class B (override val value: String)
    extends A(value)

  val objB = new B("It is a B.value")
  val objA = new A("It is a A.value")

  class FunctionPrint[-T <: A](prefix: String) {
    def apply(t: T): Unit = println(prefix + " " + t.value)
  }

  object FunctionPrint {
    def apply[T <: A](prefix: String) = new FunctionPrint[T](prefix)
  }

  def methodPrint(f: FunctionPrint[B], obj: B) = {
    f(obj)
  }

  val printA = FunctionPrint[A]("A-value:")
  val printB = FunctionPrint[B]("B-value:")

  methodPrint(printB, objB)
  methodPrint(printA, objB)

  // ---

  class Person (val name: String)

  class Student(name: String, val course: Int) extends Person(name)

  class Pair2[T](val first: T, val second: T) {
    def replaceFirst[S >: T](newValue: S): Pair2[S] = {
      new Pair2(newValue, second)
    }
  }

  def printNames(pair: Pair2[Person]): Unit = {
    println("1: " + pair.first.name + "  2: " + pair.second.name)
  }

  val pair: Pair2[Student] = new Pair2(new Student("Pavel", 1), new Student("Oleg", 5))
  printNames(pair.replaceFirst(new Person("Oliver")))

  // --

  type Kleisli[F[_], A, B] = A => F[B]
}
