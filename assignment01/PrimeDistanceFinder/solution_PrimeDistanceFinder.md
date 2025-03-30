# Solution

## Seven principles of specification testing

### Understanding the requirement

The method receives one input:
- An array of integers `nums`, which can include prime and non-prime numbers.

The method returns an integer as output:
- The smallest positive difference between two prime numbers in the array. If there are less than two prime numbers in the array, the method should return -1.

From the specification, it tells me that the code should first find all prime numbers in the array, and then find the smallest difference between any two of them. If there is only one or no prime numbers, it should be handled properly.


### Explore the program

I expect the program to return -1 when I pass an empty array or an array with only one element.
I expect the program to return the smallest difference between two prime numbers when I pass an array with multiple prime numbers.

Based on these expectations, I have designed the following test cases:
- `twoPrimes` passes an array with two prime numbers.
- `onePrime` passes an array with only one prime number.
- `noPrimes` passes an array with no prime numbers.
- `multiplePrimes` passes an array with > 2 prime numbers.


### Identify the partitions

Inputs:
- Array of integers:
  - Null
  - Empty array
  - One integer
  - Multiple integers

Combining the input with the specification, we can identify more partitions:
- Array containing at least two prime numbers
- Array containing one prime number
- Array containing no prime numbers


### Analyze the boundaries

- The array passing from Null to Empty array.
- The array passing from Empty array to One integer.
- The array passing from One integer to Multiple integers.
- The array containing at least two prime numbers.
- The array containing a duplicate prime number.


### Devise test cases

- `twoPrimes` passes an array with two prime numbers.
- `onePrime` passes an array with only one prime number.
- `noPrimes` passes an array with no prime numbers.
- `multiplePrimes` passes an array with > 2 prime numbers.
- `nullArray` passes a null array.
- `emptyArray` passes an empty array.
- `oneNonPrime` passes an array with one non-prime number.
- `duplicatePrime` passes an array with duplicate prime numbers.


### Automate the Test Cases

Not much to say here. Implemented the devised test cases in the `PrimeDistanceFinderTest.java` file.


### Augment

I have decided to augment the test cases with the following:

- `negativePrimes` passes an array with the negative counterpart of the prime numbers, to check whether it can distinguish correctly that the negative value of a prime number is a non-prime.
- `mixPrimeNonPrime` passes an array with both prime and non-prime numbers, to check whether it can also handle more complex cases.


## Coverage

The jacoco report reports a test coverage of 96% for the class and 100% for the methods `findSmallestPrimeDistance()` and `isPrime()` . We are not achieving 100% because jacoco reports that line 7 (`public class PrimeDistanceFinder`) has not been tested. We could achieve 100% coverage by also testing the class instantiation, but since we are testing a class with two static methods, we do not need to test class instantiation.

## Mutation Testing

Running the pitest mutation testing tool, we have killed 22 out of 25 mutations, achieving 88% mutation coverage.

The first mutation that survived was the removal of the call to `Collections.sort(primes)` on line 29. This mutation survived because the test cases did not cover the case where the array of prime numbers is not sorted. I have added an additional test case `unsortedPrimes` to cover this case, and the mutation gets killed.
The second mutation that survived was a changed conditional boundary on line 33, replacing `diff < smallestDiff` with `diff <= smallestDiff`. This mutation survives but it does not affect the output of the method, because the only change happening here is that if two different pairs of prime numbers have the same difference, it now takes the latter pair instead of the first pair that has been checked. The output is still the same.
The third mutation that survived was a changed conditional boundary on line 46, replacing `number <= 1` with `number < 1`. This mutation lets the method `isPrime(number)` continue in the method even if the number is 1, which is not a prime number. it will not enter the for loop because the condition `i * i <= number` will never be met, and therefore `isPrime(1)` will return true. This mutation survives because we have not checked for the case where we have an array with one prime-number and the number 1. I have added an additional test case `primeWithOne` to cover this case, and the mutation gets killed.

## Bug Reports

### Missing imports

The `findSmallestPrimeDistance` method uses the `List`, `ArrayList` and `Collections` classes, which are not imported in the class. In turn, I have added the Import Statement for these classes.

### Null array

The method cannot handle cases where the inout is `null`. In the test case `nullArray`, the method throws a `NullPointerException`. To fix this exception, I have added an if-statement to check whether the input is `null` and returns -1 if true.