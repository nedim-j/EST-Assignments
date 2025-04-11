package zest;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;
import net.jqwik.api.constraints.Positive;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ArrayRotatorTest {
    @Test
    public void testRotateSuccess() {
        int[] inputArray = {1, 2, 3, 4, 5};
        int rotationCount = 2;

        int[] expected = {4, 5, 1, 2, 3};

        int[] result = ArrayRotator.rotate(inputArray, rotationCount);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testRotateInputArrayNull() {
        int[] inputArray = null;
        int rotationCount = 2;

        assertThrows(
                IllegalArgumentException.class,
                () -> ArrayRotator.rotate(inputArray, rotationCount),
                "Expected an IllegalArgumentException to be thrown"
        );
    }

    @Test
    public void testRotateRotationCountNegative() {
        int[] inputArray = {1, 2, 3, 4, 5};
        int rotationCount = -1;

        assertThrows(
                IllegalArgumentException.class,
                () -> ArrayRotator.rotate(inputArray, rotationCount),
                "Expected an IllegalArgumentException to be thrown"
        );
    }

    @Test
    public void testRotateRotationCountZero() {
        int[] inputArray = {1, 2, 3, 4, 5};
        int rotationCount = 0;

        int[] expected = inputArray;

        int[] result = ArrayRotator.rotate(inputArray, rotationCount);

        assertArrayEquals(expected, result);
    }

    @Property
    public void failRotationCountNegative(@ForAll int[] inputArray, @ForAll @Negative int rotationCount) {
        assertThrows(
                IllegalArgumentException.class,
                () -> ArrayRotator.rotate(inputArray, rotationCount),
                "Expected an IllegalArgumentException to be thrown"
        );
    }

    @Property
    public void failArrayNull(@ForAll @Positive int rotationCount) {
        assertThrows(
                IllegalArgumentException.class,
                () -> ArrayRotator.rotate(null, rotationCount),
                "Expected an IllegalArgumentException to be thrown"
        );
    }

    @Property
    public void successRotationCountZero(@ForAll int[] inputArray) {
        int[] result = ArrayRotator.rotate(inputArray, 0);

        assertArrayEquals(inputArray, result);
    }
}
