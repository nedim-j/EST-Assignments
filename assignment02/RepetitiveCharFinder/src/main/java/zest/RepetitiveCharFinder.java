package zest;
import java.util.*;

public class RepetitiveCharFinder {

    /**
     * Finds and returns a list of non-unique characters from the given string.
     * Each character that appears more than once is included in the list, without duplicates,
     * and in the order of their first appearance.
     *
     * @param input the string to check for repeated characters
     * @return a list of repeated characters
     */
    public static List<Character> findNonUniqueCharacters(String input) {

        // Pre-condition
        if (input == null) {
            throw new IllegalArgumentException();
        }

        for (char c : input.toCharArray()) {
            if (c < 0 || c > 127) {
                throw new IllegalArgumentException();
            }
        }

        Map<Character, Integer> characterCounts = new LinkedHashMap<>(); // Preserves insertion order
        List<Character> nonUniqueCharacters = new ArrayList<>();

        for (char c : input.toCharArray()) {
            characterCounts.put(c, characterCounts.getOrDefault(c, 0) + 1);

            // Invariant check: character count should always be >= 1
            if (characterCounts.get(c) < 1) {
                throw new IllegalStateException();
            }
        }

        for (Map.Entry<Character, Integer> entry : characterCounts.entrySet()) {
            if (entry.getValue() > 1) {

                // Invariant check: duplicates?
                if (nonUniqueCharacters.contains(entry.getKey())) {
                    throw new IllegalStateException();
                }


                nonUniqueCharacters.add(entry.getKey());
            }
        }

        // Post-condition
        // if input is empty, nonUniqueCharacters list should be empty
        if(input.isEmpty()) {
            if(!nonUniqueCharacters.isEmpty()) {
                throw new IllegalStateException();
            }
        }

        // check that the characters in nonUniqueCharacters are in the input at least twice
        for(Character c : nonUniqueCharacters) {
            int count = 0;
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == c) {
                    count++;
                }
            }
            if (count < 2) {
                throw new IllegalStateException();
            }
        }

        return nonUniqueCharacters;
    }


}


