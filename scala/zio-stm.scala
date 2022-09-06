import zio._
import zio.stm._

object Main extends ZIOAppDefault {

  def transferMoney(from: TRef[Long], to: TRef[Long], amount: Long): STM[String, Long] =
    for {
      senderBal <- from.get
      _         <- if (senderBal < amount) STM.fail("Not enough money") else STM.unit
      _         <- from.update(existing => existing - amount)
      _         <- to.update(existing => existing + amount)
      recvBal   <- to.get
    } yield recvBal

  val program: IO[String, Long] = for {
    sndAcc  <- STM.atomically(TRef.make(1000L))
    rcvAcc  <- STM.atomically(TRef.make(15L))
    recvAmt <- STM.atomically(transferMoney(sndAcc, rcvAcc, 1200L))
  } yield recvAmt

  val run = for {
    r <- program.catchAll(err => ZIO.logError(err))
    _ <- Console.printLine(r)
  } yield ()
}
