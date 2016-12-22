case class Company(name: String, children: Seq[Company]=Nil)

val companies = List(Company("B"), Company("A"), Company("T"))
