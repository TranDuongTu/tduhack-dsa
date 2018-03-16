package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;
import com.tduhack.dsa.Utils;

import java.util.List;

/**
 * https://www.geeksforgeeks.org/find-the-missing-number/
 */
@ProblemAnnotation(name = "Missing number", level = 1)
public class MissingNumber {
  
  public static int findMissingNumberLinear(final List<Integer> numbers) {
    final int n = numbers.size() + 2;
    int totalSum = (n - 1) * (n - 2) / 2;
    for (final Integer number : numbers) totalSum -= number;
    return totalSum;
  }

  public static int findMissingNumber(final List<Integer> numbers) {
    return find(numbers, 0, numbers.size() - 1);
  }

  private static int find(List<Integer> numbers, int from, int to) {
    if (from < to) {
      final int pivot = partition(numbers, from, to);
      if (numbers.get(pivot) == pivot) {
        return find(numbers, pivot + 1, to);
      }
      int result = find(numbers, from, pivot - 1);
      return result == -1 ? pivot : result;
    } else if (from == to && numbers.get(from) != from) {
      return from;
    }
    return -1;
  }

  private static int partition(List<Integer> numbers, int from, int to) {
    final int pivot = numbers.get(to);
    int i = from - 1;
    for (int j = from; j < to; j++) {
      if (numbers.get(j) < pivot) {
        i++;
        Utils.swap(numbers, i, j);
      }
    }
    Utils.swap(numbers, i + 1, to);
    return i + 1;
  }
}
