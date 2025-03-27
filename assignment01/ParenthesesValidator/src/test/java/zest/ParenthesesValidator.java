package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParenthesesValidatorTest {

  @Test
  void simpleMatch() {
    assertTrue(ParenthesesValidator.isValid("()"));
    assertTrue(ParenthesesValidator.isValid("[]"));
    assertTrue(ParenthesesValidator.isValid("{}"));
  }

  @Test
  void simpleMismatch() {
    assertFalse(ParenthesesValidator.isValid("(]"));
  }

  @Test
  void multipleMismatch() {
    assertFalse(ParenthesesValidator.isValid("([)]"));
  }

  @Test
  void missingClosing() {
    assertFalse(ParenthesesValidator.isValid("("));
  }

  @Test
  void missingOpening() {
    assertFalse(ParenthesesValidator.isValid(")"));
  }

  @Test
  void multipleMatch() {
    assertTrue(ParenthesesValidator.isValid("()[]{}"));
  }

  @Test
  void multipleNested() {
    assertTrue(ParenthesesValidator.isValid("{[()]}"));
  }

  @Test
  void emptyString() {
    assertTrue(ParenthesesValidator.isValid(""));
  }

  @Test
  void nullString() {
    assertFalse(ParenthesesValidator.isValid(null));
  }

  @Test
  void invalidCharacter() {
    assertFalse(ParenthesesValidator.isValid("a"));
  }

  @Test
  void invalidCharacterInbetween() {
    assertFalse(ParenthesesValidator.isValid("(a)"));
  }

  @Test
  void maxString() {
    assertTrue(ParenthesesValidator.isValid(maxString(Integer.MAX_VALUE / 4)));
  }

  @Test
  void whiteSpace() {
    assertFalse(ParenthesesValidator.isValid(" "));
  }


  private static String maxString(int n) {
    return "()".repeat(Math.max(0, n));
  }

}