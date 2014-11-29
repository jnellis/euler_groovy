/*
Pandigital multiples
Problem 38

Take the number 192 and multiply it by each of 1, 2, and 3:

    192 × 1 = 192
    192 × 2 = 384
    192 × 3 = 576

By concatenating each product we get the 1 to 9 pandigital, 192384576.
We will call 192384576 the concatenated product of 192 and (1,2,3)

The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4,
and 5, giving the pandigital, 918273645, which is the concatenated product
of 9 and (1,2,3,4,5).

What is the largest 1 to 9 pandigital 9-digit number that can be formed as the
concatenated product of an integer with (1,2, ... , n) where n > 1?

*/


def digits = "123456789".toList()

def max = 0

(1..100000).each { num ->
  def n = 2
  def pandigital = num.toString()
  while (n <= 5) {
    pandigital += (n * num).toString() // concat each successive product
    // check for nine unique digits
    if (pandigital.size() == 9 && pandigital.toList().containsAll(digits)) {
      max = [max, Integer.valueOf(pandigital)].max()
      println "num = $num and pandigital is $pandigital where highest multiplier is $n"
      n = 5    // short circuit out of here
    }
    n++
  }
}
println "max is $max"