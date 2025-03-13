# Solution

## Seven principles of specification testing

### Understand the requirement
The objective of the `maxConsecutiveSum` function is to find the maximum sum possible from any consecutive 
subarray within the given array `numbers`.

### Explore the program
If `numbers` is null or empty, the program returns 0.

If `numbers` is not null or empty, the program calculates the maximum sum by iterating 
through the `numbers` array and updating the `currentSum` and `maxSum` variables.

In the end, `maxSum` is returned.

### Identify the partitions

The input array `numbers` can either be null, empty, or have length greater than 0. 
Since the first two cases are treated in the same way, we have two partitions.

In the first partition, 0 is returned, while in the second partition, the program returns 
the maximum possible sum.

### Analyze the boundaries

Length of `numbers` is equal to 0, and length of `numbers` is greater than 0.

### Devise test cases

- `testBasicCase` passes an array with multiple elements into the function.

- `testEmptyArray` passes an empty array into the function.

- `testNullInput` passes null into the function.

- `testSingleElement` passes an array of length 1 into the function.

### Augment

Two additional test cases have been added:

- `testOnlyNegatives` passes an array with only negative numbers.
- `testOnlyPositives` passes an array with only positive numbers.

## Coverage

After running the test suite, IntelliJ reports 100% line coverage.

## Mutation testing

After mutation testing, we have 100% mutation coverage.

## Bug reports

The implementation of `maxConsecutiveSum` does not contain bugs.