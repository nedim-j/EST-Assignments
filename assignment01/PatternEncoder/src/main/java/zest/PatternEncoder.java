package zest;

public class PatternEncoder {
    /**
     * Encodes the input string by compressing consecutive repeated substrings.
     * @param input the string to encode.
     * @return the encoded string.
     */
    public static String encodeString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder encoded = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            int maxRunLength = 1;
            String maxRunSubstring = input.substring(i, i+1);

            // Check each substring length
            for (int subLength = 1; subLength <= (input.length() - i); subLength++) {
                int runLength = 1;
                String substring = input.substring(i, i + subLength);

                // Expand substring as long as it repeats
                while (i + runLength * subLength + subLength <= input.length() &&
                        input.substring(i + runLength * subLength, i + runLength * subLength + subLength).equals(substring)) {
                    runLength++;
                }

                if (runLength > 1 && runLength * subLength > maxRunLength) {
                    maxRunLength = runLength * subLength;
                    maxRunSubstring = substring;
                }
            }

            if (maxRunLength > 1) {
                String encoding = maxRunLength / maxRunSubstring.length() + "[" + maxRunSubstring + "]";

                // Only append the encoding if it is shorter than the substring
                if (encoding.length() < maxRunSubstring.length() * (maxRunLength / maxRunSubstring.length())) {
                    encoded.append(encoding);
                } else {
                    // append the substring by maxRunLength / maxRunSubstring.length() times
                  encoded.append(maxRunSubstring.repeat(maxRunLength / maxRunSubstring.length()));
                }
                i += maxRunLength;
            } else {
                encoded.append(input.charAt(i));
                i++;
            }
        }
        return encoded.toString();
    }
}
