package net.jnellis.util

import junit.framework.TestCase

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 12/17/13
 * Time: 8:05 AM
 * To change this template use File | Settings | File Templates.
 */
class QuicksortTest extends TestCase {

  def unsorted = [4, 3, 5, 2, 8, 7, 1, 0, 9, 6] as List

  void testQuicksort() {
    def result = Quicksort.sort(unsorted)
    assertEquals([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], result)
  }

  void testEmptyQuicksort() {
    def result = Quicksort.sort([])
    assertEquals([], result)
  }

  void testOneQuicksort() {
    def result = Quicksort.sort([4])
    assertEquals([4], result)
  }

  void testTwoQuicksort() {
    def result = Quicksort.sort([2, 1])
    assertEquals([1, 2], result)
  }

  void testNullQuicksort() {
    def result = Quicksort.sort(null)
    assertEquals(null, result)
  }

  void testLargeSort() {
    def coll = (0..10000000).inject([]) { acc, it -> acc << it }
    Collections.shuffle(coll)
    println coll[0..100]
    def result
    println "Quicksort time: " + Benchmark.time {
      result = Quicksort.sort(coll)
    }
    def expected
    println "default sort time: " + Benchmark.time {
      expected = coll.sort()
    }
    assertEquals(expected, result)
  }

  void testMedianOfMedians() {
    def data = (0..1000).inject([]) { acc, it -> acc << it }
    Collections.shuffle(data)
    def index = Quicksort.medianOfMedians(data)
    def result = data[index]
    println "index: $index \t value: $result"

    assertTrue(result > 300 & result < 700)
  }
}
