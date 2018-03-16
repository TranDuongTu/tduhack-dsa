package com.tduhack.dsa.algorithms;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class UnionFindTest {

  @Test
  public void testRandomUniteAndFind() throws Exception {
    final int SIZE = 50000;
    
    final List<Integer> indices = IntStream.range(0, SIZE).boxed().collect(Collectors.toList());
    Collections.shuffle(indices);

    final UnionFind uf1 = new UnionFind(SIZE);
    for (int i = 0; i < indices.size() - 1; i++) {
      uf1.unite(indices.get(i), indices.get(i + 1));
    }
    assertThat(uf1.count()).isEqualTo(1);
    
    final int isolateNumber = indices.remove(0);
    final int inNumber = indices.get(0);
    final UnionFind uf2 = new UnionFind(SIZE);
    for (int i = 0; i < indices.size() - 1; i++) {
      uf2.unite(indices.get(i), indices.get(i + 1));
    }
    assertThat(uf2.count()).isEqualTo(2);
    assertThat(indices.stream().anyMatch(index -> uf2.find(index, isolateNumber))).isFalse();
    assertThat(indices.stream().anyMatch(index -> uf2.find(index, inNumber))).isTrue();
  }
}
