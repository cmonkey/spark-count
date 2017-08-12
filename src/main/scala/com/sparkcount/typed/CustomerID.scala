package com.sparkcount.typed

import scala.util.Random

object CustomerID{

  def apply(name: String) = s"$name--${Random.nextLong}"

  def unapply(customerID: String): Option[String] = {
    val name = customerID.split("---").head
    if(name.nonEmpty) Some(name) else None
  }

  def main(args: Array[String]): Unit = {
    val customerID = CustomerID("sukyoung")
    customerID match {
      case CustomerID(name) => println(name)
      case _ => println("Could not extract a CustomerId")
    }
  }

}
