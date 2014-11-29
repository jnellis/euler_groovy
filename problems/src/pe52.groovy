/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 10/21/13
 * Time: 4:48 PM
 *
 Permuted multiples
 Problem 52

 It can be seen that the number, 125874, and its double, 251748, contain
 exactly the same digits, but in a different order.

 Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x,
 contain the same digits.

 */

def x = 1
boolean done = false
while (!done) {
  x++
  def digits = (x.toString() as List).sort()
  done = (2..6).every {
    digits == ((it * x).toString() as List).sort()
  }
}
println x