package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NonPrimeSpiralMatrixCreatorTest {

    @Test
    void testZeroN() {
        int[] expected = {};
        int[] result = NonPrimeSpiralMatrixCreator.createSpiralMatrix(0);
        assertArrayEquals(result, expected);
    }

    @Test
    void testNegativeN() {
        int[] expected = {};
        int[] result = NonPrimeSpiralMatrixCreator.createSpiralMatrix(-1);
        assertArrayEquals(result, expected);
    }

    @Test
    void test1x1Matrix() {
        int[] expected = {1};
        int[] result = NonPrimeSpiralMatrixCreator.createSpiralMatrix(1);
        assertArrayEquals(result, expected);
    }

    @Test
    void test2x2Matrix() {
        int[] expected = {1, 4, 8, 6};
        int[] result = NonPrimeSpiralMatrixCreator.createSpiralMatrix(2);
        assertArrayEquals(result, expected);
    }

    @Test
    void test3x3Matrix() {
        //int[] expected = {4, 6, 8, 15, 16, 9, 14, 12, 10};
        int[] expected = {1, 4, 6, 14, 15, 8, 12, 10, 9};
        int[] result = NonPrimeSpiralMatrixCreator.createSpiralMatrix(3);
        assertArrayEquals(result, expected);
    }

    @Test
    void test4x4Matrix() {
        //int[] expected = {4, 6, 8, 9, 21, 22, 24, 10, 20, 26, 25, 12, 18, 16, 15, 14};
        int[] expected = {1, 4, 6, 8, 20, 21, 22, 9, 18, 25, 24, 10, 16, 15, 14, 12};
        int[] result = NonPrimeSpiralMatrixCreator.createSpiralMatrix(4);
        assertArrayEquals(result, expected);
    }

    @Test
    void test5x5Matrix() {
        int[] expected = {1, 4, 6, 8, 9, 25, 26, 27, 28, 10, 24, 35, 36, 30, 12, 22, 34, 33, 32, 14, 21, 20, 18, 16, 15};
        int[] result = NonPrimeSpiralMatrixCreator.createSpiralMatrix(5);
        assertArrayEquals(result, expected);
    }
}