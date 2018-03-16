package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@ProblemAnnotation(name = "Running Median", level = 3)
public class RunningMedian {
  public static int[] findRunningMedians(final int[] numbers) {
    final Queue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    final Queue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o));
    
    final int[] medians = new int[numbers.length];
    for (int i = 0; i < numbers.length; i++) {
      if (maxHeap.isEmpty() || numbers[i] <= maxHeap.peek()) {
        maxHeap.add(numbers[i]);
      } else {
        minHeap.add(numbers[i]);
      }
      if (minHeap.size() > maxHeap.size()) {
        maxHeap.add(minHeap.poll());
      } else if (maxHeap.size() > minHeap.size() + 1) {
        minHeap.add(maxHeap.poll());
      }
      medians[i] = maxHeap.peek();
    }
    return medians;
  }
}
