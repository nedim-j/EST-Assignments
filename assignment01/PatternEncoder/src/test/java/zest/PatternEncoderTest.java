package zest;

import org.junit.jupiter.api.Test;

import java.util.Objects;

class PatternEncoderTest {
  @Test
  public void singleCompressibleString() {
    String input = "aaaaa";
    String expected = "5[a]";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void nonCompressibleString() {
    String input = "abc";
    String expected = "abc";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void nonCompressibleSizeString() {
    String input = "abab";
    String expected = "abab";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void whitespaceString() {
    String input = "     ";
    String expected = "5[ ]";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void uppercaseString() {
    String input = "AAAAA";
    String expected = "5[A]";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void specialCharacterString() {
    String input = "!!!!!";
    String expected = "5[!]";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void emptyString() {
    String input = "";
    String expected = "";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void nullString() {
    String input = null;
    String expected = null;
    String actual = PatternEncoder.encodeString(input);
    assert Objects.equals(expected, actual);
  }

  @Test
  public void singleCharacterString() {
    String input = "a";
    String expected = "a";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void multipleCompressibleStrings() {
    String input = "abababccccc";
    String expected = "3[ab]5[c]";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void compressibleAndNonCompressibleStrings() {
    String input = "abababcccccabc";
    String expected = "3[ab]5[c]abc";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void boundarySubstringLength() {
    String input = "aaaa";
    String expected = "aaaa";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }

  @Test
  public void mixedCaseString() {
    String input = "aaAAaa";
    String expected = "aaAAaa";
    String actual = PatternEncoder.encodeString(input);
    assert expected.equals(actual);
  }
 
}