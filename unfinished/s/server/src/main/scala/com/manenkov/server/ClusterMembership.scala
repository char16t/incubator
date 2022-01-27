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

import akka.actor.{ Actor, Props }
import akka.cluster.ClusterEvent.MemberEvent
import akka.cluster.{ Cluster, ClusterEvent, Member }

object ClusterMembership {
  val Name = "cluster-membership"

  def props: Props = Props(new ClusterMembership)

  sealed trait Message

  /**
   * Sent as a request to obtain membership info
   */
  case object GetMembershipInfo extends Message

  /**
   * Sent as a reply to [[GetMembershipInfo]]; contains the list of [[members]] of the cluster.
   */
  case class MembershipInfo(members: Set[Member]) extends Message
}

abstract class ClusterMembershipAware extends Actor {

  override def receive = handleMembershipEvents(Set.empty)

  protected def handleMembershipEvents(members: Set[Member]): Receive = {
    case event: ClusterEvent.MemberRemoved =>
      context.become(handleMembershipEvents(members.filterNot(_ == event.member)))

    case event: ClusterEvent.MemberEvent =>
      context.become(handleMembershipEvents(members.filterNot(_ == event.member) + event.member))

    case ClusterMembership.GetMembershipInfo =>
      sender() ! ClusterMembership.MembershipInfo(members)
  }
}

/**
 * Subscribes to the membership events, stores the updated list of the members in the Akka cluster.
 */
class ClusterMembership extends ClusterMembershipAware {
  private val cluster = Cluster(context.system)

  override def preStart(): Unit =
    cluster.subscribe(self, initialStateMode = ClusterEvent.InitialStateAsEvents, classOf[MemberEvent])

  override def postStop(): Unit =
    cluster.unsubscribe(self)
}
