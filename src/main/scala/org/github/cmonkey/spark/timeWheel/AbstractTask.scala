package org.github.cmonkey.spark.timeWheel
/**
  * 定时范围一个月，精度一秒, 4个表盘
  *  second 秒级表盘，每秒钟转动一次
  *  minute 分钟表盘， 每分钟转动一次
  *  hours 小时表盘, 每一小时转动一次
  *  days 天级表盘，每天24小时转动一次
  */
abstract class AbstractTask extends Task with Cloneable{

  val period: Long
  val secondPeriod: Int = 0
  val minutePeriod: Int = 0
  val hourPeriod: Int = 0
  val dayPeriod: Int = 0
  val taskId: String

  val isWorked: Boolean = false

  override def getPeriod(): Long = period

}
