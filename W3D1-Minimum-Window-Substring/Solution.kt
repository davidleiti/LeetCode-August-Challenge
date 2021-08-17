object Solution {

    @JvmStatic
    fun main(args: Array<String>) {
        println(minWindow("ADOBECODEBANC", "ABC"))
    }

    fun minWindow(first: String, second: String): String {
        val charCounts: MutableMap<Char, Int> = second.groupBy { it }
            .map { (char, occurrences) -> char to occurrences.size }
            .toMap(mutableMapOf())

        var minWindowStart = -1
        var minWindowLength = Int.MAX_VALUE
        var windowStart = 0
        var windowCharCount = second.length

        for (windowEnd in first.indices) {  // expand the window on the right side
            val char = first[windowEnd]
            if (char in charCounts) {
                val newCount = (charCounts[char] ?: 0) - 1
                charCounts[char] = newCount
                if (newCount >= 0) windowCharCount--
            }

            while (windowCharCount == 0) {  // window is valid
                val windowLength = windowEnd - windowStart
                if (windowLength < minWindowLength) {
                    minWindowLength = windowLength
                    minWindowStart = windowStart
                }

                val startChar = first[windowStart]  // contract the window on the left side
                windowStart++
                if (startChar in charCounts) {
                    val newCount = (charCounts[startChar] ?: 0) + 1
                    charCounts[startChar] = newCount
                    if (newCount > 0) {
                        windowCharCount++
                    }
                }
            }
        }

        return if (minWindowStart != -1) first.substring(minWindowStart..minWindowStart + minWindowLength) else ""
    }
}