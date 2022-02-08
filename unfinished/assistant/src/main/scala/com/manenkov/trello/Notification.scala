package com.manenkov.trello

import java.time.LocalDateTime

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsObject, JsPath, Reads, Writes}

/** Notification.
  *
  * @param id              The ID of the notification
  * @param data            Relevant data regarding the notification
  * @param date            The datetime the notification was triggered
  * @param idMemberCreator The ID of the member who triggered the notification
  * @param `type`          The type of the notification
  * @param unread          Whether the notification hasn't been read yet
  * @see https://developers.trello.com/reference#notifications
  * @see https://developers.trello.com/reference#notification-object
  */
case class Notification(
  id: String,
  data: JsObject,
  date: LocalDateTime,
  idMemberCreator: String,
  `type`: String,
  unread: Boolean,
)

trait NotificationFormats {
  implicit val notificationReads: Reads[Notification] = (
    (JsPath \ "id").read[String] and
      (JsPath \ "data").read[JsObject] and
      (JsPath \ "date").read[LocalDateTime] and
      (JsPath \ "idMemberCreator").read[String] and
      (JsPath \ "type").read[String] and
      (JsPath \ "unread").read[Boolean]
    ) (Notification.apply _)
  implicit val notificationWrites: Writes[Notification] = (
    (JsPath \ "id").write[String] and
      (JsPath \ "data").write[JsObject] and
      (JsPath \ "date").write[LocalDateTime] and
      (JsPath \ "idMemberCreator").write[String] and
      (JsPath \ "type").write[String] and
      (JsPath \ "unread").write[Boolean]
    ) (unlift(Notification.unapply))
}
