package org.github.cmonkey.spark.timeWheel
trait AbstractScheduler{

  def start(): Boolean
  def stop(): Boolean
  def build(): AbstractScheduler

  def unregistTask(abstractTask: AbstractTask)

  def destroy()
}
