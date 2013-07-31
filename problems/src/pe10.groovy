/* Problem 10
08 February 2002

The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.*/



def ceil = 2000000
def sieve = new BitSet(ceil)
(2..ceil).each { sieve.set(it) }

def p = 2
BigInteger sum = 0
while (p != -1) {
  sum += p
  ((p + p)..ceil).step(p) { sieve.clear(it) }
  p = sieve.nextSetBit(p + 1)
}

println sum