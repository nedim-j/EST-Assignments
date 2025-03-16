# Solution

## Seven principles of specification testing

### Understand the requirement
The objective of the `fractionToDecimal` function is to determine the decimal form of a fraction and 
return it as a string, where a repeating part is enclosed in parentheses.

### Explore the program
If `denominator` is 0, the program returns null.

If either `numerator` or `denominator` is less than zero (but not both), a `-` is appended to the 
beginning of the resulting string.

Afterwards, the absolute value of both the `numerator` and `denominator` is determined and the integer 
part of the decimal value is calculated and appended to `result`. The program then calculates the remainder. 
If the remainder is `0`, just the integer part is returned.

If the remainder is not `0`, `.` is appended to the end of `result` and the program tries to find a 
repeating part to insert curly braces.

In the end, the program returns `result`.

### Identify the partitions

The `denominator` can either be `0` or something else.

In the first partition, `null` is returned, while in the second partition, the program returns 
either the `result` string.

We also have the case, where either the `numerator` or the `denominator` is negative, making 
the resulting string also negative.

### Analyze the boundaries

`denominator` is `0` and `denominator` is not `0`.

`numerator` XOR `denominator` is negative.

### Devise test cases

- `testBasicCase` passes a basic `numerator` and `denominator`.

- `testZeroDenominator` passes a `denominator` with value `0`.

- `testNegativeNumerator` passes a negative `numerator`.

- `testNegativeDenominator` passes a negative `denominator`.

- `testNegativeNumeratorAndDenominator` passes both a negative `numerator` and a negative 
`denominator`.

### Augment

One additional test case was added:

- `testLongRepetition` passes a `numerator` and `denominator` such that the decimal part 
is long.
- `testNoRemainder` passes parameters such that no remainder exists.

## Coverage

After running the test suite, IntelliJ reports 100% line coverage.

## Mutation testing

After mutation testing, we have 89% mutation coverage. Two mutations survived because 
the boundaries on line 20 was changed. We can kill one of these mutations by introducing 
the test `testZeroNumerator` which passes a `numerator` with a `0` value.

The second mutation, which changes `denominator < 0` to `denominator <= 0`, cannot be 
killed because we already return `null` if the `denominator` is `0`.

After running mutation testing again, we have 94% mutation coverage.

## Bug reports

The implementation of `fractionToDecimal` does not contain bugs.