/*
Problem 23
02 August 2002

A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.

Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.

*/

def divisors = {
  def result = [1]
  int high = Math.ceil(Math.sqrt(it))
  if (high * high == it) {
    result << high
  }
  int low = 2
  for (; low < high; low++) {
    if (it % low == 0) {
      result << low << (it / low)
    }
  }
  result
}

def ceil = 28123

BitSet abundants = new BitSet(ceil + 1)
(12..ceil).collect {
  if (divisors(it).sum() > it) {
    abundants.set(it)
  }
}

//println abundants

def isSumOfAbundants = { n ->
  def a = abundants.nextSetBit(0)
  while (a != -1) {
    //println "$n \t $a \t ${n-a}"
    // quick bailouts
    // if the abundant is greater than n
    if (a >= n) {
      return false
    }
    if (abundants[n - a]) {
      return true
    }
    a = abundants.nextSetBit(a + 1)
  }
  return true
}

assert true == isSumOfAbundants(24)
assert true == isSumOfAbundants(30)
assert true == isSumOfAbundants(100)


def results = (0..ceil).findResults { isSumOfAbundants(it) ? null : it }

println results.sum()
  
