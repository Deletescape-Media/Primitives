package ch.deletescape.primitives.arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PrFloatArrayTest {
  @Test
  public void fromInt() {
    assertThat(PrFloatArray.from(new int[] { 1, Integer.MAX_VALUE, Integer.MAX_VALUE - 1 }),
        is(new float[] { 1, 2147483647, 2147483646 }));
  }

  @Test
  public void fromShort() {
    assertThat(PrFloatArray.from(new short[] { 1, Short.MAX_VALUE }), is(new float[] { 1, 32767 }));
  }

  @Test
  public void fromByte() {
    assertThat(PrFloatArray.from(new byte[] { 1, Byte.MAX_VALUE }), is(new float[] { 1, 127 }));
  }

  @Test
  public void fromChar() {
    assertThat(PrFloatArray.from(new char[] { '\0', Character.MAX_VALUE, 1 }), is(new float[] { 0, 65535, 1 }));
  }

  @Test
  public void fromDouble() {
    assertThat(PrFloatArray.from(new double[] { 1.0, 1.3, Double.MAX_VALUE }),
        is(new float[] { 1, 1.3f, Float.POSITIVE_INFINITY }));
  }

  @Test
  public void fromLong() {
    assertThat(PrFloatArray.from(new long[] { 1L, Long.MAX_VALUE }), is(new float[] { 1, 9223372036854775807f }));
  }

  @Test
  public void fromBoolean() {
    assertThat(PrFloatArray.from(new boolean[] { true, false }), is(new float[] { 1, 0 }));
  }

}
