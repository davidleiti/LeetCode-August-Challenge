# [Valid Sudoku](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/615/week-3-august-15th-august-21st/3904/)

Determine if a `9 x 9` Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

1. Each row must contain the digits `1-9` without repetition.
2. Each column must contain the digits `1-9` without repetition.
3. Each of the nine `3 x 3` sub-boxes of the grid must contain the digits `1-9` without repetition.

**Note:**

- A Sudoku board (partially filled) could be valid but is not necessarily solvable.
- Only the filled cells need to be validated according to the mentioned rules.
 

### Example 1:

```
Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
```

### Example 2:
```
Input: board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
```
 
### Constraints:

- `board.length == 9`
- `board[i].length == 9`
- `board[i][j]` is a digit or `'.'`.

# Solution

This problem can be solved in the naive way by essentially performing three traversals of the input matrix and verifying if the currently traversed rows/columns/grids contain diplicates or not.

However, a more efficient approach is initialising a structure such as a `Set` or `Map` for each row, column, and grid which would represent the occurrences of digits. 
After this, the problem can be solved by iterating a single time over the matrix and for the current element checking if it has already been found in either its row, column, or grid structure.

An even more efficient approach (and the used as the final solution) is using the bits of Integers as flags representing the presence of numbers on individual rows, columns, or grids. For example, the following row `["8","3",".",".","7",".",".",".","."]` could easily be converted to an integer made up of the following bits: `0000...0110010000` (would have been even better if Sudoku tables were made up of 8 rows and columns, so Bytes could be used instead of Integers, but oh well :)).
Updating the occurrences of a row, for example, can be then made simply like so: `row or (1 shl cell)`, where `cell` is the current element from the matrix. 
Based on this, the algorithm itself is exactly the same as the second iteration, but more efficient based on the following:
- Space complexity is much smaller since for each row, column, and grid we only have to allocate an `Integer` instead of an entire data structure.
- Verifying if a number was already used on a row, column, or grid, as well as updating the occurrences is much more efficient since only a couple of bitwise operations need to be performed on the integers keeping track of the numbers instead of data structure operations that most likely would imply calculating hash codes and additional overhead for modifying the data structures.