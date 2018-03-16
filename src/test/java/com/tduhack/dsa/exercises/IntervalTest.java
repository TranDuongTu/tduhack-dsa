package com.tduhack.dsa.exercises;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntervalTest {

  @Test
  public void testIntervals() {
    final Interval i1 = new Interval(1, 2);
    final Interval i2 = new Interval(5, 10);
    assertThat(i2.length()).isEqualTo(5);
    assertThat(i1.union(i2)).isNull();
    assertThat(i1.intersection(i2)).isNull();

    final Interval i3 = new Interval(4, 6);
    final Interval i4 = new Interval(8, 15);
    final Interval i5 = new Interval(6, 9);
    assertThat(i2.isOverlap(i3)).isTrue();
    assertThat(i2.intersection(i3)).isEqualByComparingTo(new Interval(5, 6));
    assertThat(i2.union(i3)).isEqualByComparingTo(new Interval(4, 10));
    assertThat(i2.intersection(i4)).isEqualByComparingTo(new Interval(8, 10));
    assertThat(i2.union(i4)).isEqualByComparingTo(new Interval(5, 15));
    assertThat(i2.intersection(i5)).isEqualByComparingTo(i5);
    assertThat(i2.union(i5)).isEqualByComparingTo(new Interval(5, 10));

    final Interval r1 = new Interval(1, 10)
            .intersection(new Interval(5, 100))
            .intersection(new Interval(6, 9))
            .union(new Interval(9, 10))
            .union(new Interval(5, 6))
            .intersection(new Interval(5, 10));
    assertThat(r1).isEqualByComparingTo(new Interval(5, 10));
  }

}