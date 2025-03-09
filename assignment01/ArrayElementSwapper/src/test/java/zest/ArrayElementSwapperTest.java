package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayElementSwapperTest {
    // Testing the implementation using a basic array containing negatives, zeroes, and non-negatives.
    @Test
    void testBasicCase() {
        int[] numbers = {3, -1, 0, -3, 5, -2};
        int[] expected = {-1, -3, -2, 3, 0, 5};
        ArrayElementSwapper.swapElements(numbers);
        assertArrayEquals(expected, numbers);
    }

    // Testing an empty array.
    @Test
    void testEmptyArray() {
        int[] numbers = {};
        int[] expected = {};
        ArrayElementSwapper.swapElements(numbers);
        assertArrayEquals(expected, numbers);
    }

    // Testing null input.
    @Test
    void testNullInput() {
        int[] numbers = null;
        ArrayElementSwapper.swapElements(numbers);
        assertNull(numbers);
    }

    // Testing an array with one element.
    @Test
    void testSingleElement() {
        int[] numbers = {1};
        int[] expected = {1};
        ArrayElementSwapper.swapElements(numbers);
        assertArrayEquals(expected, numbers);
    }

    // Testing an array with no negative numbers.
    @Test
    void testNoNegatives() {
        int[] numbers = {3, 4, 2};
        int[] expected = {3, 4, 2};
        ArrayElementSwapper.swapElements(numbers);
        assertArrayEquals(expected, numbers);
    }

    // Testing an array with no non-negative numbers.
    @Test
    void testNoNonNegatives() {
        int[] numbers = {-3, -4, -2};
        int[] expected = {-3, -4, -2};
        ArrayElementSwapper.swapElements(numbers);
        assertArrayEquals(expected, numbers);
    }

    // Testing an already sorted array.
    @Test
    void testAlreadyReordered() {
        int[] numbers = {-1, -3, -2, 3, 0, 5};
        int[] expected = {-1, -3, -2, 3, 0, 5};
        ArrayElementSwapper.swapElements(numbers);
        assertArrayEquals(expected, numbers);
    }
}