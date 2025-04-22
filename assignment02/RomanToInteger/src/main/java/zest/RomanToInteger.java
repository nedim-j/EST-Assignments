package zest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RomanToInteger {
    // Map to store Roman numerals and their corresponding integer values
    private static final Map<Character, Integer> romanMap = new HashMap<>();
    private static final Set<String> validSubtractions = Set.of("IV", "IX", "XL", "XC", "CD", "CM");

    static {
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
    }

    public int romanToInt(String s) {
        // Pre-condition checks
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        for (char c : s.toCharArray()) {
            if (!romanMap.containsKey(c)) {
                throw new IllegalArgumentException("Invalid Roman numeral character: " + c);
            }
        }

        for (int i = 0; i + 1 < s.length(); i++) {
            int current = romanMap.get(s.charAt(i));
            int next = romanMap.get(s.charAt(i+1));
            if (current < next) {
                String pair = s.substring(i, i+2);
                if (!validSubtractions.contains(pair)) {
                    throw new IllegalArgumentException("Invalid subtractive pair: " + pair);
                }
            }
        }

        String clonedString = new String(s);
        int result = romanMap.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; i--) {
            if (romanMap.get(s.charAt(i)) < romanMap.get(s.charAt(i + 1))) {
                result -= romanMap.get(s.charAt(i));
            } else {
                result += romanMap.get(s.charAt(i));
            }
        }

        // Post-condition check
        if (result < 1 || result > 3999) {
            throw new IllegalArgumentException("The input string is not between 1 and 3999.");
        }

        // Invariant check
        if (!clonedString.equals(s)) {
            throw new RuntimeException("The input string was modified during conversion.");
        }

        return result;
    }
}
