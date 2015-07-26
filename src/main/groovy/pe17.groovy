/*
Problem 17
17 May 2002

If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?

NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
*/

def digits = ['', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
def teens = ['ten', 'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']
def tens = ['twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']
def h = 'hundred'

def nums = []
def first100 = []
digits.each { first100 << it }
teens.each { first100 << it }
tens.each { e -> digits.each { d -> first100 << e + d } }
first100.each { nums << it }
digits[1..9].each { e -> first100.each { it -> nums << e + h + (it ? "and" : it) + it } }
nums << "onethousand"
println nums.sum { it.size() }
  