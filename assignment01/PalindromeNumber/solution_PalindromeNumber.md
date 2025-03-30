# Solution

## Seven principles of specification testing

### Understand the requirement

The purpose of `PalindromeNumber` is to determine whether an input integer is a palindrome or not.

### Explore the program

The function first checks whether the given number is negative, in which case it immediately returns false.
Then it initializes two pointers, one at the beginning of the number and one at the end, and compares the two current digits
moving to the middle of the number with each iteration. If one digit pair is not equal the function returns false.
If the pointers meet in the middle, the function returns true.

### Identify the partitions

The input can be any integer, positive, negative or zero. Only negative numbers are not palindromes.

### Analyze the boundaries

* The input number can be any valid integer from -2147483648 to 2147483647 (Integer.MIN_/MAX_VALUE),
with null not being a valid input.
* Negative numbers are no palindromes, so there is a boundary around 0.

### Devise test cases

See the test cases in `PalindromeNumberTest.java`. Note that the integer boundaries are not really necessary to test
in the real world for this function, as it is very simple and doesn't have any edge cases that would be affected by the
boundaries, but it is in the requirements. It's impossible to test for an int underflow or overflow, as the function
wouldn't even be called with such a small / large number, unless we change the datatype of the input to long for example.

### Augment

No specific test cases have been added.

## Coverage

After running the test suite, IntelliJ using JaCoCo reports 100% condition+line coverage.

## Mutation testing

PITest indicates that the mutation coverage & test strength is 91%. It doesn't make sense to get it to 100% because the
only mutation the change of boundary in the while-loop on line 16 `while (left < right) {`. Changing the boundary
to `while (left <= right) {` would have the same effect as the original, as it is for the loop to stop when the left
pointer and the right pointer meet. Having them meet at the same index (<= boundary) or one before (< boundary) doesn't
matter, as one digit is a palindrome with itself, so it would just introduce one extra iteration in the first case.

To kill the changed-boundary mutation on line 10 though, we added the tests `testZeroDigitNumber`, `testMinusOneNumber`
and `testSingleDigitNumber` to cover the edge cases of the input number being 0, -1 and 1, respectively.


## Bug reports

No bugs have been found. 
