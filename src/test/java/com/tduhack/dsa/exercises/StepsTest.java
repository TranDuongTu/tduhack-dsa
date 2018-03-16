package com.tduhack.dsa.exercises;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StepsTest {

  @Test
  public void testBuildStairs() throws Exception {
    final Steps steps = new Steps();
    assertThat(steps.buildStairs(3))
        .isEqualTo(new char[][]{
            {'#'},
            {'#', '#'},
            {'#', '#', '#'},
        });

    assertThat(steps.buildStairs(5))
        .isEqualTo(new char[][]{
            {'#'},
            {'#', '#'},
            {'#', '#', '#'},
            {'#', '#', '#', '#'},
            {'#', '#', '#', '#', '#'},
        });
  }

  @Test
  public void testBuildPyramid() throws Exception {
    final Steps steps = new Steps();
    assertThat(steps.buildPyramid(2))
        .isEqualTo(new char[][]{
            {' ', '#', ' '},
            {'#', '#', '#'},
        });

    assertThat(steps.buildPyramid(3))
        .isEqualTo(new char[][]{
            {' ', ' ', '#', ' ', ' '},
            {' ', '#', '#', '#', ' '},
            {'#', '#', '#', '#', '#'},
        });

    assertThat(steps.buildPyramid(5))
        .isEqualTo(new char[][]{
            {' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', '#', '#', '#', ' ', ' ', ' '},
            {' ', ' ', '#', '#', '#', '#', '#', ' ', ' '},
            {' ', '#', '#', '#', '#', '#', '#', '#', ' '},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#'},
        });
  }
}