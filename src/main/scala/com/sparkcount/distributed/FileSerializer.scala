package com.sparkcount.distributed

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

object FileSerializer{

  def writeObjectToFile(obj: Object, file: String) = {
    val fileStream = new FileOutputStream(file)

    val oos = new ObjectOutputStream(fileStream)

    oos.writeObject(obj)
    oos.close()
  }

  def readObjectFromFile(file: String): Object = {
    val fileStream = new FileInputStream(file)
    val ois = new ObjectInputStream(fileStream)

    val obj = ois.readObject()

    ois.close()
    obj
  }
}
