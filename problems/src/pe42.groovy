/*
Coded triangle numbers
Problem 42

The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1);
so the first ten triangle numbers are:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

By converting each letter in a word to a number corresponding to its
alphabetical position and adding these values we form a word value.
For example, the word value for SKY is 19 + 11 + 25 = 55 = t10. If the word
value is a triangle number then we shall call the word a triangle word.

Using words.txt (right click and 'Save Link/Target As...'), a 16K text file
containing nearly two-thousand common English words, how many are triangle words?

 */

def base = 64 // 'A' character is code point 65

def triangleNum(n) {
  n * (n + 1) / 2.0
}

// inverse of triangleNum
def inv(t) {
  def n = (Math.sqrt(8 * t + 1) - 1) / 2
  def decimal = n - n.trunc()
  decimal == 0 ? n : 0
}


def count = 0

new File("words.txt").splitEachLine(',') { words ->
  words.each { word ->
    word = word.replaceAll('"', '')
    def sum = word.collect { ltr ->
      ltr.codePointAt(0) - base
    }.sum()
    if (inv(sum)) {
      count++
      println "$word is a triangle word at $sum"
    }
  }
}
println "There are $count triangle words."


