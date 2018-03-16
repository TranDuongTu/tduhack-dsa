package com.tduhack.dsa.algorithms;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class MstTest {

  private static String TESTCASE_PATH = "/MST/";
  private static String[] TESTCASES = new String[]{"input1.txt", "input2.txt"};
  private static String[] OUTPUTS = new String[]{"output1.txt", "output2.txt"};

  @Test
  public void testKruskal() throws Exception {
    testMST(Kruskal::findMST);
  }

  @Test
  public void testPRIM() throws Exception {
    testMST(PRIM::findMST);
  }

  private void testMST(final Function<List<int[]>, List<int[]>> algorithm) throws Exception {
    for (int i = 0; i < TESTCASES.length; i++) {
      final String input = TESTCASE_PATH + TESTCASES[i];
      final InputStream inputStream = MstTest.class.getResourceAsStream(input);
      final List<int[]> graph = readGraph(inputStream);
      final List<int[]> mst = algorithm.apply(graph);

      final String output = TESTCASE_PATH + OUTPUTS[i];
      InputStream outputStream = MstTest.class.getResourceAsStream(output);
      final int totalWeight = mst.stream().map(edge -> edge[2]).reduce(0, (x, y) -> x + y);
      assertThat(totalWeight).isEqualTo(readOutput(outputStream));
    }
  }

  private List<int[]> readGraph(final InputStream stream) throws Exception {
    final BufferedReader br = new BufferedReader(new InputStreamReader(stream));
    try {
      final List<int[]> graph = new ArrayList<>();
      String[] buff = br.readLine().split(" ");
      final int n = Integer.valueOf(buff[0]);
      final int m = Integer.valueOf(buff[1]);
      graph.add(new int[]{n, m});

      for (int i = 0; i < m; i++) {
        buff = br.readLine().split(" ");
        final int u = Integer.valueOf(buff[0]);
        final int v = Integer.valueOf(buff[1]);
        final int w = Integer.valueOf(buff[2]);
        graph.add(new int[]{u, v, w});
      }

      return graph;
    } finally {
      br.close();
    }
  }
  
  private int readOutput(final InputStream stream) throws Exception {
    final BufferedReader br = new BufferedReader(new InputStreamReader(stream));
    try {
      return Integer.valueOf(br.readLine());
    } finally {
      br.close();
    }
  }
}
