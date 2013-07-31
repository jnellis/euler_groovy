/*
Integer right triangles
Problem 39

If p is the perimeter of a right angle triangle with integral length sides,
{a,b,c}, there are exactly three solutions for p = 120.

{20,48,52}, {24,45,51}, {30,40,50}

For which value of p ≤ 1000, is the number of solutions maximised?

*/

def maxSolutions = 0
def maxp = 1
(11..1000).each { p ->
  def c = 1
  def solutions = 0
  (1..<p / 2 - c).each { a ->
    (a..<(p - a - 2)).each { b ->
      c = (p - a) - b
      assert c >= 1
      if ((a * a) + (b * b) == (c * c)) {
        solutions++
      }
    }
  }
  if (solutions > maxSolutions) {
    maxSolutions = solutions
    maxp = p
  }
}
println "p = $maxp max solutions: $maxSolutions"