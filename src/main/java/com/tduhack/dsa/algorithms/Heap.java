package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;
import com.tduhack.dsa.Utils;

@ProblemAnnotation(name = "Heap and Heap sort", level = 3)
public class Heap {

  public static void buildMaxHeap(final int[] heap) {
    for (int i = (heap.length - 2) / 2; i >= 0; i--) {
      heapify(heap, heap.length, i);
    }
  }

  private static void heapify(final int[] heap, int length, final int i) {
    final int left = i * 2 + 1;
    final int right = i * 2 + 2;
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


  public static void heapSort(final int[] array) {
    buildMaxHeap(array);
    
    int length = array.length;
    while (length > 1) {
      Utils.swap(array, 0, length - 1);
      heapify(array, length - 1, 0);
      length--;
    }
  }
}
