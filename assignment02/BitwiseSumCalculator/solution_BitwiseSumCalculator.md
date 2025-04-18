# Solution

## Task 2

<b>Pre-conditions: </b><br>
- The input for `a` should be an integer representing the first operand.
- The input for `b` should be an integer representing the second operand.

<b>Post-conditions: </b><br>
- The output should be an integer representing the addition of `a` and `b`.

<b>Invariants: </b><br>
- `a + b` should always be the same, even though `a` and `b` are modified in the while loop.

## Task 3

We implemented the following tests:

- `testSum`: We take `4` and `5` as input and expect `9` as output.
- `testSumNegativeA`: We take `-1` and `1` as input and expect `0` as output.
- `testSumNegativeB`: We take `1` and `-1` as input and expect `0` as output.
- `testSumZeroB`: We take `5` and `0` as input and expect `5` as output.

## Task 1

After executing the tests from task 3, JaCoCo reports 80% line coverage in the `BitwiseSumCalculator.getSum` 
function. 100% wasn't reached, since we have invariant and post-condition checks, which realistically would never be 
reached.

## Task 4

The following property-based tests have been added:
- `testSumRandomARandomB`: We take two randomly generated integers as input and expect their sum as output.

## Bugs

No bugs were found in this implementation of the `BitwiseSumCalculator.getSum` function.