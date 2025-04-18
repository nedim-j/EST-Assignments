# Solution

## Designing Contracts

### Pre-conditions:
- `nums` is sorted in a strictly increasing order.
- `nums` cannot be null or empty.
- `nums` cannot have null elements in it.

### Post-conditions:
- The output array should be a level-wise representation of the tree. For nodes at each level, list node values from left to right.
- The output tree should be a height-balanced binary search tree (BST).
- Absent nodes should be represented as null in the output array.
- The output cannot be null or empty (Because the Input is not null or empty).

### Invariants:
- All nodes in the tree have the following structure: left_child < parent < right_child.
- The tree is height-balanced, meaning the depth of the two subtrees of every node never differs by more than one.

### Implementation:

#### Pre-conditions:
- I have added a for loop that checks whether all elements are sorted in strictly increasing order.
- The implementation already checks if the input array is null or empty.
- Since the input is an integer array `int[]`, it cannot have null elements in it by definition.

#### Post-conditions:
- To ensure that the post-conditions are met, we have designed utility methods that check whether the tree is following the BST structure and is height-balanced.

#### Invariants:
- The Invariants are also checked by the same way as the post-conditions.

## Testing Contracts:
Based on the specified contract in the previous Task, we have come up with the following test cases:
- `null`: Test with a null input array.
- `empty`: Test with an empty array.
- `singleElement`: Test with a single element array.
- `unsorted`: Test with an unsorted array.
- `sorted`: Test with a sorted array.
- `duplicate`: Test with an array containing duplicate elements.

## Property-based testing:
- `sortedArrayToBST`: Test with a randomly generated sorted array and then checks whether the property holds that the output is a BST.
- `sortedArrayIsBalanced`: Test with a randomly generated sorted array and then checks whether the property holds that the output is a balanced Tree.
- `imbalancedTree`: Test with a randomly generated imbalanced TreeNode and then checks whether `isBalanced` can correctly identify imbalanced trees. This ensures that, if we change the implementation of `SortedArray2BST`, that the testing Suite can correctly identify imbalanced Trees if such a bug comes up.

## Test Coverage:
The test coverage is 96% for the `sortedArrayToBST` function.
The only line that was not reached is line 83, which is the while loop that is supposed to remove trailing nulls. However, we can never reach a trailing null (i.e. the right-most leaf node is null), because of the way we handle uneven and even length of the remaining subtree.

## Bug Reports

### Precondition check for sorting:
There is a bug in the precondition check that the array is sorted: It always results in an ouf of bounds exception, because
we are iterating over the entire array, but i goes up to the length of the array, and then tries to access i + 1. 
This has caused the tests `singleElement`, `unsorted`, `sorted`, `duplicate`, `evenLength` and `unevenLength` to fail.
To fix this, we changed the condition to `i < nums.length - 1` in the for loop.

### Even length array leading to unbalanced tree:
This is because if the array has an even number of elements, the code tries to Ensure mid does not fall below left, but this is an unnecessary check and leads to an unbalanced Tree. This has caused the tests `evenLength` and `duplicate` to fail. To fix this, we removed this unnecessary check.
