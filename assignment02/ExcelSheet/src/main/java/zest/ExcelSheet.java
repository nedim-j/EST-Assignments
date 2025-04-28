package zest;

public class ExcelSheet {

    /**
     * Converts a column title from an Excel sheet to its corresponding column number.
     *
     * @param columnTitle A string representing the column title (e.g., "A", "AB", "ZY").
     * @return The corresponding column number.
     */
    public static long titleToNumber(String columnTitle) {
        // Pre-conditions
        if(columnTitle.isEmpty() || columnTitle.length() > 7) {
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < columnTitle.length(); i++) {
            char currentChar = columnTitle.charAt(i);
            if (currentChar < 'A' || currentChar > 'Z') {
                throw new IllegalArgumentException();
            }
        }


        long result = 0;
        int length = columnTitle.length();

        for (int i = 0; i < length; i++) {
            char currentChar = columnTitle.charAt(i);
            long value = currentChar - 'A' + 1; // Convert character to number (A=1, B=2, ..., Z=26)
            result = result * 26 + value; // Multiply current result by 26 (base) and add the current value

            // Invariant
            if (result < 1 || result > 8353082582L) {
                throw new IllegalArgumentException();
            }
        }

        // Post-condition
        if (result < 1 || result > 8353082582L) {
            throw new IllegalArgumentException();
        }

        return result;
    }
}
