package org.github.cmonkey.spark

import java.util

class MerkleTrees(txList: util.List[String], root: String) {

  def merkle_tree(): Unit = {
    val tempTxList = new util.ArrayList[String];

    txList.forEach(t => tempTxList.add(t))

    val newTxList = getNewTxList(tempTxList)

  }

  def getNewTxList(strings: util.ArrayList[String]):: util.List[String] = {
    val newTxList = new util.ArrayList[String]()

    val index = 0

    newTxList
  }
}
