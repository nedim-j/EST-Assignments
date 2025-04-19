package zest;


import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClimbingStairsTest {
    ClimbingStairs climbingStairs;

    @BeforeEach
    public void setup() {
        climbingStairs = new ClimbingStairs();
    }

    @Test
    public void testClimbStairsBasic() {
        int n = 10;

        long expected = 89;

        long result = climbingStairs.climbStairs(n);

        assertEquals(expected, result);
    }

    @Test
    public void testClimbStairsBorder0() {
        int n = 0;

        long expected = 0;

        long result = climbingStairs.climbStairs(n);

        assertEquals(expected, result);
    }

    @Test
    public void testClimbStairsBorder1() {
        int n = 1;

        long expected = 1;

        long result = climbingStairs.climbStairs(n);

        assertEquals(expected, result);
    }

    @Test
    public void testClimbStairsBorder2() {
        int n = 2;

        long expected = 2;

        long result = climbingStairs.climbStairs(n);

        assertEquals(expected, result);
    }

    @Test
    public void testClimbStairsNegativeInput() {
        int n = -1;

        assertThrows(
                IllegalArgumentException.class,
                () -> climbingStairs.climbStairs(n),
                "Expected IllegalArgumentException to be thrown"
        );
    }

    @Property
    public void testClimbStairsNegativeInputProperty(@ForAll @Negative int n) {
        climbingStairs = new ClimbingStairs();

        assertThrows(
                IllegalArgumentException.class,
                () -> climbingStairs.climbStairs(n),
                "Expected IllegalArgumentException to be thrown"
        );
    }
}


