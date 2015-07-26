/*
Pandigital prime
Problem 41

We shall say that an n-digit number is pandigital if it makes use of all the
digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and
is also prime.

What is the largest n-digit pandigital prime that exists?

*/

import static net.jnellis.util.Math.sieve

def ceiling = 987654321
def sieve = sieve(ceiling)
def digits = "123456789".toList()
def max = 0

def prime = sieve.previousSetBit(ceiling)

while (prime != -1) {
  def primeStr = prime.toString().toList()
  if (primeStr.containsAll(digits[0..<primeStr.size()])) {
    max = [prime, max].max()
  }
  prime = sieve.previousSetBit(prime - 1)
}

println "largest pandigital prime is $max"