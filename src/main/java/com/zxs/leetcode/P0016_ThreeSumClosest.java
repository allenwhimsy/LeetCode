package com.zxs.leetcode;

import java.util.Arrays;

/**
 * 【LeetCode 第 16 题】最接近的三数之和
 *
 * <p>题目描述：
 * 给你一个长度为 n 的整数数组 nums 和一个整数 target，从 nums 中选择三个整数，使它们的和尽可能接近 target。
 * 返回三个整数之和。假设每组输入只存在唯一答案。
 *
 * <p>示例：
 * 示例 1：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)
 *
 * 示例 2：
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 *
 * <p>约束条件：
 * - 3 <= nums.length <= 1000
 * - -1000 <= nums[i] <= 1000
 * - -10^4 <= target <= 10^4
 *
 * <p>解题思路：
 * 【方法一：排序 + 双指针（推荐）】O(n²)
 * - 先将数组排序
 * - 固定第一个数，然后用双指针从两端向中间逼近
 * - 每次计算三数之和与 target 的差值，更新最接近的结果
 * - 根据差值决定指针移动方向
 *
 * 【方法二：暴力枚举】O(n³)，不可取
 *
 * <p>推荐方法一，时间复杂度 O(n²)，空间复杂度 O(1)。
 *
 * @Author 郑晓胜
 */
public class P0016_ThreeSumClosest {

    /**
     * 排序 + 双指针解法
     *
     * @param nums   输入数组
     * @param target 目标值
     * @return 最接近 target 的三数之和
     */
    public int threeSumClosest(int[] nums, int target) {
        // 边界处理
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("数组长度至少为3");
        }

        // 先排序，便于双指针处理
        Arrays.sort(nums);
        int n = nums.length;

        // 记录最接近的结果，初始化为前三个数之和
        int result = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            // 跳过重复的第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 双指针：left 从 i+1 开始，right 从末尾开始
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 如果正好等于 target，直接返回
                if (sum == target) {
                    return sum;
                }

                // 更新最接近的结果
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }

                // 根据 sum 与 target 的大小关系移动指针
                if (sum < target) {
                    // sum 太小，left 右移增大
                    left++;
                } else {
                    // sum 太大，right 左移减小
                    right--;
                }
            }
        }

        return result;
    }

    // ====== 测试代码 ======
    public static void main(String[] args) {
        P0016_ThreeSumClosest solution = new P0016_ThreeSumClosest();

        // 测试用例 1
        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println("测试1: " + solution.threeSumClosest(nums1, target1)); // 期望: 2

        // 测试用例 2
        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println("测试2: " + solution.threeSumClosest(nums2, target2)); // 期望: 0

        // 测试用例 3
        int[] nums3 = {1, 1, 1, 0};
        int target3 = -100;
        System.out.println("测试3: " + solution.threeSumClosest(nums3, target3)); // 期望: 2
    }
}
