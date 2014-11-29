/*
Prime digit replacements
Problem 51

By replacing the 1st digit of the 2-digit number *3, it turns out that six of
the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.

By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit
number is the first example having seven primes among the ten generated numbers,
yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993.
Consequently 56003, being the first member of this family, is the smallest
prime with this property.

Find the smallest prime which, by replacing part of the number
(not necessarily adjacent digits) with the same digit, is part of an eight
prime value family.

*/

/* Thoughts
* Iterate through a bit pattern that represents numbers to replace.
* Skip the 'ones' digit since it must be odd to be prime.
* We need 8 prime out of 10 digit changes so 3 failures can prune the search.
*
 */

def sieveSize = 1000000
sieve = net.jnellis.util.Math.sieve(sieveSize)
familySize = 8

def index = 56003
// get prime to change digits on
int candidate = sieve.nextSetBit(index)

while (-1 != candidate) {
  // of 10 digits, we look for at least 8 to be prime so 2 of them can be non-prime
  // therefore check for 0's, 1's, and 2's to disqualify a candidate
  // that doesn't have at least one of them.
  int maxNonPrimes = 10 - familySize
  if ((0..<maxNonPrimes).any {
    int number = candidate / 10
    hasDigit(number, it)
  }) {
    // start replacement in the tens column, ones column is always odd.
    def bitPattern = 0
    def primes = null
    while (primes == null && 1 << (int) Math.ceil(Math.log10(candidate)) > bitPattern) {
      bitPattern += 2
      primes = primesFamily(candidate, bitPattern, familySize)
    }
    if (primes) {
      println "candidate: $candidate\t bitpattern: ${Integer.toBinaryString(bitPattern)} \tprimes are $primes"
    }

  }
  candidate = sieve.nextSetBit(candidate + 1)
}

/*
* Given a bitpattern representing decimal digits to replace,
* computes the base number with zeros inserted and unit number to add
* each time.
* Example computeZeroedAndBase(567789, 0b001100 ) returns [560089,1100]
* so that the sequence can be created [560089,561189,562289,...569989]
 */

def computeZereodAndBase(candidate, bitPattern) {
  int base = 0
  int zeroed = 0
  int column = 0
  int lastDigit = -1
  while (column <= Math.ceil(Math.log10(candidate))) {
    int power = Math.pow(10.0, column)
    int digit = getDigit(candidate, column)
    if (bitPattern & (1 << column)) {
      base += power
      if (lastDigit != -1 && lastDigit != digit) {
        // bitPattern digits are different, there is no solution here.
        return null
      }
      lastDigit = digit
      //println base
    } else {
      //println "$candidate\t$power\t$digit\t$zeroed"
      zeroed += digit * power
    }
    column++
  }
  [zeroed, base]
}

// count primes for this bitPattern replacement
def primesFamily(candidate, bitPattern, familySize) {
  def primes = []
// look for count - primesCounted > 10 - family
  def primesCounted = 0
  def count = 0
  def maxNonPrimes = 10 - familySize

  def zeroAndBase = computeZereodAndBase(candidate, bitPattern)
  if (zeroAndBase != null) {
    def zero = zeroAndBase[0]
    def base = zeroAndBase[1]
    while (count < maxNonPrimes || count - primesCounted <= maxNonPrimes) {
      int testIndex = zero + count * base

      if (sieve.get(testIndex)) {
        primesCounted++
        primes << testIndex
      }
      count++
    }
  }
  primes.size() >= familySize &&
      Math.floor(Math.log10(primes[0])) == Math.floor(Math.log10(primes[primes.size() - 1])) ? primes : null
}

boolean hasDigit(int number, digit) {
  if (number == 0) {
    return false
  }
  if (number % 10 == digit) {
    return true
  }
  number /= 10
  hasDigit(number, digit)
}

def getDigit(number, column) {
  int power = Math.pow(10.0, column)
  int shifted = number / power
  int mask = shifted / 10
  shifted - mask * 10
}
