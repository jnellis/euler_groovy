/*
Sub-string divisibility
Problem 43

The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

    d2d3d4=406 is divisible by 2
    d3d4d5=063 is divisible by 3
    d4d5d6=635 is divisible by 5
    d5d6d7=357 is divisible by 7
    d6d7d8=572 is divisible by 11
    d7d8d9=728 is divisible by 13
    d8d9d10=289 is divisible by 17

Find the sum of all 0 to 9 pandigital numbers with this property.

 */

def sum = 0
def sieve = net.jnellis.util.Math.sieve(17)
def digits = "0123456789".toList()

digits.permutations().each { pan ->
  def base = 1
  def prime = sieve.nextSetBit(0)
  boolean divisible = true
  while (base < 8 && divisible) {
    def subStr = Integer.valueOf(pan[base..base + 2].join())
    divisible &= ((subStr % prime) == 0)

    prime = sieve.nextSetBit(prime + 1)
    base++
  }
  if (divisible) {
    sum += Long.valueOf(pan.join())
    println "$pan is a pandigital property number"
  }
}
println "their sum is $sum"