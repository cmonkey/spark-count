package org.github.cmonkey.spark

import scala.collection.mutable.ArrayBuffer

/**
  * 缓存行: Cache Line
  * 操作系统内存如下: CPU 有三级缓存， L1, L2, L3, 最后主内存, 越靠近CPU速度越快
  * L1 > L2 > L3 > 主内存
  *
  * CPU缓存(Cache)由很多缓存行(Cache Line )组成, 一个缓存行占64字节
  * java 的long 类型是8个字节, 则一个缓存行能够容纳8个long 类型的数据
  * CPU 从主内存中拉取数据的时候，相邻的数据会优先放入缓存行
  * long 数组中的一个数据， 这其他7个数据也会被放入同一个缓存行
  * 所以读取同一个缓存行的数据, 效率高
  */
object CacheLine extends App{

  val row = 1000000

  val longArr = Array.ofDim[Long](row, 8)

  var marked = System.currentTimeMillis()

  for(i <- 0 until row){
    for(j <- 0 until 8){
      longArr(i)(j) = i + j
    }
  }

  println(s"loop add data time = ${System.currentTimeMillis() - marked}")

  marked = System.currentTimeMillis()

  var sum = 0L

  for(i <- 0 until row){
    for(j <-0 until 8){
      sum += longArr(i)(j)
    }
  }

  println(s"loop read data in cache line time = ${System.currentTimeMillis() - marked}")

  println(sum)

  marked = System.currentTimeMillis()

  sum = 0L

  for(i <- 0 until 8){
    for(j <- 0 until row){
      sum += longArr(j)(i)
    }
  }

  println(s"loop read data not in cache line time = ${System.currentTimeMillis() - marked}")

  println(sum)

}
