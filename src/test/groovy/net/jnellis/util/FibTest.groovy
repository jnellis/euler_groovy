package net.jnellis.util

import junit.framework.TestCase

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 7/25/13
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
class FibTest extends TestCase {
  void testFib() {
    assertEquals(1, Fib.fib(1))
    assertEquals(5, Fib.fib(5))
    assertEquals(6765, Fib.fib(20))
    assertEquals(new BigInteger(1100087778366101931), Fib.fib(88))
    assertEquals(new BigInteger("43466557686937456435688527675040625802564660517371780402481729089536555417949051890403879840079255169295922593080322634775209689623239873322471161642996440906533187938298969649928516003704476137795166849228875")
        , Fib.fib(1000))
  }
}
