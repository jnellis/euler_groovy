/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnellis.util;

import java.math.BigInteger;

/**
 * @author Joe
 */
public class Factorial {

  public static BigInteger fact(long n) {
    if (n < 0) {
      throw new RuntimeException("Trying to take factorial of negative number.");
    }
    BigInteger result = new BigInteger("1");
    while (n > 1) {
      result = result.multiply(BigInteger.valueOf(n));
      n--;
    }
    return result;
  }

  public static long fact(int n) {
    if (n < 0) {
      throw new RuntimeException("Trying to take factorial of negative number.");
    }
    long _n = n;
    long result = 1L;
    while (_n > 0) {
      result *= _n;
      _n--;
    }
    return result;
  }

}
