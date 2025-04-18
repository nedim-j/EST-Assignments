package zest;


import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SortedArray2BSTTest {
  SortedArray2BST sortedArray2BST = new SortedArray2BST();

  @Test
  public void nullTest() {
    TreeNode root = sortedArray2BST.sortedArrayToBST(null);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    assertNull(root);
    assertTrue(order.isEmpty());
  }

  @Test
  public void empty() {
    int[] nums = {};
    TreeNode root = sortedArray2BST.sortedArrayToBST(nums);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    assertNull(root);
    assertTrue(order.isEmpty());
  }

  @Test
  public void singleElement() {
    int[] nums = {1};
    TreeNode root = sortedArray2BST.sortedArrayToBST(nums);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    assertEquals(List.of(1), order);
  }

  @Test
  public void unsorted() {
    int[] nums = {3, 1, 2};
    TreeNode root = sortedArray2BST.sortedArrayToBST(nums);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    assertTrue(order.isEmpty());
  }

  @Test
  public void sorted() {
    int[] nums = {1, 2, 3};
    TreeNode root = sortedArray2BST.sortedArrayToBST(nums);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    assertEquals(List.of(2, 1, 3), order);
  }

  @Test
  public void duplicate() {
    int[] nums = {1, 2, 2, 3};
    TreeNode root = sortedArray2BST.sortedArrayToBST(nums);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    assertTrue(order.isEmpty());
  }

  @Test
  public void evenLength() {
    int[] nums = {1, 2, 3, 4};
    TreeNode root = sortedArray2BST.sortedArrayToBST(nums);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    assertEquals(new ArrayList<>(Arrays.asList(2, 1, 3, null, null, null, 4)), order);
  }

  @Test
  public void unevenLength() {
    int[] nums = {1, 2, 3, 4, 5};
    TreeNode root = sortedArray2BST.sortedArrayToBST(nums);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    assertEquals(new ArrayList<>(Arrays.asList(3, 1, 4, null, 2, null, 5)), order);
  }

  @Provide
  Arbitrary<int[]> strictlyIncreasingArrays() {
    return Arbitraries.integers()
            .between(1, 100)
            .set()
            .ofMaxSize(10)
            .map(set -> set.stream().sorted().mapToInt(Integer::intValue).toArray());
  }

  @Provide
  Arbitrary<TreeNode> imbalancedTrees() {
    return Arbitraries.integers()
            .between(3, 10)
            .map(len -> {
              TreeNode root = new TreeNode(1);
              TreeNode current = root;
              for (int i = 2; i <= len; i++) {
                current.left = new TreeNode(i);
                current = current.left;
              }
              return root;
            });
  }

  @Property
  void sortedArrayToBST(@ForAll("strictlyIncreasingArrays") int[] nums) {
    TreeNode root = sortedArray2BST.sortedArrayToBST(nums);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    if (nums == null || nums.length == 0) {
      assert root == null;
      assertTrue(order.isEmpty());
    } else {
      assertFalse(order.isEmpty());
      assertTrue(sortedArray2BST.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
  }

  @Property
  void sortedArrayIsBalanced(@ForAll("strictlyIncreasingArrays") int[] nums) {
    TreeNode root = sortedArray2BST.sortedArrayToBST(nums);
    List<Integer> order = sortedArray2BST.levelOrder(root);
    if (nums == null || nums.length == 0) {
      assert root == null;
      assertTrue(order.isEmpty());
    } else {
      assertFalse(order.isEmpty());
      assertTrue(sortedArray2BST.isBalanced(root));
    }
  }

  @Property
  void imbalancedTree(@ForAll("imbalancedTrees") TreeNode root) {
    assertFalse(sortedArray2BST.isBalanced(root));
  }
}
