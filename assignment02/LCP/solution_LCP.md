# Solution

## Task 2

<b>Pre-conditions: </b><br>

- The input for `lcp` should be an array of strings of which the longest common prefix is to be found. 
  The array must contain at least one string, meaning it cannot be null.
- Every letter in the strings must be a lowercase English letter.

<b>Post-conditions: </b><br>

- The output string should be the prefix in each of the strings.


<b>Invariants: </b><br>

- The prefix can never be longer than the shortest string in the array nor can it be shorter than 0. (already implemented
by the `return "";`" inside the loop)


## Task 3

We needed to make the method in the `LongestCommonPrefix` class static to be able to test them.

We implemented the following tests:

- `testExamples`: We test the given example strings.
- `testOneString`: We test the case where the input array contains only one string.
- `testNoCommonPrefix`: We test the case where the input array contains strings with no common prefix.
- `testNearCommonPrefix`: We test the case where the input array contains some strings with a common prefix but
not all of them.


## Task 1

After executing the tests from task 3, JaCoCo reports 83% line coverage in the `LongestCommonPrefix` class.
The missed lines were only in the pre-/post-condition and invariant checks, which simply throw errors.
We could easily reach 100% coverage by writing some more boilerplate tests.

## Task 4

The following property-based tests have been added:
- `testCommonPrefix`: We take an array of at least 2 random strings and prepend a common prefix to each.
  We check whether the prefixes of the strings in the array is equal to the common prefix by comparing the output of the
    `LongestCommonPrefix` class with the expected prefix. An array of at least 2 elements is chosen, because the
it can happen that in random cases the common prefix is longer than expected, when the first characters of all 
    strings are the same. Furthermore, we often encountered the case where only few equivalent characters were
generated, which lead to a longer common prefix as well. Because of this, we implemented that the first character of
the second string is shifted by one character, if it matches with the first character of the first string.
- `testNoCommonPrefix`: We take an array of at least 2 random strings and check whether the common prefix is empty. 
Like in the property test before, because of random assertion failures, we shifted the first character of the second
string by one character, if it matches with the first character of the first string.

## Bugs

We did not find any bugs in the implementation of the `LongestCommonPrefix` class.
