import zio._

import java.io.IOException

case class LayerA(b: Prefix, c: Postfix) {
  def act(name: String): ZIO[Any, Nothing, String] =
    for {
      prefix <- b.act()
      postfix <- c.act()
    } yield (prefix + ", " + name + postfix)
}
object LayerA {
  var live: ZLayer[Prefix with Postfix, Throwable, LayerA] = ZLayer.fromFunction(LayerA(_, _))
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

object Main extends ZIOAppDefault {

  val layers: ZIO[Scope, Throwable, LayerA] = ZLayer.makeSome[Scope, LayerA](
    LayerA.live,
    Prefix.live,
    Postfix.live
  ).build.map(_.get[LayerA])

  def program(dep: LayerA): ZIO[Any, IOException, Unit] = for {
    _ <- dep.act("Valeriy").flatMap(Console.printLine(_))
    _ <- dep.act("Tatiana").flatMap(Console.printLine(_))
    _ <- dep.act("Denis").flatMap(Console.printLine(_))
  } yield ()

  val run = layers.flatMap(program)
}
