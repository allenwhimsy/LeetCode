package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 162. 寻找峰值
 *
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回 任意一个峰值 的索引即可。
 * 你可以认为 nums[-1] = nums[n] = -∞ 。
 *
 * 示例 1：
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，nums[2] > nums[1] 且 nums[2] > nums[3]
 *
 * 示例 2：
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 可以返回索引 1（峰值2）或索引5（峰值6）
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 *
 * 解题思路：
 * 方法一：二分查找（推荐）
 * 利用 nums[-1] = nums[n] = -∞ 的特性
 * 如果 nums[mid] < nums[mid+1]，则峰值在右半边
 * 否则（nums[mid] > nums[mid+1]），峰值在左半边（包括mid）
 * 时间复杂度 O(log n)，空间复杂度 O(1)
 *
 * 方法二：线性扫描
 * 遍历数组，找到第一个 nums[i] > nums[i+1] 的位置
 * 时间复杂度 O(n)，空间复杂度 O(1)
 */
public class P0162_FindPeakElement {

    // 方法一：二分查找
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                // 峰值在右半边
                left = mid + 1;
            } else {
                // 峰值在左半边（包括mid）
                right = mid;
            }
        }
        return left;
    }

    // 方法二：线性扫描
    public int findPeakElementLinear(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    public static void main(String[] args) {
        P0162_FindPeakElement solution = new P0162_FindPeakElement();
        System.out.println(solution.findPeakElement(new int[]{1, 2, 3, 1}));         // 2
        System.out.println(solution.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4})); // 1 或 5
    }
}
