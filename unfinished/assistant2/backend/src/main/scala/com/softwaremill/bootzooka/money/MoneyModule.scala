package com.softwaremill.bootzooka.money

import com.softwaremill.bootzooka.email.{EmailScheduler, EmailTemplates}
import com.softwaremill.bootzooka.http.Http
import com.softwaremill.bootzooka.security.{ApiKey, ApiKeyService, Auth}
import com.softwaremill.bootzooka.util.BaseModule
import doobie.util.transactor.Transactor
import monix.eval.Task

trait MoneyModule extends BaseModule {
  lazy val moneyModel = new MoneyModel
  lazy val moneyApi = new MoneyApi(http, apiKeyAuth, moneyService, xa)
  lazy val moneyService = new MoneyService(moneyModel, idGenerator)

  def http: Http
  def apiKeyAuth: Auth[ApiKey]
  def emailScheduler: EmailScheduler
  def emailTemplates: EmailTemplates
  def apiKeyService: ApiKeyService
  def xa: Transactor[Task]
}
