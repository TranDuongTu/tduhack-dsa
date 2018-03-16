package com.tduhack.dsa.exercises;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class KthSmallestElementTest {

  @Test
  public void testFindingKthSmallest() throws Exception {
    Assertions.assertThat(KthSmallestElement.findSmallest(new int[]{3, 1, 2}, 1)).isEqualTo(1);
    Assertions.assertThat(KthSmallestElement.findSmallest(new int[]{7, 10, 4, 3, 20, 15}, 3)).isEqualTo(7);
    Assertions.assertThat(KthSmallestElement.findSmallest(new int[]{7, 10, 4, 3, 20, 15}, 4)).isEqualTo(10);
  }
}