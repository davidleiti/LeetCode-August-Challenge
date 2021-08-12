class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val groups: MutableMap<Int, List<String>> = mutableMapOf()
        for (str in strs) { 
            val key = generateKey(str)
            groups[key] = groups[key]?.plus(str) ?: listOf(str)
        }

        return groups.values.toList()
    }

    fun generateKey(str: String): Int {
        var key: Int = 0
        for (char in str) { 
            val charPos: Int = char - 'a'
            var mask: Int = 1 shl charPos
            key = key or mask
        }

        return key
    }
}