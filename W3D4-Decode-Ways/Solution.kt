class Solution {
    
    fun numDecodings(s: String): Int {
        return if (s[0] == '0') 0 else { 
            val combinations = MutableList<Int>(s.length + 1) { 0 }   
            combinations[combinations.lastIndex] = 1
            for (index in s.lastIndex downTo 0) { 
                if (s[index] != '0') { 
                    combinations[index] = combinations[index + 1]
                    if (index < s.lastIndex && isValidPair(s[index], s[index + 1])) {
                        combinations[index] += combinations[index + 2]
                    }
                }
            }
            
            combinations[0]
        }
    }
    
    inline fun isValidPair(first: Char, second: Char) = 
        first == '1' || (first == '2' && second <= '6')
}