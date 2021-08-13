# [Set Matrix Zeroes](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/614/week-2-august-8th-august-14th/3888/)



Given an `m x n` integer matrix matrix, if an element is `0`, set its entire row and column to `0`'s, and return the matrix.

You must do it *in place*.

### Example 1:

```
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
```
### Example 2:
```
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
```

### Constraints:

- `m == matrix.length`
- `n == matrix[0].length`
- `1 <= m, n <= 200`
- `-2^31 <= matrix[i][j] <= 2^31 - 1`

### Follow up:
- A straightforward solution using O(mn) space is probably a bad idea.
- A simple improvement uses O(m + n) space, but still not the best solution.
- Could you devise a constant space solution?

# Solution

The tricky part of this problem is respecting the restrictions of (a) performing the algorithm in place and (b) getting to a space complexity of O(1).

- My first, less optimal solution was based on maintaining two maps, one for the rows, one for the columns that can flag which rows and columns should be cleared (i.e. set to 0). This approach has a space complexity of O(m + n).
- The second solution, inspired by the provided hints, was similar in terms of steps, however, instead of generating the flag maps, relied on flagging the first cells of the rows
and columns that have to be cleared with 0. With this approach, no additional allocations need to be made, so the space complexity is O(1).

For both solutions, the time complexity was very similar *O(n*m)*, as the core algorithm consisted of largely the same steps: 
1. Perform an initial traversal of the matrix, persisting the rows and columns that need to be saved (either in a data structure or on the first cells of the respecitve rows and columns)
2. Perform an additional traversal of all of the rows and colums that need to be cleared based on the persisted information.
3. For the optimized solutions, two flags also need to be set during the first traversal signaling whether or not the first row and column have to be cleared, after which at the end they should be individually traversed and cleared. This is necessary because the cell from the indices (0, 0) could represent either of these cases, so additional information is necessary.
 
