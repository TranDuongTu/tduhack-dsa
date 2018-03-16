package com.tduhack.dsa.exercises;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FlattenListTest {

  @Test
  public void testFlatten() throws Exception {
    final List<Integer> integers1 = new ArrayList<>();
    integers1.add(1);
    integers1.add(2);
    integers1.add(3);
    final List<Integer> integers2 = new ArrayList<>();
    integers2.add(4);
    integers2.add(5);
    final List<Integer> integers3 = new ArrayList<>();
    integers3.add(6);
    integers3.add(7);
    integers3.add(8);
    
    final List<Object> input1 = new ArrayList<>();
    input1.add(integers1);
    input1.add(new ArrayList<>());
    final List<Object> input1Sub = new ArrayList<>();
    input1Sub.add(integers2);
    input1Sub.add(integers3);
    input1.add(input1Sub);
    Assertions.assertThat(FlattenList.flatten(input1)).hasSize(8)
        .containsExactly(1, 2, 3, 4, 5, 6, 7, 8);
  }
}