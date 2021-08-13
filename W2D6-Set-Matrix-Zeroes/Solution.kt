class Solution {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        var clearFirstRow = false
        var clearFirstCol = false
        
        // Iterate over the matrix and flag the first cell of the row and 
        // column (by setting it's value to 0) if the current cell is 0
        for (row in matrix.indices) {
            for (col in matrix[row].indices) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0
                    matrix[0][col] = 0
                    if (row == 0) clearFirstRow = true
                    if (col == 0) clearFirstCol = true
                }
            }
        }

        // Clears each columns' cells where they were flagged as such
        for (col in 1 until matrix[0].size) {
            if (matrix[0][col] == 0) {
                for (row in 1 until matrix.size) {
                    matrix[row][col] = 0
                }
            }
        }
        
        // Clears each rows' cells where they were flagged as such
        for (row in 1 until matrix.size) {
            if (matrix[row][0] == 0) {
                for (col in 1 until matrix[0].size) {
                    matrix[row][col] = 0
                }
            }
        }

        if (clearFirstRow) {
            for (col in matrix[0].indices) matrix[0][col] = 0
        }

        if (clearFirstCol) {
            for (row in matrix.indices) matrix[row][0] = 0
        }
    }
}