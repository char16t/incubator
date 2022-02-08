package com.manenkov.assistant

import play.api.libs.json.{JsPath, Reads}
import play.api.libs.functional.syntax._

object JsonFormats {
  implicit val locationReads: Reads[TrelloList] = (
    (JsPath \ "id").read[String] and
    (JsPath \ "name").read[String]
  )(TrelloList.apply _)
}
