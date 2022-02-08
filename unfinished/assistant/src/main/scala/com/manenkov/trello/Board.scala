package com.manenkov.trello

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsObject, JsPath, Reads, Writes}

/** Board.
  *
  * @param id             The ID of the board
  * @param name           The name of the board
  * @param desc           The description of the board
  * @param descData       If the description includes custom emoji, this will contain the data necessary to display them
  * @param closed         Boolean whether the board has been closed or not
  * @param idOrganization MongoID of the organization to which the board belongs
  * @param pinned         Boolean whether the board has been pinned or not
  * @param url            Persistent URL for the board
  * @param shortUrl       URL for the board using only its shortMongoID
  * @param prefs          Short for "preferences", these are the settings for the board
  * @param labelNames     Object containing color keys and the label names given for one label of each color on the board
  * @param starred        Whether the board has been starred by the current request's user
  * @param limits         An object containing information on the limits that exist for the board. Read more about at Limits.
  * @param memberships    Array of objects that represent the relationship of users to this board as memberships.
  * @see https://developers.trello.com/reference#boards-2
  * @see https://developers.trello.com/reference#board-object
  * @see https://developers.trello.com/v1.0/reference#boardsboardidlabels
  * @see https://developers.trello.com/docs/limits
  */
case class Board(
  id: String,
  name: String,
  @deprecated desc: String,
  descData: String,
  closed: Boolean,
  idOrganization: String,
  pinned: Boolean,
  url: String,
  shortUrl: String,
  prefs: JsObject,
  labelNames: JsObject,
  starred: Boolean,
  limits: JsObject,
  memberships: JsObject,
)

trait BoardFormats {
  implicit val boardReads: Reads[Board] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "name").read[String] and
      (JsPath \ "desc").read[String] and
      (JsPath \ "descData").read[String] and
      (JsPath \ "closed").read[Boolean] and
      (JsPath \ "idOrganization").read[String] and
      (JsPath \ "pinned").read[Boolean] and
      (JsPath \ "url").read[String] and
      (JsPath \ "shortUrl").read[String] and
      (JsPath \ "prefs").read[JsObject] and
      (JsPath \ "labelNames").read[JsObject] and
      (JsPath \ "starred").read[Boolean] and
      (JsPath \ "limits").read[JsObject] and
      (JsPath \ "memberships").read[JsObject]
    ) (Board.apply _)
  implicit val boardWrites: Writes[Board] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "name").write[String] and
      (JsPath \ "desc").write[String] and
      (JsPath \ "descData").write[String] and
      (JsPath \ "closed").write[Boolean] and
      (JsPath \ "idOrganization").write[String] and
      (JsPath \ "pinned").write[Boolean] and
      (JsPath \ "url").write[String] and
      (JsPath \ "shortUrl").write[String] and
      (JsPath \ "prefs").write[JsObject] and
      (JsPath \ "labelNames").write[JsObject] and
      (JsPath \ "starred").write[Boolean] and
      (JsPath \ "limits").write[JsObject] and
      (JsPath \ "memberships").write[JsObject]
    ) (unlift(Board.unapply))
}
