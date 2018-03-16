package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.Helpers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SortsTest {

  private static final int SIZE = 50000;
  private static final int MAX_VALUE = 1000;
  private static int[] input;
  private int[] copiedInput;

  @BeforeClass
  public static void createInput() {
    input = Helpers.generate(SIZE, MAX_VALUE);
  }

  @Before
  public void setUp() throws Exception {
    copiedInput = new int[input.length];
    Helpers.copyArray(input, copiedInput);
  }

  @Test
  public void testBubbleSort() throws Exception {
    Sorts.bubbleSort(copiedInput);
    assertThat(copiedInput).isSorted();
  }

  @Test
  public void testSelectionSort() throws Exception {
    Sorts.selectionSort(copiedInput);
    assertThat(copiedInput).isSorted();
  }

  @Test
  public void testInsertionSort() throws Exception {
    Sorts.insertionSort(copiedInput);
    assertThat(copiedInput).isSorted();
  }

  @Test
  public void testQuicksort() throws Exception {
    Sorts.quickSort(copiedInput);
    assertThat(copiedInput).isSorted();
  }

  @Test
  public void testMergeSort() throws Exception {
    Sorts.mergeSort(copiedInput);
    assertThat(copiedInput).isSorted();
  }

  @Test
  public void testRadixSort() throws Exception {
    Sorts.radixSort(copiedInput, 10);
    assertThat(copiedInput).isSorted();
  }

  @Test
  public void testCountSort() throws Exception {
    Sorts.countSort(copiedInput);
    assertThat(copiedInput).isSorted();
  }

  @Test
  public void testHeapSort() throws Exception {
    Sorts.heapSort(copiedInput);
    assertThat(copiedInput).isSorted();
  }
}
