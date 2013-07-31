/*
Truncatable primes
Problem 37

The number 3797 has an interesting property. Being prime itself, it is
possible to continuously remove digits from left to right, and remain prime
at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left:
3797, 379, 37, and 3.

Find the sum of the only eleven primes that are both truncatable from left to
right and right to left.

NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

 */

def num = 20
def sum = 0
def count = 0
def sieve = net.jnellis.util.Math.sieve(1000000)
while (count < 11) {
  num = sieve.nextSetBit(num + 1)
  def numStr = num.toString().toList()
  // left to right
  def lr = numStr
  def rl = numStr
  def truncatable = (1..<numStr.size()).every {
    lr = lr.tail()
    rl.pop()
    sieve.get(Integer.valueOf(lr.join())) && sieve.get(Integer.valueOf(rl.join()))
  }
  if (truncatable) {
    count++
    sum += num
    println "$num is truncatable"
  }
}
println "sum of truncatables is $sum"
