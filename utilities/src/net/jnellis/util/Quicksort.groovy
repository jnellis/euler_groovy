package net.jnellis.util
/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 12/17/13
 * Time: 7:49 AM
 * To change this template use File | Settings | File Templates.
 */
class Quicksort {
  static List sort(List unsorted) {


    if (unsorted?.size() <= 1) {
      return unsorted
    }
    // peek at list to pick a first pivot point
//    def pivotIndex = choosePivotIndex(unsorted)
    def pivotIndex = unsorted.size() >> 1
    def pivot = unsorted[pivotIndex]
    List left = new ArrayList()
    List right = new ArrayList()
    List result = new ArrayList()

    partition(unsorted, left, pivotIndex, right)

    result.addAll(sort(left))
    result.add(pivot)
    result.addAll(sort(right))
    return result
  }

  static def partition(List list, List left, index, List right) {
    def pivot = list[index]
    [list.subList(0, index), list.subList(index + 1, list.size())].each {
      it.each { num ->
        if (num < pivot) {
          left << num
        } else {
          right << num
        }
      }
    }
  }

  static def choosePivotIndex(List list) {
    list.size() > 25 * 5 ? medianOfMedians(list) : list.size() >> 1
  }

  /**
   * Break sample into five sections, take five samples from each
   * and find median value, then take median of the five medians
   * @param list
   * @return index position in list of median of medians sample
   */
  static def medianOfMedians(List list) {
    def rand = new Random()
    int lsize = list.size()
    int segmentSize = lsize / 5L

    (0..<5).collect { segment -> // find median of sample
      int base = segment * segmentSize
      // get five random values
      def indexes = []
      5.times {
        indexes << rand.nextInt(segmentSize) + base
      }
      // sort the indexes by value and return middle index
      indexes.sort { list[it] }[2]

    }.sort { medianIndex -> //find median of medians
      list[medianIndex]
    }[2]
  }

}
