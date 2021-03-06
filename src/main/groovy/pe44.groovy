/*
Pentagon numbers
Problem 44

Pentagonal numbers are generated by the formula, Pn=n(3n−1)/2. The first ten
pentagonal numbers are:

1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...

It can be seen that P4 + P7 = 22 + 70 = 92 = P8. However, their difference,
70 − 22 = 48, is not pentagonal.

Find the pair of pentagonal numbers, Pj and Pk, for which their sum and
difference are pentagonal and D = |Pk − Pj| is minimised; what is the value of D?

 */

def pentagonal = { n ->
  (n * (3 * n - 1)) / 2
}
def invP = { p ->
  def index = (Math.sqrt(24 * p + 1) + 1) / 6
  index - index.trunc() == 0 ? index : 0
}


boolean found = false
(1..10000).each { j ->
  if (!found) {
    def jp = pentagonal(j)
    ((j + 1)..10001).each { k ->
      def kp = pentagonal(k)
      if (invP(kp - jp) != 0 && invP(jp + kp) != 0) {
        found = true
        println "pentagonal numbers $jp and $kp and their difference is ${kp - jp}"
      }
    }
  }
}