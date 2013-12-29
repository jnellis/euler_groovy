package net.jnellis.util

import static net.jnellis.util.Benchmark.time

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 12/25/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
class QsortTest2 extends GroovyTestCase {

  void testNullQsort() {
    Object[] array = null
    def result = Qsort.sort(array)
    assertEquals(null, result)
  }

  void testEmptyQsort() {
    Integer[] array = new Integer[0]
    Integer[] result = Qsort.sort(array)
    assertArrayEquals(array, result)
    assertTrue(result.size() == 0)
  }

  void testOneQsort() {
    Integer[] a = [42]
    def result = Qsort.sort(a)
    assertEquals([42], result)
  }

  void testTwoQsort() {
    def result = Qsort.sort([2, 1] as Integer[])
    assertEquals([1, 2], result)
  }

  void testQsort() {
    Integer[] result = [4, 3, 5, 2, 8, 7, 1, 0, 9, 6]
    println result
    Qsort.sort(result)
    assertEquals([0, 1, 2, 3, 4, 5, 6, 7, 8, 9], result)
  }


  void testAllSame() {
    Integer[] result = [4, 4, 4, 4, 4, 4, 4, 4, 4, 4]
    println result
    Qsort.sort(result)
    assertEquals([4, 4, 4, 4, 4, 4, 4, 4, 4, 4], result)
  }


  void testLargeSort() {
    int sizor = 10000000
    Integer[] result = new int[sizor]
    Integer[] expected = new int[sizor]
    Random rand = new Random();

    (0..<sizor).each { i ->
      int num = rand.nextInt(sizor >> 2)
      result[i] = num
      expected[i] = num
    }

    println "default sort time: " + time {
      Arrays.sort(expected)
    }

    println "Qsort time: " + time {
      Qsort.sort(result)
    }

    result.inject() { prev, num ->
      prev <= num ?: fail("failed to sort.")
      num
    }
  }
}
