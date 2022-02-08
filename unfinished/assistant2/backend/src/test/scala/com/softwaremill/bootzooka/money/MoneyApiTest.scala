package com.softwaremill.bootzooka.money

import java.time.Clock

import com.softwaremill.bootzooka.MainModule
import com.softwaremill.bootzooka.config.Config
import com.softwaremill.bootzooka.infrastructure.Doobie.Transactor
import com.softwaremill.bootzooka.test.{BaseTest, Requests, TestConfig, TestEmbeddedPostgres}
import monix.eval.Task
import org.scalatest.concurrent.Eventually
import sttp.client.impl.monix.TaskMonadAsyncError
import sttp.client.testing.SttpBackendStub
import sttp.client.{NothingT, SttpBackend}

class MoneyApiTest extends BaseTest with TestEmbeddedPostgres with Eventually {
  lazy val modules: MainModule = new MainModule {
    override def xa: Transactor[Task] = currentDb.xa
    override lazy val baseSttpBackend: SttpBackend[Task, Nothing, NothingT] = SttpBackendStub(TaskMonadAsyncError)
    override lazy val config: Config = TestConfig
    override lazy val clock: Clock = testClock
  }

  val requests = new Requests(modules)
  import requests._

  "/money/transaction" should "create transaction" in {}
}
