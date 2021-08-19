# [Path Sum II](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/613/week-1-august-1st-august-7th/3838/)

Given the `root` of a binary tree and an integer `targetSum`, return *all **root-to-leaf** paths where the sum of the node values in the path equals `targetSum`. Each path should be returned as a list of the node values, not node references.*

A **root-to-leaf** path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

### Example 1:
```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: [[5,4,11,2],[5,8,4,5]]
Explanation: There are two paths whose sum equals targetSum:
5 + 4 + 11 + 2 = 22
5 + 8 + 4 + 5 = 22
```

### Example 2:
```
Input: root = [1,2,3], targetSum = 5
Output: []
```

### Example 3:
```
Input: root = [1,2], targetSum = 0
Output: []
```

### Constraints:

- The number of nodes in the tree is in the range `[0, 5000]`.
- `-1000 <= Node.val <= 1000`
- `-1000 <= targetSum <= 1000`

# Solution

This problem can be easily solved with a simple Depth-First traversal of the input graph. The implementation I chose was a recursive one, where each recursive call was made with the following arguments:
- `node: TreeNode` the current node in the traversal
- `sum: Int` the sum that still had to be accummulated by traversing further. This value is originally the `targetSum` which is decremented during each recursive call by the current `node`'s value
- `path: Stack<Int>` a stack representing the path from the root node up until the current node. **Note:** Originally I used a list instead of a task for this purpose to which I added the current node value at each call, but using a `Stack` to which the node at hand can be pushed and popped when backtracking proved to be much more efficient.
- `res: MutableList<List<Int>>` the accummulator list representing the result of the algorithm, this is where appropriate **root-to-leaf** paths will be stored.
The algorithm itself (Solution.kt) is quite self-explanatory, so I will not go into more detail here.

### TIL:
- Prefer using mutable structures in DFS algorithms wherever you can, since updates are much more efficient than copying and appending to immutable structures. (`Stack` or `MutableList` vs `List` or `Array`)