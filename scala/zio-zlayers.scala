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
  def act(name: String): ZIO[Welcome, Nothing, String] = ZIO.serviceWithZIO[Welcome](_.act(name))
  var live: ZLayer[Prefix with Postfix, Throwable, Welcome] = ZLayer {
    for {
      prefix <- ZIO.service[Prefix]
      postfix <- ZIO.service[Postfix]
    } yield Welcome(prefix, postfix)
  }
}

case class Prefix() {
  def act(): ZIO[Any, Nothing, String] = ZIO.succeed("Hello")
}
object Prefix {
  def act(): ZIO[Prefix, Nothing, String] = ZIO.serviceWithZIO[Prefix](_.act())
  val live: ZLayer[Any, Nothing, Prefix] = ZLayer.fromZIO(ZIO.succeed(Prefix()))
}

case class Postfix() {
  def act(): ZIO[Any, Nothing, String] = ZIO.succeed("!!!")
}
object Postfix {
  def act(): ZIO[Postfix, Nothing, String] = ZIO.serviceWithZIO[Postfix](_.act())
  val live: ZLayer[Any, Nothing, Postfix] = ZLayer.fromZIO(ZIO.succeed(Postfix()))
}

case class Smile(eyes: SmileEyes, mouth: SmileMouth) {
  def act(): ZIO[Any, Nothing, String] = for {
    e <- eyes.act()
    m <- mouth.act()
  } yield (e + m)
}
object Smile {
  def act(): ZIO[Smile, Nothing, String] = ZIO.serviceWithZIO[Smile](_.act())
  val live: ZLayer[SmileEyes with SmileMouth, Nothing, Smile] = ZLayer {
    for {
      eyes <- ZIO.service[SmileEyes]
      mouth <- ZIO.service[SmileMouth]
    } yield Smile(eyes, mouth)
  }
}

case class SmileEyes() {
  def act(): ZIO[Any, Nothing, String] = ZIO.succeed(";")
}
object SmileEyes {
  def act(): ZIO[SmileEyes, Nothing, String] = ZIO.serviceWithZIO[SmileEyes](_.act())
  val live: ZLayer[Any, Nothing, SmileEyes] = ZLayer.fromZIO(ZIO.succeed(SmileEyes()))
}

case class SmileMouth() {
  def act(): ZIO[Any, Nothing, String] = ZIO.succeed("))")
}
object SmileMouth {
  def act(): ZIO[SmileMouth, Nothing, String] = ZIO.serviceWithZIO[SmileMouth](_.act())
  val live: ZLayer[Any, Nothing, SmileMouth] = ZLayer.fromZIO(ZIO.succeed(SmileMouth()))
}

case class Hello(smile: Smile, welcome: Welcome) {
  def act(name: String): ZIO[Any, Nothing, String] = for {
    w <- welcome.act(name)
    s <- smile.act()
  } yield (w + " " + s)
}
object Hello {
  def act(name: String): ZIO[Hello, Nothing, String] = ZIO.serviceWithZIO[Hello](_.act(name))
  val live: ZLayer[Smile with Welcome, Nothing, Hello] = ZLayer {
    for {
      smile <- ZIO.service[Smile]
      welcome <- ZIO.service[Welcome]
    } yield (Hello(smile, welcome))
  }
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
  )

  val app = for {
    w1 <- Hello.act("Valeriy")
    w2 <- Hello.act("Tatiana")
    w3 <- Hello.act("Anna")
    _ <- Console.printLine(w1)
    _ <- Console.printLine(w2)
    _ <- Console.printLine(w3)
  } yield ()

  val run = app.provideLayer(hello)
}
