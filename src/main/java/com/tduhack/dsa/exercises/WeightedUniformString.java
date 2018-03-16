package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/weighted-uniform-string/problem
 */
@ProblemAnnotation(name = "Weighted uniform string", level = 2)
public class WeightedUniformString {

  public static boolean[] queryUniform(final String string, final int[] queries) {
    final Map<Character, Integer> charMaxLength = createCharMaxLengthMap(string);
    final Set<Character> chars = new HashSet<>();
    for (int i = 0; i < string.length(); i++) chars.add(string.charAt(i));
    
    final boolean[] result = new boolean[queries.length];
    for (int i = 0; i < queries.length; i++) {
      final int w = queries[i];
      final List<Character> uniformChars = getUniformsFromWeight(chars, w);
      result[i] = uniformChars.stream().anyMatch(c -> {
        final int time = w / getWeight(c);
        return charMaxLength.get(c) >= time;
      });
    }
    return result;
  }

  private static Map<Character, Integer> createCharMaxLengthMap(String string) {
    final Map<Character, Integer> result = new HashMap<>();
    for (int i = 0, j = -1; i < string.length(); i++) {
      final char c = string.charAt(i);

      int time;
      if (j == -1 || string.charAt(j) != c) {
        time = 1;
        j = i;
      } else {
        time = i - j + 1;
      }

      if (result.containsKey(c)) {
        result.put(c, Math.max(result.get(c), time));
      } else {
        result.put(c, time);
      }
    }
    return result;
  }

  private static int getWeight(final char c) {
    return c - 'a' + 1;
  }

  private static List<Character> getUniformsFromWeight(final Set<Character> chars, final int w) {
    final List<Character> result = new ArrayList<>();
    for (final Character c : chars) {
      int weight = getWeight(c);
      if (w % weight == 0) {
        result.add(c);
      }
    }
    return result;
  }
}
