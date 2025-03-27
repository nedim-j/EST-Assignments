package zest;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class IntervalMergerTest {

    @Test
    void testEmptyArray() {
        int[][] intervals = {};
        int[][] expected = {};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testNullArray() {
        int[][] intervals = null;
        int[][] expected = null;
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testNullIntervals() {
        int[][] intervals = {null, null, {1, 3}, {2, 5}};
        int[][] expected = {null, null, {1, 3}, {2, 5}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testEmptyIntervals() {
        int[][] intervals = {{}, {}, {1, 3}, {2, 5}};
        int[][] expected = {{}, {}, {1, 3}, {2, 5}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testSingleInterval() {
        int[][] intervals = {{1, 3}};
        int[][] expected = {{1, 3}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        //assert Arrays.deepEquals(result, expected);
        assert result.length == 1;
        assert Arrays.equals(result[0], expected[0]);
    }

    @Test
    void testTwoIntervals() {
        int[][] intervals = {{1, 3}, {2, 4}};
        int[][] expected = {{1, 4}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testExampleCase() {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] expected = {{1, 6}, {8, 10}, {15, 18}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testStarttimeEqualsEndtime() {
        int[][] intervals = {{1, 3}, {3, 4}, {5, 6}};
        int[][] expected = {{1, 4}, {5, 6}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testNoOverlaps() {
        int[][] intervals = {{1, 2}, {3, 4}, {5, 6}};
        int[][] expected = {{1, 2}, {3, 4}, {5, 6}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testEqualIntervals() {
        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
        int[][] expected = {{1, 2}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testInvalidIntervals() {
        int[][] intervals = {{1, 2}, {3, 1}, {5, 6}};
        int[][] expected = {{1, 2}, {3, 1}, {5, 6}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testMultipleOverlaps() {
        int[][] intervals = {{1, 3}, {2, 4}, {3, 5}};
        int[][] expected = {{1, 5}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }

    @Test
    void testUnsortedIntervals() {
        int[][] intervals = {{2, 6}, {1, 3}, {7, 9}};
        int[][] expected = {{1, 6}, {7, 9}};
        int[][] result = IntervalMerger.mergeIntervals(intervals);

        assert Arrays.deepEquals(result, expected);
    }
}