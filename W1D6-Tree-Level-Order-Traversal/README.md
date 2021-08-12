# [N-ary Tree Level Order Traversal](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/613/week-1-august-1st-august-7th/3871/)

Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

### Example 1:
```
Input: root = [1,null,3,2,4,null,5,6]
Output: [[1],[3,2,4],[5,6]]
```

### Example 2:
```
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
``` 

### Constraints:

- The height of the n-ary tree is less than or equal to `1000`
- The total number of nodes is between `[0, 104]`

# Solution

This problem is easily solved by performing a breadth-first traversal of the input graph and saving the list of node values at each depth level along the way.
The algorithm can be summarized as follows: 
1. Initialize an empty array of arrays, where each inner array represents the values of the nodes from the depth level of the its position within the enclosing array.
2. Add the root node to a stack which will keep track of all the nodes that still need to be explored along their depths in the graph.
3. While the node stack is not empty, keep popping nodes and adding them to the sub-array at the appropriate position in the larger array. Once a node is added, its children need to be added to the node stack **in reverse order** so they can be traversed later.
4. Once the node stack becomes empty, i.e. the entire graph has been traversed, return the list of collected values.