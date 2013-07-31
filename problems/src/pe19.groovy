/*
Problem 19
14 June 2002

You are given the following information, but you may prefer to do some research for yourself.

    1 Jan 1900 was a Monday.
    Thirty days has September,
    April, June and November.
    All the rest have thirty-one,
    Saving February alone,
    Which has twenty-eight, rain or shine.
    And on leap years, twenty-nine.
    A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.

How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
*/


def daysPerMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
def daysOfWeek = ['Su', 'M', 'T', 'W', 'Th', 'F', 'Sa']
def jan11900 = daysOfWeek.indexOf('M')  // 1 Jan 1900 was a Monday.
// 1900 is not divisible by 400 and so not a leap year
// Jan 1st 1901 is what day?
def jan11901 = (jan11900 + daysPerMonth.sum()) % 7

def extraLeapYearDay(year, days) {
  (days == 28 && (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)) ? 1 : 0
}

def sundays = 0
def currentDay = jan11901
(1901..2000).each { year ->
  daysPerMonth.each { days ->
    if (currentDay % 7 == 0) {
      sundays++
    }
    currentDay = (currentDay + days + extraLeapYearDay(year, days)) % 7
  }
}
println "$sundays Sundays between Jan 1, 1901 and Jan 1, 2000"