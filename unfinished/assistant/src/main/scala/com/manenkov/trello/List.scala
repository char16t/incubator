package com.manenkov.trello

/** List.
  *
  * @param id         The ID of the list
  * @param name       The name of the list
  * @param closed     Whether the list is closed (archived)
  * @param idBoard    The ID of the board the list is on
  * @param pos        The position of the list on the board
  * @param subscribed Whether the member is subscribed to this list
  * @param softLimit  A soft limit for number of open cards in the list used by the List Limits Power-Up
  * @see https://developers.trello.com/reference#lists
  * @see https://developers.trello.com/reference#list-object
  * @see https://trello.com/power-ups/5c2462c384ab8949b1724a20
  */
case class List(
  id: String,
  name: String,
  closed: Boolean,
  idBoard: String,
  pos: Float,
  subscribed: Boolean,
  softLimit: Option[Int]
)

trait ListFormats {

}
