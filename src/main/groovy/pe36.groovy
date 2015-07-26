/*
Double-base palindromes
Problem 36

The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.

Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

(Please note that the palindromic number, in either base, may not include leading zeros.)

 */

def sum = 0
(1..<1000000).step(2) { n ->
  def decimalString = n.toString()
  def binaryString = Integer.toBinaryString(n)
  if (binaryString == binaryString.reverse() &&
      decimalString == decimalString.reverse()) {
    sum += n
    //println "palindromic number $n is palindromic in binary $binaryString"
  }

}
println "The sum of all palindromic numbers in base2 and base10 is $sum"