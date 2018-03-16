package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

@ProblemAnnotation(name = "Rotate Array", level = 2)
public class RotateArray {
  public static void rotateRight(final int[][] array) {
    final int n = array.length;
    for (int layer = 0; layer < n / 2; layer++) {
      for (int i = layer; i < n - layer - 1; i++) {
        final int up = array[layer][layer + i];
        final int right = array[layer + i][n - 1 - layer];
        final int down = array[n - 1 - layer][n - 1 - layer - i];
        final int left = array[n - 1 - layer - i][layer];
        array[layer][layer + i] = left;
        array[layer + i][n - 1 - layer] = up;
        array[n - 1 - layer][n - 1 - layer - i] = right;
        array[n - 1 - layer - i][layer] = down;
      }
    }
  }
  
  public static int[][] spiralArray(final int n) {
    final int[][] result = new int[n][n];
    
    int startRow = 0, endRow = n - 1, startCol = 0, endCol = n - 1;
    int count = 1;
    
    while (startRow <= endRow && startCol <= endCol) {
      for (int i = startCol; i <= endCol; i++) result[startRow][i] = count++;
      startRow++;
      for (int i = startRow; i <= endRow; i++) result[i][endCol] = count++;
      endCol--;
      for (int i = endCol; i >= startCol; i--) result[endRow][i] = count++;
      endRow--;
      for (int i = endRow; i >= startRow; i--) result[i][startCol] = count++;
      startCol++;
    }
    
    return result;
  }
}
