# [Minimum Window Substring](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/615/week-3-august-15th-august-21st/3891/)

Given two strings `s` and `t` of lengths `m` and `n` respectively, return the minimum window substring of `s` such that every character in `t` (including duplicates) is included in the window. If there is no such substring, return the empty string `""`.

The testcases will be generated such that the answer is **unique**.

A substring is a contiguous sequence of characters within the string.

### Example 1:
```
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
```

### Example 2:
```
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
```

### Example 3:
```
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
```

### Constraints:

- `m == s.length`
- `n == t.length`
- `1 <= m, n <= 105`
- `s` and `t`consist of uppercase and lowercase English letters.

**Follow up**: Could you find an algorithm that runs in `O(m + n)` time?

# Solution

## First solution
Originally I solved this problem in with a *small brain* approach, by creating a map of occurrences for each character in `t` and maintaining a list of positions at which they are present in `s`. 
Based on this structure, the problem was solved by performing a single iteration of `s`, by at each step calculating the minimum and maximum from the lists of occurrences based on the contained characters from `t`.
Based on this structure, the implemented algorithm conceptually would have had a complexity of `O(m + n)` since both strings were iterated only once, however, given the amount of computation that needed to be done at each step in the iteration of the first string, the performance was subpar. (continually calculating minimums and maximums didn't work out as planned).

## Good solution
Given the failure that was the first approach, after consulting the hints, I implemented a much more efficient solution that was based on the **Sliding Window Approach**. This idea should have come naturally from the problem description, since a minimum length window had to be found that meets a given criteria (in this case, containing all of the characters from `t`). 
Based on this approach, the resulting algorithm managed to achieve a time complexity of `O(m + n)` and a space complexity of `O(n)`, and it was implemented as follows:
1. Create a map of occurrences for the characters of `t` (same as in the previous implementation)
2. Initialise the following variables:
   1. `minWindowStart = -1` and `minWindowLength = Int.MAX_VALUE` - the parameters of the smallest window found
   2. `windowStart = 0` and `windowEnd = 0` - the bounds of the current sliding window
   3. `windowCharCount = t.length` - the number of characters that still needs to be found by the current window
3. Start expanding the current window to the right until the condition isn't met, i.e. there are still characters that need to be included in the current window (`windowCharCount > 0`)
4. Once the window met the criteria (`windowCharCount == 0`), check if its smaller than the current minimum and update the minimum if necessary.
5. Start contracting the current window from the left as long as its still valid, and increment the `windowCharCount` once it omitted the first character that makes the window invalid
6. Repeat the steps from (3) to (5) until the entire string has been traversed and the last window option was considered
7. Return the substring from the indices `minWindowStart..minWindowStart + minWindowLength` or `""` if no valid window was found.

## Lessons learned: 
- Plan ahead more before starting an implementation, think about existing solutions that would fit the problem
- Illustrate and conceptualize the solution on paper before implementing and if stuck - saves hours of wasted time
- Keep It Simple Stupid - if a solution gets too complicated (too long/too many random conditions), it's probably not a good one