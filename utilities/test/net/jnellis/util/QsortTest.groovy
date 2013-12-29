package net.jnellis.util

import static net.jnellis.util.Benchmark.time

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 12/17/13
 * Time: 8:05 AM
 * To change this template use File | Settings | File Templates.
 */
class QsortTest extends GroovyTestCase {

  def unsorted = [4, 3, 5, 2, 8, 7, 1, 0, 9, 6]

  void testQsort() {
    def result = Qsort.sort(unsorted)
    assertEquals([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], result)
  }

  void testEmptyQsort() {
    def same = []
    def result = Qsort.sort(same)
    this.assertArrayEquals(same, result)
  }

  void testOneQsort() {
    def result = Qsort.sort([4])
    assertEquals([4], result)
  }

  void testTwoQsort() {
    def result = Qsort.sort([2, 1])
    assertEquals([1, 2], result)
  }

  void testNullQsort() {
    def result = Qsort.sort(null)
    assertEquals(null, result)
  }

  void testLargeSort() {
    def coll = (0..10000000).inject([]) { acc, it -> acc << it }
    Collections.shuffle(coll)
    println coll[0..100]
    def result
    println "Qsort time: " + time {
      result = Qsort.sort(coll)
    }
    def expected
    println "default sort time: " + time {
      expected = coll.sort()
    }
    assertEquals(expected, result)
  }

}
