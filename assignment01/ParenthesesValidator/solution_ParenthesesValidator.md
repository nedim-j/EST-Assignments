# Solution

## Seven principles of specification testing

### Understand the requirement

The objective of the `isValid` method is to check if a given string, which only contains the characters '(', ')', '{', '}', '[' and ']', is valid on conditions that open brackets must be closed by the same type of brackets and in the correct order.

The method receives one input:
- A string `s` containing only characters: `(`, `)`, `{`, `}`, `[` and `]`.

The method returns a boolean value:
- `true` if the string is valid as per the rules above.
- `false` otherwise.

### Explore the program

Based on the Specification of the Program, I will be exploring the code to see how some basic inputs are handled to gain more understanding.
I expect the program to return `true` for basic cases when there is only one matching opening and closing bracket, such as `()`, `[]`, and `{}`.
I also expect the program to return `false` for cases where the brackets are not correctly matched, such as `(]`, or when there is a missing opening or closing bracket, such as `(` or `)`.

Based on these expectations, I have designed the following test cases:

- `simpleMatch` passes a string with a single matching pair of brackets.
- `multipleMatch` passes a string with multiple pairs of matching brackets.
- `simpleMismatch` passes a string with a single mismatched pair of brackets.
- `missingOpening` passes a string with a missing opening bracket.
- `missingClosing` passes a string with a missing closing bracket.

### Identify the partitions

Inputs:

- str s - The string should contain only valid bracket types as mentioned earlier. According to the specification, the empty string should be handled as a valid string. There is no mention of the null case, but we should test it anyway. A string can also have a length of 1, or length > 1. The string should contain only valid bracket types, but a string can also contain other characters.
    - Null string
    - Empty string
    - String containing an opening bracket, but no closing bracket
    - String containing a closing bracket, but no opening bracket
    - String containing a single matching pair of brackets (Length = 2)
    - String containing multiple matching pairs of brackets (Length > 4)
    - String containing multiple nested pairs of brackets (Length >= 4)
    - String containing a single mismatched pair of brackets (Length = 2)
    - String containing multiple mismatched pairs of brackets (Length >= 4)
    - String containing invalid characters.

One could also argue that the single and multiple paris can be combined into one partition: `String is balanced` and `String is unbalanced`, but I have decided to be more granular.

Outputs:

- boolean - The method should return `true` if the string is valid, and `false` otherwise. Given that we only have a single boolean value, the method should only ever return `true` or `false`.$
    - True - The string is valid
    - False - The string is not valid

### Analyze the boundaries

- The String passing from Empty Length to Length of 1.
- The String passing from Length of 1 to Length of 2.
- The String passing from Length of 2 to Length of 4.
- The String with only valid characters to the String containing an invalid character.

### Devise test cases

- `simpleMatch` passes a string with a single matching pair of brackets.
- `multipleMatch` passes a string with multiple pairs of matching brackets.
- `simpleMismatch` passes a string with a single mismatched pair of brackets.
- `missingOpening` passes a string with a missing opening bracket.
- `missingClosing` passes a string with a missing closing bracket.
- `emptyString` passes an empty string.
- `nullString` passes a null string.
- `multipleMismatch` passes a string with multiple mismatched pairs of brackets.
- `multipleNested` passes a string with multiple nested pairs of brackets.
- `invalidCharacter` passes a string with only invalid characters.
- `invalidCharacterInBetween` passes a string with valid and invalid characters.

### Automate the Test Cases

Not much to say here. Implemented the devised test cases in the `ParenthesesValidatorTest.java` file.

### Augment

I have added the following test cases to the test suite:

- `reversedMismatch` passes a string with a pair of brackets in reverse, i.e. `)(`. 
- `whiteSpaces` passes a string with white spaces between the brackets.

## Coverage

Running the Intellij Coverage tool, we are achieving 100% Line and Branch Coverage. But with the Jacoco Plugin, we are achieving 95% Line Coverage and 100% Branch Coverage. This is due to the fact that the line for the Class definition hasnt been tested, but this is not necessary because the class only has a static method, so no instance of the class is created and therefore it is unnecessary to test the class definition line.

## Mutation Testing

Running the pitest mutation testing tool, we have killed 10 out of 10 mutations, achieving 100% mutation coverage.

## Bug Reports

### Missing Imports

The `isValid` method uses the `Stack` class, which is not imported in the class. In turn, I have added the Import Statement for the `Stack` class to the class.

### Null String

If we pass a null string to the `isValid` method, it will throw a `NullPointerException` because the method does not handle the null case. Because there is no mention of how the method should handle a null string in the specification, I have decided that the output for the input of `null` will be `false`, as it is not a String object, so I have added a check for null strings in the beginning of the method.