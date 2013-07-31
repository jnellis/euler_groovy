/*
Problem 28
11 October 2002

Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

21 22 23 24 25
20  7  8  9 10
19  6  1  2 11
18  5  4  3 12
17 16 15 14 13

It can be verified that the sum of the numbers on the diagonals is 101.

What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
*/
int dim = 1001
def spiral = new int[dim * dim]
int x = dim / 2
int y = x
enum CW {
  E(1, 0), S(0, 1), W(-1, 0), N(0, -1);
  def x, y

  public CW(x, y) {
    this.x = x
    this.y = y
  }
}


CW direction = CW.N

(1..(dim * dim)).each { i ->
  spiral[x * dim + y] = i
  //println "x:$x, y:$y = $i in direction $direction \t $spiral"
  direction++ // assume we are turning
  if (spiral[(x + direction.x) * dim + (y + direction.y)] != 0) {
    direction-- // unturn, there is no empty position there.
  }
  x += direction.x
  y += direction.y
}

def sumOfDiagonals = (0..<dim * dim).collect { index ->
  [index % (dim + 1) ? 0 : spiral[index],
      (dim * dim - index) % (dim - 1) ? 0 : spiral[dim * dim - index]]
}.flatten().unique().sum()

println "Sum of diagonals of a $dim by $dim spiral is $sumOfDiagonals"