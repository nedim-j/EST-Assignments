# Solution

## Seven principles of specification testing

### Understand the requirements, inputs & outputs

The purpose of `mergeIntervals` is to from a given array of intervals (i.e. [(1,3), (2,6)]) merge all overlapping intervals (i.e. [(1,6)]).

### Explore the program for various inputs

The function first checks whether the given list of intervals is smaller or equal to one, in which case it immediately returns it.

If more than one interval is given, the function sorts the intervals by their start time.

It then checks for each two consecutive intervals whether they overlap, by comparing the end time of the first interval with the start time of the second interval.
If endTime of Interval1 >= startTime of Interval2, the intervals overlap and are merged into a single interval.

### Identify the partitions

The input can either be null, an empty array, an array with one interval or an array with more than one interval.

Because in this case null, empty or one-element arrays are treated the same way, there are two partitions.

In the first partition, the array is returned as is. In the second partition, the list is modified if there are overlapping intervals.

### Analyze the boundaries

* Array of intervals: length needs to be > 1
* End time of interval 1 >= start time of interval 2, then there is an overlap

### Devise test cases

Exceptional cases:

* `testEmptyArray` passes an empty array into the function.
* `testNullArray` passes null into the function.
* `testNullIntervals` passes an array into the function, with some intervals being null.
* `testEmptyIntervals` passes an array into the function, with some intervals being empty.
* `testSingleInterval` passes an array with a single interval into the function.

Intervals length > 1:
* `testTwoIntervals` passes an array with two overlapping intervals into the function.
* `testExampleCase` passes an array with multiple elements into the function (from the given example).
* `testStarttimeEqualsEndtime` passes an array with multiple intervals into the function, where the end time of the first interval is equal to the start time of the second interval.
* `testNoOverlaps` passes an array with multiple distinct intervals into the function, where no intervals overlap.
* `testEqualIntervals` passes an array with multiple equal intervals into the function.

### Automate test cases

See `IntervalMergerTest.java`.

### Augment

Three additional test cases have been added:

* `testInvalidIntervals` passes an array with multiple invalid intervals into the function, where the end time of an interval is smaller than its start time.
* `testMultipleOverlaps` passes an array with multiple distinct intervals into the function, where the first and the last interval overlap with the middle one.
* `testUnsortedIntervals` passes an array with multiple unsorted intervals into the function.

## Coverage

After running the test suite, IntelliJ using JaCoCo reports 100% branch and line coverage.

## Enhancing the test suite with structural testing

Because we already achieved 100% condition+line coverage, we do not need to further enhance the test suite.

## Mutation testing

Performing mutation testing with PItest, we achieve 92% mutation coverage. The only mutation that survives is
on line 14, for the if-condition whether the length of the intervals is smaller or equal to 1.

Unfortunately, we could not figure out how to kill this mutation, as it's killed for the negated conditional but not
for the "changed conditional boundary" mutation. There are test cases for empty arrays, arrays with one interval and
arrays with more than one interval, so we are not sure how to further enhance the test suite to kill this mutation.

## Bug reports

The function crashes for the following test cases.
We changed the code, so it returns before executing any further code that makes it crash for the given inputs.

* testNullArray revealed that the function fails for `intervals` being null.
For that we added a check to the if-condition for `intervals`.


```java
    public static int[][] mergeIntervals(int[][] intervals) {
        // Solution: Added if-condition to also check if interval is null
        if (intervals == null) {
            return null;
        }
    ...
```

* testNullIntervals revealed that the function fails for`intervals` containing null intervals.
* testEmptyIntervals revealed that the function fails for `intervals` containing empty intervals.

To make those two tests pass, we iterate over the intervals and check if an interval is null or does not contain exactly
one start and one end time, in which case we return `intervals` again.

```java
        ...
        // Solution: Added check for null or invalid intervals
        for (int[] interval : intervals) {
            if (interval == null || interval.length != 2) {
                return intervals;
            }
        }
        ...
```