class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val occurrences: MutableMap<Int, Int> = mutableMapOf()
        nums.forEachIndexed { index, num -> 
            val complement = target - num
            if (complement in occurrences) { 
                return intArrayOf(occurrences[complement] ?: 0, index)
            }
            occurrences[num] = index
        }
        
        return intArrayOf()
    }
}