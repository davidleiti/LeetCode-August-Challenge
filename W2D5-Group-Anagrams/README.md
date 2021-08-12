# [Group Anagrams](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/614/week-2-august-8th-august-14th/3887/)



Given an array of strings `strs`, group the **anagrams** together. You can return the answer in **any order**.

An **Anagram** is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

### Example 1:

```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```
### Example 2:
```
Input: strs = [""]
Output: [[""]]
```
### Example 3:
```
Input: strs = ["a"]
Output: [["a"]]
```

### Constraints:

- `1 <= strs.length <= 104`
- `0 <= strs[i].length <= 100`
- `strs[i]` consists of lower-case English letters.

# Solution

For this problem I implemented two solution variants, one for the case in which the strings can have duplicate characters, and a more performant version for the case in which each string is composed of distinct characters only.
For both of these cases, the basic idea is to group the strings based on a generated key that would be the same for each string from an anagram group. 
The only distinction between the two variants is the way in which these keys are obtained: 
1. A String obtained from counting the occurrences of each character from a string. See **SolutionAnyChars** for the implementation
2. An Integer which's last 26 bits represent the presence of each character from the alphabet. This approach would be much more efficient where applicable since it uses much less memory and executes faster as it relies only on a sequence of bitwise operations. See **SolutionDistinctChars** for the implementation.
