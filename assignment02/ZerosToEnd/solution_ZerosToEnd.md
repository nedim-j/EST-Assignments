# Solution

## Designing Contracts

### Pre-conditions:
- The length of the input array must be: 0 <= n <= 10
- The input array cannot be null.
- The input array can contain any integer values (positive, zero, negative).

### Post-conditions:
- The output array must contain all non-zero elements in the same order as the input array.
- The output array must contain all zero elements at the end.
- The output array cannot be null (Due to the Pre-Conditions).
- The output array has the same length as the input array.

### Invariants:
- The output array must have the same length as the input array.
- The input array must not be changed.
- The output array must contain all non-zero elements in the same order as the input array.
- The output array must contain all zero elements at the end.

### Implementation:

#### Pre-conditions:
The given implementation does perform a pre-condition check, but it checks whether the arr.length is zero or the length is greater-equal 10. This is not correct, because it needs to check for length larger than 10, not also equal.
The check for `arr.length == 0` is not necessary for pre-conditions, but it gives an early return for a valid input that is empty.
We have corrected the maximum length check to `arr.length > 10`.
We have also added a check for null inputs, which raises an `IllegalArgumentException`.

#### Post-conditions:
We have added checks before returning whether `temp` is null or has not the same length as the input array.
We have added a check whether each element in `temp` exists in the input array.
Given that it is the methods job to implement the logic that the original order is being kept, we have left this post-condition check to the test cases.

#### Invariants:
We have added a check whether the input array has changed. This is done by cloning the array and then comparing at the end.
All other invariants are being checked via the pre- and post-condition checks.

## Testing Contracts:
Based on the specified contract in the previous Task, we have come up with the following test cases:
- `null`: Test with null `arr` array.
- `empty`: Test with an empty `arr` array.
- `overSized`: Test with an array larger than 10 elements.
- `oneElement`: Test with a single element array.
- `multipleElements`: Test with an array of length 1 to 10 containing random integer elements.
- `allZeros`: Test with an array of length 1 to 10 containing only zeros.
- `noZeros`: Test with an array of length 1 to 10 containing only non-zero elements.
- `sameLengthInputOutput`: Test with an array of length 1 to 10 containing random integer elements, check that the output has the same length as the input.
- `inputArrayNotChanged`. Test with an array of length 1 to 10 containing random integer elements, check that the input array has not changed.

## Property-based testing:
We have defined the following properties:
- For a valid input array, the output must contain all non-zero elements in the same order as the input array.
- For a valid input array, the output must contain all zero elements at the end.
- The output array must have the same length as the input array.

For this, we have defined the following tests. If a test has the same name as from the Testing Contract, it means we have adapted its test into a property-based test.
- `multipleElements`: Test with an array of length 1 to 10 containing random integer elements.
- `noZeros`: Test with an array of length 1 to 10 containing only non-zero elements.
- `sameLengthInputOutput`: Test with an array of length 1 to 10 containing random integer elements, check that the output has the same length as the input.
- `inputArrayNotChanged`. Test with an array of length 1 to 10 containing random integer elements, check that the input array has not changed.

# Test Coverage:
The test coverage is 84% for the `zerosToEnd` function. The reason it is not 100% is because of the post-condition and invariant checks, which always pass and therefore we never reach the lines that throw exceptions, which is a good thing.

## Bug Reports:

### Wrong pre-condition checks:
The given implementation does perform a pre-condition check, but it checks whether the arr.length is zero or th elength is greater-equal 10. This is not correct, because it needs to check for length larger than 10, not also equal.
