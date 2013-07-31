/*
Prime permutations
Problem 49

The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases
by 3330, is unusual in two ways: (i) each of the three terms are prime, and,
(ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes,
exhibiting this property, but there is one other 4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms in this sequence?

 */

def sieve = net.jnellis.util.Math.sieve(10000)

def prime = sieve.nextSetBit(1000)

while (prime != -1) {
  def perms = []
  // check permutations are prime
  def primeStr = prime.toString().toList()
  if (!primeStr.contains('0')) {      // no three number sequences
    primeStr.permutations().each { p ->
      def val = Integer.valueOf(p.join())
      if (sieve[val]) {
        perms << val
      }
    }
    // check the difference between two of them are the same
    if (perms.size() > 2) {
      perms.sort(true)
      (perms.size() - 1..2).each { last ->
        (last - 1..1).each { mid ->
          def diff = perms[last] - perms[mid]
          (mid - 1..0).each { first ->
            if (perms[mid] - perms[first] == diff && diff == 3330) {
              println "${perms[first]} \t${perms[mid]} \t${perms[last]}"
            }
          }
        }
      }
    }
  }
  prime = sieve.nextSetBit(prime + 1)
}