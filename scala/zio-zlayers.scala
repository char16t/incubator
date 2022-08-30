import zio._

import java.io.IOException

case class Welcome(pre: Prefix, post: Postfix) {
  def act(name: String): ZIO[Any, Nothing, String] =
    for {
      prefix <- pre.act()
      postfix <- post.act()
    } yield (prefix + ", " + name + postfix)
}
object Welcome {
  var live: ZLayer[Prefix with Postfix, Throwable, Welcome] = ZLayer.fromFunction(Welcome(_, _))
}

case class Prefix() {
  def act(): ZIO[Any, Nothing, String] = ZIO.succeed("Hello")
}
object Prefix {
  val live: ZLayer[Any, Nothing, Prefix] = ZLayer.fromZIO(ZIO.succeed(Prefix()))
}

case class Postfix() {
  def act(): ZIO[Any, Nothing, String] = ZIO.succeed("!!!")
}
object Postfix {
  val live: ZLayer[Any, Nothing, Postfix] = ZLayer.fromZIO(ZIO.succeed(Postfix()))
}

case class Smile(eyes: SmileEyes, mouth: SmileMouth) {
  def act(): ZIO[Any, Nothing, String] = for {
    e <- eyes.act()
    m <- mouth.act()
  } yield (e + m)
}
object Smile {
  val live: ZLayer[SmileEyes with SmileMouth, Nothing, Smile] = ZLayer.fromFunction(Smile(_, _))
}

case class SmileEyes() {
  def act(): ZIO[Any, Nothing, String] = ZIO.succeed(";")
}
object SmileEyes {
  val live: ZLayer[Any, Nothing, SmileEyes] = ZLayer.fromZIO(ZIO.succeed(SmileEyes()))
}

case class SmileMouth() {
  def act(): ZIO[Any, Nothing, String] = ZIO.succeed("))")
}
object SmileMouth {
  val live: ZLayer[Any, Nothing, SmileMouth] = ZLayer.fromZIO(ZIO.succeed(SmileMouth()))
}

case class Hello(smile: Smile, welcome: Welcome) {
  def act(name: String): ZIO[Any, Nothing, String] = for {
    w <- welcome.act(name)
    s <- smile.act()
  } yield (w + " " + s)
}
object Hello {
  val live: ZLayer[Smile with Welcome, Nothing, Hello] = ZLayer.fromFunction(Hello(_, _))
}

object Main extends ZIOAppDefault {

  val welcome: ZLayer[Scope, Throwable, Welcome] = ZLayer.makeSome[Scope, Welcome](
    Welcome.live,
    Prefix.live,
    Postfix.live
  )

  val smile: ZLayer[Scope, Throwable, Smile] = ZLayer.makeSome[Scope, Smile](
    Smile.live,
    SmileEyes.live,
    SmileMouth.live
  )

  val hello = ZLayer.makeSome[Scope, Hello](
    Hello.live,
    welcome,
    smile
  ).build.map(_.get[Hello])

  def program(dep: Hello): ZIO[Any, IOException, Unit] = for {
    _ <- dep.act("Valeriy").flatMap(Console.printLine(_))
    _ <- dep.act("Tatiana").flatMap(Console.printLine(_))
    _ <- dep.act("Anna").flatMap(Console.printLine(_))
  } yield ()

  val run = hello.flatMap(program)
}
