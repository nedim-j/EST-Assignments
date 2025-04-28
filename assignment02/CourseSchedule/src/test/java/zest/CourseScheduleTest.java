package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CourseScheduleTest {

    @Test
    public void testCycle() {
        int[][] prerequisites = {{1, 0}, {0, 1}};
        boolean result = CourseSchedule.canFinish(2, prerequisites);
        assertFalse(result);
    }

    @Test
    public void testNoCycle() {
        int[][] prerequisites = {{1, 0}, {2, 1}};
        boolean result = CourseSchedule.canFinish(3, prerequisites);
        assertTrue(result);
    }

    @Test
    public void testVisitedMultipleTimes() {
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}, {3, 2}};
        boolean result = CourseSchedule.canFinish(4, prerequisites);
        assertTrue(result);
    }



}
