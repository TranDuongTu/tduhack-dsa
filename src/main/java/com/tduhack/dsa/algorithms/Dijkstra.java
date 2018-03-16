package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.ArrayList;
import java.util.List;

@ProblemAnnotation(name = "Dijkstra", level = 5)
public class Dijkstra {

  public static int[] shortestDistancesFromSource(final int[][] input) {
    final Graph graph = readGraph(input);
    final int[] distTo = new int[graph.size()];
    final boolean[] marked = new boolean[graph.size()];

    final int source = graph.getSource();
    for (int i = 0; i < distTo.length; i++) distTo[i] = Integer.MAX_VALUE;
    distTo[source] = 0;

    final IndexHeap indexHeap = new IndexHeap(graph.size());
    indexHeap.insert(source, distTo[source]);

    while (!indexHeap.isEmpty()) {
      final int u = indexHeap.pop();
      marked[u] = true;
      for (final Edge edge : graph.adj(u)) {
        final int v = edge.other(u);
        if (!marked[v] && distTo[u] + edge.w < distTo[v]) {
          distTo[v] = distTo[u] + edge.w;
          if (indexHeap.contains(v)) {
            indexHeap.decreaseKey(v, distTo[v]);
          } else {
            indexHeap.insert(v, distTo[v]);
          }
        }
      }
    }

    final int[] result = new int[distTo.length - 1];
    for (int i = 0, k = 0; i < distTo.length; i++) {
      if (i != source) {
        result[k++] = distTo[i] == Integer.MAX_VALUE ? -1 : distTo[i];
      }
    }
    return result;
  }

  private static Graph readGraph(int[][] input) {
    final int N = input[0][0];
    final int M = input[0][1];

    final Graph graph = new Graph(N);
    for (int i = 0; i < M; i++) {
      final int[] edge = input[i + 1];
      final int u = edge[0] - 1;
      final int v = edge[1] - 1;
      final int w = edge[2];
      graph.addEdge(new Edge(u, v, w));
    }
    graph.setSource(input[input.length - 1][0] - 1);
    return graph;
  }

  private static class Graph {
    private List<Edge>[] adjList;
    private int source;

    public Graph(int n) {
      this.adjList = new List[n];
      for (int i = 0; i < n; i++) adjList[i] = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
      adjList[edge.from].add(edge);
      adjList[edge.to].add(edge);
    }

    public void setSource(int source) {
      this.source = source;
    }

    public int size() {
      return adjList.length;
    }

    public int getSource() {
      return source;
    }

    public Iterable<Edge> adj(int u) {
      return adjList[u];
    }
  }

  private static class Edge {
    public final int from, to, w;

    public Edge(final int from, final int to, final int w) {
      this.from = from;
      this.to = to;
      this.w = w;
    }

    public int other(int u) {
      return u == from ? to : from;
    }
  }

  private static class IndexHeap {

    private final int[] queue, inverseQueue, keys;
    private int size;

    public IndexHeap(final int maxSize) {
      queue = new int[maxSize];
      inverseQueue = new int[maxSize];
      keys = new int[maxSize];
      for (int i = 0; i < maxSize; i++) {
        queue[i] = inverseQueue[i] = keys[i] = -1;
      }
    }

    public void insert(int i, int key) {
      keys[i] = key;
      queue[size] = i;
      inverseQueue[i] = size;
      size++;
      swim(size - 1);
    }

    private void swim(int i) {
      while (i > 0) {
        final int parent = (i - 1) / 2;
        if (keys[queue[i]] < keys[queue[parent]]) {
          swap(i, parent);
        }
        i = parent;
      }
    }

    private void swap(int i, int j) {
      final int tmp = queue[i];
      queue[i] = queue[j];
      queue[j] = tmp;
      inverseQueue[queue[i]] = i;
      inverseQueue[queue[j]] = j;
    }

    public boolean isEmpty() {
      return size == 0;

    }

    public int pop() {
      final int min = queue[0];
      swap(0, size - 1);
      size--;
      sink(0);
      assert queue[size] == min;
      assert inverseQueue[min] == size;
      queue[size] = -1;
      inverseQueue[min] = -1;
      return min;
    }

    private void sink(int i) {
      final int left = i * 2 + 1;
      final int right = left + 1;

      int smallest = i;
      if (left < size && keys[queue[left]] < keys[queue[smallest]]) {
        smallest = left;
      }

      if (right < size && keys[queue[right]] < keys[queue[smallest]]) {
        smallest = right;
      }

      if (smallest != i) {
        swap(smallest, i);
        sink(smallest);
      }
    }

    public boolean contains(int i) {
      return inverseQueue[i] != -1;
    }

    public void decreaseKey(int i, int key) {
      keys[i] = key;
      swim(inverseQueue[i]);
    }
  }
}
