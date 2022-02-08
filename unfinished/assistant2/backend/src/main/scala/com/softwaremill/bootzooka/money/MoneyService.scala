package com.softwaremill.bootzooka.money

import com.softwaremill.bootzooka.infrastructure.Doobie.ConnectionIO
import com.softwaremill.bootzooka.user.User
import com.softwaremill.bootzooka.util.{Id, IdGenerator}
import com.softwaremill.tagging.@@
import shapeless._

class MoneyService(moneyModel: MoneyModel, idGenerator: IdGenerator) {

  def createTransaction(transaction: MoneyTransaction, generateId: Boolean = false): ConnectionIO[Unit] = {
    val transaction1 = if (generateId)
      transaction.copy(id = idGenerator.nextId())
    else
      transaction
    moneyModel.insert(transaction1)
  }

  def getMonthlySummary(userId: Id @@ User, month: Int, year: Int): ConnectionIO[List[String :: Double :: HNil]] = {
    moneyModel.getMonthlySummary(userId, month, year)
  }

  def getCategoriesSuggestions(userId: Id @@ User): ConnectionIO[List[String]] = {
    moneyModel.getCategoriesSuggestions(userId)
  }
}
