package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ProblemAnnotation(name = "Kruskal Minimum Spanning Tree", level = 5)
public class Kruskal {

  public static List<int[]> findMST(final List<int[]> input) {
    final Graph graph = readGraph(input);
    final List<Edge> edges = graph.edges();
    edges.sort(Comparator.comparingInt(e -> e.weight));

    final DisjointSet disjointSet = new DisjointSet(graph.size());
    final List<int[]> result = new ArrayList<>();
    for (final Edge edge : edges) {
      final int from = edge.from;
      final int to = edge.to;
      if (!disjointSet.find(from, to)) {
        result.add(new int[]{from, to, edge.weight});
        disjointSet.unite(from, to);
      }
      
      if (disjointSet.size() == 1) {
        break;
      }
    }
    return result;
  }

  private static Graph readGraph(List<int[]> input) {
    final int n = input.get(0)[0];
    final int m = input.get(0)[1];
    final Graph graph = new Graph(n);

    for (int i = 0; i < m; i++) {
      final int[] edge = input.get(i + 1);
      graph.addEdge(new Edge(edge[0] - 1, edge[1] - 1, edge[2]));
    }

    return graph;
  }

  private static class Graph {

    private final List<Edge> edges = new ArrayList<>();
    private final int n;

    public Graph(final int n) {
      this.n = n;
    }

    public List<Edge> edges() {
      return edges;
    }

    public void addEdge(final Edge edge) {
      this.edges.add(edge);
    }

    public int size() {
      return n;
    }
  }

  private static class Edge {

    public final int from;
    public final int to;
    public final int weight;

    public Edge(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

  private static class DisjointSet {
    private final int[] ids;
    private int size;

    public DisjointSet(final int size) {
      this.ids = new int[size];
      for (int i = 0; i < size; i++) {
        ids[i] = i;
      }
      this.size = size;
    }

    public void unite(int u, int v) {
      final int rootU = root(u);
      final int rootV = root(v);
      if (rootU != rootV) {
        ids[rootU] = rootV;
        size--;
      }
    }

    private int root(int u) {
      while (u != ids[u]) {
        ids[u] = ids[ids[u]];
        u = ids[u];
      }
      return u;
    }

    public int size() {
      return size;
    }

    public boolean find(int from, int to) {
      return root(from) == root(to);
    }
  }
}
