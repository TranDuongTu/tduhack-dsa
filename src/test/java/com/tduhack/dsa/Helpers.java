package com.tduhack.dsa;

import org.assertj.core.api.Assertions;

import java.util.Random;

public class Helpers {

  public static boolean isMaxHeap(final int[] array) {
    for (int i = 0; i < array.length; i++) {
      final int leftChild = 2 * i + 1;
      final int rightChild = 2 * i + 2;
      final boolean greaterThanChildren = (leftChild >= array.length || array[i] >= array[leftChild])
          && (rightChild >= array.length || array[i] >= array[rightChild]);
      if (!greaterThanChildren) {
        return false;
      }
    }
    return true;
  }

  public static int[] generate(final int length, final int bound) {
    final int[] result = new int[length];
    final Random random = new Random();
    for (int i = 0; i < result.length; i++) {
      result[i] = random.nextInt(bound);
    }
    return result;
  }

  public static void copyArray(final int[] original, final int[] copy) {
    Assertions.assertThat(original).hasSameSizeAs(copy);
    for (int i = 0; i < original.length; i++) {
      copy[i] = original[i];
    }
  }
}
