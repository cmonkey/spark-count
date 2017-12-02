package org.github.cmonkey.spark

import java.io.RandomAccessFile
import java.nio.channels.FileChannel

object MemoryMapperFile extends App{

  val count = 10285760

  val fileName = ""

  val memoryMapperFile = new RandomAccessFile(fileName, "rw")

  val out = memoryMapperFile.getChannel.map(FileChannel.MapMode.READ_WRITE, 0, count)

  memoryMapperFile.close()

}
