package com.tduhack.dsa.algorithms;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IntervalTreeTest {

  @Test
  public void testFindOverlaps() {
    final IntervalTree intervalTree = new IntervalTree();
    assertThat(intervalTree.isEmpty()).isTrue();
    intervalTree.put(1, 2);
    intervalTree.put(2, 5);
    intervalTree.put(10, 15);
    intervalTree.put(12, 20);
    intervalTree.put(14, 16);
    intervalTree.put(8, 11);
    assertThat(intervalTree.isEmpty()).isFalse();

    List<int[]> overlaps = intervalTree.findOverlaps(1, 6);
    assertThat(overlaps).hasSize(2).contains(new int[]{1, 2}, new int[]{2, 5});
    overlaps = intervalTree.findOverlaps(7, 13);
    assertThat(overlaps).hasSize(3).contains(new int[]{10, 15}, new int[]{12, 20}, new int[]{8, 11});
    overlaps = intervalTree.findOverlaps(12, 20);
    assertThat(overlaps).hasSize(3).contains(new int[]{10, 15}, new int[]{12, 20}, new int[]{14, 16});
  }
}