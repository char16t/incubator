import java.time.LocalDateTime
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

sealed trait State
case object Todo extends State
case object Done extends State

case class Node(
 id: String,
 expand: Boolean,
 ordered: Boolean,
 priority: Long,
 state: State,
 estimate: Option[Long],
 startFrom: Option[LocalDateTime],
 due: Option[LocalDateTime],
 sub: Seq[Node] = Seq()
 ) {
 def startTo: Option[LocalDateTime] = for (d <- due; e <- estimate) yield d.minusMinutes(e)
 def endFrom: Option[LocalDateTime] = for (s <- startFrom; e <- estimate) yield s.plusMinutes(e)
 def endTo: Option[LocalDateTime] = due
}

def traverse(root: Node): Seq[Node] = {
 val stack = new mutable.Stack[Node]()
 val result = new ListBuffer[Node]()
 stack.push(root)
 while (stack.nonEmpty) {
 val node = stack.pop()
 if (!node.expand) {
 result.addOne(node)
 }
 if (node.expand) {
 val sub = if (node.ordered) node.sub.filter(_.state == Todo).take(1) else node.sub.filter(_.state == Todo)
 stack.pushAll(sub)
 }
 }
 result.toList.reverse
}

def traverseAvailable(tree: Node, after: LocalDateTime, before: LocalDateTime) =
 traverse(tree)
 .filter(node => node.startFrom.exists(ldt => ldt.isAfter(after) && ldt.isBefore(before)))
 .sortBy(_.startTo)
 .sortBy(- _.priority)

def deepEstimate(root: Node): Node = {
 if (root.sub.isEmpty) {
 return root.estimate match {
 case Some(value) => root.copy(estimate = Some(value))
 case None => root.copy(estimate = Some(0))
 }
 }
 val s = root.sub.foldLeft(Seq[Node]())((seq, node) => {
 seq :+ deepEstimate(node)
 })
 val e = s.foldLeft(Long.box(0))((sum, node) => {
 sum + (node.estimate match {
 case Some(value) => value
 case None => 0
 })
 })
 root.copy(estimate = Some(e), sub = s)
}

def collapse(root: Node): Node = {
 root.copy(expand = false)
}

def collapseAll(root: Node): Node = {
 root.copy(expand = false, sub = root.sub.map(collapseAll))
}

def deepCollapse(root: Node, target: String): Node = {
 if (root.id == target) {
 collapseAll(root)
 } else if (root.sub.exists(_.id == target)) {
 root.copy(sub = root.sub.map {
 case x if x.id == target => collapseAll(x)
 case x => x
 })
 } else {
 root.copy(sub = root.sub.map(x => deepCollapse(x, target)))
 }
}

def collapseNode(root: Node, target: String): Node = {
 if (root.id == target) {
 collapseAll(root)
 } else if (root.sub.exists(_.id == target)) {
 root.copy(sub = root.sub.map {
 case x if x.id == target => collapseAll(x)
 case x => x
 })
 } else {
 root.copy(sub = root.sub.map(x => collapseNode(x, target)))
 }
}

def expand(root: Node): Node = {
 root.copy(expand = true)
}

def expandAll(root: Node): Node = {
 root.copy(expand = if (root.sub.nonEmpty) true else false, sub = root.sub.map(expandAll))
}

def deepExpand(root: Node, target: String): Node = {
 if (root.id == target) {
 expandAll(root)
 } else if (root.sub.exists(_.id == target)) {
 root.copy(sub = root.sub.map {
 case x if x.id == target => expandAll(x)
 case x => x
 })
 } else {
 root.copy(sub = root.sub.map(x => deepExpand(x, target)))
 }
}

def expandNode(root: Node, target: String): Node = {
 if (root.id == target) {
 expand(root)
 } else if (root.sub.exists(_.id == target)) {
 root.copy(sub = root.sub.map {
 case x if x.id == target => expand(x)
 case x => x
 })
 } else {
 root.copy(sub = root.sub.map(x => expandNode(x, target)))
 }
}

def changeState(node: Node, state: State): Node = {
 node.copy(state = state, sub = node.sub.map(changeState(_, state)))
}

def changeStateOf(node: Node, target: String, state: State): Node = {
 if (node.id == target) {
 changeState(node, state)
 } else if (node.sub.exists(_.id == target)) {
 if (state == Done && node.sub.filter(_.id != target).forall(_.state == Done)) {
 changeState(node, Done)
 } else {
 node.copy(sub = node.sub.map {
 case x if x.id == target => changeState(x, state)
 case x => x
 })
 }
 } else {
 node.copy(sub = node.sub.map(x => changeStateOf(x, target, state)))
 }
}

def deepSetStartFrom(root: Node, startFrom: LocalDateTime): Node = {
 root.copy(startFrom = Some(startFrom), sub = root.sub.map(deepSetStartFrom(_, startFrom)))
}

def deepChangePriority(node: Node, priority: Long): Node = {
 node.copy(priority = priority, sub = node.sub.map(deepChangePriority(_, priority)))
}

def deepChangePriorityOf(node: Node, target: String, priority: Long): Node = {
 if (node.id == target) {
 deepChangePriority(node, priority)
 } else if (node.sub.exists(_.id == target)) {
 node.copy(sub = node.sub.map {
 case x if x.id == target => deepChangePriority(x, priority)
 case x => x
 })
 } else {
 node.copy(sub = node.sub.map(x => deepChangePriorityOf(x, target, priority)))
 }
}

/**
 * Note: deepEstimate should be applied to root node before deepSetDue
 */
def deepSetDue(root: Node, due: LocalDateTime): Node = {
 val sub = if (root.ordered) {
 val delays = root.sub.foldLeft(Seq[Long]())((seq, node) => {
 val last = seq.lastOption match {
 case Some(value) => value
 case None => 0
 }
 val current = node.estimate match {
 case Some(value) => value
 case None => 0
 }
 seq :+ (last+current)
 })
 root.sub.zip(delays).map(pair => {
 val node = pair._1
 val delay = pair._2
 deepSetDue(node, due.minusMinutes(delay))
 })
 } else {
 root.sub.map(child => deepSetDue(child, due))
 }
 root.copy(due = Some(due), sub = sub)
}

val tree = Node(
 "A",
 expand = false,
 ordered = false,
 priority = 0,
 state = Todo,
 estimate = None,
 startFrom = None,
 due = None,
 Seq(
 Node("AA",
 expand = false,
 ordered = false,
 priority = 0,
 state = Todo,
 estimate = None,
 startFrom = None,
 due = None,
 Seq(
 Node(
 "AAA", expand = false, ordered = true, priority = 0, state = Todo, estimate = None, startFrom = None, due = None,
 Seq(
 Node("AAAA", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 Node("AAAB", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 Node("AAAC", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 ),
 ),
 Node("AAB", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 Node("AAC", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 )
 ),
 Node("AB",
 expand = false,
 ordered = false,
 priority = 0,
 state = Todo,
 estimate = None,
 startFrom = None,
 due = None,
 Seq(
 Node("ABA", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 Node("ABB", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 Node("ABC", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 ),
 ),
 Node("AC",
 expand = false,
 ordered = false,
 priority = 0,
 state = Todo,
 estimate = None,
 startFrom = None,
 due = None,
 Seq(
 Node("ACA", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 Node("ACB", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 Node("ACC", expand = false, ordered = false, priority = 0, state = Todo, estimate = Some(5), startFrom = None, due = None),
 ),
 ),
 ),
)

val t0 = deepEstimate(tree)
val t1 = collapseAll(t0)
val t2 = expand(t1)
val t22 = deepSetDue(t2, LocalDateTime.now())
val t23 = deepChangePriorityOf(t22, "AAC", 3)
val t24 = deepChangePriorityOf(t23, "AAB", 1)
val t25 = deepChangePriorityOf(t24, "AC", 1)
val t26 = deepSetStartFrom(t25, LocalDateTime.now().minusMinutes(120L))

//

val t3 = expandNode(t26, "AA")
val t4 = deepExpand(t3, "AC")
val t5 = collapseNode(t4, "AA")
val t6 = collapseNode(t5, "A")
val t7 = expand(t6)
val t8 = changeState(t7, Done)
val t9 = changeState(t8, Todo)
val t10 = expandNode(t9, "AB")
val t11 = changeStateOf(t10, "AB", Done)
val t12 = deepExpand(t11, "AA")
val t13 = changeStateOf(t12, "AAAA", Done)
val t14 = changeStateOf(t13, "AAAB", Done)
val t15 = changeStateOf(t14, "AAAC", Done)

traverse(t1).map(_.id)
traverse(t2).map(_.id)
traverse(t3).map(_.id)
traverse(t4).map(_.id)
traverse(t5).map(_.id)
traverse(t6).map(_.id)
traverse(t7).map(_.id)
traverse(t8).map(_.id)
traverse(t9).map(_.id)
traverse(t10).map(_.id)
traverse(t11).map(_.id)
traverse(t12).map(_.id)
traverse(t13).map(_.id)
traverse(t14).map(_.id)
traverse(t15).map(_.id)

traverseAvailable(t15, LocalDateTime.now().minusDays(1L), LocalDateTime.now())
 .map(node => s"${node.id}: start = (${node.startFrom}, ${node.startTo}), end = (${node.endFrom}, ${node.endTo})")
 .foreach(println)
