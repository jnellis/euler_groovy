/*
Problem 205

Peter has nine four-sided (pyramidal) dice, each with faces numbered 1, 2, 3, 4.
Colin has six six-sided (cubic) dice, each with faces numbered 1, 2, 3, 4, 5, 6.

Peter and Colin roll their dice and compare totals: the highest total wins. The result is a draw if the totals are equal.

What is the probability that Pyramidal Pete beats Cubic Colin? Give your answer rounded to seven decimal places in the form 0.abcdefg

*/

import static net.jnellis.util.Factorial.fact

// n choose k is n!/(k!(n-k)!)

// iterate through each probability for sums of dice rolls
def probP = 0.25
long numP = 9
def dieP = 4
def probC = 0.1666666666666666666666666667
long numC = 6
def distP = []
def binProb = { long n /*trials*/, long k /*successes*/, p ->
  def q = 1 - p
  (fact(n) / (fact(k) * fact(n - k))) * (p ** k) * ((q ** (n - k)))
}



def table = (0..<9).collect { col ->
  (0..<4).collect { row ->
    row + 1
  }
}
println table

def count = 1
def dist =
  (0..<4).collect { val ->
    (0..<9).collect { die ->
      [(0..<36).collect { trial ->
        ((int) (count++ / 4)) %4  +1
    } ]
  }
}

dist.each{
  println it
}
