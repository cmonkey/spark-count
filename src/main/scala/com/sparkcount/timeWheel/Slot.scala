package com.sparkcount.timeWheel

/**
  * 时间轮槽数据结构，用于存储定时任务
  * @tparam T
  */
class Slot[T] {

  val expiredTask = new java.util.ArrayList[T]()

  /** 添加任务
   */
  def addTask(task: T): Unit = {
    expiredTask.add(task)
  }

  /**
    * 判断是否存在可以执行的任务
    */
  def hasTask(): Boolean ={
    return !expiredTask.isEmpty
  }

  /**
    * 获取指定索引的任务
    */
  def getTask(index: Int): Option[T] = {
    if(index < 0){
      throw new IllegalArgumentException("index of slot can not be less than zero.")
    }

    if (index >= expiredTask.size()){
      throw new IllegalArgumentException("index of slot can not be more than the count which exiting in sloat")
    }

    if(hasTask()){
      val task = expiredTask.get(index)
      Some(task)
    }else{
      None
    }
  }

  def removeTask(abstractTask: AbstractTask) = {
    expiredTask.remove(abstractTask)
  }
}
