package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

@ProblemAnnotation(name = "Steps", level = 1)
public class Steps {

  public char[][] buildStairs(final int n) {
    final char[][] result = new char[n][];
    buildStairs(n, result);
    return result;
  }

  private void buildStairs(int n, char[][] result) {
    if (n > 0) {
      buildStairs(n - 1, result);
      final char[] stair = new char[n];
      for (int i = 0; i < stair.length; i++) stair[i] = '#';
      result[n - 1] = stair;
    }
  }

  public char[][] buildPyramid(final int n) {
    final int width = 2 * n - 1;
    final char[][] result = new char[n][width];
    final int mid = (width) / 2;
    for (int row = 1; row <= n; row++) {
      for (int col = 0; col < width; col++) result[row - 1][col] = ' ';
      for (int col = 0; col < row; col++) {
        result[row - 1][mid + col] = '#';
        result[row - 1][mid - col] = '#';
      }
    }
    return result;
  }
}
