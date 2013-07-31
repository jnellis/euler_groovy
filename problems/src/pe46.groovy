/*
Goldbach's other conjecture
Problem 46

It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.

9 = 7 + 2×12
15 = 7 + 2×22
21 = 3 + 2×32
25 = 7 + 2×32
27 = 19 + 2×22
33 = 31 + 2×12

It turns out that the conjecture was false.

What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
*/

def sieve = net.jnellis.util.Math.sieve(100000)
def n = 2

def done = false
while (!done) {
  def comp = sieve.nextClearBit(n++)    // look at non-primes
  if (comp % 2) {                        // that are odd
    // find the previous prime to start at
    def prime = sieve.previousSetBit(comp)
    def noSolution = true
    while (prime != -1) {
      def square = (comp - prime) / 2
      def val = Math.sqrt(square)
      // check if it is a whole number,if so then it is a solution, so void the rest
      if (val - val.trunc() == 0) {
        // println "comp is $comp, $comp = $prime + 2 x $val^2"
        noSolution &= false
        break
      }
      prime = sieve.previousSetBit(prime - 1)
    }
    if (noSolution) {
      println "comp is $comp and has no solution"
      done = true
    }
  }

}