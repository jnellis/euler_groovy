/*
Problem 27
27 September 2002

Euler published the remarkable quadratic formula:

n� + n + 41

It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41� + 41 + 41 is clearly divisible by 41.

Using computers, the incredible formula  n� ? 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79. The product of the coefficients, ?79 and 1601, is ?126479.

Considering quadratics of the form:

    n� + an + b, where |a| < 1000 and |b| < 1000

    where |n| is the modulus/absolute value of n
    e.g. |11| = 11 and |?4| = 4

Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n, starting with n = 0.
*/

def isPrime = { n ->
  def result = n >= 2
  for (int i = 2; i * i <= n; i++) {
    if (n % i == 0) {
      result = false
      break;
    }
  }
  result
}.memoize()

def A = -999..<1000
def B = -999..<1000
def coA, coB
def maxConsecutivePrimes = 0
def coefficients = 0

A.each { a ->
  B.each { b ->
    def n = 0
    while (isPrime(n * n + a * n + b)) {
      n++
    }

    if (n - 1 > maxConsecutivePrimes) {
      maxConsecutivePrimes = n - 1
      coefficients = a * b
      coA = a
      coB = b
    }
  }
}


println "$maxConsecutivePrimes primes with $coefficients as product of a= $coA and b= $coB."

