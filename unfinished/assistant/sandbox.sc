case class Thing (
  title: String,
  parent: Option[Thing] = None
)

val morning = Thing("Morning")
val bed = Thing("Bed", Option(morning))
val bag = Thing("Bag", Option(morning))
val passport = Thing("Passport", Option(bag))
val money = Thing("Money", Option(bag))
val wear = Thing("Wear", Option(morning))

val things = List(morning, bed, bag, passport, money, wear)

