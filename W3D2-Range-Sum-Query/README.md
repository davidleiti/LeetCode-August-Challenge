# [Range Sum Query - Immutable](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge-2021/615/week-3-august-15th-august-21st/3892/)

Given an integer array `nums`, handle multiple queries of the following type:

Calculate the sum of the elements of `nums` between indices `left` and `right` inclusive where `left <= right`.
Implement the `NumArray` class:
- `NumArray(int[] nums)` Initializes the object with the integer array `nums`.
- `int sumRange(int left, int right)` Returns the sum of the elements of `nums` between indices `left` and `right` **inclusive** (i.e. `nums[left] + nums[left + 1] + ... + nums[right]`).
 

### Example 1:
```
Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]

Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
```

### Constraints:

- `1 <= nums.length <= 10^4`
- `-10^5 <= nums[i] <= 10^5`
- `0 <= left <= right < nums.length`
- At most `10^4` calls will be made to `sumRange`.

# Solution

- The naive way of solving this would be by saving the array of number passed as constructor argument as a field to the `NumArray` class and calculating the sum for each query when `sumRange()` is called.
- The improved solution to this problem implies only using the consturctor argument during the class's initialisation instead of persisting it as a field. During the classes `init { }` step, an array can be built based on the input array containing the sum for each element up until its position. 
By storing this sum array, querying a sum range with the `sumRange(left, right)` method implies only subtracting from the sum at position `right` the sum at position `left`, obtaining the same result as if manually performing a `sum` on the input elements from that interval.