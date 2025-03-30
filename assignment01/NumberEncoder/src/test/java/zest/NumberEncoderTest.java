package zest;

import org.junit.jupiter.api.Test;

class NumberEncoderTest {

    @Test
    void testEmptyNumber() {
        String number = "";
        char[] mapping = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        String expected = "";
        String result = NumberEncoder.encodeNumber(number, mapping);

        assert result.equals(expected);
    }

    @Test
    void testAlphabetMapping() {
        String number = "0123456789";
        char[] mapping = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        String expected = "abcdefghij";
        String result = NumberEncoder.encodeNumber(number, mapping);

        assert result.equals(expected);
    }

    @Test
    void testNonNumericString() {

        String number = "abc";
        char[] mapping = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        try {
            NumberEncoder.encodeNumber(number, mapping);
            assert false;
        }
        // see if IllegalArgumentException is thrown with the correct message
        catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Input contains invalid characters.");
        }
    }

    @Test
    void testNegativeNumber() {
        String number = "-123";
        char[] mapping = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        try {
            NumberEncoder.encodeNumber(number, mapping);
            assert false;
        } catch (IllegalArgumentException e) {
            assert e.getMessage().equals("Input contains invalid characters.");
        }
    }

    @Test
    void testMappingArrayLessThanTen() {
        String number = "123";
        char[] mapping = {'a', 'b', 'c', 'd'};
        try {
            NumberEncoder.encodeNumber(number, mapping);
            assert false;
        } catch (ArrayIndexOutOfBoundsException e) {
            assert true;
        }
    }

    @Test
    void testMappingArrayGreaterThanTen() {
        String number = "123";
        char[] mapping = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
        try {
            NumberEncoder.encodeNumber(number, mapping);
            assert false;
        } catch (ArrayIndexOutOfBoundsException e) {
            assert true;
        }
    }

    @Test
    void testNumberNull() {
        String number = null;
        char[] mapping = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        String expected = "";
        String result = NumberEncoder.encodeNumber(number, mapping);
    }

    @Test
    void testMappingNull() {
        String number = "123";
        char[] mapping = null;
        try {
            NumberEncoder.encodeNumber(number, mapping);
            assert false;
        } catch (ArrayIndexOutOfBoundsException e) {
            assert true;
        }
    }
}