package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ProblemAnnotation(name = "Union Find", level = 3)
public class UnionFind {
  
  private final int[] ids, sz;
  
  public UnionFind(final int size) {
    ids = new int[size];
    sz = new int[size];
    for (int i = 0; i < ids.length; i++) {
      ids[i] = i;
      sz[i] = 1;
    }
  }
  
  public void unite(final int i, final int j) {
    int iRoot = root(i);
    int jRoot = root(j);
    
    if (sz[iRoot] < sz[jRoot]) {
      ids[iRoot] = jRoot;
      sz[jRoot] += sz[iRoot];
    } else {
      ids[jRoot] = iRoot;
      sz[iRoot] += sz[jRoot];
    }
  }
  
  public boolean find(final int i, final int j) {
    return root(i) == root(j);
  }
  
  private int root(int i) {
    while (ids[i] != i) {
      ids[i] = ids[ids[i]]; // Compression
      i = ids[i];
    }
    return i;
  }
  
  public int count() {
    final Set<Integer> roots = IntStream.range(0, ids.length)
        .map(this::root).boxed().collect(Collectors.toSet());
    return roots.size();
  }
}
