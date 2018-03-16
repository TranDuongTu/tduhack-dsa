package com.tduhack.dsa.algorithms;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShortestPathTest {

  @Test
  public void testDistancesFromSourceToAll() throws Exception {
    final int SIZE = 2;
    for (int i = 1; i <= SIZE; i++) {
      final String inputFile = "/ShortestPath/input" + i + ".txt";
      final String outputFile = "/ShortestPath/output" + i + ".txt";
      final InputStream inputStream = ShortestPathTest.class.getResourceAsStream(inputFile);
      final InputStream outputStream = ShortestPathTest.class.getResourceAsStream(outputFile);

      final BufferedReader inputBr = new BufferedReader(new InputStreamReader(inputStream));
      final BufferedReader outputBr = new BufferedReader(new InputStreamReader(outputStream));
      final int T = Integer.valueOf(inputBr.readLine());
      final int[][] expectedOutput = readOutput(T, outputBr);
      try {
        for (int t = 0; t < T; t++) {
          final int[][] input = readInput(inputBr);
          final int[] result = Dijkstra.shortestDistancesFromSource(input);
          Assertions.assertThat(result).hasSameSizeAs(expectedOutput[t]).isEqualTo(expectedOutput[t]);
        }
      } finally {
        inputBr.close();
        outputBr.close();
      }
    }
  }

  private int[][] readInput(final BufferedReader br) throws Exception {
    String[] buff = br.readLine().split(" ");
    final int n = Integer.valueOf(buff[0]);
    final int m = Integer.valueOf(buff[1]);
    final int[][] result = new int[m + 2][];
    result[0] = new int[]{n, m};
    for (int i = 0; i < m; i++) {
      buff = br.readLine().split(" ");
      final int u = Integer.valueOf(buff[0]);
      final int v = Integer.valueOf(buff[1]);
      final int w = Integer.valueOf(buff[2]);
      result[i + 1] = new int[]{u, v, w};
    }
    result[result.length - 1] = new int[]{Integer.valueOf(br.readLine())};
    return result;
  }

  private int[][] readOutput(final int T, final BufferedReader br) throws Exception {
    final int[][] result = new int[T][];
    for (int t = 0; t < T; t++) {
      final String[] buff = br.readLine().split(" ");
      final int[] testcase = new int[buff.length];
      for (int i = 0; i < testcase.length; i++) {
        testcase[i] = Integer.valueOf(buff[i]);
      }
      result[t] = testcase;
    }
    return result;
  }
}
