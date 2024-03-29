import collection.mutable.Stack
import org.scalatest._
import flatspec._
import matchers._

class Module2Spec extends AnyFlatSpec with should.Matchers {

  "A Module2" should "pop values in last-in-first-out order" in {
    val stack = new Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }
}
