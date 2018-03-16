package com.tduhack.dsa.exercises;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class WeightedUniformStringTest {

  @Test(timeout = 1000)
  public void testHackerrankTestcases() throws Exception {
    final int NO_TEST = 2;
    for (int t = 1; t <= NO_TEST; t++) {
      final String testFile = "/WeightedUniforms/input" + t + ".txt";
      final InputStream inputStream = WeightedUniformStringTest.class.getResourceAsStream(testFile);
      final BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(inputStream));
      final String string = inputStreamReader.readLine();
      final int Q = Integer.valueOf(inputStreamReader.readLine());
      final int[] queries = new int[Q];
      for (int i = 0; i < Q; i++) queries[i] = Integer.valueOf(inputStreamReader.readLine());
      final boolean[] result = WeightedUniformString.queryUniform(string, queries);
      
      final String outFile = "/WeightedUniforms/output" + t + ".txt";
      final InputStream outputStream = WeightedUniformStringTest.class.getResourceAsStream(outFile);
      final BufferedReader outStreamReader = new BufferedReader(new InputStreamReader(outputStream));
      for (int i = 0; i < Q; i++) {
        final String actual = outStreamReader.readLine();
        assertThat(result[i]).isEqualTo("Yes".equals(actual));
      }
      
      inputStreamReader.close();
      outStreamReader.close();
      inputStream.close();
      outputStream.close();
    }
  }
}