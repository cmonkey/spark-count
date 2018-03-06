package org.github.cmonkey.spark

class JumpConsistentHash {
  def consistentHash(input: Int, buckets: Long): Int = {

    if(buckets < 0){
      throw new IllegalArgumentException("buckets must be positive")
    }

    val linearCongruentialGenerator = new LinearCongruentialGenerator(input)

    var candidate = 0
    var next = 0

    while(true){

      next = (Int)(candidate + 1) / linearCongruentialGenerator.nextDouble()

      if(next >= 0 && next < buckets){
        candidate = next
      }else{
        candidate
      }
    }

    candidate

  }
}
