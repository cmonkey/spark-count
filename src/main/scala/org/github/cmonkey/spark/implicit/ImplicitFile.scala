package org.github.cmonkey.spark.`implicit`
import java.io.{BufferedReader, File, FileReader}

class ImplicitFile(file: File) {

  def lines: Array[String] = {

    val fileReader: FileReader = new FileReader(file)

    val reader = new BufferedReader(fileReader)

    try{

      var lines = Array[String]()

      var line = reader.readLine()

      while(line != null){

        lines = lines :+ line

        line = reader.readLine()
      }

      lines
    }finally {
      fileReader.close()
      reader.close()
    }
  }
}

object ImplicitFileApp extends  App{

  /*
  private val file: ImplicitFile = new ImplicitFile(new File("~/.zsh_history"))

  file.lines foreach println
  */
}
