package com.zxs.leetcode;

/**
 * P0041 缺失的第一个正数
 *
 * 题目描述：
 * 给你一个未排序的整数数组 nums ，
 * 请你找出其中没有出现的最小的正整数（要求时间复杂度为 O(n)，空间复杂度为 O(1)）。
 *
 * 示例 1：
 * 输入: nums = [1,2,0]
 * 输出: 3
 *
 * 示例 2：
 * 输入: nums = [3,4,-1,1]
 * 输出: 2
 *
 * 示例 3：
 * 输入: nums = [7,8,9,11,12]
 * 输出: 1
 *
 * 约束条件：
 * - 1 <= nums.length <= 5 * 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 *
 * 解题思路：
 * 【方法一：原地哈希（推荐）】O(n) 时间，O(1) 空间（不计算输出数组）
 * 核心思想：将数字 i 放到索引 i-1 的位置（即让 nums[i] == i+1）。
 * 最终遍历数组，第一个不满足 nums[i] == i+1 的位置 i，答案为 i+1。
 * 若全部满足，则答案为 n+1。
 *
 * 【方法二：哈希集合】O(n) 时间，O(n) 空间 - 不满足 O(1) 空间要求
 *
 * @Author 郑晓胜
 */
public class P0041_FirstMissingPositive {

    /**
     * 方法一：原地哈希（推荐）- O(n) 时间，O(1) 空间
     * 核心：把数字 x（1 <= x <= n）放到下标 x-1 的位置
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        int n = nums.length;

        // 第一步：将每个数字放到其值对应的索引位置（原地哈希）
        for (int i = 0; i < n; ) {
            int x = nums[i];
            // 条件：x 是有效正数（1~n）且不在正确位置
            if (x >= 1 && x <= n && nums[x - 1] != x) {
                // 交换 nums[i] 和 nums[x-1]
                swap(nums, i, x - 1);
                // 注意：交换后 nums[i] 可能仍是有效数字，需要继续处理
            } else {
                i++;
            }
        }

        // 第二步：再次遍历，找到第一个不满足 nums[i] == i+1 的位置
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 全部满足（1~n 都在），答案为 n+1
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0041_FirstMissingPositive solution = new P0041_FirstMissingPositive();

        int[][] numss = {{1, 2, 0}, {3, 4, -1, 1}, {7, 8, 9, 11, 12}, {1, 1, 0, -1, 2, 2}};
        for (int[] nums : numss) {
            int result = solution.firstMissingPositive(nums);
            System.out.print("输入: [");
            for (int i = 0; i < nums.length; i++) {
                System.out.print(nums[i] + (i < nums.length - 1 ? "," : ""));
            }
            System.out.println("] -> 缺失最小正数: " + result);
        }
    }
}
