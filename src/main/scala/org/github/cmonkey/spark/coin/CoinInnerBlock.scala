package org.github.cmonkey.spark.coin

import java.security.MessageDigest

case class CoinInnerBlock(index: Int,
                           timestamp: String,
                           data: String,
                           previousHash: String
                          ) {

  def byte2Hex(bytes: Array[Byte]): String = {

    val stringBuffer = new StringBuffer()

    var temp:String = null

    for(i <- 0 until bytes.length){

      temp = Integer.toHexString(bytes(i) & 0xFF)

      if(temp.length == 1){
        stringBuffer.append("0")
      }

      stringBuffer.append(temp)
    }

    stringBuffer.toString
  }

  def getSHA256(src: String): String = {
    val messageDigest: MessageDigest = MessageDigest.getInstance("SHA-256")

    messageDigest.update(src.getBytes("UTF-8"))

    val encodeString = byte2Hex(messageDigest.digest())

    encodeString

  }

  def hashBack(): String = {

    val src = index + timestamp + data + previousHash

    val sha = getSHA256(src)

    sha
  }

  val hash = hashBack()

  override def toString: String =
    "ScoinInnerBlock [index = " + index + ", timestamp = " + timestamp + ", data = " + data + ", previousHash = " + previousHash
}
