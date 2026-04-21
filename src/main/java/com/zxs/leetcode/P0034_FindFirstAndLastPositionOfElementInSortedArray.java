package com.zxs.leetcode;

/**
 * P0034 在排序数组中查找元素的第一个和最后一个位置
 *
 * 题目描述：
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找到给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 约束条件：
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * - nums 是一个非递减数组
 * - -10^9 <= target <= 10^9
 *
 * 解题思路：
 * 【方法一：二分查找（推荐）】O(log n)
 * 用两次二分查找：
 * 1. 找左边界：在 nums[mid] >= target 时收紧右边界
 * 2. 找右边界：在 nums[mid] <= target 时收紧左边界
 *
 * 【方法二：直接遍历】O(n) - 不满足 O(log n) 要求，仅作参考
 *
 * @Author 郑晓胜
 */
public class P0034_FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * 方法一：二分查找（推荐）- O(log n)
     * @return 包含起始和结束位置的数组
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int[] result = new int[2];
        result[0] = findLeftBound(nums, target);
        result[1] = findRightBound(nums, target);
        return result;
    }

    /**
     * 查找左边界（第一个 >= target 的位置）
     */
    private int findLeftBound(int[] nums, int target) {
        int left = 0, right = nums.length; // 注意 right 初始化为 length，而非 length-1
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                // 收缩右边界，在左侧继续找
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 退出时 left == right，检查是否越界或等于 target
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 查找右边界（最后一个 <= target 的位置）
     */
    private int findRightBound(int[] nums, int target) {
        int left = 0, right = nums.length; // right 初始化为 length
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                // 收缩左边界，在右侧继续找
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 退出时 left == right，检查是否越界或等于 target
        if (right <= 0 || nums[right - 1] != target) {
            return -1;
        }
        return right - 1;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0034_FindFirstAndLastPositionOfElementInSortedArray solution =
            new P0034_FindFirstAndLastPositionOfElementInSortedArray();

        int[][] numss = {
            {5, 7, 7, 8, 8, 10},
            {5, 7, 7, 8, 8, 10},
            {}
        };
        int[] targets = {8, 6, 0};

        for (int i = 0; i < numss.length; i++) {
            int[] result = solution.searchRange(numss[i], targets[i]);
            System.out.println("target=" + targets[i] + " -> [" + result[0] + "," + result[1] + "]");
        }
    }
}
