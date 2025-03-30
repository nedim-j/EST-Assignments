package zest;

public class NumberEncoder {
    /**
     * Encodes a numeric string into a new string based on character mapping.
     * @param number the numeric string to encode.
     * @param mapping the character array where each index corresponds to a digit.
     * @return the encoded string.
     * @throws IllegalArgumentException if non-numeric or negative characters are included.
     * @throws ArrayIndexOutOfBoundsException if mapping array is less than 10 characters.
     */
    public static String encodeNumber(String number, char[] mapping) {
        // Solution: Added a check for null number.
        if (number == null){
            return "";
        }

        // Solution: Added a check for the length of the mapping array.
        // If it is less than 10 characters or null, throw ArrayIndexOutOfBoundsException.
        if (mapping == null || mapping.length != 10) {
            throw new ArrayIndexOutOfBoundsException();
        }

        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            // Solution: Added a check for non-numeric or negative characters.
            // Throw IllegalArgumentException if any are found, with message "Input contains invalid characters."
            if (!Character.isDigit(number.charAt(i))) {
                throw new IllegalArgumentException("Input contains invalid characters.");
            }
            int digit = Character.getNumericValue(number.charAt(i));
            encoded.append(mapping[digit]);
        }
        return encoded.toString();
    }
}
