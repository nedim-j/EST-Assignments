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

    @Test
    public void testInvalidPairLength() {
        int[][] prerequisites = {{1, 0}, {2}};
        assertThrows(IllegalArgumentException.class, () -> {
            CourseSchedule.canFinish(3, prerequisites);
        });
    }

    @Test
    public void testPrerequisiteCourseExceedsCourseNumber() {
        int[][] prerequisites = {{1, 0}, {2, 3}};
        assertThrows(IllegalArgumentException.class, () -> {
            CourseSchedule.canFinish(3, prerequisites);
        });
    }

    @Test
    public void testPrerequisitesLengthExceeds64() {
        int[][] prerequisites = new int[65][2];
        for (int i = 0; i < 65; i++) {
            prerequisites[i][0] = 1;
            prerequisites[i][1] = 0;
        }
        assertThrows(IllegalArgumentException.class, () -> {
            CourseSchedule.canFinish(2, prerequisites);
        });
    }

    @Test
    public void testNumCoursesExceeds64() {
        int[][] prerequisites = {{1, 0}, {2, 1}};
        assertThrows(IllegalArgumentException.class, () -> {
            CourseSchedule.canFinish(65, prerequisites);
        });
    }

}
