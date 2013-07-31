/*
Problem 31
22 November 2002

In England the currency is made up of pound, �, and pence, p, and there are eight coins in general circulation:

    1p, 2p, 5p, 10p, 20p, 50p, �1 (100p) and �2 (200p).

It is possible to make �2 in the following way:

    1ף1 + 1�50p + 2�20p + 1�5p + 1�2p + 3�1p

How many different ways can �2 be made using any number of coins?
*/
denoms = [200, 100, 50, 20, 10, 5, 2, 1]
maxes = denoms.collect { 200 / it }
toDepth = denoms.size()

def countWaysToMake(remainder, depth = 0) {
  (0..maxes[depth]).sum { numCoins ->
    def newRem = remainder - denoms[depth] * numCoins
    newRem > 0 && depth + 1 < toDepth ? countWaysToMake(newRem, depth + 1) :
      newRem == 0 ? 1 : 0
  }
}

println countWaysToMake(200)