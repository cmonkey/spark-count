class ClassWithValParameter(val name: String) 

object ClassWithValParameter{

  def main(args: Array[String]):Unit = {
    val aClass = new ClassWithValParameter("Ga")
    println(aClass.name)
  }
}

