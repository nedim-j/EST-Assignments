package zest;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.constraints.LowerChars;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.api.constraints.UniqueChars;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.LowerCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RepetitiveCharFinderTest {

   @Test
   public void testExample() {
       String input = "Hello World";
       List<Character> expected = List.of('l', 'o');
       List<Character> result = RepetitiveCharFinder.findNonUniqueCharacters(input);
       assertEquals(expected, result);
   }

    @Test
    public void testNoRepetitiveChars() {
         String input = "abcdefg";
         List<Character> expected = List.of();
         List<Character> result = RepetitiveCharFinder.findNonUniqueCharacters(input);
         assertEquals(expected, result);
    }

    @Test
    public void testCaseSensitivity() {
        String input = "abAB";
        List<Character> expected = List.of();
        List<Character> result = RepetitiveCharFinder.findNonUniqueCharacters(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyString() {
        String input = "";
        List<Character> expected = List.of();
        List<Character> result = RepetitiveCharFinder.findNonUniqueCharacters(input);
        assertEquals(expected, result);
    }

    @Test
    public void testNullInput() {
        String input = null;
        assertThrows(IllegalArgumentException.class, () -> {
            RepetitiveCharFinder.findNonUniqueCharacters(input);
        });
    }

    @Test
    public void testNonASCIIInput() {
        String input = "Hellö Wörld";
        assertThrows(IllegalArgumentException.class, () -> {
            RepetitiveCharFinder.findNonUniqueCharacters(input);
        });
    }

    @Property
    public void testRandomUniqueLowerCaseCharacters(@ForAll @StringLength(min=26) @UniqueChars @LowerChars String input) {
        List<Character> result = RepetitiveCharFinder.findNonUniqueCharacters(input);
        assertEquals(0, result.size());
        assertEquals(result, new ArrayList<>());
    }

    @Property
    public void testRandomNonUniqueLowerCaseCharacters(@ForAll @StringLength(min=26) @UniqueChars @LowerChars String input) {
       String repeated = input + input;
       List<Character> result = RepetitiveCharFinder.findNonUniqueCharacters(repeated);
        assertEquals(input.chars().distinct().count(), result.size());

        List<Character> expected = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            expected.add(input.charAt(i));
        }
        assertEquals(result, expected);
    }

    @Property
    public void testRandomNonUniqueMixedCaseCharacters(@ForAll @StringLength(min=26) @UniqueChars @LowerChars String input) {
        String mix = input + input.toUpperCase();
        List<Character> result = RepetitiveCharFinder.findNonUniqueCharacters(mix);
        assertEquals(0, result.size());
        assertEquals(result, new ArrayList<>());
    }
}
