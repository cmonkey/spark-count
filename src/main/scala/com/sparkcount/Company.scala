case class CompanyInfo(name: String, children: Seq[Company]=Nil)

/*val companies = List(Company("B"), Company("A"), Company("T"))*/

object CompanyObj{

  def main(args: Array[String]): Unit = {

    val companies = List(CompanyInfo("B"), CompanyInfo("A"), CompanyInfo("T"))

    println(companies)
  }
}