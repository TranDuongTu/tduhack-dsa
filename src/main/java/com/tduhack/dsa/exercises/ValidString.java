package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem
 */
@ProblemAnnotation(name = "Valid string", level = 2)
public class ValidString {
  
  public static boolean isValid(final String s) {
    final Map<Character, Integer> frequencies = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      final char c = s.charAt(i);
      if (frequencies.containsKey(c)) {
        frequencies.put(c, frequencies.get(c) + 1);
      } else {
        frequencies.put(c, 1);
      }
    }
    
    final Map<Integer, Integer> frequencyOfFrequency = new HashMap<>();
    for (final Integer frequency : frequencies.values()) {
      if (frequencyOfFrequency.containsKey(frequency)) {
        frequencyOfFrequency.put(frequency, frequencyOfFrequency.get(frequency) + 1);
      } else {
        frequencyOfFrequency.put(frequency, 1);
      }
    }
    
    if (frequencyOfFrequency.size() == 1) {
      return true;
    }
    
    if (frequencyOfFrequency.size() > 2) {
      return false;
    }

    final Integer oneFrequency = frequencyOfFrequency.keySet().stream()
        .filter(f -> frequencyOfFrequency.get(f) == 1).findFirst().orElse(-1);
    if (oneFrequency == -1) {
      return false;
    }
    
    final Integer otherFrequency = frequencyOfFrequency.keySet().stream()
        .filter(f -> !Objects.equals(f, oneFrequency)).findFirst().get();
    return frequencyOfFrequency.size() == 1 || oneFrequency == 1 || oneFrequency - otherFrequency == 1;
  }
}
