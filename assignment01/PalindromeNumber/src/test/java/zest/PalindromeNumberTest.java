package zest;

import org.junit.jupiter.api.Test;

class PalindromeNumberTest {

    @Test
    void testPositiveNumber() {
        int x = 121;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == true;
    }

    @Test
    void testNegativeNumber() {
        int x = -121;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == false;
    }

    @Test
    void testNonPalindromeNumber() {
        int x = 123;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == false;
    }

    @Test
    void testSingleDigitNumber() {
        int x = 1;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == true;
    }

    @Test
    void testZeroDigitNumber() {
        int x = 0;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == true;
    }

    @Test
    void testMinusOneNumber() {
        int x = -1;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == false;
    }

    @Test
    void testIntegerMaxValue() {
        int x = Integer.MAX_VALUE;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == false;
    }

    @Test
    void testIntegerMaxValuePlusOne() {
        int x = Integer.MAX_VALUE + 1;
        assert x < 0;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == false;
    }

    @Test
    void testIntegerMinValue() {
        int x = Integer.MIN_VALUE;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == false;
    }

    @Test
    void testLargestPalindrome() {
        int x = 2147447412;
        boolean result = PalindromeNumber.isPalindrome(x);
        assert result == true;
    }

}