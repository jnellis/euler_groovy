package net.jnellis.util;

import java.util.BitSet;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 7/24/13
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Math {

  /**
   * Greatest common divisor
   *
   * @param a
   * @param b
   * @return greatest common divisor for a and b.
   */
  public static long gcd(long a, long b) {
    while (b > 0) {
      long c = a % b;
      a = b;
      b = c;
    }
    return a;
  }

  /**
   * Sieve of Eratosthenes
   *
   * @param ceiling Precompute all primes below this number.
   * @return BitSet with indexes of primes set to true.
   */
  public static BitSet sieve(int ceiling) {
    BitSet sieve = new BitSet(ceiling + 1);
    // set all bits from 2 to ceiling as primes
    sieve.set(2, ceiling + 1);
    // 2 is the first prime number
    int p = 2;
    while (p * p <= ceiling) {
      // clear bit of indexes that are multiples of p
      // but starting from p^2 because previous lower multiples
      // have already been covered.
      int i = p * p;
      while (i <= ceiling) {
        sieve.clear(i);
        i = i + p;
      }
      // returns -1 if no set bit is left to test
      p = sieve.nextSetBit(p + 1);
    }
    return sieve;
  }


}
