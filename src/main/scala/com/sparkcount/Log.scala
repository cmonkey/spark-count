package com.sparkcount

import java.util.Date

/**
  * Created by cmonkey on 4/1/17.
  */
object Log {

  def main(args: Array[String]): Unit  = {
    val date = new Date(System.currentTimeMillis());
    val logMethod = log(date, _: String);

    val msg1 = "message1";
    logMethod(msg1)
    val msg2 = "message2";
    logMethod(msg2)
    val msg3 = "message3";
    logMethod(msg3)

  }

  def log(date: Date, message: String): Unit = {
    println(date + "------" + message)
  }
}
