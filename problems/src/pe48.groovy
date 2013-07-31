/*
Self powers
Problem 48

The series, 11 + 22 + 33 + ... + 1010 = 10405071317.

Find the last ten digits of the series, 11 + 22 + 33 + ... + 10001000.

 */

println((1..1000).sum { n -> n ** n } % (10 ** 10))
