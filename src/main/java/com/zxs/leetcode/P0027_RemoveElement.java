package com.zxs.leetcode;

/**
 * LeetCode 0027 - 移除元素 (Remove Element)
 *
 * 【题目描述】
 * 给你一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素。
 * 元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
 *
 * 示例 1：输入 nums = [3,2,2,3], val = 3 → 输出 2, nums = [2,2,_,_]
 * 示例 2：输入 nums = [0,1,2,2,3,0,4,2], val = 2 → 输出 5, nums = [0,1,4,0,3,_,_,_]
 *
 * 约束：0 <= nums.length <= 100, 0 <= nums[i] <= 50, 0 <= val <= 100
 *
 * 【解题思路】
 * 方法一：双指针-覆盖法（O(n) 时间，O(1) 空间）✅ 推荐
 *   用快慢指针，快指针遍历数组，遇到不等于 val 的元素就覆盖到慢指针位置。
 *
 * 方法二：双指针-交换法（O(n) 时间，O(1) 空间）
 *   左右指针向中间靠拢，将等于 val 的元素交换到末尾。减少写操作次数。
 *
 * @Author 郑晓胜
 */
public class P0027_RemoveElement {

    /**
     * 双指针覆盖法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    /**
     * 双指针交换法（减少写操作）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeElementSwap(int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                // 将 val 交换到末尾
                nums[left] = nums[right];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
