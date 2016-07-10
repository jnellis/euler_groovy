/**
 * User: Joe Nellis
 * Date: 7/10/2016 
 * Time: 1:34 PM
 *
 *

 A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^100 is almost
 unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits
 in each number is only 1.

 Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?

 */

def sumOfDigits(BigInteger num) {
  num.toString().collect { ch -> ch.toInteger() }.sum();
}

int max = 0

for (BigInteger a = 2; a < 100; a++) {
  // bailout optimizations for a
  if (a.mod(10) == BigInteger.ZERO) {    // powers of ten only yield a sum of one.
    continue
  }
  int tempMax = (2..<100).collect { sumOfDigits(a.pow(it)) }
                         .max();
  max = Math.max(max, tempMax);
}

println max

// cleaned up
println( (2..<100).collect { a ->
  (2..<100).collect { b -> sumOfDigits(new BigInteger(a).pow(b)) }.max()
}.max() )