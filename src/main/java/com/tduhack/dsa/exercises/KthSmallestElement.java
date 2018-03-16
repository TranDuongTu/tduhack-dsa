package com.tduhack.dsa.exercises;


import com.tduhack.dsa.ProblemAnnotation;
import com.tduhack.dsa.Utils;

@ProblemAnnotation(name = "Kth Smallest element", level = 3)
public class KthSmallestElement {
  
  public static int findSmallest(final int[] array, final int k) {
    return quickFind(array, 0, array.length - 1, k - 1);
  }

  private static int quickFind(int[] array, int from, int to, int k) {
    if (from < to) {
      final int p = partition(array, from, to);
      return k <= p ? quickFind(array, from, p, k) : quickFind(array, p + 1, to, k);
    }
    return from == k ? array[from] : -1;
  }

  private static int partition(int[] array, int from, int to) {
    int pivot = array[from], i = from - 1, j = to + 1;
    while (true) {
      do {
        i++;
      } while (array[i] < pivot);
      
      do {
        j--;
      } while (array[j] > pivot);
      
      if (i >= j) {
        return j;
      }
      Utils.swap(array, i, j);
    }
  }
}
