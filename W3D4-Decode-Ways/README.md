# [Decode Ways](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/615/week-3-august-15th-august-21st/3902/)

A message containing letters from `A-Z` can be **encoded** into numbers using the following mapping:
```
'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
```

To **decode** an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, `"11106"` can be mapped into:

- `"AAJF"` with the grouping `(1 1 10 6)`
- `"KJF"` with the grouping `(11 10 6)`
Note that the grouping `(1 11 06)` is invalid because `"06"` cannot be mapped into `'F'` since `"6"` is different from `"06"`.

Given a string `s` containing only digits, return *the **number** of ways to **decode** it.*

The answer is guaranteed to fit in a **32-bit** integer.

### Example 1:
```
Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
```

### Example 2:
```
Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
```

###Example 3:
```
Input: s = "0"
Output: 0
Explanation: There is no character that is mapped to a number starting with 0.
The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20", neither of which start with 0.
Hence, there are no valid ways to decode this since all digits need to be mapped.
```

### Example 4:
```
Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
```

### Constraints:

- `1 <= s.length <= 100`
- `s` contains only digits and may contain leading zero(s).

# Solution

This is yet another one of the problems for which the `Keep It Simple, Stupid` principle should have applied. 
Originally I kind of solved this issue in an overcomplicated way with a single pass of the input string and lots of conditions and exceptions, which, as it should have been clear to me by now, is not the way to go. After this, I found the correct solution in one of the **Discussions** threads and it dawned on me how simply this problem can be reduced to a Dynamic Programming problem. 

The key to simplifying this problem is by traversing it in a reversed order, and considering the number of possible decoding combinations for the substring from the current index until the end of the string. Naturally, the last element can only be decoded in a single way, so the number of ways for the last element will be 0.
For the one before it, if the pair made of the last two elements is a valid one for decoding, the number of ways increases by the number of ways after the last two elements (1 in this case). Similarly, the entire string can be traversed one time to obtain the result in the following way:
1. Check if the first element of the input string is `'0'`, in this case return `0` right away since no valid string can be decoded
2. Initialise an array `combinations` with length `s.length + 1`, where the last element will be initialised with `1` and the rest with `0`, and which will hold at each index `i` the number of ways the substring of `s` between indices `i..s.length` can be decoded.
3. Iterate over the `s` string `for index in s.lastIndex downTo 0`. At the current index perform the following:
   1. Check if the `s[index] == '0'`. If so, continue to the next index.
   2. Mark that the substring from this index until the end can be decoded in the same number of ways as the next one: `combinations[index] = combinations[index + 1]`
   3. If the characters at `index` and `index + 1` make a valid decode pair (as defined above), then the substring can naturally be decoded in as many additional ways as the substring from `index + 2` => `combinations[index] += combinations[index + 2]`.
4. At the end of the iteration, the element at `combinations[0]` will contain the total number of ways in which the entire string can be decoded. In case there were any `'0'` elements that couldn't be used in valid pairs, this traversal already handled it such that the result will be `0`.

### Observations: 
- The logic from the (3) step of the algorithm leads to a "sort of" Fibonacci sequence of numbers, which in retrospective makes sense since when introducing "appending" a new character at the beginning of the string, if it can be also decoded as a pair with the first element, the resulting string can be decoded in as many ways as before + as if the first two characters were decoded as a pair. I say "sort of", because `'0'` characters disrupt this Fibonacci sequence, since they can only be used in pairs with other non-zero characters.
- When the solution to a problem becomes too complex, most of the time it's better to scrap the solution and try finding a new approach, instead of tunnel visioning
- **Remember to try using Dynamic Programming when the problem suggests it**