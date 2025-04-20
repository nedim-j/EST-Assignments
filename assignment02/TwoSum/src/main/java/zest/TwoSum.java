package zest;


import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] findTwoSum(int[] nums, int target) {
        // Pre-condition checks
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Input array must contain at least two elements");
        }


        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                // Post-condition checks
                int[] result = new int[]{numMap.get(complement), i};
                if (result[0] != result[1] && result[0] >= 0 && result[1] >= 0 && result[0] < nums.length
                        && result[1] < nums.length && nums[result[0]] + nums[result[1]] == target) {
                    return result;
                }
            }
            numMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
