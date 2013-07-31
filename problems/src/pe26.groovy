/*
Problem 26
13 September 2002

A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:

    1/2    =     0.5
    1/3    =     0.(3)
    1/4    =     0.25
    1/5    =     0.2
    1/6    =     0.1(6)
    1/7    =     0.(142857)
    1/8    =     0.125
    1/9    =     0.(1)
    1/10    =     0.1

Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.

Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.

*/
def max = 0
def maxIndex = 1

(1..1000).each { d ->
  def starts = []
  def recur = []
  def num = 1
  // when we see the same remainder twice, the remaining
  // divisions will be the same so then here marks the cycle.
  while (!(starts.contains(num) || num == 0)) {
    if (num < d) {
      num *= 10
      if (num < d) {
        recur << 0
      }
    } else {
      def q = (int) (num / d)
      def rem = num % d

      starts << num
      recur << q
      num = rem
    }
  }
  // remove trailing zeros
  while (recur[-1] == 0) {
    recur.pop()
  }

  def startIndex = starts.indexOf(num)
  def match = recur[startIndex..-1]

  if (match.size() > max) {
    max = match.size()
    maxIndex = d
  }
  println "d = $d\tmatch = $match\t of size ${match.size()}"
}

println "max recurring digits is $max when d = $maxIndex"
