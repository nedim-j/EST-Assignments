package zest;


import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BitwiseSumCalculatorTest {
    @Test
    public void testSum() {
        int a = 4;
        int b = 5;

        int expected = 9;

        int result = BitwiseSumCalculator.getSum(a, b);

        assertEquals(expected, result);
    }

    @Test
    public void testSumNegativeA() {
        int a = -1;
        int b = 1;

        int expected = 0;

        int result = BitwiseSumCalculator.getSum(a, b);

        assertEquals(expected, result);
    }

    @Test
    public void testSumNegativeB() {
        int a = 1;
        int b = -1;

        int expected = 0;

        int result = BitwiseSumCalculator.getSum(a, b);

        assertEquals(expected, result);
    }

    @Test
    public void testSumZeroB() {
        int a = 5;
        int b = 0;

        int expected = 5;

        int result = BitwiseSumCalculator.getSum(a, b);

        assertEquals(expected, result);
    }

    @Property
    public void testSumRandomARandomB(@ForAll @IntRange() int a, @ForAll @IntRange() int b) {
        int expected = a + b;

        int result = BitwiseSumCalculator.getSum(a, b);

        assertEquals(expected, result);
    }
}

