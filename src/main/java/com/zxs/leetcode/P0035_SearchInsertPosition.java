package com.zxs.leetcode;

/**
 * P0035 搜索插入位置
 *
 * 题目描述：
 * 给定一个排序数组和一个目标值，将目标值插入数组中的位置（返回索引）。
 * 数组中无重复元素。
 * 如果目标值大于数组中的所有元素，则插入到数组末尾。
 * 如果目标值小于数组中的所有元素，则插入到开头。
 * 你可以假设数组中无重复元素。
 *
 * 示例 1：
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 示例 2：
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 *
 * 示例 3：
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * 示例 4：
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 *
 * 约束条件：
 * - 1 <= nums.length <= 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - nums 为无重复元素的升序数组
 * - -10^4 <= target <= 10^4
 *
 * 解题思路：
 * 【方法一：二分查找（推荐）】O(log n)
 * 标准二分：找第一个 >= target 的位置。
 * 当 left == right 时退出，left 即为插入位置。
 *
 * 【方法二：暴力遍历】O(n) - 不推荐
 * 遍历数组找到第一个 >= target 的位置。
 *
 * @Author 郑晓胜
 */
public class P0035_SearchInsertPosition {

    /**
     * 方法一：二分查找（推荐）- O(log n)
     * 找第一个 >= target 的位置，即为插入位置
     */
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 退出时 left 为插入位置
        return left;
    }

    /**
     * 方法二：二分查找（左闭右开变形）
     */
    public int searchInsert2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0035_SearchInsertPosition solution = new P0035_SearchInsertPosition();

        int[] nums = {1, 3, 5, 6};
        int[] targets = {5, 2, 7, 0};

        for (int target : targets) {
            int pos = solution.searchInsert(nums, target);
            System.out.println("target=" + target + " -> 插入位置: " + pos);
        }
    }
}
