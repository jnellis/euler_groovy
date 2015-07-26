/*
Champernowne's constant
Problem 40

An irrational decimal fraction is created by concatenating the positive integers:

0.123456789101112131415161718192021...

It can be seen that the 12th digit of the fractional part is 1.

If dn represents the nth digit of the fractional part, find the value of the following expression.

d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000

 */

int i = 1
def idf = new StringBuffer(".")
while (idf.size() <= 1000001) {
  idf.append(i++)
}
println idf
def expr = (0..6).inject(1) { acc, e ->
  acc *= Integer.valueOf(idf[10 ** e])
}

println "d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000 = $expr"
