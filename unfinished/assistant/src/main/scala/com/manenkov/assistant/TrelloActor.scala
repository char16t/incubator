package com.manenkov.assistant

import java.time.{ZoneId, ZonedDateTime}

import akka.actor.{Actor, ActorLogging, Props}
import com.softwaremill.sttp._
import play.api.libs.json.Json

object TrelloActor {
  def props(key: String, token: String): Props = Props(new TrelloActor(key, token))
  final case class DailyLists(boardId: String)
  final case class AddNowCard(name: String)
  final case class DeleteNowCard(name: String)
}

class TrelloActor(key: String, token: String) extends Actor with ActorLogging {
  import TrelloActor._
  import JsonFormats._

  implicit val backend = HttpURLConnectionBackend()

  override def preStart(): Unit = log.info("Trello Actor started")
  override def postStop(): Unit = log.info("Trello Actor stopped")
  override def receive = {
    case DailyLists(boardId) =>
      val moscowTimeZone = ZoneId.of("Europe/Moscow")

      // Get lists
      val request = sttp.get(uri"https://api.trello.com/1/boards/$boardId?lists=open&key=$key&token=$token")
      val response = request.send()
      val json = Json.parse(response.unsafeBody)
      val lists = (json \ "lists").as[List[TrelloList]]
      val today = java.time.format.DateTimeFormatter.ofPattern("dd.MM.YYYY").format(ZonedDateTime.now.withZoneSameInstant(moscowTimeZone).toLocalDate.minusDays(1))
      val tomorrow = java.time.format.DateTimeFormatter.ofPattern("dd.MM.YYYY").format(ZonedDateTime.now.withZoneSameInstant(moscowTimeZone).toLocalDate)
      val tomorrowTomorrow = java.time.format.DateTimeFormatter.ofPattern("dd.MM.YYYY").format(ZonedDateTime.now().withZoneSameInstant(moscowTimeZone).toLocalDate.plusDays(1))
      val todayListId = lists.find(_.name contains today).get
      val tomorrowListId = lists.find(_.name contains tomorrow).get
      val tomorrowTomorrowListId = lists.find(_.name contains tomorrowTomorrow).get


      // Move all cards from today to tomorrow
      val result = sttp.get(uri"https://api.trello.com/1/lists/${todayListId.id}/cards?fields=id,due&key=$key&token=$token").send()
      val resultJson = Json.parse(result.unsafeBody)
      val cards = (resultJson \\ "id").zip(resultJson \\ "due")
      val cardsWithoutDue = cards.filter(_._2.asOpt[String].isEmpty)
      for (card <- cardsWithoutDue) {
        val due = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T20:59:00Z'").format(ZonedDateTime.now.withZoneSameInstant(moscowTimeZone).toLocalDate.minusDays(1))
        sttp.put(uri"https://api.trello.com/1/cards/${card._1.as[String]}?due=$due&key=$key&token=$token").send()
      }
      for (card <- cards) {
        sttp.put(uri"https://api.trello.com/1/cards/${card._1.as[String]}?idList=${tomorrowListId.id}&key=$key&token=$token").send()
      }

      // rename tomorrow -> today
      val newName = s"Today ($tomorrow)"
      sttp.put(uri"https://api.trello.com/1/lists/${tomorrowListId.id}?name=$newName&key=$key&token=$token").send()

      // Rename dd.mm.yyyy -> tomorrow (dd.mm.yyyy)
      val newName2 = s"Tomorrow ($tomorrowTomorrow)"
      sttp.put(uri"https://api.trello.com/1/lists/${tomorrowTomorrowListId.id}?name=$newName2&key=$key&token=$token").send()

      // Archive Today list
      sttp.put(uri"https://api.trello.com/1/lists/${todayListId.id}/closed?value=true&key=$key&token=$token").send()

      // Create new list
      val newListName = java.time.format.DateTimeFormatter.ofPattern("dd.MM.YYYY").format(java.time.LocalDate.now.plusDays(14))
      val rsp = sttp.post(uri"https://api.trello.com/1/lists?name=$newListName&pos=bottom&idBoard=$boardId&key=$key&token=$token").send()
      val jsonRsp = Json.parse(rsp.unsafeBody)
      val listId = (jsonRsp \ "id").as[String]
      java.time.LocalDate.now.plusDays(14).getDayOfWeek match {
        //        case DayOfWeek.MONDAY =>
        //        case DayOfWeek.TUESDAY =>
        //        case DayOfWeek.WEDNESDAY =>
        //        case DayOfWeek.THURSDAY =>
        //        case DayOfWeek.FRIDAY =>
        //        case DayOfWeek.SATURDAY =>
        //        case DayOfWeek.SUNDAY =>
        case _ =>
          val cards = List(
            "Доброе утро! (05:00)",
            "Спокойной ночи! (21:00)",
          )
          for (cardName <- cards) {
            sttp.post(uri"https://api.trello.com/1/cards?name=$cardName&idList=$listId&key=$key&token=$token'").send()
          }
      }

      sender ! "Done"
    case AddNowCard(name) =>
      log.info("Add card")
      val listId = "5d28d1bbbe894265f74fda80"
      val res = sttp.post(uri"https://api.trello.com/1/cards?idList=$listId&key=$key&token=$token&name=$name").send()
    case DeleteNowCard(name) =>
      log.info("Del card")
      val listId = "5d28d1bbbe894265f74fda80"
      val res = sttp.get(uri"https://api.trello.com/1/lists/$listId/cards?key=$key&token=$token").send()
      val json = Json.parse(res.unsafeBody)
      val cardsToDel =
        ((json \\ "id").map(_.as[String]), (json \\ "name").map(_.as[String]))
        .zipped
        .map((_, _))
        .filter(_._2 == name)
      for (c <- cardsToDel) {
        sttp.delete(uri"https://api.trello.com/1/cards/${c._1}?key=$key&token=$token").send()
      }
  }
}
