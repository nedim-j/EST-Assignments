package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EventSchedulerTest {
    // Tests a random array.
    @Test
    void testBasicCase() {
        int[][] events = {{1, 3}, {2, 4}, {5, 7}};
        boolean expected = true;
        boolean result = EventScheduler.hasConflict(events);
        assertEquals(expected, result);
    }

    // Tests an empty array.
    @Test
    void testEmptyArray() {
        int[][] events = {};
        boolean expected = false;
        boolean result = EventScheduler.hasConflict(events);
        assertEquals(expected, result);
    }

    // Tests an array with one element.
    @Test
    void testSingleElement() {
        int[][] events = {{1, 5}};
        boolean expected = false;
        boolean result = EventScheduler.hasConflict(events);
        assertEquals(expected, result);
    }

    // Tests an array with no overlaps.
    @Test
    void testNoOverlaps() {
        int[][] events = {{1, 3}, {4, 5}, {6, 9}};
        boolean expected = false;
        boolean result = EventScheduler.hasConflict(events);
        assertEquals(expected, result);
    }

    // Tests an array with no overlaps, but the start time of one event is the end time of another.
    @Test
    void testNoOverlapsEndTimeStartTime() {
        int[][] events = {{1, 3}, {4, 5}, {5, 9}};
        boolean expected = false;
        boolean result = EventScheduler.hasConflict(events);
        assertEquals(expected, result);
    }
}