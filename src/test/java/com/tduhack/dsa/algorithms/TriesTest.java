package com.tduhack.dsa.algorithms;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TriesTest {

  private static final Tries.Alphabet ALPHABET = new Tries.Alphabet() {

    private char[] characters;
    private final Map<Character, Integer> map = new LinkedHashMap<>();
    private static final int MIN_CHAR = 97, MAX_CHAR = 122;

    {
      characters = new char[MAX_CHAR - MIN_CHAR + 1];
      for (int i = MIN_CHAR, index = 0; i <= MAX_CHAR; i++) {
        final char character = (char) i;
        characters[index] = character;
        map.put(character, index);
        index++;
      }
    }

    @Override
    public int R() {
      return map.size();
    }

    @Override
    public int index(char character) {
      return map.get(character);
    }

    @Override
    public char charAt(int index) {
      return characters[index];
    }
  };

  @Test
  public void testInsertAndQuery() throws Exception {
    final Tries<Integer> tries = new Tries<>(ALPHABET);
    assertThat(tries.isEmpty()).isTrue();
    tries.put("abc", 1);
    tries.put("acb", 2);
    tries.put("abcd", 3);
    tries.put("abd", 4);
    assertThat(tries.isEmpty()).isFalse();
    assertThat(tries.contains("abcd")).isTrue();
    assertThat(tries.contains("abce")).isFalse();
    assertThat(tries.contains("abc")).isTrue();
    tries.delete("abc");
    assertThat(tries.contains("abc")).isFalse();
  }

  @Test
  public void testPrefixes() throws Exception {
    final Tries<Integer> tries = new Tries<>(ALPHABET);
    tries.put("a", 1);
    tries.put("ab", 2);
    tries.put("ac", 3);
    tries.put("abc", 4);
    tries.put("abd", 5);
    tries.put("abde", 6);
    tries.put("ace", 1);
    assertThat(tries.longestPrefixOf("abceeee")).isEqualTo("abc");
    assertThat(tries.longestPrefixOf("abdef")).isEqualTo("abde");
    assertThat(tries.keysWithPrefix("abd")).hasSize(2)
        .contains("abd", "abde");
    assertThat(tries.get("abd")).isEqualTo(5);
    assertThat(tries.get("abde")).isEqualTo(6);
    assertThat(tries.keys()).hasSize(7)
        .contains("a", "ab", "ac", "abc", "abd", "abde", "ace");
  }

  @Test
  public void testLongestPrefixes() throws Exception {
    final Tries<Integer> tries = new Tries<>(ALPHABET);
    tries.put("a", 1);
    tries.put("abcdddddef", 2);
    tries.put("abcdddddfe", 1);
    tries.put("acbd", 5);
    assertThat(tries.longestPrefixOf("abcddd")).isEqualTo("a");
    assertThat(tries.longestPrefixOf("abcdddiiiii")).isEqualTo("a");
    assertThat(tries.longestPrefixOf("abcdddddefiiii")).isEqualTo("abcdddddef");
    assertThat(tries.longestPrefixOf("acbiiii")).isEqualTo("a");
    assertThat(tries.longestPrefixOf("acbdiiii")).isEqualTo("acbd");
    assertThat(tries.longestPrefixOf("iii")).isEqualTo("");
  }

  @Test
  public void testPutAndGet() throws Exception {
    final Tries<Integer> tries = new Tries<>(ALPHABET);
    tries.put("a", 1);
    tries.put("abc", 2);
    tries.put("abd", 3);
    assertThat(tries.get("a")).isEqualTo(1);
    assertThat(tries.get("ab")).isNull();
    assertThat(tries.get("abc")).isEqualTo(2);
    assertThat(tries.get("abd")).isEqualTo(3);
    assertThat(tries.get("b")).isNull();
    tries.put("bc", 5);
    assertThat(tries.get("b")).isNull();
    tries.put("b", 6);
    assertThat(tries.get("b")).isEqualTo(6);
  }

  @Test
  public void testSize() throws Exception {
    final Tries<Integer> tries = new Tries<>(ALPHABET);
    tries.put("a", 1);
    tries.put("abcde", 2);
    tries.put("abcdf", 3);
    tries.put("abcdef", 4);
    assertThat(tries.size()).isEqualTo(4);
    tries.delete("a");
    assertThat(tries.size()).isEqualTo(3);
  }
}