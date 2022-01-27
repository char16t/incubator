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

import akka.actor.{ ActorSystem, Props }
import akka.cluster.ClusterEvent.{ MemberJoined, MemberRemoved, MemberUp }
import akka.cluster.{ MemberStatus, TestCluster }
import akka.testkit.TestProbe
import com.manenkov.server
import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpec }

import scala.concurrent.Await
import scala.concurrent.duration._

object ClusterMembershipSpec {
  class TestClusterMembership extends ClusterMembershipAware
}

class ClusterMembershipSpec extends WordSpec with Matchers with BeforeAndAfterAll {
  private val systemName = this.getClass.getSimpleName
  private implicit val actorSystem = ActorSystem(systemName)

  override protected def afterAll(): Unit =
    Await.ready(actorSystem.terminate(), atMost = 10.seconds)

  "cluster membership" should {
    "keep track of member states" in {
      val client = TestProbe()
      val clusterMembership = actorSystem.actorOf(Props(new ClusterMembershipSpec.TestClusterMembership()))

      val member1 = TestCluster.createMember(systemName, "10.0.0.1", 9004, 10L, Set("server"))

      clusterMembership ! MemberJoined(member1)

      client.send(clusterMembership, ClusterMembership.GetMembershipInfo)
      client.expectMsg(ClusterMembership.MembershipInfo(Set(member1)))

      val member1Up = member1.copyUp(1)
      clusterMembership ! MemberUp(member1Up)

      client.send(clusterMembership, ClusterMembership.GetMembershipInfo)
      client.expectMsg(ClusterMembership.MembershipInfo(Set(member1Up)))

      val member2 = TestCluster.createMember(systemName, "10.0.0.2", 9004, 10L, Set("server"))

      clusterMembership ! MemberJoined(member2)

      client.send(clusterMembership, ClusterMembership.GetMembershipInfo)
      client.expectMsg(ClusterMembership.MembershipInfo(Set(member1Up, member2)))

      val member1Removed = member1Up.copy(MemberStatus.Removed)
      clusterMembership ! MemberRemoved(member1Removed, previousStatus = MemberStatus.Up)

      client.send(clusterMembership, ClusterMembership.GetMembershipInfo)
      client.expectMsg(ClusterMembership.MembershipInfo(Set(member2)))
    }
  }
}
