package zest;


import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TwoSumTest {
  TwoSum twoSum = new TwoSum();

  @Test
  void nullCase() {
    int[] nums = null;
    int target = 9;
    assertThrows(IllegalArgumentException.class, () -> twoSum.findTwoSum(nums, target));
  }

  @Test
  void empty() {
    int[] nums = {};
    int target = 9;
    assertThrows(IllegalArgumentException.class, () -> twoSum.findTwoSum(nums, target));
  }

  @Test
  void oneElement() {
    int[] nums = {1};
    int target = 9;
    assertThrows(IllegalArgumentException.class, () -> twoSum.findTwoSum(nums, target));
  }

  @Provide
  Arbitrary<Tuple.Tuple2<int[], Integer>> validInputs() {
    return Arbitraries.integers()
            .between(-100, 100)
            .array(int[].class)
            .ofMinSize(2)
            .ofMaxSize(10)
            .flatMap(nums -> {
              boolean isTargetUnique = false;
              int target = 0;

              while (!isTargetUnique) {
                int index1 = ThreadLocalRandom.current().nextInt(0, nums.length);
                int index2 = ThreadLocalRandom.current().nextInt(0, nums.length);
                while (index1 == index2) {
                  index2 = ThreadLocalRandom.current().nextInt(0, nums.length);
                }
                target = nums[index1] + nums[index2];

                // Check if the target is unique
                isTargetUnique = true;
                for (int i = 0; i < nums.length; i++) {
                  if (i != index1 && i != index2 && nums[i] == target) {
                    isTargetUnique = false;
                    break;
                  }
                }
              }

              return Arbitraries.of(Tuple.of(nums, target));
            });
  }

  @Provide
  Arbitrary<Tuple.Tuple2<int[], Integer>> invalidInputs() {
    return Arbitraries.integers()
            .between(-100, 100)
            .array(int[].class)
            .ofMinSize(2)
            .ofMaxSize(10)
            .flatMap(nums -> {
              int target = 1000;
              return Arbitraries.of(Tuple.of(nums, target));
            });
  }

  @Property
  void multipleElementsHappy(@ForAll("validInputs") Tuple.Tuple2<int[], Integer> input) {
    int[] nums = input.get1();
    int target = input.get2();
    int[] result = twoSum.findTwoSum(nums, target);
    assertEquals(2, result.length);
    assertEquals(nums[result[0]] + nums[result[1]], target);
  }

  @Property
  void multipleElementsFail(@ForAll("invalidInputs") Tuple.Tuple2<int[], Integer> input) {
    int[] nums = input.get1();
    int target = input.get2();
    assertThrows(IllegalArgumentException.class, () -> {
      twoSum.findTwoSum(nums, target);
    });
  }

  @Property
  void oneSolution(@ForAll("validInputs") Tuple.Tuple2<int[], Integer> input) {
    int[] nums = input.get1();
    int target = input.get2();
    int[] result = twoSum.findTwoSum(nums, target);
    assertEquals(2, result.length);
    assertEquals(nums[result[0]] + nums[result[1]], target);

    // Randomly shuffle the array
    List<Integer> numsList = Arrays.stream(nums)
            .boxed()
            .collect(Collectors.toList());
    Collections.shuffle(numsList);
    int[] shuffledNums = numsList.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    result = twoSum.findTwoSum(shuffledNums, target);
    assertEquals(2, result.length);
    assertEquals(shuffledNums[result[0]] + shuffledNums[result[1]], target);
  }
}
