package net.jnellis.util;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 12/19/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */

public class Qsort {

  public static <T extends Comparable<T>> T[] sort(T[] array) {

    if (array == null || array.length < 2) {
      return array;
    }
    sort(array, 0, array.length);
    return array;
  }

  private static <T extends Comparable<T>> void sort(T[] array,
                                                     int start,
                                                     int end) {
    if (end - start < 2) {
      return;
    }

    int pivotIndex = start + ((end - start) >> 1);
    T pivot = array[pivotIndex];
    pivotIndex = partition(array, start, pivotIndex, end);

    //System.out.println("pivot: " + pivot + " for " + Arrays.toString(array));

    sort(array, start, pivotIndex);
    sort(array, pivotIndex + 1, end);

  }

  /**
   * In-place partition.
   */
  public static <T extends Comparable<T>> int partition(T[] array,
                                                        int start,
                                                        int pivotIndex,
                                                        int end) {

    int left = start, right = end - 1;
    T pivot = array[pivotIndex];

    // move left and right markers to points where
    // a swap needs to happen.
    while (array[left].compareTo(pivot) < 0) {
      left++;
    }
    while (pivot.compareTo(array[right]) < 0) {
      right--;
    }

    while (left < right) {

      //swap
      T val = array[right];
      array[right] = array[left];
      array[left] = val;

      // make progress by pushing left and/or right indexes toward pivot.
      if (left == pivotIndex) {
        pivotIndex = right;
        left++;
      } else if (right == pivotIndex) {
        pivotIndex = left;
        right--;
      } else {
        left++;
        right--;
      }

      // move left and right markers to points where
      // a swap needs to happen.
      while (array[left].compareTo(pivot) < 0) {
        left++;
      }
      while (pivot.compareTo(array[right]) < 0) {
        right--;
      }

    }

    return pivotIndex;
  }

  private static <T> void swap(T[] array, int left, int right) {
    T val = array[right];
    array[right] = array[left];
    array[left] = val;
  }

}
