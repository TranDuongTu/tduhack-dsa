package com.tduhack.dsa.algorithms;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ProblemAnnotation(name = "Interval tree", level = 4)
public class IntervalTree {

  private Node root;

  public boolean isEmpty() {
    return root == null;
  }

  public void put(final int from, final int to) {
    root = put(root, new Interval(from, to));
  }

  private Node put(final Node node, final Interval interval) {
    if (node == null) return new Node(interval);

    if (interval.compareTo(node.value) < 0) {
      node.left = put(node.left, interval);
    } else {
      node.right = put(node.right, interval);
    }

    node.max = max(node);
    return node;
  }

  private int max(final Node node) {
    return node == null ? 0 : Math.max(node.max, Math.max(max(node.left), max(node.right)));
  }

  public List<int[]> findOverlaps(final int from, final int to) {
    final List<Interval> overlaps = new ArrayList<>();
    findOverlaps(root, new Interval(from, to), overlaps);
    return overlaps.stream().map(i -> new int[]{i.from, i.to}).collect(Collectors.toList());
  }

  private boolean shouldGoLeft(final Node node, final Interval interval) {
    return node.left != null && node.left.max >= interval.from;
  }

  private boolean findOverlaps(Node node, Interval interval, final List<Interval> overlaps) {
    boolean result = false;
    if (node == null) return false;
    if (interval.overlap(node.value)) {
      overlaps.add(node.value);
      result = true;
    }

    boolean leftFound = false;
    if (node.left != null && node.left.max >= interval.from)
      leftFound = findOverlaps(node.left, interval, overlaps);
    if (leftFound || node.left == null || node.left.max < interval.from)
      result |= findOverlaps(node.right, interval, overlaps);
    return result || leftFound;
  }

  private static class Interval implements Comparable<Interval> {
    public final int from, to;

    public Interval(final int from, final int to) {
      this.from = from;
      this.to = to;
    }

    public boolean overlap(final Interval other) {
      return to >= other.from && from <= other.to;
    }

    @Override
    public int compareTo(Interval other) {
      return from == other.from ? to - other.to : from - other.from;
    }

    @Override
    public String toString() {
      return "(" + from + ", " + to + ")";
    }
  }

  private static class Node {
    public Interval value;
    public int max;
    public Node left, right;

    public Node(final Interval value) {
      this.value = value;
      this.max = value.to;
    }

    @Override
    public String toString() {
      return value.toString();
    }
  }
}
