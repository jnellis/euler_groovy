/*
 Digit canceling fractions
 Problem 33

 The fraction 49/98 is a curious fraction, as an inexperienced mathematician in
  attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is
  correct, is obtained by cancelling the 9s.

 We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

 There are exactly four non-trivial examples of this type of fraction, less
 than one in value, and containing two digits in the numerator and denominator.

 If the product of these four fractions is given in its lowest common terms,
 find the value of the denominator.

 */

import net.jnellis.util.Math

def productNum = 1
def productDenom = 1

// start beyond a first trivial case 10/Y and end before num/denom is 1, 98/99
(11..98).each { num ->
  // ensure num/denom < 1
  ((num + 1)..99).each { denom ->
    def n1 = (int) (num / 10)
    def n2 = num % 10
    def d1 = (int) (denom / 10)
    def d2 = denom % 10
    // only check first digit of denom equal to last digit of num.
    // any other case implies trivial case or num/denom > 1.
    if (d1 == n2 && d2 != 0) {
      def q = num / denom
      if (n1 / d2 == q) {
        println "$num / $denom"
        productNum *= n1
        productDenom *= d2
      }
    }
  }
}

def gcd = Math.gcd(productNum, productDenom)
println "gcd is $gcd"
println "$productNum / $productDenom = ${productNum / gcd} / ${productDenom / gcd}"