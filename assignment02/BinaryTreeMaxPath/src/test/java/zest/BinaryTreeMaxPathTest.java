package zest;


import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinaryTreeMaxPathTest {
    BinaryTreeMaxPath calculator = new BinaryTreeMaxPath();

    public BinaryTreeMaxPath.TreeNode setup(Integer[] input) {
        calculator.setMaxPathSum(Integer.MIN_VALUE);
        Integer[] treeArray = input;
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(treeArray);

        return root;
    }

    @Test
    public void testMaxPathSuccess() {
        Integer[] array = {1, 2, 3};
        BinaryTreeMaxPath.TreeNode root = setup(array);

        int expected = 6;

        int result = calculator.maxPathSum(root);

        assertEquals(expected, result);
    }

    @Test
    public void testMaxPathNullElementsSuccess() {
        Integer[] array = {-10, 9, 20, null, null, 15, 7};
        BinaryTreeMaxPath.TreeNode root = setup(array);

        int expected = 42;

        int result = calculator.maxPathSum(root);

        assertEquals(expected, result);
    }

    @Test
    public void testMaxPathIllegalPositiveNodeLeft() {
        Integer[] array = {-10, 10001, 20, null, null, 15, 7};
        BinaryTreeMaxPath.TreeNode root = setup(array);

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.maxPathSum(root),
                "Expected IllegalArgumentException to be thrown"
        );
    }

    @Test
    public void testMaxPathIllegalNegativeNodeLeft() {
        Integer[] array = {-10, -10001, 20, null, null, 15, 7};
        BinaryTreeMaxPath.TreeNode root = setup(array);

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.maxPathSum(root),
                "Expected IllegalArgumentException to be thrown"
        );
    }

    @Test
    public void testMaxPathIllegalPositiveNodeRight() {
        Integer[] array = {-10, 9, 10001, null, null, 15, 7};
        BinaryTreeMaxPath.TreeNode root = setup(array);

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.maxPathSum(root),
                "Expected IllegalArgumentException to be thrown"
        );
    }

    @Test
    public void testMaxPathIllegalNegativeNodeRight() {
        Integer[] array = {-10, 9, -10001, null, null, 15, 7};
        BinaryTreeMaxPath.TreeNode root = setup(array);

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.maxPathSum(root),
                "Expected IllegalArgumentException to be thrown"
        );
    }

    @Test
    public void testMaxPathEmptyList() {
        Integer[] array = {};
        BinaryTreeMaxPath.TreeNode root = setup(array);

        int expected = 0;

        int result = calculator.maxPathSum(root);

        assertEquals(expected, result);
    }

    @Test
    public void testMaxPathNullRoot() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.maxPathSum(null),
                "Expected IllegalArgumentException to be thrown"
        );
    }

    @Test
    public void testMaxPath1024Array() {
        Integer[] input = new Integer[1024];

        for (int i = 0; i < 1024; i++) {
            input[i] = i;
        }

        BinaryTreeMaxPath.TreeNode root = setup(input);

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.maxPathSum(root),
                "Expected IllegalArgumentException to be thrown"
        );
    }

    @Property
    public void testIllegalNodeLeft(@ForAll @IntRange(min = 10001) Integer illegalPositive, @ForAll @IntRange(min = Integer.MIN_VALUE, max = -10001) Integer illegalNegative) {
        Random random = new Random();
        int randomInt = random.nextInt(2);

        Integer[] array = {1, 2, 9};

        if (randomInt == 0) {
            array[1] = illegalPositive;
        } else {
            array[1] = illegalNegative;
        }

        BinaryTreeMaxPath.TreeNode root = setup(array);

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.maxPathSum(root),
                "Expected IllegalArgumentException to be thrown"
        );
    }

    @Property
    public void testIllegalNodeRight(@ForAll @IntRange(min = 10001) Integer illegalPositive, @ForAll @IntRange(min = Integer.MIN_VALUE, max = -10001) Integer illegalNegative) {
        Random random = new Random();
        int randomInt = random.nextInt(2);

        Integer[] array = {1, 2, 9};

        if (randomInt == 0) {
            array[2] = illegalPositive;
        } else {
            array[2] = illegalNegative;
        }

        BinaryTreeMaxPath.TreeNode root = setup(array);

        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.maxPathSum(root),
                "Expected IllegalArgumentException to be thrown"
        );
    }
}
