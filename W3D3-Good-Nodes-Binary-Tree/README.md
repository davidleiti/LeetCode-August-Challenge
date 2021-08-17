# [Count Good Nodes in Binary Tree](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/615/week-3-august-15th-august-21st/3899/)
Given a binary tree `root`, a node X in the tree is named **good** if in the path from root to X there are no nodes with a value _greater than_ X.

Return the number of **good** nodes in the binary tree.

### Example 1:
<img src="https://assets.leetcode.com/uploads/2020/04/02/test_sample_1.png" width="250" height="150" />

```
Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
```

### Example 2:
<img src="https://assets.leetcode.com/uploads/2020/04/02/test_sample_2.png" width="150" height="150" />

```
Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
```

### Example 3:
```
Input: root = [1]
Output: 1
Explanation: Root is considered as good.
```

### Constraints:
- The number of nodes in the binary tree is in the range `[1, 10^5]`.
- Each node's value is between `[-10^4, 10^4]`.
  
# Solution

This problem was one of the simplest ones so far, the solution implies performing a traversal of the input graph (either DFS or BFS) by keeping track of the maximum value on the respective branch down until the current node.
`Solution.kt` includes two solutions to this problem, a recursive and an interative one, both performing a DFS traversal (the iterative one can be made to use BFS by replacing the Stack with a Queue).

### Recursive
In each recursive call check if the current node is greater or equal than the previous maximum received as an argument. Based on this, return either `0` or `1` plus the result of the recursive calls with the node's left and right children (if they exist). After the tree traversal has finished, the top-most function call will be able to return the reduced count of good nodes from its subsequent recursive calls.

### Iterative
1. Initialise a `Stack<Pair<TreeNode, Int>>` by pushing initially the root node along with its value.
2. Pop the top element of the stack and performt the following steps:
   1. Increment the counter of "good nodes" if it meets the criteria
   2. Calculate the new maximum value on the branch (between the previous max and the value of the current node)
   3. Push the left and right children of the node (if they exist) to the stack along with the branch maximum
3. Repeat the second step while the stack is not empty
4. Return the calculated count
