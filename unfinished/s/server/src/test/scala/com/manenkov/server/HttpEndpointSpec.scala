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

import akka.actor.ActorRef
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.settings.RoutingSettings
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ Matchers, WordSpec }

class HttpEndpointSpec
  extends WordSpec
  with Matchers
  with ScalaFutures
  with ScalatestRouteTest
  with HttpEndpoint
  with JsonSerializer {

  override val clusterMembership: ActorRef = system.actorOf(ClusterMembership.props, ClusterMembership.Name)
  override val itemRegistryActor: ActorRef = system.actorOf(ItemActor.props, ItemActor.Name)
  override val rs: RoutingSettings = RoutingSettings(system)

  "ItemRoutes" should {
    "return no items if no present (GET /items)" in {
      val request = HttpRequest(uri = "/items")
      request ~> routes ~> check {
        status shouldBe StatusCodes.OK
        contentType shouldBe ContentTypes.`application/json`
        entityAs[String] should ===("""{"items":[]}""")
      }
    }

    "be able to add items (POST /items)" in {
      val item = Item("Kapi", 42, "jp")
      val itemEntity = Marshal(item).to[MessageEntity].futureValue
      val request = Post("/items").withEntity(itemEntity)

      request ~> routes ~> check {
        status should ===(StatusCodes.Created)
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should ===("""{"description":"Item Kapi created."}""")
      }
    }

    "be able to remove items (DELETE /items)" in {
      val request = Delete(uri = "/items/Kapi")
      request ~> routes ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should ===("""{"description":"Item Kapi deleted."}""")
      }
    }
  }
}
