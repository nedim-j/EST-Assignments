package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConsecutiveElementsSumTest {
    // Tests the basic case with a random array.
    @Test
    void testBasicCase() {
        int[] numbers = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expected = 6;
        int result = ConsecutiveElementsSum.maxConsecutiveSum(numbers);
        assertEquals(expected, result);
    }

    // Tests with an empty array.
    @Test
    void testEmptyArray() {
        int[] numbers = {};
        int expected = 0;
        int result = ConsecutiveElementsSum.maxConsecutiveSum(numbers);
        assertEquals(expected, result);
    }

    // Tests with a null array.
    @Test
    void testNullInput() {
        int[] numbers = null;
        int expected = 0;
        int result = ConsecutiveElementsSum.maxConsecutiveSum(numbers);
        assertEquals(expected, result);
    }

    // Tests an array with one element.
    @Test
    void testSingleElement() {
        int[] numbers = {1};
        int expected = 1;
        int result = ConsecutiveElementsSum.maxConsecutiveSum(numbers);
        assertEquals(expected, result);
    }

    // Tests an array with only negative numbers.
    @Test
    void testOnlyNegatives() {
        int[] numbers = {-1, -5, -4, -6};
        int expected = -1;
        int result = ConsecutiveElementsSum.maxConsecutiveSum(numbers);
        assertEquals(expected, result);
    }

    // Tests an array with only positive numbers.
    @Test
    void testOnlyPositives() {
        int[] numbers = {1, 5, 4, 6};
        int expected = 16;
        int result = ConsecutiveElementsSum.maxConsecutiveSum(numbers);
        assertEquals(expected, result);
    }
}