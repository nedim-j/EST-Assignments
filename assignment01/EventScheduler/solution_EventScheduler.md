# Solution

## Seven principles of specification testing

### Understand the requirement
The objective of the `hasConflict` function is to determine whether there is a conflict 
between two events in an `events` array.

### Explore the program
If `events` is empty, or has length 1, the program returns `false`.

If `events` is has length greater than 1, the program sorts the events array by the starting time. 
Afterwards, it determines whether there are any overlaps by comparing `events[i][1]` with 
`events[i + 1][0]`.

In the end, the program returns either `true` or `false`.

### Identify the partitions

The input array `events` can either be empty, have length 1 or have length greater than 1. 
Since the first two cases are treated in the same way, we have two partitions.

In the first partition, `false` is returned, while in the second partition, the program returns 
either `true` or `false` depending on whether there are any overlaps or not.

### Analyze the boundaries

Length of `events` is smaller or equal to 1, and length of `events` is greater than 1.

### Devise test cases

- `testBasicCase` passes an array with multiple elements into the function.

- `testEmptyArray` passes an empty array into the function.

- `testSingleElement` passes an array of length 1 into the function.

### Augment

Two additional test cases have been added:

- `testNoOverlaps` passes an array with no overlaps.
- `testNoOverlapsEndTimeStartTime` passes an array with no overlaps, 
but the start time of one event is the end time of another.

## Coverage

After running the test suite, IntelliJ reports 100% line coverage.

## Mutation testing

After mutation testing, we have 77% mutation coverage.

## Bug reports

The implementation of `hasConflict` does not contain bugs.