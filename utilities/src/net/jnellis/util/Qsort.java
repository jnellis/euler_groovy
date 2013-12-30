package net.jnellis.util;

/**
 * Created with IntelliJ IDEA.
 * User: Joe
 * Date: 12/19/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */

public class Qsort {

  public interface PivotChooser {
    <T> int getPivotIndex(T[] array, int start, int end);

    static PivotChooser midpoint = new PivotChooser() {
      public <T> int getPivotIndex(T[] array, int start, int end) {
        return start + ((end - start) >> 1);
      }
    };

  }


  public static <T extends Comparable<T>> T[] sort(T[] array) {

    if (array == null || array.length < 2) {
      return array;
    }
    sort(array, 0, array.length - 1, PivotChooser.midpoint);
    return array;
  }

  private static <T extends Comparable<T>> void sort(T[] array,
                                                     int start,
                                                     int end,
                                                     PivotChooser pivotChooser) {


    int pivotIndex = partition(array, start, pivotChooser, end);

    if (start < pivotIndex - 1) {
      sort(array, start, pivotIndex - 1, pivotChooser);
    }
    if (pivotIndex < end) {
      sort(array, pivotIndex, end, pivotChooser);
    }
  }

  /**
   * In-place partition.
   */
  public static <T extends Comparable<T>> int partition(T[] array,
                                                        int start,
                                                        PivotChooser pivotChooser,
                                                        int end) {

    int left = start, right = end;
    T pivot = array[pivotChooser.getPivotIndex(array, start, end)];

    while (left <= right) {

      // move left and right markers to points where
      // a swap needs to happen.
      while (pivot.compareTo(array[left]) > 0) {
        left++;
      }
      while (pivot.compareTo(array[right]) < 0) {
        right--;
      }

      // make progress by pushing left and/or right indexes toward pivot.
      if (left <= right) {
        //swap
        T val = array[right];
        array[right] = array[left];
        array[left] = val;
        left++;
        right--;
      }
    }


    return left;
  }

  private static <T> void swap(T[] array, int left, int right) {
    T val = array[right];
    array[right] = array[left];
    array[left] = val;
  }

}
