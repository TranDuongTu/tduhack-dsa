package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ProblemAnnotation(name = "HV line segments", level = 5)
public class HvLineSegments {

  public List<Point> findJointPoint(final List<HvSegment> segments) {
    final List<Event> events = getEvents(segments);
    Collections.sort(events);
    final BST tree = new BST();

    final List<Point> result = new ArrayList<>();
    for (final Event event : events) {
      if (event.segment.isVertical()) {
        final int[] range = event.segment.getRange();
        final List<Integer> joints = tree.findRange(range[0], range[1]);
        for (final Integer y : joints) {
          result.add(new Point(event.point.x, y));
        }
      } else if (event.isHorizontalLeftEvent()) {
        tree.put(event.point.y);
      } else { // Right point
        tree.delete(event.point.y);
      }
    }

    return result;
  }

  private List<Event> getEvents(final List<HvSegment> segments) {
    return segments.stream().flatMap(segment -> {
      final List<Event> events = new ArrayList<>();
      events.add(new Event(segment, segment.p1));
      if (!segment.isVertical()) {
        events.add(new Event(segment, segment.p2));
      }
      return events.stream();
    }).collect(Collectors.toList());
  }

  public static class HvSegment {
    private final Point p1, p2;

    public HvSegment(final Point p1, final Point p2) {
      this.p1 = p1;
      this.p2 = p2;
    }

    public boolean isVertical() {
      return p1.x == p2.x;
    }

    public Point other(final Point point) {
      return point.compareTo(p1) == 0 ? p2 : p1;
    }

    public int[] getRange() {
      if (isVertical()) {
        return new int[]{Math.min(p1.y, p2.y), Math.max(p1.y, p2.y)};
      } else {
        return new int[]{Math.min(p1.x, p2.x), Math.max(p1.x, p2.x)};
      }
    }
  }

  public static class Point implements Comparable<Point> {
    public final int x, y;

    public Point(final int x, final int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Point other) {
      return x == other.x ? y - other.y : x - other.x;
    }

    @Override
    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }

  private static class Event implements Comparable<Event> {
    public final HvSegment segment;
    public final Point point;

    public Event(final HvSegment segment, final Point point) {
      this.segment = segment;
      this.point = point;
    }

    public boolean isHorizontalLeftEvent() {
      return !segment.isVertical() && point.compareTo(segment.other(point)) < 0;
    }

    @Override
    public int compareTo(Event other) {
      if (point.x == other.point.x) {
        return -Boolean.compare(segment.isVertical(), other.segment.isVertical());
      }
      return point.compareTo(other.point);
    }

    @Override
    public String toString() {
      if (segment.isVertical()) {
        return "Vertical - " + point.toString(); 
      } else if (isHorizontalLeftEvent()) {
        return "Left - " + point.toString();
       } else {
        return "Right - " + point.toString();
      }
    }
  }

  private static class BST {
    private Node root;

    public void put(int value) {
      root = put(root, value);
    }

    private Node put(Node node, int value) {
      if (node == null) return new Node(value);
      if (value < node.value) node.left = put(node.left, value);
      else node.right = put(node.right, value);
      return node;
    }

    public void delete(int value) {
      root = delete(root, value);
    }

    private Node delete(Node node, int value) {
      if (node == null) throw new IllegalArgumentException("Cannot find value in tree: " + value);
      if (value < node.value) node.left = delete(node.left, value);
      else if (value > node.value) node.right = delete(node.right, value);
      else {
        if (node.left == null) return node.right;
        if (node.right == null) return node.left;
        final Node t = node;
        node = min(t.right);
        node.right = deleteMin(t.right);
        node.left = t.left;
      }
      return node;
    }

    private Node deleteMin(Node node) {
      if (node.left == null) return node.right;
      node.left = deleteMin(node.left);
      return node;
    }

    private Node min(Node node) {
      if (node == null) return null;
      return node.left == null ? node : min(node.left);
    }

    public List<Integer> findRange(int from, int to) {
      final List<Integer> list = new ArrayList<>();
      collect(root, from, to, list);
      return list;
    }

    private void collect(Node node, int from, int to, List<Integer> list) {
      if (node == null) return;
      if (node.value >= from) collect(node.left, from, to, list);
      if (node.value >= from && node.value <= to) list.add(node.value);
      if (node.value <= to) collect(node.right, from, to, list);
    }

    private static class Node {
      public final Integer value;
      public Node left, right;
      public Node(final int value) {
        this.value = value;
      }
    }
  }
}
