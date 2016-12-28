package com.sparkcount

import org.apache.spark.SparkContext

object FoldSpark{

  def main(args: Array[String]): Unit = {

    val sparkContext = new SparkContext("local", "functional")

    val employeeData = List(("Jack", 1000.0), ("Bob", 2000.0), ("Carl", 7000.0))

    val employeeRDD = sparkContext.makeRDD(employeeData)

    val dummyEmployee = ("dummy", 0.0)

    val maxSalaryEmployee = employeeRDD.fold(dummyEmployee)((acc,employee) => {
      if(acc._2 < employee._2) 
        employee 
      else 
        acc
    })

    println("employee with maximum salary is " + maxSalaryEmployee)

    /*
    val deptEmployees = List(
      ("cs",("jack",1000.0)),
      ("cs",("bron",1200.0)),
      ("phy",("sam",2200.0)),
      ("phy",("ronaldo",500.0))
    )
    val employeeRDD1 = sparkContext.makeRDD(deptEmployees)

    val maxByDept = employeeRDD1.foldByKey(("dummy",0.0))
    ((acc,element)=> if(acc._2 > element._2) acc else element)

    println("maximum salaries in each dept" + maxByDept)
    */
  }
}
