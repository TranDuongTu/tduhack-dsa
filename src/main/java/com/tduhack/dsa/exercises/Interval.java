package com.tduhack.dsa.exercises;

import com.tduhack.dsa.ProblemAnnotation;

@ProblemAnnotation(name = "Interval", level = 1)
public class Interval implements Comparable<Interval> {
  private final int from, to;

  public Interval(final int from, final int to) {
    this.from = from;
    this.to = to;
  }

  public int length() {
    return to - from;
  }

  public boolean isOverlap(final Interval other) {
    return to >= other.from && from <= other.to;
  }

  public Interval intersection(final Interval other) {
    if (isOverlap(other)) {
      return new Interval(Math.max(from, other.from), Math.min(to, other.to));
    }
    return null;
  }

  public Interval union(final Interval other) {
    if (isOverlap(other)) {
      return new Interval(Math.min(from, other.from), Math.max(to, other.to));
    }
    return null;
  }

  @Override
  public int compareTo(Interval other) {
    return from == other.from ? to - other.to : from - other.from;
  }
}
