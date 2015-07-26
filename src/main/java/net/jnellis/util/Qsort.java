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
    sort(array, 0, array.length - 1);
    return array;
  }

  private static <T extends Comparable<T>> void sort(T[] array,
                                                     int start,
                                                     int end) {
    // The pivot choosing strategy uses three elements to choose a median.
    // Detect when only two values exist and just swap these if necessary.
    if (end - start < 2) {
      if (end - start == 1 && array[end].compareTo(array[start]) < 0) {
        swap(array, start, end);
      }
      return;
    }

    int pivotIndex = partition(array, start, end);

    sort(array, start, pivotIndex - 1);
    sort(array, pivotIndex + 1, end);

  }

  /**
   * In-place partition.
   */
  public static <T extends Comparable<T>> int partition(T[] array,
                                                        int start,
                                                        int end) {

    int left = start, right = end;
    int middle = (left + right) / 2;
    // correct middle and right
    if (array[middle].compareTo(array[right]) > 0) {
      Qsort.swap(array, middle, right);
    }
    // correct left and right
    if (array[left].compareTo(array[right]) > 0) {
      Qsort.swap(array, left, right);
    }
    // correct left and middle
    if (array[left].compareTo(array[middle]) > 0) {
      Qsort.swap(array, left, middle);
    }
    // move pivot out of the way for now.
    // right has been checked already,
    // so store in right - 1
    swap(array, middle, right - 1);
    T pivot = array[right - 1];
    --right;

    while (left < right) {

      // move left and right markers to points where
      // a swap needs to happen.
      while (array[++left].compareTo(pivot) < 0) ;
      while (pivot.compareTo(array[--right]) < 0) ;

      if (left < right) {
        swap(array, left, right);
      }
    }

    // swap pivot back
    swap(array, left, end - 1);
    return left;
  }

  private static <T> void swap(T[] array, int left, int right) {
    T val = array[right];
    array[right] = array[left];
    array[left] = val;
  }

}
