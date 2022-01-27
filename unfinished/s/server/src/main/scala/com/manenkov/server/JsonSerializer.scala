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

import akka.cluster.{ Member, MemberStatus }
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._
import com.manenkov.server.ItemActor.ActionPerformed

trait JsonSerializer extends SprayJsonSupport {
  import DefaultJsonProtocol._

  implicit val memberStatusJsonSerializer: RootJsonFormat[MemberStatus] = new RootJsonFormat[MemberStatus] {
    override def read(json: JsValue): MemberStatus =
      throw new UnsupportedOperationException("Reading MemberStatus from json is not supported")

    override def write(o: MemberStatus): JsValue =
      JsString(
        o match {
          case MemberStatus.Joining  => "Joining"
          case MemberStatus.WeaklyUp => "WeaklyUp"
          case MemberStatus.Up       => "Up"
          case MemberStatus.Down     => "Down"
          case MemberStatus.Exiting  => "Exiting"
          case MemberStatus.Leaving  => "Leaving"
          case MemberStatus.Removed  => "Removed"
        })
  }

  implicit val memberJsonSerializer: RootJsonFormat[Member] = new RootJsonFormat[Member] {
    override def read(json: JsValue): Member =
      throw new UnsupportedOperationException("Reading Member from json is not supported")

    override def write(o: Member): JsValue =
      JsObject(
        "address" -> o.uniqueAddress.address.toString.toJson,
        "status" -> o.status.toJson,
        "roles" -> o.roles.toJson)
  }

  implicit val membershipInfoJsonSerializer: RootJsonFormat[ClusterMembership.MembershipInfo] =
    jsonFormat1(ClusterMembership.MembershipInfo)

  implicit val itemJsonSerializer: RootJsonFormat[Item] = jsonFormat3(Item)
  implicit val itemsJsonSerializer: RootJsonFormat[Items] = jsonFormat1(Items)
  implicit val actionPerformedJsonSerializer: RootJsonFormat[ActionPerformed] = jsonFormat1(ActionPerformed)
}
