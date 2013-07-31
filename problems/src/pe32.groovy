/*
 Pandigital products
 Problem 32

 We shall say that an n-digit number is pandigital if it makes use of all
 the digits 1 to n exactly once; for example, the 5-digit number, 15234,
 is 1 through 5 pandigital.

 The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing
  multiplicand, multiplier, and product is 1 through 9 pandigital.

 Find the sum of all products whose multiplicand/multiplier/product identity
 can be written as a 1 through 9 pandigital.
 HINT: Some products can be obtained in more than one way so be sure to only
 include it once in your sum.

 */

def digits = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]

def seenProducts = [] as Set
def sum = 0

digits.permutations().each { numStr ->
  (1..4).each { mtake ->
    def multiplicand = Integer.valueOf(numStr[0..<mtake].join())

    (1..(6 - mtake)).each { ttake ->
      def multiplier = Integer.valueOf(numStr[mtake..<(mtake + ttake)].join())
      def product = Integer.valueOf(numStr[(mtake + ttake)..-1].join())
      if (multiplicand * multiplier == product && seenProducts.add(product)) {
        sum += product
        println "$multiplicand * $multiplier = $product"
      }
    }
  }
}
println "sum of all products is $sum"
