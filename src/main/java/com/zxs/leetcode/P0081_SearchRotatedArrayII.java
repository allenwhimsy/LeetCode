package com.zxs.leetcode;

/**
 * 【P0081】搜索旋转排序数组 II
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0081_SearchRotatedArrayII {

    /**
     * 题目描述：
     * 已知存在一个按非降序排列的整数数组 nums，预先按未知 pivot 进行了旋转。
     * 数组中的值可能重复。给定目标值 target，如果 nums 中存在，返回 true。
     *
     * 示例：
     * 输入：nums = [2,5,6,0,0,1,2], target = 0
     * 输出：true
     *
     * 约束：
     * 1 <= nums.length <= 5000
     * -10^4 <= nums[i] <= 10^4
     *
     * 解题思路：
     * 二分查找变体：每次比较 nums[mid] 与 nums[left]，判断哪半边有序 —— 【推荐】平均 O(log n)，最坏 O(n)
     * 关键：若 nums[mid] == nums[left]，无法判断哪半边有序（因为有重复），此时 left++ 跳过。
     * 相比 P0033（有重复时需 left++ 跳过），代码略有不同。
     */
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;

            // 关键：当无法判断哪半边有序时，缩小范围
            if (nums[left] == nums[mid]) {
                left++; // 有重复，无法二分，跳过
            } else if (nums[left] < nums[mid]) {
                // 左半边 [left, mid) 有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target 在左半边
                } else {
                    left = mid + 1; // target 在右半边
                }
            } else {
                // 右半边 (mid, right] 有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // target 在右半边
                } else {
                    right = mid - 1; // target 在左半边
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P0081_SearchRotatedArrayII solution = new P0081_SearchRotatedArrayII();
        System.out.println(solution.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0)); // true
        System.out.println(solution.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3)); // false
    }
}
