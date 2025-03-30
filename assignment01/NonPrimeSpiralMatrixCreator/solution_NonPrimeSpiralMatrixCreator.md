# Solution

## Seven principles of specification testing

### Understand the requirement

The purpose of `NonPrimeSpiralMatrixCreator` is to create a (flattened, one dimensional) n*n matrix filled with integers
from 1 up to n^2, placed in a spiral order, with the condition that no cell should contain a prime number.
If a "candidate" number is prime, it's continually incremented by one until 
it is no longer a prime.

### Explore the program

* The function first checks whether the given size of the matrix is 0, in which case it immediately returns an empty array.
* If the size is greater than 0, the function initializes the matrix with the given size and fills it with the numbers from 1 to n^2. 
* The left, right, top, and bottom boundaries signify the "corners" of the spiral. Upon hitting them, the direction of
the spiral changes. 
* The private function `isPrime` checks whether a number is prime or not. While-loops increase "candidate" numbers until
a non-prime number is reached. 

### Identify the partitions

Inputs must be non-negative integers, while per requirements the function should return an empty array if n=0.

The outputs will always be a (one dimensional) array with a length of n^2.

### Analyze the boundaries

It follows that there are two boundaries:
* n=0
* n>0

### Devise test cases

See the test cases in `NonPrimeSpiralMatrixCreatorTest.java`.

### Augment

We added the following test case:
* `testNegativeN` tests the case where n is negative.

## Coverage

After running the test suite, IntelliJ using JaCoCo first didn't report 100% branch and line coverage. This was because
of the line 25 with the `while (left <= right && top <= bottom) {`, where one branch was missed.
We suspect after debugging that because of short-circuiting, the right side (top <= bottom) was not reached when the left side
was already false. We tried to fix this by changing the logical AND (&&) operator to a bitwise AND (&) operator,
and sure enough the condition+line coverage became 100%.

See in the second part of the mutation section, why we discarded this idea and changed it back to the logical AND (&&) operator,
while that makes us not hit exactly 100% coverage, instead we have 100% line and 97% branch coverage.

## Mutation testing

Mutation testing indicated that for the bugfix on line 17 (described below in the Bug reports) for changing the boundary,
some mutations survived. We fixed that by changing the if-condition to `if (n == 0 || n <= -1) {` instead of
`n <= 0 if (n <= 0) {`.

Furthermore, changing the logical AND operator (&&) to a bitwise AND operator (&) on line 25, as described in the Coverage section,
resulted in a mutation which survived. Concretely, the case where the "&" operator in the condition `while (left <= right & top <= bottom) {`
was replaced by a bitwise OR. It survived, because the program does not rely on both of the conditions being true,
as the program still returns the correct arrays, even if this condition is changed to an OR instead of an AND.
That is because of the statements inside the while loop, which ensure that after each iteration, either both sides
are `true` or both sides are `false`. Because it is safer in a while-loop to use the logical AND operator instead
of an OR, we changed it back.

Two other mutations survived, namely for the lines 38 & 46:
* if (bottom >= top) {
* if (right >= left) {

It is unnecessary to write tests for these mutations, because the bottom and top, as well as the right and left, are always
strictly larger than the other, because of the way the loop is constructed (increasing top / decreasing right at the
start of every iteration before those if-conditions). For that reason, it doesn't matter if the condition is larger equal
or strictly larger than the other, because they have the same outcome, hence why the mutations survive. This is also 
the reason behind the problem earlier, with the logical AND operator. 

The result is 97% line & mutation coverage, as well as test strength.

## Bug reports

The boundary in the if-condition, checking if n is valid, was only set to `n == 0` instead of also checking for negative
n's (`n < 0`), so it crashed because later in the program it tried to instantiate a matrix with negative size.
The test case `testNegativeN` discovered that. 

Also, the program wrongly starts from the value 4, which doesn't follow the description of the task. The test case
`test1x1Matrix` discovered that, as well as examining the given example. It should start from 1, which is a valid
non-prime number in the range of 1 and n^2.


