package com.tduhack.dsa.exercises;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EquilibriumPointTest {

  @Test
  public void testEquilibrium() throws Exception {
    assertThat(EquilibriumPoint.findEquilibrium(new int[]{1, 3, 5, 2, 2})).isEqualTo(2);
    assertThat(EquilibriumPoint.findEquilibrium(new int[]{1})).isEqualTo(0);
    assertThat(EquilibriumPoint.findEquilibrium(new int[]{1, 100, 5, 2, 2})).isEqualTo(-1);
  }
}