package com.manenkov.trello

import play.api.libs.json.{JsValue, Json}

object MainTest
  extends ActionFormats
    with BoardFormats
    with CardFormats
    with ChecklistFormats
    with LabelFormats
    with ListFormats
    with NotificationFormats {
  val json: JsValue = Json.parse(
    """
      |{
      |  "id": "592f11060f95a3d3d46a987a",
      |  "idMemberCreator": "5191197f9433cf5507006338",
      |  "data": {
      |    "list": {
      |      "name": "Professional",
      |      "id": "54a17e9db559f0356ce022e4"
      |    },
      |    "board": {
      |      "shortLink": "BdarzfKF",
      |      "name": "Life Goals",
      |      "id": "54a17d76d4a5072e3931736b"
      |    },
      |    "card": {
      |      "shortLink": "gplJv6dx",
      |      "idShort": 2,
      |      "name": "Increase revenue by 10%",
      |      "id": "54a1844d304d9736e54d2546",
      |      "due": "2017-12-12T17:00:00.000Z"
      |    },
      |    "old": {
      |      "due": "2017-05-01T16:00:00.000Z"
      |    }
      |  },
      |  "type": "updateCard",
      |  "date": "2017-05-31T18:52:54.933Z",
      |  "memberCreator": {
      |    "id": "5191197f9433cf5507006338",
      |    "avatarHash": "ae0fde383cc2a195c053f1ad42c02022",
      |    "fullName": "Brian Cervino",
      |    "initials": "BC",
      |    "username": "brian"
      |  },
      |  "display": {
      |    "translationKey": "action_changed_a_due_date",
      |    "entities": {
      |      "card": {
      |        "type": "card",
      |        "due": "2017-12-12T17:00:00.000Z",
      |        "id": "54a1844d304d9736e54d2546",
      |        "shortLink": "gplJv6dx",
      |        "text": "Increase revenue by 10%"
      |      },
      |      "date": {
      |        "type": "date",
      |        "date": "2017-12-12T17:00:00.000Z"
      |      },
      |      "memberCreator": {
      |        "type": "member",
      |        "id": "5191197f9433cf5507006338",
      |        "username": "brian",
      |        "text": "Brian Cervino"
      |      }
      |    }
      |  }
      |}
    """.stripMargin)

  val p = json.as[Action]
  val r = Json.toJson(p).toString()
  println(p)
  println(r)


}
