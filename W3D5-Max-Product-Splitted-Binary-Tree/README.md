# [Maximum Product of Splitted Binary Tree](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/615/week-3-august-15th-august-21st/3903/)

Given the `root` of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.

Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it **modulo** `10^9 + 7`.

**Note** that you need to maximize the answer before taking the mod and not after taking it.

### Example 1:
<img src="https://assets.leetcode.com/uploads/2020/01/21/sample_1_1699.png" width="400" height="140" />

```
Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
```

### Example 2:
<img src="https://assets.leetcode.com/uploads/2020/01/21/sample_2_1699.png" width="400" height="140" />

```
Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
```

### Example 3:
```
Input: root = [2,3,9,10,7,8,6,5,4,11,1]
Output: 1025
```

### Example 4:
```
Input: root = [1,1]
Output: 1
```

### Constraints:

- The number of nodes in the tree is in the range `[2, 5 * 10^4]`.
- `1 <= Node.val <= 10^4`

# Solution

This problem has been solved with two distinct but similar approaches, the first one was my original implementation and the second one based on the hint provided: "If we know the sum of a subtree, the answer is `max( (total_sum - subtree_sum) * subtree_sum)` in each node."

Both implementations are essentially based on the following observation: for each node (except the leaves), there are three options: either "cut" the edge of the left or the right node, if this cut maximizes the product of the sums of the two resulting subgraphs, or don't cut anything. 
To know if a cut maximizes said product, we have to know for each node the total sum of the subgraph above it, as well as the sums of its left and right subgraphs.
For ensuring that we have the total sum of the entire subgraph above it, we have to perform an original traversal of the tree (order doesn't matter), and record the sum of each subgraph in this process.

### Base solution
This is where the two solutions start differing: in the first approach, these sums were persisted in the form of a new binary tree, with the same `TreeNode` structure as before, having each node representing the sum of the subgraph for which it's the root. 

Having the sums of the original tree stored in a sum tree, all that's left is to once again traverse it, and for each node calculate the maximum product that can be obtained by cutting either its left or right node. This can be calculated in the following way: 
`val product = if (leftSum < rightSum) (leftSum + nodeSum) * rightSum else (rightSum + nodeSum) * leftSum }`, where `nodeSum` is the sum of the subraph above the current node + the node's value.

If one of these products is larger than the current maximum, replace it, and at the end of the algorithm return said value, which will hold the maximum product that can be obtained by splitting the input Binary Tree.

### Simple solution
The solution hinted on the problem is slightly simpler: instead of persisting the sums of the subgraphs as a new Binary Tree, it stores them in a simple array (`LinkedList<Int>` was chosen for efficiency). 

After having the array of sums built, all that's left is to iterate over it and calculate the possible products by in the following way: `val product = sum * (targetSum - sum)`, where `targetSum` is the sum of the entire input graph.

## Observations
- While the second solution is much simpler in terms of complexity, for some reason the test benchmarks on LeetCode on average suggested the original solution to be around ~12.5% (350ms vs 400ms) faster in terms of execution speed, also using ~34% less memory (60 MB vs 81 MB). I am uncertain so far why this is the case, so I'll  hopefully get around to investigating it later.
- Both approaches had a time complexity of `O(2*n) ~ O(n)`, and a space complexity of `O(n)`, where `n` is the number of nodes from the Binary Tree.
