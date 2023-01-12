package org.github.cmonkey.spark.test

import org.github.cmonkey.spark.RingBuffer
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class RingBufferSpec extends AnyFlatSpec with Matchers {

    val ringBuffer = new RingBuffer[Int](3)
    it should "should have a fixed-size" in {
      var size = ringBuffer.enqueue(1)
      size should be (1)
      size = ringBuffer.enqueue(2)
      size should be (2)
      size = ringBuffer.enqueue(3)
      size should be (3)
      size = ringBuffer.enqueue(4)
      size should be (3)
    }
    it should  "should be a circular queue" in {

      var elem = ringBuffer.dequeue
      elem should be (2)

      elem = ringBuffer.dequeue
      elem should be (3)

      elem = ringBuffer.dequeue
      elem should be (4)
    }
}
