package com.tduhack.dsa.exercises;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SeparateNumbersStringTest {

  @Test
  public void testSeparateNumbersString() throws Exception {
    assertThat(SeparateNumbersString.checkSeparateNumbersString("1234567891011121314151617181920")).isEqualTo("1");
    assertThat(SeparateNumbersString.checkSeparateNumbersString("5678910111213141516171819202122")).isEqualTo("5");
    assertThat(SeparateNumbersString.checkSeparateNumbersString("9101112131415161718192021222324")).isEqualTo("9");
    assertThat(SeparateNumbersString.checkSeparateNumbersString("979899100101102103104105106107")).isEqualTo("97");
    assertThat(SeparateNumbersString.checkSeparateNumbersString("998999100010011002100310041005")).isEqualTo("998");
    assertThat(SeparateNumbersString.checkSeparateNumbersString("1234567891011120314151617181920")).isNull();
    assertThat(SeparateNumbersString.checkSeparateNumbersString("5678910111213140516171819202122")).isNull();
    assertThat(SeparateNumbersString.checkSeparateNumbersString("9101112131415160718192021222324")).isNull();
    assertThat(SeparateNumbersString.checkSeparateNumbersString("979899100101102003104105106107")).isNull();
    assertThat(SeparateNumbersString.checkSeparateNumbersString("998999100010011902100310041005")).isNull();
    
  }
}