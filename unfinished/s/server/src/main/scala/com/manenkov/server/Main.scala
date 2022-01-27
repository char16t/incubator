/*
 * Copyright (c) 2019 Valeriy Manenkov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.manenkov.server

import java.util.concurrent.TimeUnit

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.{ Http, HttpExt }
import akka.http.scaladsl.settings.RoutingSettings
import akka.stream.ActorMaterializer

import scala.concurrent.duration._

object Main extends App with HttpEndpoint {
  val httpHost =
    sys.props.getOrElse(
      "httpHost",
      "localhost")

  val httpPort =
    sys.props
      .get("httpPort")
      .map(_.toInt)
      .getOrElse(8091)

  val clusterMembershipAskTimeout =
    sys.props
      .get("clusterMembershipAskTimeout")
      .map(v => FiniteDuration(v.toLong, TimeUnit.MILLISECONDS))
      .getOrElse(5000.millis)

  val actorSystemName =
    sys.props.getOrElse(
      "akkaActorSystemName",
      "myapp")

  implicit val actorSystem: ActorSystem = ActorSystem(actorSystemName)
  implicit val mat: ActorMaterializer = ActorMaterializer()
  import actorSystem.dispatcher
  implicit val http: HttpExt = Http(actorSystem)

  override val clusterMembership = actorSystem.actorOf(ClusterMembership.props, ClusterMembership.Name)
  override val itemRegistryActor: ActorRef = actorSystem.actorOf(ItemActor.props, ItemActor.Name)
  override val rs: RoutingSettings = RoutingSettings(actorSystem)

  http.bindAndHandle(routes, httpHost, httpPort)
}
