package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

import java.math.BigInteger;

@ProblemAnnotation(name = "Separate numbers", level = 2)
public class SeparateNumbersString {
  
  public static String checkSeparateNumbersString(final String string) {
    if (string.length() == 1) return null;

    int i = 1;
    while (i <= string.length() / 2) {
      final BigInteger smallest = new BigInteger(string.substring(0, i));

      int j = i;
      BigInteger next = smallest.add(BigInteger.ONE);
      while (string.startsWith(next.toString(), j)) {
        j += next.toString().length();
        next = next.add(BigInteger.ONE);
      }

      if (j == string.length()) return smallest.toString();
      i++;
    }
    return null;
  }
}
