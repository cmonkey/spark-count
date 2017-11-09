package org.github.cmonkey.spark.distributed
object TestSerializer{

  def main(args: Array[String]):Unit = {

    val task = new SimpleTask()
    task.run()

    FileSerializer.writeObjectToFile(task, "task.ser")
    ClassManipulator.saveClassFile(task)

    val fileClassLoader = new FileClassLoader()

    val readObjectTask = FileSerializer.readObjectFromFile("task.ser", fileClassLoader).asInstanceOf[Task]
    readObjectTask.run()
  }
}
