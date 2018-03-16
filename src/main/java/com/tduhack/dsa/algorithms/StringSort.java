package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.Collections;
import java.util.List;

@ProblemAnnotation(name = "String Sorts", level = 5)
public class StringSort {

  public static void quickSort(final List<String> strings) {
    quickSort(strings, 0, strings.size() - 1);
  }

  private static void quickSort(List<String> strings, int from, int to) {
    if (from < to) {
      final int pivot = partition(strings, from, to);
      quickSort(strings, from, pivot);
      quickSort(strings, pivot + 1, to);
    }
  }

  private static int partition(List<String> strings, int from, int to) {
    final String pivot = strings.get(from);
    int i = from - 1, j = to + 1;

    while (true) {
      do {
        i++;
      } while (strings.get(i).compareTo(pivot) < 0);

      do {
        j--;
      } while (strings.get(j).compareTo(pivot) > 0);

      if (i >= j) {
        return j;
      }

      Collections.swap(strings, i, j);
    }
  }

  public static void msdSort(final List<String> strings, final int radix) {
    final String[] aux = new String[strings.size()];
    msdSort(strings, aux, 0, strings.size() - 1, radix, 0);
  }

  private static void msdSort(final List<String> strings, final String[] aux,
                              final int from, final int to,
                              final int radix, final int dth) {
    if (from >= to) {
      return;
    }
    
    final int[] count = new int[radix + 2];
    for (int i = from; i <= to; i++) {
      int index = charAt(strings.get(i), dth) + 2;
      count[index]++;
    }

    for (int r = 0; r < radix + 1; r++) {
      count[r + 1] += count[r];
    }

    for (int i = from; i <= to; i++) {
      aux[count[charAt(strings.get(i), dth) + 1]++] = strings.get(i);
    }

    for (int i = from; i <= to; i++) {
      strings.set(i, aux[i - from]);
    }

    for (int r = 0; r < radix; r++) {
      msdSort(strings, aux, from + count[r], from + count[r + 1] - 1, radix, dth + 1);
    }
  }

  private static int charAt(String s, int d) {
    return d < s.length() ? s.charAt(d) : -1;
  }
}
