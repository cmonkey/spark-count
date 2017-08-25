package com.sparkcount.Puzzle

import java.util

import org.apache.commons.lang3.StringUtils


object Puzzle {

  def permute(str: String): util.LinkedList[String] = {

    if (StringUtils.isBlank(str) || str.length == 1){
      val ret = new util.LinkedList[String]()
      ret.add(str)

      ret
    }else {

      val permutations = new util.LinkedList[String]()

      for (i <- 0 until str.length) {
        val left = "" + str.charAt(i)
        val build = new StringBuilder(str)

        build.deleteCharAt(i)

        val subPermutations = permute(build.toString)

        //subPermutations.forEach((_) -> permutations.add(left+_))

        /*
        for(perm <- subPermutations){
          permutations.add(left + perm)
        }
        */
      }

      permutations
    }
  }

  def main(args: Array[String]): Unit = {

    val permutations = permute("abc")

    System.out.println(String.format("permutations size: %d", Array(permutations.size)))

    /*
    for(perm <- permutations){
    }
    */
  }
}
