package com.tduhack.dsa.algorithms;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexPQTest {
  
  private IndexPQ queue;

  @Before
  public void setUp() throws Exception {
    int MAX_SIZE = 100000;
    queue = new IndexPQ(MAX_SIZE);
  }

  @Test
  public void testInsertAndDelete() throws Exception {
    queue.insert(5, 3);
    queue.insert(1, 4);
    queue.insert(0, 5);
    queue.insert(1000, 0);
    queue.insert(10000, 1);
    assertThat(queue.pop()).isEqualTo(1000);
    queue.delete(1);
    assertThat(queue.pop()).isEqualTo(10000);
    assertThat(queue.pop()).isEqualTo(5);
    assertThat(queue.pop()).isEqualTo(0);
  }

  @Test
  public void testContains() throws Exception {
    queue.insert(0, 100);
    queue.insert(1, 200);
    queue.insert(2, 0);
    queue.insert(3, 1);
    assertThat(queue.contains(3)).isTrue();
    queue.delete(3);
    assertThat(queue.contains(3)).isFalse();
    
    assertThat(queue.contains(2)).isTrue();
    assertThat(queue.pop()).isEqualTo(2);
    assertThat(queue.contains(2)).isFalse();
    assertThat(queue.contains(0)).isTrue();
    assertThat(queue.contains(1)).isTrue();
    assertThat(queue.pop()).isEqualTo(0);
    assertThat(queue.pop()).isEqualTo(1);
    assertThat(queue.isEmpty()).isTrue();
  }

  @Test
  public void testIncreaseKey() throws Exception {
    queue.insert(5, 3);
    queue.insert(1, 4);
    queue.insert(0, 5);
    queue.insert(1000, 0);
    queue.insert(10000, 1);
    queue.increaseKey(1000, 100);
    assertThat(queue.pop()).isEqualTo(10000);
    queue.increaseKey(5, 6);
    assertThat(queue.pop()).isEqualTo(1);
    assertThat(queue.pop()).isEqualTo(0);
    assertThat(queue.pop()).isEqualTo(5);
  }

  @Test
  public void testDecreaseKey() throws Exception {
    queue.insert(5, 30);
    queue.insert(1, 40);
    queue.insert(0, 50);
    queue.insert(1000, 0);
    queue.insert(10000, 1);
    queue.decreaseKey(0, 5);
    assertThat(queue.pop()).isEqualTo(1000);
    assertThat(queue.pop()).isEqualTo(10000);
    assertThat(queue.pop()).isEqualTo(0);
    assertThat(queue.pop()).isEqualTo(5);
    assertThat(queue.pop()).isEqualTo(1);
  }
}