import collection.mutable.Stack
import org.scalatest._
import flatspec._
import matchers._

class Module1Spec extends AnyFlatSpec with should.Matchers {

  "A Module1" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }
}
