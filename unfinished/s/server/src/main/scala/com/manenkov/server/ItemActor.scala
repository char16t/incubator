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

import akka.actor.{ Actor, ActorLogging, Props }

final case class Item(name: String, age: Int, countryOfResidence: String)
final case class Items(items: Seq[Item])

object ItemActor {
  val Name = "itemRegistryActor"

  final case class ActionPerformed(description: String)
  final case object GetItems
  final case class CreateItem(item: Item)
  final case class GetItem(name: String)
  final case class DeleteItem(name: String)

  def props: Props = Props[ItemActor]
}

class ItemActor extends Actor with ActorLogging {
  import ItemActor._

  var items = Set.empty[Item]

  def receive: Receive = {
    case GetItems =>
      sender() ! Items(items.toSeq)
    case CreateItem(item) =>
      items += item
      sender() ! ActionPerformed(s"Item ${item.name} created.")
    case GetItem(name) =>
      sender() ! items.find(_.name == name)
    case DeleteItem(name) =>
      items.find(_.name == name) foreach { item => items -= item }
      sender() ! ActionPerformed(s"Item $name deleted.")
  }
}
