# Solution

## Seven principles of specification testing

### Understanding the requirement

The method receives two inputs:
- String `start`, which is the starting string we want to transform.
- String `target`, which is the target string we want to transform the starting string into.

The method returns an integer as output:
- The minimum number of operations needed to transform the starting string into the target string. For this, we can perform three operations:
  - Replace a character.
  - Insert a character.
  - Delete a character.

From the specification, the function should also handle cases where either the start or target string is empty. The method is using a dynamic programming approach, which means that the method will calculate the minimum number of operations for each combination of substrings of the start and target strings to find the minimum number of operations for the whole strings.

### Explore the program

I expect the program to return the correct number of operations needed to transform a starting string to a target string.
I expect the program to return 0 when the starting string is the same as the target string.
If the starting string is empty, I expect the program to return the length of the target string and vice versa. 

Based on these expectations, I have designed the following test cases:
- `sameString` passes two identical strings.
- `emptyStart` passes an empty starting string.
- `emptyTarget` passes an empty target string.
- `differentStringsOfSameLength` passes two different strings of the same length.
- `differentStringsOfDifferentLength` passes two different strings of different lengths.

### Identify the partitions

Inputs:
- String start:
  - Null string
  - Empty string
  - String of length 1
  - String of length > 1
- String target:
  - Null string
  - Empty string
  - String of length 1
  - String of length > 1

Combination of Inputs:
- Start and target strings are the same.
- Start string is empty, target string is non-empty.
- Start string is non-empty, target string is empty.
- Start and target strings are different and non-empty, but of same length.
- Start and target strings are different and non-empty, but of different lengths.

Outputs:
- int minimum number of operations:
  - 0
  - `> 0`


### Analyze the boundaries

- String passing from null to empty.
  - The question arises how to handle null strings. Should it be treated as an empty string, or should it return a value such as -1 to indicate that the input is invalid? I will treat null strings as invalid inputs and expect a return value of -1.
- String passing from empty to non-empty.
- Start to target transformation only needing one of the three operations (Insert, delete, replace).


### Devise test cases

- `sameString` passes two identical strings.
- `emptyStart` passes an empty starting string.
- `emptyTarget` passes an empty target string.
- `differentStringsOfSameLength` passes two different strings of the same length.
- `differentStringsOfDifferentLength` passes two different strings of different lengths.
- `nullStart` passes a null starting string.
- `nullTarget` passes a null target string.
- `nullStartAndTarget` passes a null starting and target string.
- `insertOperation` passes a string that needs only an insert operation.
- `deleteOperation` passes a string that needs only a delete operation.
- `replaceOperation` passes a string that needs only a replace operation.
- `multipleOperation` passes a string that needs a combination of operations.

### Automate the Test Cases

Not much to say here. Implemented the devised test cases in the `StringTransformerTest.java` file.


### Augment

I have decided to augment the test cases with the following:

- `caseSensitive` passes two strings that are equivalent but one is in uppercase and the other in lowercase. This is to test whether the method is capable of distinguishing between uppercase and lowercase characters.

## Coverage

The jacoco report reports a test coverage of 97% for the class and 100% for the method `minOperations()`. We are not achieving 100% because jacoco reports that line 3 (`public class StringTransformer`) has not been tested. We could achieve 100% coverage by also testing the class instantiation, but since we are testing a class with only one static method, we do not need to test class instantiation.

## Mutation Testing

Running the pitest mutation testing tool, we have killed 24 out of 24 mutations, achieving 100% mutation coverage.

## Bug Reports

### Null array

The method cannot handle cases where either input is `null`. In the test cases `nullStart`, `nullTarget` and `nullStartAndTarget`, the method throws a `NullPointerException`. To fix this exception, I have added an if-statement to check whether either input is `null` and returns -1 if true.