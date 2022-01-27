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

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{ as, complete, concat, delete, entity, get, onSuccess, path, pathEnd, pathEndOrSingleSlash, pathPrefix, post, rejectEmptyResponse }
import akka.http.scaladsl.settings.RoutingSettings
import akka.http.scaladsl.server.{ Directives, Route }
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.FiniteDuration
import scala.concurrent.Future
import com.manenkov.server.ItemActor._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._

trait HttpEndpoint extends JsonSerializer {
  import Directives._

  def rs: RoutingSettings
  def clusterMembership: ActorRef
  def itemRegistryActor: ActorRef

  // Required by the `ask` (?) method below
  implicit lazy val timeout: Timeout = Timeout(5.seconds) // usually we'd obtain the timeout from the system's configuration

  lazy val routes: Route = Route.seal(
    path("members") {
      pathEndOrSingleSlash {
        get {
          complete {
            clusterMembership.ask(ClusterMembership.GetMembershipInfo)(timeout)
              .mapTo[ClusterMembership.MembershipInfo]
          }
        }
      }
    } ~ pathPrefix("items") {
      concat(
        pathEnd {
          concat(
            get {
              val items: Future[Items] =
                (itemRegistryActor ? GetItems).mapTo[Items]
              onSuccess(items) {
                complete(_)
              }
            },
            post {
              entity(as[Item]) { item =>
                val itemCreated: Future[ActionPerformed] =
                  (itemRegistryActor ? CreateItem(item)).mapTo[ActionPerformed]
                onSuccess(itemCreated) { performed =>
                  complete((StatusCodes.Created, performed))
                }
              }
            })
        },
        path(Segment) { name =>
          concat(
            get {
              val maybeItem: Future[Option[Item]] =
                (itemRegistryActor ? GetItem(name)).mapTo[Option[Item]]
              rejectEmptyResponse {
                complete(maybeItem)
              }
            },
            delete {
              val itemDeleted: Future[ActionPerformed] =
                (itemRegistryActor ? DeleteItem(name)).mapTo[ActionPerformed]
              onSuccess(itemDeleted) { performed =>
                complete((StatusCodes.OK, performed))
              }
            })
        })
    })(rs)
}
