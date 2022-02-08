package com.softwaremill.bootzooka.money

import cats.data.NonEmptyList
import com.softwaremill.bootzooka.http.Http
import com.softwaremill.bootzooka.infrastructure.Doobie._
import com.softwaremill.bootzooka.infrastructure.Json._
import com.softwaremill.bootzooka.security.{ApiKey, Auth}
import com.softwaremill.bootzooka.util.ServerEndpoints
import doobie.util.transactor.Transactor
import monix.eval.Task

class MoneyApi(http: Http, auth: Auth[ApiKey], moneyService: MoneyService, xa: Transactor[Task]) {
  import MoneyApi._
  import http._
  private val MoneyPath = "money"
  private val MoneySuggestionsPath = MoneyPath / "suggestions"
  private val createTransactionEndpoint = secureEndpoint.post
    .in(MoneyPath / "transaction")
    .in(jsonBody[CreateTransaction_IN])
    .out(jsonBody[CreateTransaction_OUT])
    .serverLogic {
      case (authData, transaction) =>
        (for {
          userId <- auth(authData)
          _ <- moneyService.createTransaction(transaction.copy(userId = userId), generateId = true).transact(xa)
        } yield CreateTransaction_OUT()).toOut
    }

  private val summaryEndpoint = secureEndpoint.get
    .in(MoneyPath / "summary")
    .in(query[Int]("month"))
    .in(query[Int]("year"))
    .out(jsonBody[Summary_OUT])
    .serverLogic {
      case (authData, month, year) =>
        (for {
          userId <- auth(authData)
          res <- moneyService.getMonthlySummary(userId, month, year).transact(xa)
        } yield Summary_OUT(response = res.map(p => SummaryItem(category = p.head, total = p.tail.head)))).toOut
    }

  private val categoriesSuggestionsEndpoint = secureEndpoint.get
    .in(MoneySuggestionsPath / "categories")
    .out(jsonBody[CategoriesSuggestions_OUT])
    .serverLogic { authData =>
        (for {
          userId <- auth(authData)
          res <- moneyService.getCategoriesSuggestions(userId).transact(xa)
        } yield res).toOut
    }


  val endpoints: ServerEndpoints = NonEmptyList.of(
    createTransactionEndpoint,
    summaryEndpoint,
    categoriesSuggestionsEndpoint
  )
}

object MoneyApi {
  type CreateTransaction_IN = MoneyTransaction
  case class CreateTransaction_OUT()
  case class Summary_OUT(response: List[SummaryItem])
  case class SummaryItem(category: String, total: Double)

  type CategoriesSuggestions_OUT = List[String]
}
