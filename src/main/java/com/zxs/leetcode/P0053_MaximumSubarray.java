package com.zxs.leetcode;

/**
 * 【P0053】最大子数组和
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0053_MaximumSubarray {

    /**
     * 题目描述：
     * 给你一个整数数组 nums，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例：
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 约束：
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     *
     * 解题思路：
     * 方法1：贪心（Kadane算法）—— 遍历过程中累加，遇到负和则丢弃重来 —— 【推荐】O(n)时间，O(1)空间
     * 方法2：动态规划 —— dp[i] 表示以 i 结尾的最大子序和，dp[i] = max(nums[i], dp[i-1]+nums[i])
     * 方法3：分治法 —— 求最大子序和的经典分治解法
     *
     * 本实现使用方法1（贪心），与动态规划本质相同但更简洁。
     */

    /**
     * 贪心：遇到当前和小于0时，重新从下一个位置开始累加
     *
     * @param nums 输入数组
     * @return 最大子数组和
     */
    public int maxSubArray(int[] nums) {
        // 初始化为 Long.MIN_VALUE 避免全负数情况
        int maxSum = nums[0];
        int curSum = 0;

        for (int num : nums) {
            // 当前和小于0时，重置为0（丢弃之前的所有贡献）
            if (curSum < 0) {
                curSum = 0;
            }
            // 加上当前元素
            curSum += num;
            // 更新最大和
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    // 动态规划版本（等效解法）
    public int maxSubArrayDP(int[] nums) {
        int dp = nums[0];      // dp[i-1]
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // dp[i] = max(nums[i], dp[i-1] + nums[i])
            // 要么以当前元素重新开始，要么与之前的子数组连接
            dp = Math.max(nums[i], dp + nums[i]);
            maxSum = Math.max(maxSum, dp);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        P0053_MaximumSubarray solution = new P0053_MaximumSubarray();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray(nums)); // 6
    }
}
