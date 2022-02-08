package com.softwaremill.bootzooka.money

import java.time.LocalDate

import shapeless._
import cats.implicits._
import com.softwaremill.bootzooka.infrastructure.Doobie
import com.softwaremill.bootzooka.infrastructure.Doobie.{ConnectionIO, _}
import com.softwaremill.bootzooka.user.User
import com.softwaremill.bootzooka.util.Id
import com.softwaremill.tagging.@@

class MoneyModel {

  def insert(transaction: MoneyTransaction): ConnectionIO[Unit] = {
    sql"""INSERT INTO money_transactions (
         |  id, user_id, date, category, payee, comment, outcome_account,
         |  outcome_amount, outcome_currency, income_account, income_amount,
         |  income_currency, status
         |) VALUES (
         |${transaction.id},
         |${transaction.userId},
         |${transaction.date},
         |${transaction.category},
         |${transaction.payee},
         |${transaction.comment},
         |${transaction.outcomeAccount},
         |${transaction.outcomeAmount},
         |${transaction.outcomeCurrency},
         |${transaction.incomeAccount},
         |${transaction.incomeAmount},
         |${transaction.incomeCurrency},
         |${transaction.status}
         |)""".stripMargin.update.run.void
  }

  def getMonthlySummary(userId: Id @@ User, month: Int, year: Int): doobie.ConnectionIO[List[String :: Double :: HNil]] = {
    val nextPeriodMonth = if (month == 12) 1 else month + 1
    val nextPeriodYear = if (month == 12) year + 1 else year

    val dateFrom = LocalDate.of(year,month,1)
    val dateTo = LocalDate.of(nextPeriodYear,nextPeriodMonth,1)

    val query = sql"""
         |with
         |expences as (
         |select
         |  category,
         |  -1 * sum(outcome_amount) as total
         |from money_transactions
         |where
         |  outcome_amount is not null
         |  and date >= $dateFrom
         |  and date < $dateTo
         |  and user_id = $userId
         |group by category
         |),
         |revenue as (
         |select
         |  category,
         |  sum(income_amount) as total
         |from money_transactions
         |where
         |  income_amount is not null
         |  and date >= $dateFrom
         |  and date < $dateTo
         |  and user_id = $userId
         |group by category
         |),
         |all_rows as (
         |  select * from expences
         |  union
         |  select * from revenue
         |)
         |select
         |  category,
         |  sum(total) as total
         |from all_rows
         |group by category
         |""".stripMargin
    query
      .query[String :: Double :: HNil]
      .to[List]
  }

  def getCategoriesSuggestions(userId: Id @@ User): ConnectionIO[List[String]] = {
    sql"""select distinct category from money_transactions where user_id = $userId"""
      .query[String]
      .to[List]
  }
}

case class MoneyTransaction (
  id: Id @@ MoneyTransaction,
  userId: Id @@ User,
  date: LocalDate,
  category: String,
  payee: Option[String],
  comment: Option[String],
  outcomeAccount: Option[String],
  outcomeAmount: Option[Double],
  outcomeCurrency: Option[String],
  incomeAccount: Option[String],
  incomeAmount: Option[Double],
  incomeCurrency: Option[String],
  status: String
)
