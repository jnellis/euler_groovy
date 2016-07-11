/**
 * User: Joe Nellis
 * Date: 7/11/2016 
 * Time: 10:56 AM
 *
 *

 Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side
 length 7 is formed.

 37 36 35 34 33 32 31
 38 17 16 15 14 13 30
 39 18  5  4  3 12 29
 40 19  6  1  2 11 28
 41 20  7  8  9 10 27
 42 21 22 23 24 25 26
 43 44 45 46 47 48 49

 It is interesting to note that the odd squares lie along the bottom right diagonal, but what is
 more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is,
 a ratio of 8/13 ≈ 62%.

 If one complete new layer is wrapped around the spiral above, a square spiral with side length 9
 will be formed. If this process is continued, what is the side length of the square spiral for
 which the ratio of primes along both diagonals first falls below 10%?

 */
isPrime = { n ->
  def result = n >= 2
  for (int i = 2; i * i <= n; i++) {
    if (n % i == 0) {
      result = false
      break;
    }
  }
  result
}

primeCount = 3
diagonalCount = 5    // assume we have the first square

sideCount = 2
index = 1  // starting point

nextDiagonals = {
  index += sideCount * 4
  sideCount += 2
  (1..4).each {
    int diagonal = index + (sideCount * it)
    if (isPrime(diagonal)) {
      primeCount++
    }
    diagonalCount++
  }
}

while (primeCount / diagonalCount >= 0.10) {
  println sideCount
  nextDiagonals()
}
println "Side length of spiral square is ${sideCount + 1}"



