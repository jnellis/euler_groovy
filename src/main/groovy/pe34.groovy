/*
Problem 34
03 January 2003

145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

Find the sum of all numbers which are equal to the sum of the factorial of their digits.

Note: as 1! = 1 and 2! = 2 are not sums they are not included.
*/

factorials = [1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880] // 0! to 9!
(3..(factorials[9] * 9)).sum { num ->
  int sum = 0
  int n = num
  while (!(n == 0 || sum > num)) {
    sum += factorials[n % 10]
    n /= 10
  }
  sum == num ? num : 0
}