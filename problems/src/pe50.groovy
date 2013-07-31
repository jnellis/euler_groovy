/*
Consecutive prime sum
Problem 50

The prime 41, can be written as the sum of six consecutive primes:
41 = 2 + 3 + 5 + 7 + 11 + 13

This is the longest sum of consecutive primes that adds to a prime below one-hundred.

The longest sum of consecutive primes below one-thousand that adds to a prime,
contains 21 terms, and is equal to 953.

Which prime, below one-million, can be written as the sum of the most consecutive primes?

 */

def sieve = net.jnellis.util.Math.sieve(1000000)

def minConsecutivePrimesNeeded = 21
def primes = []
def prime = 0
while ((prime = sieve.nextSetBit(prime + 1)) != -1) {
  primes << prime
}

def cache = [:] as Map
def sumOfPrimes = { from, to ->
  if (cache.containsKey([from, to])) {
    return cache[[from, to]]
  }
  if (from > to && to < primes.size()) {
    return 0
  }
  def sum = primes[from..to].sum()
  cache[[from, to]] = sum
  sum
}

def mostConsecutives = 0
def whichPrimeHadMost = 0

// count down from highest prime sum
while (primes.size() > minConsecutivePrimesNeeded) {
  def sumPrime = primes.pop()
  int maxLastConsec = primes.indexOf(sieve.nextSetBit((int) (sumPrime / minConsecutivePrimesNeeded * 4)))

  (maxLastConsec..minConsecutivePrimesNeeded).each { lastConsecPrime ->
    def count = minConsecutivePrimesNeeded
    def sum = sumOfPrimes(lastConsecPrime - count, lastConsecPrime)
    while (sum < sumPrime && lastConsecPrime > count) {
      sum += primes[lastConsecPrime - (++count)]
    }
    if (sum == sumPrime && count > minConsecutivePrimesNeeded) {
      def consecutivePrimes = primes[lastConsecPrime - count..lastConsecPrime]
      //println "${consecutivePrimes.size()} consecutive primes ${consecutivePrimes} sums to $sumPrime"
      minConsecutivePrimesNeeded = count
      if (count > mostConsecutives) {
        mostConsecutives = count
        whichPrimeHadMost = sumPrime
      }
    }
  }
  //println sumPrime + " $maxLastConsec"
}

println "$whichPrimeHadMost was the sum of $mostConsecutives consecutive primes."