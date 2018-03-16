package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

/**
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 */
@ProblemAnnotation(name = "Kadane", level = 2)
public class Kadane {
  
  public static int maximumSubArraySum(final int[] array) {
    int maxValue = Integer.MIN_VALUE, maxSoFar = 0;
    for (int i = 0; i < array.length; i++) {
      maxSoFar += array[i];
      if (maxSoFar > maxValue) {
        maxValue = maxSoFar;
      }
      
      if (maxSoFar < 0) {
        maxSoFar = 0;
      }
    }
    return maxValue;
  }
}
