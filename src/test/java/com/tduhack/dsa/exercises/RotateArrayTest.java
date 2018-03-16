package com.tduhack.dsa.exercises;

import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class RotateArrayTest {

  @Test
  public void testRandomRotate() throws Exception {
    final int SIZE = 1000;
    final int[][] array = new int[SIZE][SIZE];
    final int[][] expected = new int[SIZE][SIZE];
    final Random random = new Random();
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length; j++) {
        array[i][j] = random.nextInt(1000);
        expected[i][j] = array[i][j];
      }
    }

    RotateArray.rotateRight(array);
    assertThat(array).isNotEqualTo(expected);
    RotateArray.rotateRight(array);
    assertThat(array).isNotEqualTo(expected);
    RotateArray.rotateRight(array);
    assertThat(array).isNotEqualTo(expected);
    RotateArray.rotateRight(array);
    assertThat(array).isEqualTo(expected);
  }

  @Test
  public void testSimple() throws Exception {
    final int[][] expected = new int[][]{
        {1, 2, 3, 4, 5},
        {6, 7, 8, 9, 10},
        {11, 12, 13, 14, 15},
        {16, 17, 18, 19, 20},
        {21, 22, 23, 24, 25}
    };
    
    final int[][] array = new int[5][5];
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected.length; j++) {
        array[i][j] = expected[i][j];
      }
    }
    RotateArray.rotateRight(array);
    assertThat(array)
        .isEqualTo(new int[][]{
            {21, 16, 11, 6, 1},
            {22, 17, 12, 7, 2},
            {23, 18, 13, 8, 3},
            {24, 19, 14, 9, 4},
            {25, 20, 15, 10, 5}
        });
  }

  @Test
  public void testSpiralArray() throws Exception {
    assertThat(RotateArray.spiralArray(2))
        .isEqualTo(new int[][] {
            {1, 2},
            {4, 3}
        });
    assertThat(RotateArray.spiralArray(3))
        .isEqualTo(new int[][] {
            {1, 2, 3},
            {8, 9, 4},
            {7, 6, 5}
        });

    assertThat(RotateArray.spiralArray(5))
        .isEqualTo(new int[][] {
            {1, 2, 3, 4, 5},
            {16, 17, 18, 19, 6},
            {15, 24, 25, 20, 7},
            {14, 23, 22, 21, 8},
            {13, 12, 11, 10, 9}
        });
  }
}