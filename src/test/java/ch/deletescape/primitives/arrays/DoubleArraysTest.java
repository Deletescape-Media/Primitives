package ch.deletescape.primitives.arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DoubleArraysTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private static final double ONE_THIRD = 1 / 3.0;

  @Test
  public void fromInt() {
    assertThat(DoubleArrays.from(new int[] { 1, Integer.MAX_VALUE, Integer.MAX_VALUE - 1 }),
        is(new double[] { 1, 2147483647, 2147483646 }));
  }

  @Test
  public void fromShort() {
    assertThat(DoubleArrays.from(new short[] { 1, Short.MAX_VALUE }), is(new double[] { 1, 32767 }));
  }

  @Test
  public void fromByte() {
    assertThat(DoubleArrays.from(new byte[] { 1, Byte.MAX_VALUE }), is(new double[] { 1, 127 }));
  }

  @Test
  public void fromChar() {
    assertThat(DoubleArrays.from(new char[] { '\0', Character.MAX_VALUE, 1 }), is(new double[] { 0, 65535, 1 }));
  }

  @Test
  public void fromFloat() {
    assertThat(DoubleArrays.from(new float[] { 1f, Float.MAX_VALUE }), is(new double[] { 1, 3.4028234663852886E38 }));
  }

  @Test
  public void fromLong() {
    assertThat(DoubleArrays.from(new long[] { 1L, Long.MAX_VALUE }), is(new double[] { 1, 9223372036854775807f }));
  }

  @Test
  public void fromBoolean() {
    assertThat(DoubleArrays.from(new boolean[] { true, false }), is(new double[] { 1, 0 }));
  }

  @Test
  public void join() {
    assertThat(DoubleArrays.join(";", 1.0, 2.5), is("1.0;2.5"));
  }

  @Test
  public void concat() {
    assertThat(DoubleArrays.concatAll(new double[] { 1.0, 1.3 }, new double[] { -2, 5 }),
        is(new double[] { 1, 1.3, -2, 5 }));
  }

  @Test
  public void contains() {
    assertThat(DoubleArrays.contains(new double[] { 1, 3 }, 3), is(true));
    assertThat(DoubleArrays.contains(new double[] { 1, 3 }, 5), is(false));
  }

  @Test
  public void distinct() {
    assertThat(DoubleArrays.distinct(new double[] { 1, 2, 1, 3 }), is(new double[] { 1, 2, 3 }));
    assertThat(DoubleArrays.distinct(new double[0]), is(new double[0]));
  }

  @Test
  public void findSequence() {
    assertThat(DoubleArrays.findSequence(2, new double[] { 1, 2, 1, 3 }, 2, 1), is(-1));
    assertThat(DoubleArrays.findSequence(2, new double[] { 1, 2, 1, 2 }, 1, 2), is(2));

    assertThat(DoubleArrays.findSequence(new double[] { 1, 2, 1, 3 }, 2, 1), is(1));
    assertThat(DoubleArrays.findSequence(new double[] { 1, 2, 1, 3 }, 1, 3), is(2));
    assertThat(DoubleArrays.findSequence(new double[] { 1, 2, 1, 3 }, 1, 1), is(-1));
    assertThat(DoubleArrays.findSequence(new double[] { 1, 2, 1, 3 }, (double) 3), is(3));
    assertThat(DoubleArrays.findSequence(new double[] { 1, 2, 1, 3 }, (double) 4), is(-1));
  }

  @Test
  public void countSequence() {
    assertThat(DoubleArrays.countSequence(2, new double[] { 1, 2, 1, 3 }, 2, 1), is(0));
    assertThat(DoubleArrays.countSequence(1, new double[] { 1, 2, 1, 3 }, (double) 1), is(1));

    assertThat(DoubleArrays.countSequence(new double[] { 1, 2, 1, 3 }, 2, 1), is(1));
    assertThat(DoubleArrays.countSequence(new double[] { 1, 2, 1, 3 }, (double) 1), is(2));
    assertThat(DoubleArrays.countSequence(new double[] { 1, 2, 1, 3 }, (double) 4), is(0));
  }

  @Test
  public void insert() {
    assertThat(DoubleArrays.insert(new double[] { 1, 2, 3, 4 }, new double[] { 10, 11 }, 2),
        is(new double[] { 1, 2, 10, 11, 3, 4 }));
    assertThat(DoubleArrays.insert(new double[] { 1, 2, 3, 4 }, new double[0], 0), is(new double[] { 1, 2, 3, 4 }));
    assertThat(DoubleArrays.insert(new double[] { 1, 2, 3, 4 }, new double[] { 10, 11 }, 5),
        is(new double[] { 1, 2, 3, 4, 10, 11 }));
    assertThat(DoubleArrays.insert(new double[] { 1, 2, 3, 4 }, new double[] { 10, 11 }, -4),
        is(new double[] { 10, 11, 1, 2, 3, 4 }));
  }

  @Test
  public void append() {
    assertThat(DoubleArrays.append(new double[] { 1, 2, 3, 4 }, 10, 11), is(new double[] { 1, 2, 3, 4, 10, 11 }));
    assertThat(DoubleArrays.append(new double[] { 1, 2, 3, 4 }), is(new double[] { 1, 2, 3, 4 }));
  }

  @Test
  public void min() {
    assertThat(DoubleArrays.min(1), is(1.0));
    assertThat(DoubleArrays.min(-3, 5, -6), is(-6.0));
    assertThat(DoubleArrays.min(-3, -3, -3), is(-3.0));
  }

  @Test
  public void max() {
    assertThat(DoubleArrays.max(1), is(1.0));
    assertThat(DoubleArrays.max(-3, 5, -6), is(5.0));
    assertThat(DoubleArrays.max(-3, -3, -3), is(-3.0));
  }

  @Test
  public void avg() {
    assertThat(DoubleArrays.avg(1), is(1.0));
    assertThat(DoubleArrays.avg(-3, 5, -6), is(-(1 + ONE_THIRD)));
    assertThat(DoubleArrays.avg(-3, -3, -3), is(-3.0));
  }

  @Test
  public void sum() {
    assertThat(DoubleArrays.sum(1), is(1.0));
    assertThat(DoubleArrays.sum(1, 2, 3), is(6.0));
    assertThat(DoubleArrays.sum(-3, -3, -3), is(-9.0));
    assertThat(DoubleArrays.sum(new double[0]), is(0.0));
  }

  @Test
  public void minEmptyArray() {
    thrown.expect(InvalidArrayException.class);
    thrown.expectMessage(is("Can't get min value from empty array"));
    DoubleArrays.min(new double[0]);
  }

  @Test
  public void maxEmptyArray() {
    thrown.expect(InvalidArrayException.class);
    thrown.expectMessage(is("Can't get max value from empty array"));
    DoubleArrays.max(new double[0]);
  }

  @Test
  public void avgEmptyArray() {
    thrown.expect(InvalidArrayException.class);
    thrown.expectMessage(is("Can't get average value from empty array"));
    DoubleArrays.avg(new double[0]);
  }

  // Calls the #random(int) method for coverage reasons
  @Test
  public void random() {
    DoubleArrays.random(1);
  }
}
