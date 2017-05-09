package com.sparkcount;

import java.io.File;

object ReadFiles{

  def main(args: Array[String]): Unit = {

    val filePath = "~/Dropbox/photos"

    val files = new File(filePath)

    val pngfiles = files.listFiles().filter(_.getName.endsWith(".png"))

    println(pngfiles)
  }
}

