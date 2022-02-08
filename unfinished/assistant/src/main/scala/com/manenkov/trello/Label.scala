package com.manenkov.trello

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads, Writes}

/** Label.
  *
  * @param id      The ID of the label
  * @param idBoard The ID of the board the label is on
  * @param name    The optional name of the label (0 - 16384 chars)
  * @param color   The color of the label. One of:
  *                yellow, purple, blue, red, green, orange, black, sky, pink, lime, null
  *                (null means no color, and the label will not show on the front of cards)
  * @see https://developers.trello.com/reference#labels
  * @see https://developers.trello.com/reference#label-object
  */
case class Label(
  id: String,
  idBoard: String,
  name: String,
  color: String,
)

trait LabelFormats {
  implicit val labelReads: Reads[Label] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "idBoard").read[String] and
      (JsPath \ "name").read[String] and
      (JsPath \ "color").read[String]
    ) (Label.apply _)
  implicit val labelWrites: Writes[Label] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "idBoard").write[String] and
      (JsPath \ "name").write[String] and
      (JsPath \ "color").write[String]
    ) (unlift(Label.unapply))
}
