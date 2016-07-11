/**
 * User: Joe Nellis
 * Date: 7/10/2016 
 * Time: 2:33 PM
 *
 *

 It is possible to show that the square root of two can be expressed as an infinite continued
 fraction.

 √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

 By expanding this for the first four iterations, we get:

 1 + 1/2 = 3/2 = 1.5
 1 + 1/(2 + 1/2) = 7/5 = 1.4
 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...

 The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985,
 is the first example where the number of digits in the numerator exceeds the number of digits in
 the denominator.

 In the first one-thousand expansions, how many fractions contain a numerator with more digits
 than denominator?

 */

// compute k + 1/partial(n) where k=2
getPartialSqrtOf2 = { iter ->
  def partial = iter == 0 ? [new BigInteger(2), BigInteger.ONE] : getPartialSqrtOf2(iter - 1)
  int k = 2
  // flip the reciprocal by reversing index access on numerator and denominator
  def denom = partial[0]
  // expand the k by denom and add numer
  def numer = (k * denom) + partial[1]

  return [numer, denom]
}.memoize() // avoids stack overflow when counting up since second call is a lookup

getSqrtOf2 = { iter ->
  def partial = getPartialSqrtOf2(iter)
  // subtract 1 from final calc since partial computes 2 + 1/partial instead of 1 + 1/partial
  return [partial[0] - partial[1], partial[1]]
}

def result = (0..1000).count {
  def fraction = getSqrtOf2(it)
  //println(fraction[0] + "/" + fraction[1] + "\t\t" + fraction[0] / fraction[1])
  fraction[0].toString().length() > fraction[1].toString().length()
}
println "$result iterations had a numerator with more digits than the denominator"