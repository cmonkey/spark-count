package org.github.cmonkey.spark.coin

import java.util.Date

import scala.collection.mutable.ArrayBuffer

object CoinApp extends App{

  val NUM_OF_BLOCKS_10_ADD = 10

  val coinInnerBlockChain = new ArrayBuffer[CoinInnerBlock](NUM_OF_BLOCKS_10_ADD)

  def createGenesisBlock(): CoinInnerBlock = {

    val date = new Date()

    CoinInnerBlock(0, date.toString, "slience is god", "notatail")
  }

  val genesisBlock = createGenesisBlock()

  coinInnerBlockChain += genesisBlock

  var lastBlock: CoinInnerBlock = genesisBlock

  def nextBlock(block: CoinInnerBlock): CoinInnerBlock = {

    val nextIndex = block.index + 1
    val thisData = "Hello, I am the $ coin Block " + nextIndex
    val prevHash = block.hash

    val date = new Date()

    CoinInnerBlock(nextIndex, date.toString, thisData, prevHash)

  }

  for(i <- 1 until NUM_OF_BLOCKS_10_ADD){
    val current = nextBlock(lastBlock)

    coinInnerBlockChain += current

    println(s"current block index = ${current.index} timestamp = ${current.timestamp} data = ${current.data} privousHash = ${current.previousHash}")
    println(s"current hash = ${current.hash}")

    lastBlock = current
  }

}
