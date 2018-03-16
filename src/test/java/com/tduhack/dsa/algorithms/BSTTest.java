package com.tduhack.dsa.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BSTTest {

  @Test
  public void testRandom() throws Exception {
    final int SIZE = 100000;
    final List<Integer> randomNumbers = new ArrayList<>();
    IntStream.range(0, SIZE).forEach(randomNumbers::add);
    Collections.shuffle(randomNumbers);
    
    final BST<Integer, Integer> bst = new BST<>();
    randomNumbers.forEach(x -> bst.put(x, x));
    randomNumbers.forEach(x -> {
      assertThat(bst.contains(x)).isTrue();
      assertThat(bst.get(x)).isEqualTo(x);
    });
    
    Collections.shuffle(randomNumbers);
    randomNumbers.forEach(bst::delete);
    assertThat(bst.isEmpty()).isTrue();
    
    randomNumbers.forEach(x -> bst.put(x, x));
    final List<Integer> sortedNumbers = new ArrayList<>();
    while (!bst.isEmpty()) {
      final int min = bst.min();
      bst.delete(min);
      sortedNumbers.add(min);
    }
    assertThat(sortedNumbers).isSorted();
  }
}