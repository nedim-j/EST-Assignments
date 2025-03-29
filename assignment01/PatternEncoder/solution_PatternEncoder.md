# Solution

## Seven principles of specification testing

### Understanding the requirement

The method receives one input:
- A string `s` containing lowercase characters and whitespaces, but no uppercase characters.

The method returns a string as output:
- The compressed version of the input string, where repeated substrings are compressed with the number of times it is repeated and the substring within brackets.

The goal of this function is to do lossless compression on an input string so that we can represent them more compactly, so the length of the output string must be less than the length of the input string.
The method therefore must also consider that the compressed string should be as short as possible, and that if the compression does not reduce it, it should not compress the substring. Example: "aa" should not be compressed to "2[a]", since the compressed String has length 4, while the input String has length 2.

### Explore the program

I expect the program to return the compressed String correctly when I pass a String of only lowercase characters or a string that contains also whitespaces.
Given the Specification it is not sure how the program handles cases with uppercase characters, so I aim to explore its behavior containing also uppercase characters, and also special characters.
I also expect the program to not compress substrings, when the total length of the repeated substrings is <= substring + 3, because we need to account for the brackets and the number of repetitions in the compressed representation of the String.

Based on these expectations, I have designed the following test cases:
- `compressibleString` passes a string with a single compressable substring.
- `nonCompressibleString` passes a string with a single non-compressable substring.
- `whitespaceString` passes a string with white spaces.
- `uppercaseString` passes a string with uppercase characters.
- `specialCharacterString` passes a string with special characters.

### Identify the partitions

Inputs:

- String s:
  - Null String
  - Empty String
  - String of length 1
  - String of length > 1

Combining the input with the specification, we can identify more partitions:
- String containing a compressible substring
- String containing a non-compressible substring (i.e. length of repeated substring <= length of substring + 3)
- String containing both a compressible and non-compressible substring


### Analyze the boundaries

- The String passing from Empty Length to Length of 1.
- The String passing from the length of repeated substrings = length of substring + 3 to length of repeated substrings > length of substring + 3.


### Devise test cases

- `singleCompressibleString` passes a string with a single compressable substring.
- `nonCompressibleString` passes a string with a single non-compressable substring.
- `whitespaceString` passes a string with white spaces.
- `uppercaseString` passes a string with uppercase characters.
- `specialCharacterString` passes a string with special characters.
- `emptyString` passes an empty string.
- `nullString` passes a null string.
- `singleCharacterString` passes a string with a single character.
- `multipleCompressibleStrings` passes a string with multiple compressable substrings.
- `compressableAndNonCompressableStrings` passes a string with both compressable and non-compressable substrings.
- `boundarySubstringLength` passes a string with length of repeated substrings = length of substring + 3.


### Automate the Test Cases

Not much to say here. Implemented the devised test cases in the `PatternEncoderTest.java` file.


### Augment

I have decided to augment the test cases with the following:
- `mixedCaseString` passes a string of the same characters in both uppercase and lowercase to test whether the method handles case sensitivity correctly in compression.


## Coverage

The jacoco report reports a test coverage of 97% for the class and 100% for the method encodeString(). We are not achieving 100% because jacoco reports that line 3 (public class PatternEncoder) has not been tested. We could achieve 100% coverage by also testing the class instantiation, but since we are testing a class with a single static method, we do not need to test class instantiation.


## Mutation Testing

Running the pitest mutation testing tool, we have killed 36 out of 39 mutations, achieving 92% mutation coverage.

The mutations that did fail were changed conditional boundary mutations. For example, on line 21, where we dont check each substring length anymore due to the change from <= to <, but this mutation has no effect on the correct output of the method, as the final iteration of that for loop would only check the entire remaining length of the string for repeats, but by definition there are no repeats and we would be forced to write the entire string anyway. Therefore they do not change the semantics of the program.
The second changed boundary mutation occurs on line 31, where again semantically it does not change the output of the program because we are simply replacing the maximum run length with another one of same runlength, therefore the two outputs would be equivalent in terms of length.
The third changed boundary mutation occurs at line 37, where we would only proceed in the compression of the substring if its runlength is larger 1. This again does not change the output semantically, as on line 41 we are checking whether the encoding is shorter than the substring, and with a run length of 1, it will never fulfill that condition and therefore append the single character.


## Bug Reports

### Not compressing substrings that consist of multiple characters

In the test cases `multipleCompressibleStrings` which consists of "abababccccc", the method does not compress the substring "ababab" to "3[ab]", which would be a shorter representation of that substring.
To fix this bug, I have added another for loop that iterates over each possible substring length that then applies the same logic as the original method to check whether the substring repeats.

### Compressing substrings resulting in the compressed string being longer than the original string

When we have a repeated substring whose length is <= the length of the substring + 3, the method should not apply the compression because the compression would be the same length or longer due to the fact that we have to add 3 extra characters to apply the compression. This does not happen and the test case `nonCompressibleSizeString` fails.
To fix this bug, we need to add a check before appending the compression that checks whether the string would actually be lower.