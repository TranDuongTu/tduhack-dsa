package com.tduhack.dsa.algorithms;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSortTest {
  
  // Alphabet ranged from '0' -> 'z'
  private static final List<Integer> ASCII_ALPHABET = IntStream.range(0, 256).boxed().collect(Collectors.toList());
  private static final Random random = new Random();
  
  private static final int SIZE = 10000;
  private static final int MAX_STRING_LENGTH = 100000;
  
  private static List<String> originalStrings, strings;

  @BeforeClass
  public static void createInput() throws Exception {
    originalStrings = new ArrayList<>();
    IntStream.range(0, SIZE).forEach(i -> {
      final int stringLength = random.nextInt(MAX_STRING_LENGTH) + 1;
      originalStrings.add(generateRandomString(stringLength));
    });
  }
  
  private static String generateRandomString(final int size) {
    final char[] chars = new char[size];
    for (int i = 0; i < size; i++) {
      final int randomIndex = random.nextInt(ASCII_ALPHABET.size());
      final int asciiValue = ASCII_ALPHABET.get(randomIndex);
      chars[i] = (char) asciiValue;
    }
    return new String(chars);
  }

  @Before
  public void copyInput() throws Exception {
    strings = new ArrayList<>(originalStrings);
  }

  @Test
  public void testJavaStringsSort() throws Exception {
    Collections.sort(strings);
    assertThat(strings).isSorted();
  }

  @Test
  public void testStringsQuicksort() throws Exception {
    StringSort.quickSort(strings);
    assertThat(strings).isSorted();
  }

  @Test
  public void testMSDStringSort() throws Exception {
    StringSort.msdSort(strings, ASCII_ALPHABET.size());
    assertThat(strings).isSorted();
  }
}