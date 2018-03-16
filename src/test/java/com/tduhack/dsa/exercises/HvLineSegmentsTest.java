package com.tduhack.dsa.exercises;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.tduhack.dsa.exercises.HvLineSegments.HvSegment;
import static com.tduhack.dsa.exercises.HvLineSegments.Point;
import static org.assertj.core.api.Assertions.assertThat;

public class HvLineSegmentsTest {
  
  private static final String TEST_FOLDER = "/HvLines";
  private static final int NO_TESTS = 1;
  
  @Test
  public void testAllTestcases() throws Exception {
    final HvLineSegments hvLineSegments = new HvLineSegments();
    for (int t = 1; t <= NO_TESTS; t++) {
      final String inputFileName = TEST_FOLDER + "/input" + t + ".txt";
      final InputStream inputStream = HvLineSegmentsTest.class.getResourceAsStream(inputFileName);
      List<HvSegment> segments = readInput(inputStream);
      final List<Point> jointPoints = hvLineSegments.findJointPoint(segments);
      
      final String outputFileName = TEST_FOLDER + "/output" + t + ".txt";
      final InputStream outputStream = HvLineSegmentsTest.class.getResourceAsStream(outputFileName);
      final List<Point> expected = readOutput(outputStream);
      Collections.sort(expected);
      Collections.sort(jointPoints);
      assertThat(jointPoints).hasSameSizeAs(expected);
      assertThat(jointPoints.toString()).isEqualTo(expected.toString());
      System.out.println("Testcase: " + t);
      System.out.println("Actual: " + jointPoints.toString());
      System.out.println("Expected: " + expected.toString());
    }
  }

  private List<Point> readOutput(InputStream outputStream) throws Exception {
    final BufferedReader br = new BufferedReader(new InputStreamReader(outputStream));
    final int size = Integer.valueOf(br.readLine());
    final List<Point> result = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      final String[] buff = br.readLine().split(" ");
      final int x = Integer.valueOf(buff[0]);
      final int y = Integer.valueOf(buff[1]);
      result.add(new Point(x, y));
    }
    br.close();
    return result;
  }

  private List<HvSegment> readInput(InputStream inputStream) throws Exception {
    final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
    final List<HvSegment> result = new ArrayList<>();
    final int size = Integer.valueOf(br.readLine());
    for (int i = 0; i < size; i++) {
      final String[] buff = br.readLine().split(" ");
      final int x1 = Integer.valueOf(buff[0]);
      final int y1 = Integer.valueOf(buff[1]);
      final int x2 = Integer.valueOf(buff[2]);
      final int y2 = Integer.valueOf(buff[3]);
      final Point p1 = new Point(x1, y1);
      final Point p2 = new Point(x2, y2);
      result.add(new HvSegment(p1, p2));
    }
    br.close();
    return result;
  }
}