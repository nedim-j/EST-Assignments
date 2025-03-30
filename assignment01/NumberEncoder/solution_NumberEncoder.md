# Solution

## Seven principles of specification testing

### Understand the requirement

The purpose of `NumberEncoder` is to encode a sequence of numerical digits into a sequence of characters based on a
specific mapping or alphabet. For each digit, mapping provides the corresponding character, where the digits are mapped by
the index.

### Explore the program

If it's implemented correctly, the program should in a happy case for the input is a string of number 0-9 and a
mapping "abc...j" return "abc...j" again.

### Identify the partitions

* The input number can be any string of numbers, including empty strings.
* The input mapping has to be an array of characters with a length of 10.
  * If the mapping is shorter (or longer) than 10, an ArrayIndexOutOfBoundsException should be thrown.

### Analyze the boundaries

* The input number can be any string of digits, including empty strings. There is no distinction between 0 or n amount of digits.
* The mapping has to be an array of characters with a length of 10. If the mapping is shorter (or longer) than 10 (or null),
an ArrayIndexOutOfBoundsException should be thrown.

### Devise test cases

See the test cases in `NumberEncoderTest.java`.

### Augment

The test `testMappingGreaterThanTen` has been added, to also test for the opposite of the given requirement of testing
against a mapping of less than 10 characters, which should throw an error. 

The tests `testNumberNull` and `testMappingNull` have been added to test for the case where the input number or mapping.
For that, we added if-conditions to return an empty string if the input number is null and to throw an ArrayIndexOutOfBoundsException
if the mapping is null (like when the length isn't equal to 10, per the requirements), to satisfy the mutation testing.

## Coverage

After running the test suite, IntelliJ using JaCoCo reports 100% condition+line coverage.

## Mutation testing

PITest indicates that the mutation coverage & test strength is 100%.

## Bug reports

The program has not been fully implemented. Concretely, the errors that the requirements specify are not thrown.

For that, we added the following if-conditions:

To throw an ArrayIndexOutOfBoundsException if the mapping is shorter than 10 characters:

```java
        if (mapping.length < 10) {
        throw new ArrayIndexOutOfBoundsException();
        }
```

To throw an IllegalArgumentException if the input contains invalid characters, like letters or special characters (negative
numbers):

```java
        if (!Character.isDigit(number.charAt(i))) {
        throw new IllegalArgumentException("Input contains invalid characters.");
        }
```

