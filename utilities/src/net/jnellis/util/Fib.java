/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.jnellis.util;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Joe
 */
public class Fib {

  private final static Map<Integer, BigInteger> cache = new ConcurrentHashMap<Integer, BigInteger>() {
    {
      put(0, BigInteger.ZERO);
      put(1, BigInteger.ONE);
    }
  };
  private final static AtomicInteger maxTermSeen = new AtomicInteger(1);

  public static BigInteger fib(int term) {
    BigInteger result = null;
    if (cache.containsKey(term)) {
      result = cache.get(term);
    } else {
      // start from largest known term
      int lkn = maxTermSeen.get();
      BigInteger tminus1 = cache.get(lkn);
      BigInteger tminus2 = cache.get(lkn - 1);
      for (int i = lkn + 1; i <= term; i++) {
        result = tminus1.add(tminus2);
        tminus2 = tminus1;
        tminus1 = result;
        cache.put(i, result);
      }
      //update largest term seen
      maxTermSeen.set(term);
    }
    return result;
  }


}
