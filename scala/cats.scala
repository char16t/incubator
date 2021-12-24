sealed trait Json
final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
final case object JsNull extends Json

trait JsonWriter[-A] {
  def write(value: A): Json
}

final case class Person(name: String, email: String)

object JsonWriterInstances {
  implicit val stringWriter: JsonWriter[String] =
    new JsonWriter[String] {
      def write(value: String): Json =
        JsString(value)
    }

  implicit val personWriter: JsonWriter[Person] =
    new JsonWriter[Person] {
      def write(value: Person): Json =
        JsObject(Map(
          "name" -> JsString(value.name),
          "email" -> JsString(value.email)
        ))
    }
  implicit def optionWriter[A]
      (implicit writer: JsonWriter[A]): JsonWriter[Option[A]] =
    new JsonWriter[Option[A]] {
      def write(option: Option[A]): Json =
        option match {
          case Some(aValue) => writer.write(aValue)
          case None         => JsNull
        }
  }
}

object Json {
  def toJson[A](value: A)(implicit w: JsonWriter[A]): Json =
    w.write(value)
}

object JsonSyntax {
  implicit class JsonWriterOps[A](value: A) {
    def toJson(implicit w: JsonWriter[A]): Json =
      w.write(value)
  }
}

import JsonSyntax._
import JsonWriterInstances._

Json.toJson(Person("Dave", "dave@example.com"))
Person("Dave", "dave@example.com").toJson
Json.toJson(Option(Person("d", "ddd")))

// Show
// ----

import cats.Show
import cats.instances.int._
import cats.instances.string._
import cats.syntax.show._

val showInt:    Show[Int]    = Show.apply[Int]
val showString: Show[String] = Show.apply[String]

val intAsString: String = showInt.show(123)
val stringAsString: String = showString.show("abc")

import java.util.Date

// implicit val dateShow: Show[Date] =
//   new Show[Date] {
//     def show(date: Date): String =
//       s"${date.getTime}ms since the epoch."
//   }

implicit val dateShow: Show[Date] =
  Show.show(date => s"${date.getTime}ms since the epoch.")

new Date().show

final case class Cat(name: String, age: Int, color: String)
implicit val catShow: Show[Cat] = Show.show[Cat] { cat =>
  val name  = cat.name.show
  val age   = cat.age.show
  val color = cat.color.show
  s"$name is a $age year-old $color cat."
}

println(Cat("Garfield", 38, "ginger and black").show)


// Eq
// --
import cats.Eq
import cats.instances.int._
import cats.instances.option._
import cats.syntax.eq._
import cats.syntax.option._
import cats.instances.string._

val eqInt = Eq[Int]
eqInt.eqv(123, 123)
eqInt.eqv(123, 234)
//eqInt.eqv(123, "234") // compile error

123 === 123
123 =!= 234
//123 === "123" // compile error
//Some(1) === None // compile error
(Some(1) : Option[Int]) === (None : Option[Int])
Option(1) === Option.empty[Int]
1.some === none[Int]
1.some =!= none[Int]

import java.util.Date
import cats.instances.long._ // for Eq

implicit val dateEq: Eq[Date] =
  Eq.instance[Date] { (date1, date2) =>
    date1.getTime === date2.getTime
  }

val x = new Date() // now
val y = new Date() // a bit later than now

x === x
// res12: Boolean = true
x === y
// res13: Boolean = false

implicit val catEqual: Eq[Cat] =
  Eq.instance[Cat] { (cat1, cat2) =>
    (cat1.name  === cat2.name ) &&
    (cat1.age   === cat2.age  ) &&
    (cat1.color === cat2.color)
  }
val cat1 = Cat("Garfield",   38, "orange and black")
// cat1: Cat = Cat("Garfield", 38, "orange and black")
val cat2 = Cat("Heathcliff", 32, "orange and black")
// cat2: Cat = Cat("Heathcliff", 32, "orange and black")

cat1 === cat2
// res15: Boolean = false
cat1 =!= cat2
// res16: Boolean = true

val optionCat1 = Option(cat1)
// optionCat1: Option[Cat] = Some(Cat("Garfield", 38, "orange and black"))
val optionCat2 = Option.empty[Cat]
// optionCat2: Option[Cat] = None

optionCat1 === optionCat2
// res17: Boolean = false
optionCat1 =!= optionCat2
// res18: Boolean = true


// Monoid & Semigroup
// ------------------

import cats.Monoid
import cats.Semigroup
import cats.instances.string._
import cats.instances.int._

Monoid[String].combine("Hi ", "there")
Monoid[String].empty

Semigroup[String].combine("Hi ", "there")
Monoid[Int].combine(32, 10)

import cats.instances.option._
val a = Option(22)
// a: Option[Int] = Some(22)
val b = Option(20)
// b: Option[Int] = Some(20)

Monoid[Option[Int]].combine(a, b)
// res6: Option[Int] = Some(42)

import cats.syntax.semigroup._
val stringResult = "Hi " |+| "there" |+| Monoid[String].empty
val intResult = 1 |+| 2 |+| Monoid[Int].empty
// intResult: Int = 3

// def add(items: List[Int]): Int =
//   items.foldLeft(Monoid[Int].empty)(_ |+| _)

// generic version
def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
  items.foldLeft(monoid.empty)(_ |+| _)

add(List(1,2,3,4))
add(List(Some(1), None, Some(2), None, Some(3)))


case class Order(totalCost: Double, quantity: Double)
implicit val monoid: Monoid[Order] = new Monoid[Order] {
  def combine(o1: Order, o2: Order) =
    Order(
      o1.totalCost + o2.totalCost,
      o1.quantity + o2.quantity
    )

  def empty = Order(0, 0)
}

// Functors
// --------
import cats.Functor
import cats.instances.function._
import cats.syntax.functor._


implicit val optionFunctor: Functor[Option] =
  new Functor[Option] {
    def map[A, B](value: Option[A])(func: A => B): Option[B] =
      value.map(func)
  }

import scala.concurrent.{Future, ExecutionContext}

implicit def futureFunctor
    (implicit ec: ExecutionContext): Functor[Future] =
  new Functor[Future] {
    def map[A, B](value: Future[A])(func: A => B): Future[B] =
      value.map(func)
  }

sealed trait Tree[+A]
final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
final case class Leaf[A](value: A) extends Tree[A]

implicit val treeFunctor: Functor[Tree] =
  new Functor[Tree] {
    def map[A, B](tree: Tree[A])(func: A => B): Tree[B] =
      tree match {
        case Branch(left, right) =>
          Branch(map(left)(func), map(right)(func))
        case Leaf(value) =>
          Leaf(func(value))
      }
  }
// Branch(Leaf(10), Leaf(20)).map(_ * 2)
// error: value map is not a member of repl.Session.App0.Branch[Int]
// Branch(Leaf(10), Leaf(20)).map(_ * 2)
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

object Tree {
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
    Branch(left, right)

  def leaf[A](value: A): Tree[A] =
    Leaf(value)
}

Tree.leaf(100).map(_ * 2)
Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2)

// Monad
// ------

import cats.Monad
import cats.instances.option._ // for Monad
import cats.instances.list._   // for Monad

val opt1 = Monad[Option].pure(3)
// opt1: Option[Int] = Some(3)
val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 2))
// opt2: Option[Int] = Some(5)
val opt3 = Monad[Option].map(opt2)(a => 100 * a)
// opt3: Option[Int] = Some(500)

val list1 = Monad[List].pure(3)
// list1: List[Int] = List(3)
val list2 = Monad[List].
  flatMap(List(1, 2, 3))(a => List(a, a*10))
// list2: List[Int] = List(1, 10, 2, 20, 3, 30)
val list3 = Monad[List].map(list2)(a => a + 123)
// list3: List[Int] = List(124, 133, 125, 143, 126, 153)

import cats.instances.option._ // for Monad

import cats.Monad
import cats.instances.option._ // for Monad
import cats.instances.list._   // for Monad

val opt11 = Monad[Option].pure(3)
// opt11: Option[Int] = Some(3)
val opt12 = Monad[Option].flatMap(opt1)(a => Some(a + 2))
// opt12: Option[Int] = Some(5)
val opt13 = Monad[Option].map(opt2)(a => 100 * a)
// opt13: Option[Int] = Some(500)

val list11 = Monad[List].pure(3)
// list1: List[Int] = List(3)
val list12 = Monad[List].
  flatMap(List(1, 2, 3))(a => List(a, a*10))
// list2: List[Int] = List(1, 10, 2, 20, 3, 30)
val list13 = Monad[List].map(list2)(a => a + 123)
// list3: List[Int] = List(124, 133, 125, 143, 126, 153)



Monad[Option].flatMap(Option(1))(a => Option(a*2))

// Eval
// ----

import cats.Eval
val now = Eval.now { math.random + 1000 }
val always = Eval.always { math.random + 3000 }
val later = Eval.later { math.random + 2000 }

now.value
always.value
later.value

val greeting = Eval
  .always{ println("Step 1"); "Hello" }
  .map{ str => println("Step 2"); s"$str world" }
greeting.value

val ans = for {
  a <- Eval.now { println("Calculating A"); 40 }
  b <- Eval.always { println("Calculating B"); 2 }
} yield {
  println("Adding A and B")
  a + b
}
ans

val saying = Eval
  .always{ println("Step 1"); "The cat" }
  .map{ str => println("Step 2"); s"$str sat on" }
  .memoize
  .map{ str => println("Step 3"); s"$str the mat" }
saying.value
saying.value

def factorial(n: BigInt): Eval[BigInt] =
  if(n == 1) {
    Eval.now(n)
  } else {
    Eval.defer(factorial(n - 1).map(_ * n))
  }
factorial(50000).value

// Writer
// ------
import cats.data.Writer
import cats.instances.vector._ // for Monoid
import cats.syntax.applicative._ // for pure
import cats.syntax.writer._ // for tell

Writer(Vector(
  "It was the best of times",
  "it was the worst of times"
), 1859)

type Logged[A] = Writer[Vector[String], A]
123.pure[Logged]

Vector("msg1", "msg2", "msg3").tell

val a = Writer(Vector("msg1", "msg2", "msg3"), 123)
val b = 123.writer(Vector("msg1", "msg2", "msg3"))
val aResult: Int = a.value
// aResult: Int = 123
val aLog: Vector[String] = a.written
// aLog: Vector[String] = Vector("msg1", "msg2", "msg3")

val (log, result) = b.run



def factorial2(n: Int): Logged[Int] =
  for {
    ans <- if(n == 0) {
             1.pure[Logged]
           } else {
             factorial2(n - 1).map(_ * n)
           }
    _   <- Vector(s"fact $n $ans").tell
  } yield ans
val (log2, res) = factorial2(5).run
log2


// Reader
// ------
import cats.data.Reader

final case class Cat(name: String, favoriteFood: String)
val catName: Reader[Cat, String] =
  Reader(cat => cat.name)

catName
catName.run(Cat("Garfield", "lasagne"))
// res1: cats.package.Id[String] = "Garfield"

val greetKitty: Reader[Cat, String] = catName.map(name => s"Hello ${name}")
greetKitty
greetKitty.run(Cat("Heathcliff", "junk food"))

val feedKitty: Reader[Cat, String] = 
  Reader(cat => s"Have a nice bowl of ${cat.favoriteFood}")
val greetAndFeed: Reader[Cat, String] =
  for {
    greet <- greetKitty
    feed  <- feedKitty
  } yield s"$greet. $feed."
greetAndFeed
greetAndFeed(Cat("Garfield", "lasagne"))
greetAndFeed(Cat("Heathcliff", "junk food"))




import cats.syntax.applicative._ // for pure

final case class Db(
  usernames: Map[Int, String],
  passwords: Map[String, String]
)
type DbReader[A] = Reader[Db, A]

def findUsername(userId: Int): DbReader[Option[String]] =
  Reader(db => db.usernames.get(userId))

def checkPassword(
      username: String,
      password: String): DbReader[Boolean] =
  Reader(db => db.passwords.get(username).contains(password))

def checkLogin(
      userId: Int,
      password: String): DbReader[Boolean] =
  for {
    username   <- findUsername(userId)
    passwordOk <- username.map { username =>
                    checkPassword(username, password)
                  }.getOrElse {
                    false.pure[DbReader]
                  }
  } yield passwordOk

val users = Map(
  1 -> "dade",
  2 -> "kate",
  3 -> "margo"
)

val passwords = Map(
  "dade"  -> "zerocool",
  "kate"  -> "acidburn",
  "margo" -> "secret"
)

val db = Db(users, passwords)

checkLogin(1, "zerocool").run(db)
// res7: cats.package.Id[Boolean] = true

checkLogin(4, "davinci").run(db)
// res8: cats.package.Id[Boolean] = false


// State
// -----

// ...
