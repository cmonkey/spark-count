package org.github.cmonkey.spark
class Employee(name: String, age: Int){

  private var s = 0.0

  def salary = s
  
  def salary_=(s:Double) = this.s = s

  def toStringConecat(): String = {
    "name: " + name + ", age " + age + ", salary: " + salary
  }

  def toStringInterplolation(): String = {
    s"Name: $name, age : $age, salary: $salary"
  }

  def toStringbuilder(): String = {
  
    val builder = new StringBuilder

    builder.append("name :")
    builder.append(name)

    builder.append(", age ")
    builder.append(age)

    builder.append(", slary")
    builder.append(salary)

    builder.toString()
  }
}

object Program{
  val empl = new Employee("john", 30)

  empl.salary = 10.50

  val times = 10000000

  def main(args: Array[String]): Unit = {

    val resultconcat = empl.toStringConecat

    val resultInterpol = empl.toStringInterplolation
    val resultBuilder = empl.toStringbuilder

    println("connect -> ", resultconcat)
    println("interpt ->", resultInterpol)
    println("builder ->", resultBuilder)

    val secondsConcat0 = run(empl.toStringConecat)
    val secondsConcat1 = run(empl.toStringConecat)
    val secondsConcat2 = run(empl.toStringConecat)

    println("concat-0" + secondsConcat0 + "s")
    println("concat-1" + secondsConcat1 + "s")
    println("concat-2" + secondsConcat2 + "s")
  }

  def run(call: => String): Double = {
    val startTime = System.nanoTime()
    var result = ""

    for(i <-0 until times){
      result = call
    }

    val endTime = System.nanoTime()

    val elapsedTime = endTime - startTime

    elapsedTime / 10000000.0
  }
}

