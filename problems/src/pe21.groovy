/*
Problem 21
05 July 2002

Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

Evaluate the sum of all the amicable numbers under 10000.

*/

def divisors
divisors = {
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

def d = { n ->
  divisors(n).sum()
}

def cache = [:]
def ap = []
(3..10000).each { a ->
  if (!cache[a]) {
    int b = d(a)
    int aa = d(b)
    if (a == aa && a != b) {
      cache[b] = a
      ap << [a, b]
    }
  }
}

println ap.flatten().sum()