package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;
import com.tduhack.dsa.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@ProblemAnnotation(name = "Sort algorithms", level = 4)
public class Sorts {

  public static void bubbleSort(final int[] array) {
    boolean swapped = true;
    int i = 0;
    while (swapped) {
      swapped = false;
      for (int j = i; j < array.length - 1; j++) {
        if (array[j] > array[j + 1]) {
          swapped = true;
          Utils.swap(array, j, j + 1);
        }
      }
    }
  }

  public static void selectionSort(final int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      int smallest = i;
      for (int j = i + 1; j < array.length; j++) {
        if (array[j] < array[smallest]) {
          smallest = j;
        }
      }

      if (smallest != i) {
        Utils.swap(array, i, smallest);
      }
    }
  }

  public static void insertionSort(final int[] array) {
    insertionSortInternal(array, 0, array.length - 1);
  }

  private static void insertionSortInternal(final int[] array, final int from, final int to) {
    for (int i = from + 1; i <= to; i++) {
      final int tmp = array[i];
      int j = i - 1;
      while (j >= 0 && array[j] > tmp) {
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = tmp;
    }
  }

  public static void quickSort(final int[] array) {
    quickSortInternal(array, 0, array.length - 1);
  }

  private static void quickSortInternal(final int[] array, final int from, final int to) {
    if (from < to) {
      final int p = partition(array, from, to);
      quickSortInternal(array, from, p);
      quickSortInternal(array, p + 1, to);
    }
  }

  private static int partition(final int[] array, final int from, final int to) {
    int pivot = array[from];
    int i = from - 1;
    int j = to + 1;
    while (true) {
      do {
        i++;
      } while (array[i] < pivot);

      do {
        j--;
      } while (array[j] > pivot);

      if (i >= j) {
        return j;
      }
      Utils.swap(array, i, j);
    }
  }

  public static void mergeSort(final int[] array) {
    final int[] auxArray = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      auxArray[i] = array[i];
    }
    mergeSortInternal(auxArray, 0, array.length, array);
  }

  private static void mergeSortInternal(final int[] auxArray,
                                        final int left, final int right,
                                        final int[] array) {
    if (right - left < 2) {
      return;
    }

    final int middle = (right + left) / 2;
    mergeSortInternal(array, left, middle, auxArray);
    mergeSortInternal(array, middle, right, auxArray);
    merge(auxArray, left, middle, right, array);
  }

  private static void merge(final int[] auxArray,
                            final int left, final int middle, final int right,
                            final int[] array) {
    for (int k = left, i = left, j = middle; k < right; k++) {
      if (i < middle && (j >= right || auxArray[i] < auxArray[j])) {
        array[k] = auxArray[i];
        i++;
      } else {
        array[k] = auxArray[j];
        j++;
      }
    }
  }

  public static void radixSort(final int[] array, final int base) {
    final int maxValue = Utils.max(array);
    
    for (int i = 0; Math.pow(base, i) <= maxValue; i++) {
      bucketsToList(listToBuckets(array, base, i), array);
    }
  }
  
  private static List<List<Integer>> listToBuckets(final int[] array, final int base, final int iteration) {
    final List<List<Integer>> buckets = new ArrayList<>();
    IntStream.range(0, base).forEach(value -> buckets.add(new ArrayList<>()));
    
    for (final Integer value : array) {
      final int bucket = (value / (int) (Math.pow(base, iteration))) % base;
      buckets.get(bucket).add(value);
    }
    return buckets;
  }
  
  private static void bucketsToList(final List<List<Integer>> buckets, final int[] array) {
    int k = 0;
    for (final List<Integer> bucket : buckets) {
      for (final Integer value : bucket) {
        array[k++] = value;
      }
    }
  }
  
  public static void countSort(final int[] array) {
    final int maxValue = Utils.max(array);
    
    final int[] buckets = new int[maxValue + 1];
    for (final Integer value : array) {
      buckets[value]++;
    }
    
    for (int i = 1; i < buckets.length; i++) {
      buckets[i] += buckets[i - 1];
    }
    
    final int[] copied = Utils.copyArray(array);
    for (int i = copied.length - 1; i >= 0; i--) {
      final int value = copied[i];
      final int pos = --buckets[value];
      array[pos] = value;
    }
  }
  
  public static void heapSort(final int[] array) {
    buildMaxHeap(array);
    
    int heapLength = array.length;
    while (heapLength > 1) {
      Utils.swap(array, 0, heapLength - 1);
      heapify(array, --heapLength, 0);
    }
  }
  
  private static void buildMaxHeap(final int[] array) {
    for (int i = (array.length - 1) / 2; i >= 0; i--) {
      heapify(array, array.length, i);
    }
  }
  
  private static void heapify(final int[] heap, final int length, final int i) {
    final int left = i * 2 + 1;
    final int right = left + 1;
    
    int largest = i;
    if (left < length && heap[left] > heap[largest]) {
      largest = left;
    }
    
    if (right < length && heap[right] > heap[largest]) {
      largest = right;
    }
    
    if (largest != i) {
      Utils.swap(heap, largest, i);
      heapify(heap, length, largest);
    }
  }
}
