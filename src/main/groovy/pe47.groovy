/*
Distinct primes factors
Problem 47

The first two consecutive numbers to have two distinct prime factors are:

14 = 2 × 7
15 = 3 × 5

The first three consecutive numbers to have three distinct prime factors are:

644 = 2² × 7 × 23
645 = 3 × 5 × 43
646 = 2 × 17 × 19.

Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?

*/

def four = 4
def success = 0
def sieve = net.jnellis.util.Math.sieve(1000000)
def num = 643
def done = false
while (!done) {
  def prime = sieve.previousSetBit(num)
  def factored = []
  int numb = num
  while (prime != -1) {
    if (numb % prime == 0) {
      factored << prime
      numb = numb / prime
    }
    prime = sieve.previousSetBit(prime - 1)
  }
  if (factored.unique().size() == four) {
    success++
  } else {
    success = 0
  }
  if (success == four) {
    done = true
    println "last consecutive number is ${num}, with distinct factors $factored"
    println " the first is ${num - 3}"
  }
  num++
}


