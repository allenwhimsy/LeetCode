package com.zxs.leetcode;

/**
 * P0033 搜索旋转排序数组
 *
 * 题目描述：
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个 pivot 进行了旋转
 * （例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]）。
 * 给你 旋转后 的数组 nums 和一个整数 target ，
 * 如果 target 在数组中，则返回它的索引，否则返回 -1 。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 * 约束条件：
 * - 1 <= nums.length <= 5000
 * - -10^4 <= nums[i] <= 10^4
 * - nums 中的每个值都 独一无二
 * - nums 原来是一个升序排序的数组，但进行了旋转
 * - -10^4 <= target <= 10^4
 *
 * 解题思路：
 * 【方法一：二分查找（推荐）】O(log n)
 * 关键：旋转后的数组总有一半是有序的。
 * 每次取 mid，比较 nums[mid] 与 nums[left] 确定哪半边有序：
 * - 若 nums[left] <= nums[mid]，左半边有序，判断 target 是否在 [left, mid) 区间
 * - 否则右半边有序，判断 target 是否在 (mid, right] 区间
 *
 * @Author 郑晓胜
 */
public class P0033_SearchInRotatedSortedArray {

    /**
     * 二分查找（推荐）
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 判断哪半边是有序的
            if (nums[left] <= nums[mid]) {
                // 左半边有序：[left, mid] 升序
                if (target >= nums[left] && target < nums[mid]) {
                    // target 落在左半边有序区间内
                    right = mid - 1;
                } else {
                    // target 在右半边
                    left = mid + 1;
                }
            } else {
                // 右半边有序：[mid, right] 升序
                if (target > nums[mid] && target <= nums[right]) {
                    // target 落在右半边有序区间内
                    left = mid + 1;
                } else {
                    // target 在左半边
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0033_SearchInRotatedSortedArray solution = new P0033_SearchInRotatedSortedArray();

        // 测试用例
        int[][] numss = {
            {4, 5, 6, 7, 0, 1, 2},
            {4, 5, 6, 7, 0, 1, 2},
            {1}
        };
        int[] targets = {0, 3, 0};

        for (int i = 0; i < numss.length; i++) {
            int result = solution.search(numss[i], targets[i]);
            System.out.println("测试: target=" + targets[i] + " -> 索引: " + result);
        }
    }
}
