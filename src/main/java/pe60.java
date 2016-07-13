import java.math.BigInteger;
import java.util.BitSet;


/**
 * User: Joe Nellis
 * Date: 7/13/2016
 * Time: 11:22 AM
 * <p>
 * <p>
 * <p>
 * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two
 * primes and concatenating them in any order the result will always be prime
 * . For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of
 * these four primes, 792, represents the lowest sum for a set of four primes
 * with this property.
 * <p>
 * Find the lowest sum for a set of five primes for which any two primes
 * concatenate to produce another prime.
 */
public class pe60 {

  static final int CERTAINTY = 10; // 1-1/(2^CERTAINTY) = % probably prime

  public static void main(String[] args) {
    int size = 10000;       // affects number of results
    BitSet sieve = net.jnellis.util.Math.sieve(size);

    // find all prime pairs that concatenate both way to make primes
    int first = 0;

    while (-1 != (first = sieve.nextSetBit(first + 1))) {
      int second = sieve.nextSetBit(first + 1);
      while (-1 != second) {
        if (hasConcatablePrimes(first, second)) {
          int third = sieve.nextSetBit(second + 1);
          while (-1 != third) {
            if (hasConcatablePrimes(second, third) &&
                hasConcatablePrimes(first, third)) {
              int fourth = sieve.nextSetBit(third + 1);
              while (-1 != fourth) {
                if (hasConcatablePrimes(third, fourth) &&
                    hasConcatablePrimes(second, fourth) &&
                    hasConcatablePrimes(first, fourth)) {
                  int fifth = sieve.nextSetBit(fourth + 1);
                  while (-1 != fifth) {
                    if (hasConcatablePrimes(fourth, fifth) &&
                        hasConcatablePrimes(third, fifth) &&
                        hasConcatablePrimes(second, fifth) &&
                        hasConcatablePrimes(first, fifth)) {

                      System.out.println(
                          first + "," + second + "," + third + "," +
                              fourth + "," + fifth + " = " +
                              (first + second + third + fourth + fifth));

                    }
                    fifth = sieve.nextSetBit(fifth + 1);
                  }
                }
                fourth = sieve.nextSetBit(fourth + 1);
              }
            }
            third = sieve.nextSetBit(third + 1);
          }
        }
        second = sieve.nextSetBit(second + 1);
      }
    }
  }

  static boolean hasConcatablePrimes(int first, int second) {
    String p1 = String.valueOf(first);
    String p2 = String.valueOf(second);
    return new BigInteger(p1 + p2).isProbablePrime(CERTAINTY) &&
        new BigInteger(p2 + p1).isProbablePrime(CERTAINTY);
  }
}
