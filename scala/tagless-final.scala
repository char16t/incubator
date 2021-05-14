import java.util.UUID

sealed trait Org
case class Dept(name: String, sub: List[Org]) extends Org
case class Pers(name: String, orgId: Long) extends Org
case class Manager(name: String, orgId: Long) extends Org

//trait OrgF[F[_]] {
//  def dept(name: String, sub: List[F[OrgF[F]]]): F[OrgF[F]]
//  def pers(name: String, orgId: Long): F[OrgF[F]]
//}

trait ManAlgebra[A] {
  def pers(name: String, orgId: Long): A
}

val makeM: ManAlgebra[Org] = new ManAlgebra[Org] {
  def pers(name: String, orgId: Long): Org = Manager(name, orgId)
}

val sizeM: ManAlgebra[Int] = new ManAlgebra[Int] {
  def pers(name: String, orgId: Long): Int = 1
}

val namesM: ManAlgebra[List[String]] = new ManAlgebra[List[String]] {
  def pers(name: String, orgId: Long): List[String] = List(name)
}

trait OrgAlgebra[A] {
  def pers(name: String, orgId: Long): A
  def dept(name: String, sub: List[A]): A
}

val make: OrgAlgebra[Org] = new OrgAlgebra[Org] {
  def dept(name: String, sub: List[Org]): Org = Dept(name, sub)
  def pers(name: String, orgId: Long): Org = Pers(name, orgId)
}

val size: OrgAlgebra[Int] = new OrgAlgebra[Int] {
  def dept(name: String, sub: List[Int]): Int = sub.sum
  def pers(name: String, orgId: Long): Int = 1
}

val names: OrgAlgebra[List[String]] = new OrgAlgebra[List[String]] {
  def dept(name: String, sub: List[List[String]]): List[String] = sub.flatten
  def pers(name: String, orgId: Long): List[String] = List(name)
}

def makeOrg[A]()(org: OrgAlgebra[A], man: ManAlgebra[A]) = 
  org.dept("Head", 
           List(org.pers("Peter", 1), 
                org.dept("Sub", 
                         List(man.pers("Jena", 2), 
                              org.pers("Melissa", 3)))))

makeOrg()(make, makeM)
makeOrg()(size, sizeM)
makeOrg()(names, namesM)
