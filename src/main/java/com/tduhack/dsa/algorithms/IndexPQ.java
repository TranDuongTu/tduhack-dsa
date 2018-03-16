package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;

@ProblemAnnotation(name = "Indexed priority Queue", level = 3)
public class IndexPQ {
  
  private int[] queue, inverseQueue, keys;
  private int size;
  
  public IndexPQ(final int maxSize) {
    queue = new int[maxSize];
    inverseQueue = new int[maxSize];
    keys = new int[maxSize];
    for (int i = 0; i < maxSize; i++) {
      queue[i] = inverseQueue[i] = keys[i] = -1;
    }
  }
  
  public void insert(final int i, final int key) {
    queue[size] = i;
    inverseQueue[i] = size;
    keys[i] = key;
    size++;
    swim(inverseQueue[i]);
  }

  private void swim(int i) {
    while (i > 0) {
      final int parent = (i - 1) / 2;
      if (less(i, parent)) {
        swap(i, parent);
        i = parent;
      } else {
        break;
      }
    }
  }

  private void swap(int i, int j) {
    final int tmp = queue[i];
    queue[i] = queue[j];
    queue[j] = tmp;
    inverseQueue[queue[i]] = i;
    inverseQueue[queue[j]] = j;
  }

  private boolean less(int i, int j) {
    return keys[queue[i]] < keys[queue[j]];
  }

  public void increaseKey(final int i, final int key) {
    keys[i] = key;
    sink(inverseQueue[i]);
  }

  private void sink(int i) {
    final int left = i * 2 + 1;
    final int right = left + 1;
    
    int smallest = i;
    if (left < size && less(left, smallest)) {
      smallest = left;
    }
    
    if (right < size && less(right, smallest)) {
      smallest = right;
    }
    
    if (smallest != i) {
      swap(smallest, i);
      sink(smallest);
    }
  }

  public void decreaseKey(final int i, final int key) {
    keys[i] = key;
    swim(inverseQueue[i]);
  }
  
  public boolean contains(final int i) {
    return inverseQueue[i] != -1;
  }
  
  public int pop() {
    final int value = queue[0];
    delete(value);
    return value;
  }
  
  public void delete(final int i) {
    final int index = inverseQueue[i];
    swap(index, size - 1);
    size--;
    sink(index);
    assert inverseQueue[i] == size;
    assert queue[size] == i;
    queue[size] = -1;
    inverseQueue[i] = -1;
    keys[i] = -1;
  }

  public boolean isEmpty() {
    return size == 0;
  }
}
