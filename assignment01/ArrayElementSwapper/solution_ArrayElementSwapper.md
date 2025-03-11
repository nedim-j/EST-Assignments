# Solution

## Seven principles of specification testing

### Understand the requirement
The objective of the `swapElements` function is to reorder the given array 
such that all negative numbers appear before all positive numbers, maintaining 
the order of appearance for negative and positive numbers within their respective groups.

### Explore the program
The implementation first checks whether the given array `numbers` is null, or the length is smaller than 1.
If that is the case, the program returns nothing and the array is not changed.

If `numbers` contains more than one element, the program counts the amount of negative numbers 
contained in the array. This determines the boundary between negative and non-negative numbers 
in the final array.

After the boundary has been determined, a second array `temp` of equal length is created and the 
elements of `numbers` are copied into `temp` (in the right order). This is done 
by looping through the elements in `numbers` and determining whether they are negative or non-negative.

- If an element is negative, the element is inserted after the last inserted negative number on the left 
side of the `temp` array.

- If the element is non-negative, it is inserted after the last inserted 
non-negative number on the right side of the `temp` array (after the boundary index).

Lastly, the contents of `temp` are copied into `numbers`.

### Identify the partitions

The input array `numbers` can either be null, empty, have length 1, or have length > 1. Since the first 
three cases are treated equally in the program, there are two partitions.

In the first partition, nothing is returned and `numbers` remains unchanged, and in the second partition, 
numbers has been changed.

### Analyze the boundaries

Length of `numbers` is smaller or equal to 1, and length of `numbers` is greater than 1.

### Devise test cases

- `testBasicCase` passes an array with length greater than 1 into the function.

- `testEmptyArray` passes an empty array into the function.

- `testNullInput` passes null into the function.

- `testSingleElement` passes an array of length 1 into the function.

### Augment

Three additional test cases have been added:

- `testNoNegatives` passes an array with no negative numbers into the function.
- `testNoNonNegatives` passes an array with no non-negative numbers into the function.
- `testAlreadyReordered` passes an array which is already in the correct order into the function.

## Coverage

After running the test suite, IntelliJ reports 100% line coverage.

## Mutation testing

After mutation testing, we have 90% mutation coverage (10 mutations, 9 killed). The mutation 
that survived only changed the if statement on line 11 from `if (numbers == null || numbers.length <= 1)` to 
`if (numbers == null || numbers.length < 1)`. This only makes the program less efficient. The resulting 
array is still correct, which is why we don't need to create additional tests to kill this mutation.

## Bug reports

The implementation of `swapElements` does not contain bugs.