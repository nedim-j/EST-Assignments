package zest;

import org.junit.jupiter.api.Test;

class PrimeDistanceFinderTest {
  @Test
  public void twoPrimes() {
    int[] input = {3, 7};
    int expected = 4;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void onePrime() {
    int[] input = {4, 7};
    int expected = -1;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void noPrimes() {
    int[] input = {4, 6};
    int expected = -1;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void multiplePrimes() {
    int[] input = {3, 7, 11, 13};
    int expected = 2;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void nullArray() {
    int[] input = null;
    int expected = -1;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void emptyArray() {
    int[] input = {};
    int expected = -1;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void oneNonPrime() {
    int[] input = {4};
    int expected = -1;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void duplicatePrime() {
    int[] input = {3, 3, 5};
    int expected = 0;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void negativePrimes() {
    int[] input = {-3, -7};
    int expected = -1;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void mixPrimeNonPrime() {
    int[] input = {-1, 3, 4, 5, 6};
    int expected = 2;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void unsortedPrimes() {
    int[] input = {7, 3, 13};
    int expected = 4;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }

  @Test
  public void primeWithOne() {
    int[] input = {1, 3};
    int expected = -1;
    int actual = PrimeDistanceFinder.findSmallestPrimeDistance(input);
    assert actual == expected;
  }
}