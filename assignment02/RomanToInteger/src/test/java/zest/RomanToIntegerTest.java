package zest;


import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RomanToIntegerTest {
  private final RomanToInteger romanToInteger = new RomanToInteger();
  private static final Map<Character, Integer> romanMap = new HashMap<>();

  static {
    romanMap.put('I', 1);
    romanMap.put('V', 5);
    romanMap.put('X', 10);
    romanMap.put('L', 50);
    romanMap.put('C', 100);
    romanMap.put('D', 500);
    romanMap.put('M', 1000);
  }

  @Test
  void nullCase() {
    String input = null;
    assertThrows(IllegalArgumentException.class, () -> romanToInteger.romanToInt(input));

  }

  @Test
  void emptyCase() {
    String input = "";
    assertThrows(IllegalArgumentException.class, () -> romanToInteger.romanToInt(input));

  }

  @Test
  void complex() {
    String input = "MCMXCIV";
    int result = romanToInteger.romanToInt(input);
    assertEquals(1994, result);
  }

  @Test
  void maximum() {
    String input = "MMMCMXCIX";
    int result = romanToInteger.romanToInt(input);
    assertEquals(3999, result);
  }

  @Test
  void invalidChar() {
    String input = "123";
    assertThrows(IllegalArgumentException.class, () -> romanToInteger.romanToInt(input));
  }

  @Test
  void outOfMaxRange() {
    String input = "MMMM";
    assertThrows(IllegalArgumentException.class, () -> romanToInteger.romanToInt(input));
  }

  @Test
  void outOfMinRange() {
    String input = "-X";
    assertThrows(IllegalArgumentException.class, () -> romanToInteger.romanToInt(input));
  }

  @Test
  void invalidSubtraction() {
    String input = "IM";
    assertThrows(IllegalArgumentException.class, () -> romanToInteger.romanToInt(input));
  }

  @Test
  void inputNotChanged() {
    String input = "MCMXCIV";
    String clonedString = new String(input);
    int result = romanToInteger.romanToInt(input);
    assertEquals(input, clonedString);
  }

  @Provide
  Arbitrary<Character> singleRomanNumeral() {
    return Arbitraries.of('I', 'V', 'X', 'L', 'C', 'D', 'M');
  }

  @Provide
  Arbitrary<String> additiveRomanNumeral() {
    return Arbitraries.of("II", "III", "XX", "LL", "CC", "DD", "MM");
  }

  @Provide
  Arbitrary<String> subtractiveRomanNumeral() {
    return Arbitraries.of("IV","IX","XL","XC","CD","CM");
  }

  @Property
  void singleLetter(@ForAll("singleRomanNumeral") Character input) {
    assertEquals(romanMap.get(input), romanToInteger.romanToInt(String.valueOf(input)));
  }

  @Property
  void addition(@ForAll("additiveRomanNumeral") String s) {
    int result = romanToInteger.romanToInt(s);
    int expected = 0;
    for (char c : s.toCharArray()) {
      expected += romanMap.get(c);
    }
    assertEquals(expected, result);
  }

  @Property
  void subtraction(@ForAll("subtractiveRomanNumeral") String s) {
    int left = romanMap.get(s.charAt(0));
    int right = romanMap.get(s.charAt(1));
    int result = romanToInteger.romanToInt(s);
    assertEquals(result, right - left);
  }
}
