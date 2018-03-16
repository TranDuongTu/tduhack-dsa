package com.tduhack.dsa;

import java.util.List;

public class Utils {
  public static void swap(final int[] array, final int i, final int j) {
    final int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
  
  public static void swap(final List<Integer> numbers, final int i, final int j) {
    final int tmp = numbers.get(i);
    numbers.set(i, numbers.get(j));
    numbers.set(j, tmp);
  }
  
  public static int max(final int[] array) {
    int max = Integer.MIN_VALUE;
    for (final Integer value : array) {
      if (value > max) {
        max = value;
      }
    }
    return max;
  }
  
  public static int[] copyArray(final int[] array) {
    final int[] result = new int[array.length];
    for (int i = 0; i < array.length; i++) result[i] = array[i];
    return result;
  }
}
