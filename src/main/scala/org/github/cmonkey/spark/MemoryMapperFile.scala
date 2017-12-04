package org.github.cmonkey.spark

import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import java.util

object MemoryMapperFile extends App{

  /*
  val count = 10285760

  val fileName = "/home/cmonkey/.profile"

  val memoryMapperFile = new RandomAccessFile(fileName, "rw").getChannel

  val out = memoryMapperFile.map(FileChannel.MapMode.READ_WRITE, 0, memoryMapperFile.size())

  System.out.println(out.isLoaded)
  System.out.println(out.capacity())


  memoryMapperFile.close()
  */

  val startTime = System.currentTimeMillis()

  val count = 10000000
  val map = new util.HashMap[String, String](count) {}

  for(i <- 0 until count){
    map.put("hi"+i, "v"+i)
  }

  val endTime = System.currentTimeMillis()

  System.out.println("time = " + (endTime - startTime))
}
