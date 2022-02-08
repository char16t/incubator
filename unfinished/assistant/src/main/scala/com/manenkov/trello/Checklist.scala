package com.manenkov.trello

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsObject, JsPath, Reads, Writes}

/** Checklist.
  *
  * @param id      The ID of the checklist
  * @param idBoard The ID of the board the checklist is on
  * @param idCard  The ID of the card the checklist is on
  * @param name    The name of the checklist
  * @param pos     The position of the checklist on the card (relative to any other checklists on the card)
  * @see https://developers.trello.com/reference#checklist
  * @see https://developers.trello.com/reference#checklist-object
  */
case class Checklist(
  id: String,
  idBoard: String,
  idCard: String,
  name: String,
  pos: Float,
)

trait ChecklistFormats {
  implicit val checklistReads: Reads[Checklist] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "idBoard").read[String] and
      (JsPath \ "idCard").read[String] and
      (JsPath \ "name").read[String] and
      (JsPath \ "pos").read[Float]
    ) (Checklist.apply _)
  implicit val checklistWrites: Writes[Checklist] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "idBoard").write[String] and
      (JsPath \ "idCard").write[String] and
      (JsPath \ "name").write[String] and
      (JsPath \ "pos").write[Float]
    ) (unlift(Checklist.unapply))
}
