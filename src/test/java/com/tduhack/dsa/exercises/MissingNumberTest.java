package com.tduhack.dsa.exercises;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class MissingNumberTest {

  @Test
  public void testCorrectness() throws Exception {
    assertThat(MissingNumber.findMissingNumber(Arrays.asList(0, 2, 3))).isEqualTo(1);
    assertThat(MissingNumber.findMissingNumber(Arrays.asList(0, 2, 3, 1, 5))).isEqualTo(4);
    assertThat(MissingNumber.findMissingNumber(Arrays.asList(0, 2, 4, 7, 8, 3, 1, 5))).isEqualTo(6);
    assertThat(MissingNumber.findMissingNumber(Arrays.asList(6, 5, 8, 0, 1, 4, 2, 9, 7))).isEqualTo(3);
    assertThat(MissingNumber.findMissingNumber(Arrays.asList(1, 2, 4, 6, 5, 9, 3, 8, 0))).isEqualTo(7);
    assertThat(MissingNumber.findMissingNumber(Arrays.asList(1, 0, 5, 3, 6, 4, 2, 9, 7))).isEqualTo(8);
  }

  @Test
  public void testPerformance() throws Exception {
    testMissingFunction(MissingNumber::findMissingNumber);
  }

  @Test
  public void testPerformanceLinear() throws Exception {
    testMissingFunction(MissingNumber::findMissingNumberLinear);
  }

  private void testMissingFunction(final Function<List<Integer>, Integer> function) {
    final int SIZE = 100000;
    final List<Integer> numbers = new ArrayList<>();
    final int missing = new Random().nextInt(SIZE);
    for (int i = 0; i < SIZE; i++) {
      if (i != missing) {
        numbers.add(i);
      }
    }
    Collections.shuffle(numbers);
    assertThat(function.apply(numbers)).isEqualTo(missing);
  }
}