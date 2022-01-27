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

import com.manenkov.server.ItemActor.ActionPerformed
import org.scalatest.{ FlatSpec, Matchers }
import spray.json._

class JsonSerializerSpec extends FlatSpec with Matchers with JsonSerializer {
  "JsonSerializer" should s"convert Item to JSON and back" in {
    val item = Item("C", 1, "B3")
    item.toJson.convertTo[Item] shouldBe item
  }
  it should s"convert Items to JSON and back" in {
    val items = Items(Seq(
      Item("A", 1, "B1"),
      Item("B", 1, "B2"),
      Item("C", 1, "B3")))
    items.toJson.convertTo[Items] shouldBe items
  }
  it should s"convert Items to JSON and back" in {
    val actionPerformed = ActionPerformed("Added")
    actionPerformed.toJson.convertTo[Items] shouldBe actionPerformed
  }
}
