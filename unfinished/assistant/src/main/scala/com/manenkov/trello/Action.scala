package com.manenkov.trello

import java.time.LocalDateTime

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsObject, JsPath, Reads, Writes}

/** Action.
  *
  * Actions are generated whenever an action occurs in Trello.
  *
  * @param id              The ID of the action
  * @param data            Relevant information regarding the action
  * @param date            When the action occurred
  * @param idMemberCreator The ID of the member who caused the action
  * @param `type`          The type of the action. See list of Action Types for options.
  * @see https://developers.trello.com/reference#actions
  * @see https://developers.trello.com/reference#action-object
  * @see https://developers.trello.com/reference-link/action-types
  */
case class Action(
  id: String,
  data: JsObject,
  date: LocalDateTime,
  idMemberCreator: String,
  `type`: String,
)

trait ActionFormats {
  implicit val actionReads: Reads[Action] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "data").read[JsObject] and
      (JsPath \ "date").read[LocalDateTime] and
      (JsPath \ "idMemberCreator").read[String] and
      (JsPath \ "type").read[String]
    ) (Action.apply _)

  implicit val actionWrites: Writes[Action] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "data").write[JsObject] and
      (JsPath \ "date").write[LocalDateTime] and
      (JsPath \ "idMemberCreator").write[String] and
      (JsPath \ "type").write[String]
    ) (unlift(Action.unapply))
}
