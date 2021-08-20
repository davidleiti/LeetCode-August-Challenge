class Solution {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val rows: Array<Int> = Array(9) { 0 }
        val cols: Array<Int> = Array(9) { 0 }
        val grids: Array<Int> = Array(9) { 0 }
        for (rowIndex in board.indices) {
            for (colIndex in board[rowIndex].indices) {
                if (board[rowIndex][colIndex] == '.') continue

                val cell = board[rowIndex][colIndex] - '0'
                val gridIndex = rowIndex / 3 * 3 + colIndex / 3
                val row = rows[rowIndex]
                val col = cols[colIndex]
                val grid = grids[gridIndex]
                
                rows[rowIndex] = row or (1 shl cell)
                cols[colIndex] = col or (1 shl cell)
                grids[gridIndex] = grid or (1 shl cell)
                if (row == rows[rowIndex] || col == cols[colIndex] || grid == grids[gridIndex]) {
                    return false
                }
            }
        }

        return true
    }
}