package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

@ProblemAnnotation(name = "Equilibrium Point", level = 1)
public class EquilibriumPoint {
  
  public static int findEquilibrium(final int[] array) {
    int sum = 0;
    for (int i = 0; i < array.length; i++) sum += array[i];
    
    int leftSum = 0;
    for (int i = 0; i < array.length; i++) {
      if (sum - array[i] == leftSum * 2) {
        return i;
      }
      leftSum += array[i];
    }
    return -1;
  }
}
