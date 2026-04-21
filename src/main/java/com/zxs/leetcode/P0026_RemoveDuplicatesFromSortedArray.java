package com.zxs.leetcode;

/**
 * LeetCode 0026 - 删除有序数组中的重复项 (Remove Duplicates from Sorted Array)
 *
 * 【题目描述】
 * 给你一个有序数组 nums，请你原地删除重复出现的元素，使每个元素只出现一次，返回删除后数组的新长度。
 * 不要使用额外的数组空间，必须在原地修改输入数组。
 *
 * 示例 1：输入 nums = [1,1,2] → 输出 2, nums = [1,2,_]
 * 示例 2：输入 nums = [0,0,1,1,1,2,2,3,3,4] → 输出 5, nums = [0,1,2,3,4,_,_,_,_,_]
 *
 * 约束：1 <= nums.length <= 3 * 10^4, -100 <= nums[i] <= 100
 *
 * 【解题思路】
 * 双指针/快慢指针（O(n) 时间，O(1) 空间）✅ 推荐
 *   慢指针指向不重复部分的末尾，快指针遍历数组。
 *   当快指针遇到新元素时，慢指针前进并赋值。
 *
 * @Author 郑晓胜
 */
public class P0026_RemoveDuplicatesFromSortedArray {

    /**
     * 双指针法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 1; // 慢指针，指向不重复部分的下一个位置
        for (int fast = 1; fast < nums.length; fast++) {
            // 快指针遇到新元素
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
