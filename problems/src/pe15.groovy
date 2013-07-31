/*
Problem 15
19 April 2002

Starting in the top left corner of a 2�2 grid, there are 6 routes (without backtracking) to the bottom right corner.

How many routes are there through a 20�20 grid?

*/

println '''
It takes 40 moves of either down or right to get to from the top left to the bottom right. 
This can be represented as a bit string where 0 is right and 1 is down. Of down moves there 
are only 20 of them so we want a combination of 40 choose 20.

nCk = n!/(k!*(n-k)!
    = 40!/(20!*(40-20)!)
    = 40!/(20!*20!)
    = 137846528820
'''