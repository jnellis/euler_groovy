/*
Problem 35
17 January 2003

The number, 197, is called a circular prime because all rotations of the
digits: 197, 971, and 719, are themselves prime.

There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71,
 73, 79, and 97.

How many circular primes are there below one million?
*/

import net.jnellis.util.Math

def limit = 1000000
def sieve = Math.sieve(limit)


def count = 0
def num = sieve.nextSetBit(0)
while (num != -1) {

  def original = num
  def numList = num.toString().toList()
  if (!numList.contains(["0", "2", "4", "6", "8"])) {
    def rotations = []
    def size = java.lang.Math.ceil(java.lang.Math.log10(num))
    def circular = (1..size).every {
      numList = numList.tail() + numList.head()
      num = Integer.valueOf(numList.join())
      rotations << num
      sieve.get(num)
    }

    if (circular) {
      println "$num  is circular in $rotations"
      count++
    }
  }
  num = sieve.nextSetBit(original + 1)


}
println "total circular primes below $limit is $count"
