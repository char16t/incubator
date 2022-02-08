package com.manenkov.trello

import java.time.LocalDateTime

import play.api.libs.functional.syntax._
import play.api.libs.json._

/** Card.
  *
  * @param id                    The ID of the card
  * @param badges                Pieces of information about the card that are displayed on the front of the card
  * @param checkItemStates
  * @param closed                Whether the card is closed (archived).
  *                              Note: Archived lists and boards do not cascade archives to cards. A card can have `closed`:
  *                              false but be on an archived board.
  * @param dateLastActivity      The datetime of the last activity on the card.
  *                              Note: There are activities that update dateLastActivity that do not create
  *                              a corresponding action. For instance, updating the name field of a checklist
  *                              item on a card does not create an action but does update the card and board's
  *                              dateLastActivity value.
  * @param desc                  The description for the card. Up to 16384 chars
  * @param descData              If the description has custom emoji, this field will provide the data necessary to display them
  * @param due                   The due date on the card, if one exists
  * @param dueComplete           Whether the due date has been marked complete
  * @param idAttachmentCover     The id of the attachment selected as the cover image, if one exists
  * @param idBoard               The ID of the board the card is on
  * @param idChecklists          An array of checklist IDs that are on this card
  * @param idLabels              An array of label IDs that are on this card
  * @param idList                The ID of the list the card is in
  * @param idMembers             An array of member IDs that are on this card
  * @param idMembersVoted        An array of member IDs who have voted on this card
  * @param idShort               Numeric ID for the card on this board. Only unique to the board,
  *                              and subject to change as the card moves
  * @param labels                Array of label objects on this card
  * @param manualCoverAttachment Whether the card cover image was selected automatically by Trello,
  *                              or manually by the user
  * @param name                  Name of the card
  * @param pos                   Position of the card in the list
  * @param shortLink             The 8 character shortened ID for the card
  * @param shortUrl              URL to the card without the name slug
  * @param subscribed            Whether this member is subscribed to the card
  * @param url                   Full URL to the card, with the name slug
  * @param address               Address of card location
  * @param locationName          Name of card location
  * @param coordinates           Either a comma-separated string in the format latitude,longitude
  *                              or an object containing keys for latitude and longitude whose values
  *                              are numbers between -180 and 180.
  * @see https://developers.trello.com/reference#cards-1
  * @see https://developers.trello.com/reference#card-object
  */
case class Card(
  id: String,
  badges: JsObject,
  checkItemStates: JsArray,
  closed: Boolean,
  dateLastActivity: LocalDateTime,
  desc: String,
  descData: JsObject,
  due: LocalDateTime,
  dueComplete: Boolean,
  idAttachmentCover: String,
  idBoard: String,
  idChecklists: scala.collection.immutable.List[String],
  idLabels: scala.collection.immutable.List[String],
  idList: String,
  idMembers: scala.collection.immutable.List[String],
  idMembersVoted: scala.collection.immutable.List[String],
  idShort: Int,
  labels: scala.collection.immutable.List[Label],
  manualCoverAttachment: Boolean,
  name: String,
  pos: Float,
  shortLink: String,
  shortUrl: String,
  subscribed: Boolean,
  url: String,
  address: String,
  locationName: String,
  coordinates: JsObject,
)

trait CardFormats {
//  implicit val cardReads: Reads[Card] = (
//    (JsPath \ "id").read[String] and
//      (JsPath \ "badges").read[JsObject] and
//      (JsPath \ "checkItemStates").read[JsArray] and
//      (JsPath \ "closed").read[Boolean] and
//      (JsPath \ "dateLastActivity").read[LocalDateTime] and
//      (JsPath \ "desc").read[String] and
//      (JsPath \ "descData").read[JsObject] and
//      (JsPath \ "due").read[JsObject] and
//      (JsPath \ "dueComplete").read[String] and
//      (JsPath \ "idAttachmentCover").read[String] and
//      (JsPath \ "idBoard").read[String] and
//      (JsPath \ "idChecklists").read[scala.collection.immutable.List[String]] and
//      (JsPath \ "idLabels").read[scala.collection.immutable.List[String]] and
//      (JsPath \ "idList").read[String] and
//      (JsPath \ "idMembers").read[scala.collection.immutable.List[String]] and
//      (JsPath \ "idMembersVoted").read[scala.collection.immutable.List[String]] and
//      (JsPath \ "idShort").read[Int] and
//      (JsPath \ "labels").read[scala.collection.immutable.List[Label]] and
//      (JsPath \ "manualCoverAttachment").read[Boolean] and
//      (JsPath \ "name").read[String] and
//      (JsPath \ "pos").read[Float] and
//      (JsPath \ "shortLink").read[String] and
//      (JsPath \ "shortUrl").read[String] and
//      (JsPath \ "subscribed").read[Boolean] and
//      (JsPath \ "url").read[String] and
//      (JsPath \ "address").read[String] and
//      (JsPath \ "locationName").read[String] and
//      (JsPath \ "coordinates").read[JsObject]
//    )(Card.apply _)
//  implicit val cardWrites: Writes[Card] = (
//    (JsPath \ "id").write[String] and
//      (JsPath \ "badges").write[JsObject] and
//      (JsPath \ "checkItemStates").write[JsArray] and
//      (JsPath \ "closed").write[Boolean] and
//      (JsPath \ "dateLastActivity").write[LocalDateTime] and
//      (JsPath \ "desc").write[String] and
//      (JsPath \ "descData").write[JsObject] and
//      (JsPath \ "due").write[JsObject] and
//      (JsPath \ "dueComplete").write[String] and
//      (JsPath \ "idAttachmentCover").write[String] and
//      (JsPath \ "idBoard").write[String] and
//      (JsPath \ "idChecklists").write[scala.collection.immutable.List[String]] and
//      (JsPath \ "idLabels").write[scala.collection.immutable.List[String]] and
//      (JsPath \ "idList").write[String] and
//      (JsPath \ "idMembers").write[scala.collection.immutable.List[String]] and
//      (JsPath \ "idMembersVoted").write[scala.collection.immutable.List[String]] and
//      (JsPath \ "idShort").write[Int] and
//      (JsPath \ "labels").write[scala.collection.immutable.List[Label]] and
//      (JsPath \ "manualCoverAttachment").write[Boolean] and
//      (JsPath \ "name").write[String] and
//      (JsPath \ "pos").write[Float] and
//      (JsPath \ "shortLink").write[String] and
//      (JsPath \ "shortUrl").write[String] and
//      (JsPath \ "subscribed").write[Boolean] and
//      (JsPath \ "url").write[String] and
//      (JsPath \ "address").write[String] and
//      (JsPath \ "locationName").write[String] and
//      (JsPath \ "coordinates").write[JsObject]
//    )(unlift(Card.unapply))
}
