package zest;

public class BitwiseSumCalculator {

    /**
     * Computes the sum of two integers using bitwise operations.
     * @param a The first integer.
     * @param b The second integer.
     * @return The sum of a and b using bitwise operations.
     */
    public static int getSum(int a, int b) {
        // Saves a and b for the post-condition check later on
        int initialA = a;
        int initialB = b;

        while (b != 0) {
            int carry = (a & b) << 1;  // Carry is AND of two bits
            a = a ^ b;                 // Sum of bits where at least one of the bits is not set
            b = carry;                 // Carry is shifted by one so that adding it to a gives the required sum

            // Invariant
            if (initialA + initialB != a + b) {
                throw new ArithmeticException();
            }
        }

        // Post-conditions
        if (initialA + initialB != a) {
            throw new ArithmeticException();
        }

        return a;
    }
}

