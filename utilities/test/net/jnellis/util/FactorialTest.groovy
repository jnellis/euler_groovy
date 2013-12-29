package net.jnellis.util

import junit.framework.TestCase

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 10/21/13
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */
class FactorialTest extends TestCase {

  void testBigIntegerFactorial() {
    assertEquals 120, Factorial.fact(5L)
    assertEquals new BigInteger('25852016738884976640000'), Factorial.fact(23L)
  }
}
