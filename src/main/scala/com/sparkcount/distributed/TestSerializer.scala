package com.sparkcount.distributed

object TestSerializer{

  def main(args: Array[String]):Unit = {

    val task = new SimpleTask()
    FileSerializer.writeObjectToFile(task, "task.ser")

    val readObjectTask = FileSerializer.readObjectFromFile("task.ser").asInstanceOf[Task]
    readObjectTask.run()
  }
}
