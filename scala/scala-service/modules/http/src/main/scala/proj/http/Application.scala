package proj.http

import zio._
import zio.http._
import zio.http.model.Method
import proj.core.Module2

object Application extends ZIOAppDefault {

  val app: HttpApp[Any, Nothing] = Http.collect[Request] {
    case Method.GET -> !! / "text" => Response.text(Module2.text)
  }

  override val run =
    Server.serve(app).provide(Server.default)
}
