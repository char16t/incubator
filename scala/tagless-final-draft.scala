import java.util.UUID

sealed trait Org
case class Dept(name: String, sub: List[Org]) extends Org
case class Pers(name: String, orgId: Long) extends Org

trait OrgAlgebra[A] {
  def dept(name: String, sub: List[A]): A
  def pers(name: String, orgId: Long): A
}

object OrgOrg extends OrgAlgebra[Org] {
  def dept(name: String, sub: List[Org]): Org = Dept(name, sub)
  def pers(name: String, orgId: Long): Org = Pers(name, orgId)
}

object Names extends OrgAlgebra[List[String]] {
  def dept(name: String, sub: List[List[String]]): List[String] = sub.flatten
  def pers(name: String, orgId: Long): List[String] = List(name)
}

object DeptNames extends OrgAlgebra[List[String]] {
  def dept(name: String, sub: List[List[String]]): List[String] = name :: sub.flatten
  def pers(name: String, orgId: Long): List[String] = List()
}

object Size extends OrgAlgebra[Int] {
  def dept(name: String, sub: List[Int]): Int = sub.sum
  def pers(name: String, orgId: Long): Int = 1
}

object DeptSize extends OrgAlgebra[Int] {
  def dept(name: String, sub: List[Int]): Int = 1 + sub.sum
  def pers(name: String, orgId: Long): Int = 0
}

def makeOrg[A]()(org: OrgAlgebra[A]) = 
  org.dept("Head", 
           List(org.pers("Peter", 1), 
                org.dept("Sub", 
                         List(org.pers("Jena", 2), 
                              org.pers("Melissa", 3)))))

makeOrg()(OrgOrg, ManagerManager)
makeOrg()(Names, ManagerManager)
makeOrg()(DeptNames, ManagerManager)
makeOrg()(Size, ManagerManager)
makeOrg()(DeptSize, ManagerManager)
