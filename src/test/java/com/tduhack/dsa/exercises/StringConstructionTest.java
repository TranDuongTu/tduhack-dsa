package com.tduhack.dsa.exercises;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringConstructionTest {

  @Test
  public void testConstructingString() throws Exception {
    assertThat(StringConstruction.constructString("abcufabcfa")).isEqualTo(5);
    assertThat(StringConstruction.constructString("abab")).isEqualTo(2);
  }
}