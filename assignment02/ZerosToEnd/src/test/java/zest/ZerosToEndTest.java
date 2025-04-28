package zest;


import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZerosToEndTest {
  ZeroesToEnd zeroesToEnd = new ZeroesToEnd();

  @Test
  void nullCase() {
    int[] input = null;
    assertThrows(IllegalArgumentException.class, () -> zeroesToEnd.pushZeroesToEnd(input));
  }

  @Test
  void empty() {
    int[] input = {};
    int[] expected = {};
    int[] output = zeroesToEnd.pushZeroesToEnd(input);
    assertArrayEquals(expected, output);
  }

  @Test
  void overSized() {
    int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    int[] expected = {};
    int[] output = zeroesToEnd.pushZeroesToEnd(input);
    assertArrayEquals(expected, output);
  }

  @Test
  void oneElement() {
    int[] input = {1};
    int[] expected = {1};
    int[] output = zeroesToEnd.pushZeroesToEnd(input);
    assertArrayEquals(expected, output);
  }

  @Test
  void allZeros() {
    int[] input = {0, 0, 0, 0, 0};
    int[] expected = {0, 0, 0, 0, 0};
    int[] output = zeroesToEnd.pushZeroesToEnd(input);
    assertArrayEquals(expected, output);
  }

  @Test
  void noZeros() {
    int[] input = {1, 2, 3, 4, 5};
    int[] expected = {1, 2, 3, 4, 5};
    int[] output = zeroesToEnd.pushZeroesToEnd(input);
    assertArrayEquals(expected, output);
  }

  @Provide
  Arbitrary<int[]> noZeroArray() {
    return Arbitraries.integers()
            .filter(i -> i != 0)
            .array(int[].class)
            .ofMinSize(1)
            .ofMaxSize(10);
  }


  @Property
  void multipleElements(@ForAll @Size(min = 1, max = 10) int[] input) {
    int[] expected = new int[input.length];

    int index = 0;
    for (int i : input) {
      if (i != 0) {
        expected[index++] = i;
      }
    }
    while (index < expected.length) {
      expected[index++] = 0;
    }

    int[] output = zeroesToEnd.pushZeroesToEnd(input);
    assertArrayEquals(expected, output);
  }

  @Property
  void noZeros(@ForAll("noZeroArray") int[] input) {
    int[] expected = input.clone();

    int[] output = zeroesToEnd.pushZeroesToEnd(input);
    assertArrayEquals(expected, output);
  }

  @Property
  void sameLengthInputOutput(@ForAll @Size(min = 1, max = 10) int[] input) {
    int[] output = zeroesToEnd.pushZeroesToEnd(input);
    assertEquals(input.length, output.length);
  }

  @Property
  void inputArrayNotChanged(@ForAll @Size(min = 1, max = 10) int[] input) {
    int[] original = input.clone();
    zeroesToEnd.pushZeroesToEnd(input);
    assertArrayEquals(original, input);
  }
}
