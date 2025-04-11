package zest;

public class BinaryTreeMaxPath {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        public static TreeNode constructTree(Integer[] array) {
            // If the array is empty, a tree is returned with the root value set to Integer.MIN_VALUE
            if (array.length == 0) {
                return new TreeNode(Integer.MIN_VALUE);
            }

            TreeNode[] nodes = new TreeNode[array.length];
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    nodes[i] = new TreeNode(array[i]);
                    if (i > 0) {
                        TreeNode parent = nodes[(i - 1) / 2];
                        if (parent != null) {
                            if (i % 2 == 1) parent.left = nodes[i];
                            else parent.right = nodes[i];
                        }
                    }
                }
            }
            return nodes[0];
        }
    }

    private int maxPathSum;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("The tree root must not be null.");
        }

        // If root.val is equal to Integer.MIN_VALUE, the input array was empty, and thus the maxPathSum is 0
        if (root.val == Integer.MIN_VALUE) {
            return 0;
        }

        // Pre-conditions
        if (treeSize(root) > 1023) {
            throw new IllegalArgumentException();
        }

        maxPathSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxPathSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        int priceNewPath = node.val + leftGain + rightGain;

        maxPathSum = Math.max(maxPathSum, priceNewPath);

        return node.val + Math.max(leftGain, rightGain);
    }

    // Calculates the amount of nodes in the tree
    private int treeSize(TreeNode node) {
        int size = 1;

        if (node.left != null) {
            // If a value is smaller than -10000 or bigger than 10000, a pre-condition doesn't hold
            if (node.left.val < -10000 || node.left.val > 10000) {
                throw new IllegalArgumentException();
            }
            size += treeSize(node.left);
        }
        if (node.right != null) {
            // If a value is smaller than -10000 or bigger than 10000, a pre-condition doesn't hold
            if (node.right.val < -10000 || node.right.val > 10000) {
                throw new IllegalArgumentException();
            }
            size += treeSize(node.right);
        }

        return size;
    }

    public void setMaxPathSum(int newMaxPathSum) {
        this.maxPathSum = newMaxPathSum;
    }
}
