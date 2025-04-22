# Solution

## Designing Contracts

### Pre-conditions:
- `s` must be a number between 1 and 3999.
- `s` must be a valid Roman numeral characters (I, V, X, L, C, D, M).
- `s` must be a non-empty string.
- `s` must not be null

### Post-conditions:
- The output must be the correct decimal value of the Roman numeral
- The output must be an integer between 1 and 3999.

### Invariants:
- Each Roman numeral character always corresponds to its correct Integer value.
- `s` is never changed.

### Implementation:

#### Pre-conditions:
- Added a check at the end if the result is inbetween 1 and 3999,
- Added a check at the start if the input is not null or empty
- Added a for loop that checks whether each char of the input is a valid Roman numeral.

#### Post-conditions:
- Added a check at the end if the result is inbetween 1 and 3999.
- The original implementation already covers the conversion from Roman numeral to Integer.

#### Invariants:
- The original implementation has a hard-coded map that maps the Roman numeral character to their Integer values.
- Added a check at the end that checks whether `s` has been changed.

## Testing Contracts:
Based on the specified contract in the previous Task, we have come up with the following test cases:
- `null`: Test with a null input string. This should throw some exception or return value representing an error.
- `empty`: Test with an empty string. This should throw some exception or return value representing an error.
- `singleLetter`: Test with a single valid Roman numeral character.
- `addition`: Test with a valid Roman numeral string that contains addition (such as III).
- `subtraction`: Test with a valid Roman numeral string that contains subtraction (such as IV).
- `complex`: Test with a complex Roman numeral string that contains both addition and subtraction (such as CCIX).
- `maximum`: Test with the maximum valid Roman numeral (MMMCMXCIX).
- `invalidChar`: Test with a String containing invalid characters (such as 123).
- `outOfMaxRange`: Test with a Roman numeral string that is out of the upper valid range (such as MMMM).
- `outOfMinRange`: Test with a Roman numeral string that is out of the lower valid range (such as -X).
- `invalidSubtratction`: Test with a valid Roman numeral string that contains invalid subtraction pairs (such as IM).
- `inputNotChanged`: Test with a valid Roman numeral string to ensure that the input string is not modified during the conversion process.


## Property Based Testing:
We have defined the following properties:
- For a valid Roman numeral string, the output must be an integer between 1 and 3999.
- The output must be the correct decimal value of the Roman numeral.

For this, we have defined the following tests. If a test has the same name as from the Testing Contract, it means we have adapted its test into a property-based test.
- `singleLetter`: Test with a single valid Roman numeral character.
- `addition`: Test with a valid Roman numeral string that contains addition (such as III).
- `subtraction`: Test with a valid Roman numeral string that contains subtraction (such as IV).


## Code Coverage:

The test coverage is 97% for the `romanToInt` function. The reason for this is because we never reach the invariant check that the input string has been modified, which is good.


## Bug Reports:

### Invalid Subtraction:
- The original implementation does not handle invalid subtraction cases correctly. For example, the input "IM" should be invalid.
To fix this, we have added a Set of valid Subtractions that will be checked against before we compute the result.