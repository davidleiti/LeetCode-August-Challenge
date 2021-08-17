class NumArray(nums: IntArray) {
    private val sums: MutableList<Int>
    
    init { 
        sums = MutableList(nums.size) { 0 }
        var sum = 0
        for (index in nums.indices) { 
            sum += nums[index]
            sums[index] = sum
        }
    }

    fun sumRange(left: Int, right: Int): Int = sums[right] - (sums.getOrNull(left - 1) ?: 0)
}