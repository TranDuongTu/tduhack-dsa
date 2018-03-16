package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.ArrayList;
import java.util.List;

@ProblemAnnotation(name = "PRIM Minimum Spanning Tree", level = 5)
public class PRIM {

  public static List<int[]> findMST(final List<int[]> input) {
    final Graph graph = readGraph(input);
    final int graphSize = graph.size();
    final int source = graph.source();
    final IndexHeap indexHeap = new IndexHeap(graphSize);

    final int[] distTo = new int[graphSize];
    final int[] edgeTo = new int[graphSize];
    final boolean[] marked = new boolean[graphSize];
    for (int i = 0; i < distTo.length; i++) distTo[i] = Integer.MAX_VALUE;
    distTo[source] = 0;

    indexHeap.put(source, distTo[source]);
    while (!indexHeap.isEmpty()) {
      final int u = indexHeap.pop();
      marked[u] = true;
      for (final Edge edge : graph.adj(u)) {
        final int v = edge.other(u);
        if (!marked[v] && distTo[v] > edge.weight) {
          distTo[v] = edge.weight;
          edgeTo[v] = u;
          if (indexHeap.contains(v)) {
            indexHeap.decreaseKey(v, distTo[v]);
          } else {
            indexHeap.put(v, distTo[v]);
          }
        }
      }
    }

    final List<int[]> result = new ArrayList<>();
    for (int i = 0; i < distTo.length; i++) {
      if (i != source) {
        result.add(new int[]{edgeTo[i], i, distTo[i]});
      }
    }
    return result;
  }

  private static Graph readGraph(List<int[]> input) {
    final int n = input.get(0)[0];
    final int m = input.get(0)[1];

    final int source = input.get(input.size() - 1)[0] - 1;
    final Graph graph = new Graph(n, source);
    for (int i = 0; i < m; i++) {
      final int[] edge = input.get(i + 1);
      final int u = edge[0] - 1;
      final int v = edge[1] - 1;
      final int w = edge[2];
      graph.addEdge(new Edge(u, v, w));
    }
    return graph;
  }


  private static class Graph {
    private final List<Edge>[] adjList;
    private final int source;

    public Graph(final int n, final int source) {
      adjList = new List[n];
      for (int i = 0; i < n; i++) adjList[i] = new ArrayList<>();
      this.source = source;
    }

    public int size() {
      return adjList.length;
    }

    public int source() {
      return source;
    }

    public List<Edge> adj(int u) {
      return adjList[u];
    }

    public void addEdge(Edge edge) {
      adjList[edge.from].add(edge);
      adjList[edge.to].add(edge);
    }
  }

  private static class IndexHeap {
    private final int[] queue, inverseQueue, keys;
    private int size;

    public IndexHeap(int maxSize) {
      queue = new int[maxSize];
      inverseQueue = new int[maxSize];
      keys = new int[maxSize];
      for (int i = 0; i < maxSize; i++) queue[i] = inverseQueue[i] = keys[i] = -1;
    }

    public void put(int value, int key) {
      queue[size] = value;
      keys[value] = key;
      size++;
      swim(size - 1);
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

    public boolean isEmpty() {
      return size == 0;
    }

    public int pop() {
      final int min = queue[0];
      swap(0, size - 1);
      size--;
      queue[size] = -1;
      inverseQueue[min] = -1;
      keys[min] = -1;
      sink(0);
      return min;
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

    public boolean contains(int value) {
      return inverseQueue[value] != -1;
    }

    public void decreaseKey(int value, int key) {
      keys[value] = key;
      swim(inverseQueue[value]);
    }
  }

  private static class Edge {
    private final int from, to, weight;

    public Edge(final int from, final int to, final int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }

    public int other(int u) {
      return u == from ? to : from;
    }
  }
}
