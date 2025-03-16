package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FractionToDecimalTest {
    @Test
    void testBasicCase() {
        int numerator = 1;
        int denominator = 3;
        String expected = "0.(3)";
        String result = FractionToDecimal.fractionToDecimal(numerator, denominator);
        assertEquals(expected, result);
    }

    @Test
    void testZeroDenominator() {
        int numerator = 1;
        int denominator = 0;
        String result = FractionToDecimal.fractionToDecimal(numerator, denominator);
        assertNull(result);
    }

    @Test
    void testNegativeNumerator() {
        int numerator = -1;
        int denominator = 3;
        String expected = "-0.(3)";
        String result = FractionToDecimal.fractionToDecimal(numerator, denominator);
        assertEquals(expected, result);
    }

    @Test
    void testNegativeDenominator() {
        int numerator = 1;
        int denominator = -3;
        String expected = "-0.(3)";
        String result = FractionToDecimal.fractionToDecimal(numerator, denominator);
        assertEquals(expected, result);
    }

    @Test
    void testNegativeNumeratorAndDenominator() {
        int numerator = -1;
        int denominator = -3;
        String expected = "0.(3)";
        String result = FractionToDecimal.fractionToDecimal(numerator, denominator);
        assertEquals(expected, result);
    }

    @Test
    void testLongRepetition() {
        int numerator = 2;
        int denominator = 7;
        String expected = "0.(285714)";
        String result = FractionToDecimal.fractionToDecimal(numerator, denominator);
        assertEquals(expected, result);
    }

    @Test
    void testNoRemainder() {
        int numerator = 4;
        int denominator = 2;
        String expected = "2";
        String result = FractionToDecimal.fractionToDecimal(numerator, denominator);
        assertEquals(expected, result);
    }
    
    @Test
    void testZeroNumerator() {
        int numerator = 0;
        int denominator = 3;
        String expected = "0";
        String result = FractionToDecimal.fractionToDecimal(numerator, denominator);
        assertEquals(expected, result);
    }
}
