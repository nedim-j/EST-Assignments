package zest;


import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.constraints.LowerChars;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LongestCommonPrefixTest {

    @Test
    public void testExamples() {
        String[] input = {"flower", "flow", "flight"};
        String expected = "fl";
        String result = LongestCommonPrefix.lcp(input);
        assertEquals(expected, result);
    }

    @Test
    public void testOneString() {
        String[] input = {"abc"};
        String expected = "abc";
        String result = LongestCommonPrefix.lcp(input);
        assertEquals(expected, result);
    }

    @Test
    public void testNoCommonPrefix() {
        String[] input = {"cat", "mouse", "dog"};
        String expected = "";
        String result = LongestCommonPrefix.lcp(input);
        assertEquals(expected, result);
    }

    @Test
    public void testNearCommonPrefix() {
        String[] input = {"car", "cardoor", "test"};
        String expected = "";
        String result = LongestCommonPrefix.lcp(input);
        assertEquals(expected, result);
    }

    @Property
    public void testRandomCommonPrefix(@ForAll @Size(min = 2) List<@LowerChars String> stringList) {
        String[] strings = stringList.toArray(new String[0]);

        // make sure, that the first two strings don't have the same first character
        if(strings.length >= 2 && !Objects.equals(strings[0], "") && !Objects.equals(strings[1], "")) {
            if (strings[0].charAt(0) == strings[1].charAt(0)) {
                // Shift the first character of the second string by one character in alphabt
                char firstChar = strings[1].charAt(0);
                char shiftedChar = (char) ((firstChar - 'a' + 1) % 26 + 'a');
                strings[1] = shiftedChar + strings[1].substring(1);
            }
        }

        int prefixLength = (int) (Math.random() * 10);
        String prefix = "";
        for (int i = 0; i < prefixLength; i++) {
            char randomChar = (char) ('a' + (int) (Math.random() * 26));
            prefix += randomChar;
        }

        for (int i = 0; i < strings.length; i++) {
            strings[i] = prefix + strings[i];
        }

        String result = LongestCommonPrefix.lcp(strings);
        assertEquals(prefix, result);
    }

    @Property
    public void testRandomNoCommonPrefix(@ForAll @Size(min = 2) List<@LowerChars String> stringList) {
        String[] strings = stringList.toArray(new String[0]);

        // make sure, that the first two strings don't have the same first character
        if(strings.length >= 2 && !Objects.equals(strings[0], "") && !Objects.equals(strings[1], "")) {
            if (strings[0].charAt(0) == strings[1].charAt(0)) {
                // Shift the first character of the second string by one character in alphabt
                char firstChar = strings[1].charAt(0);
                char shiftedChar = (char) ((firstChar - 'a' + 1) % 26 + 'a');
                strings[1] = shiftedChar + strings[1].substring(1);
            }
        }

        String result = LongestCommonPrefix.lcp(strings);

        assertEquals("", result);
    }
}
