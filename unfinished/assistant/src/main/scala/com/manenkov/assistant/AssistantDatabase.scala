package com.manenkov.assistant

import java.time.LocalDateTime

import play.api.libs.json.JsValue
import MyPostgresProfile.api._

object AssistantDatabase {

  class TrelloWebhooks(tag: Tag) extends Table[(LocalDateTime, JsValue)](tag, "trello_webhooks") {
    def time = column[LocalDateTime]("time")
    def hook = column[JsValue]("hook")
    def * = (time, hook)
    def pk = primaryKey("pk_trello_webhooks", (time, hook))
  }

  val db = Database.forConfig("mydb")
  val hooks = TableQuery[TrelloWebhooks]

  def insertHook(time: LocalDateTime, hook: JsValue) = {
    val action = DBIO.seq(
      //hooks.schema.createIfNotExists,
      hooks += (time, hook)
    )
    db.run(action)
  }
}
