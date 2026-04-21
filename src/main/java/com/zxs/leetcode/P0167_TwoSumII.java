package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 167. 两数之和 II - 输入有序数组
 *
 * 给你一个下标从 1 开始的整数数组 numbers，该数组已按 非递减顺序 排列，
 * 请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果这两个数下标 Pi 和 Pj 满足 i < j，则返回 [Pi+1, Pj+1]。
 *
 * 示例 1：
 * 输入: numbers = [2,7,11,15], target = 9
 * 输出: [1,2]
 * 解释: 2 + 7 = 9，返回 [1, 2]
 *
 * 示例 2：
 * 输入: numbers = [2,3,4], target = 6
 * 输出: [1,3]
 *
 * 示例 3：
 * 输入: numbers = [-1,0], target = -1
 * 输出: [1,2]
 *
 * 提示：
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 只存在一个有效答案
 *
 * 解题思路：
 * 方法一：双指针（推荐）
 * 左指针指向开头，右指针指向结尾
 * 若两数之和大于 target，右指针左移
 * 若小于 target，左指针右移
 * 一定有唯一解
 * 时间复杂度 O(n)，空间复杂度 O(1)
 *
 * 方法二：二分查找
 * 对每个数，在剩余范围内二分查找 target - numbers[i]
 * 时间复杂度 O(n log n)，空间复杂度 O(1)
 */
public class P0167_TwoSumII {

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};  // 题目要求1-indexed
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};  // 不可能发生（题目保证有唯一解）
    }

    public static void main(String[] args) {
        P0167_TwoSumII solution = new P0167_TwoSumII();
        int[] result1 = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(result1[0] + ", " + result1[1]); // 1, 2
        int[] result2 = solution.twoSum(new int[]{2, 3, 4}, 6);
        System.out.println(result2[0] + ", " + result2[1]); // 1, 3
    }
}
