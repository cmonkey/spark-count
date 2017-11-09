package org.github.cmonkey.spark
import java.io.File

object RemoveAll {

  def main(args: Array[String]): Unit = {

    removeAll("www")

  }

  def removeAll(path: String) = {

    def getRecursively(f: java.io.File): Seq[java.io.File] = {

      f.listFiles.filter(_.isDirectory).flatMap(getRecursively) ++ f.listFiles
    }

    getRecursively(new File(path)).foreach{f =>
      println(f)
      if(! f.delete())
        throw new RuntimeException("Failed to delete " + f.getAbsolutePath)
    }

    new File(path).delete

  }

}
