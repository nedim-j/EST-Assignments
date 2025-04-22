package zest;

import java.util.Arrays;

class ZeroesToEnd {

    public int[] pushZeroesToEnd(int arr[]) {

        // Pre-Condition checks
        if (arr == null) {
            throw new IllegalArgumentException("The Input must be non-null.");
        }
        if(arr.length == 0 || arr.length > 10){
            return new int[0];
        }

        int[] original = arr.clone();

        int temp[] = new int[arr.length];
        int t = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                temp[t] = arr[i];
                t += 1;
            }
        }
        while (t < arr.length){
            temp[t] = 0;
            t += 1;
        }

        // Post-Condition checks
        if (temp.length != arr.length) {
            throw new RuntimeException("Post-Condition Check failed: The length of the output array is not equal to the input array.");
        }

        if (temp == null) {
            throw new RuntimeException("Post-Condition Check failed: The output array is null.");
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != temp[i]) {
                boolean foundElement = false;
                for (int j = 0; j < arr.length; j++) {
                    if (arr[j] == temp[i]) {
                        foundElement = true;
                        break;
                    }
                }
                if (!foundElement) {
                    throw new RuntimeException("Post-Condition Check failed: The output array does not contain the same elements as the input array.");
                }
            }
        }

        // Invariant Check
        if (!Arrays.equals(arr, original)) {
            throw new RuntimeException("Invariant Check failed: The input array has been modified.");
        }

        return temp;
    }
}
