package com.zxs.leetcode;

/**
 * P0045 跳跃游戏II
 *
 * 题目描述：
 * 给定一个长度为 n 的整数数组 nums，初始位置为下标 0。
 * 数组中的每个元素表示你在该位置可以跳跃的最大长度。
 * 假设你总是可以到达数组的最后一个位置。
 * 请返回到达最后一个位置的最少跳跃次数。
 *
 * 示例 1：
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到下标 1 的位置（可跳3步），再跳到下标 4
 *
 * 示例 2：
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *
 * 约束条件：
 * - 1 <= nums.length <= 10^4
 * - 0 <= nums[i] <= 10^5
 *
 * 解题思路：
 * 【方法一：贪心（推荐）】O(n) 时间，O(1) 空间
 * 维护当前步能到达的最远边界 curEnd，和下一步能到达的最远边界 nextEnd。
 * 当 i 到达 curEnd 时，增加一步，并更新 curEnd = nextEnd。
 *
 * 【方法二：动态规划】O(n^2) - 不推荐
 *
 * @Author 郑晓胜
 */
public class P0045_JumpGameII {

    /**
     * 方法一：贪心（推荐）- O(n) 时间，O(1) 空间
     * 每次在当前可达范围内选择能跳到最远的下一步
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        int jumps = 0;       // 已跳跃次数
        int curEnd = 0;      // 当前步能到达的最远位置
        int nextEnd = 0;     // 下一步能到达的最远位置

        for (int i = 0; i < nums.length - 1; i++) {
            // 更新下一步的最远可达范围
            nextEnd = Math.max(nextEnd, i + nums[i]);

            // 到达当前步的边界，必须再跳一步
            if (i == curEnd) {
                jumps++;
                curEnd = nextEnd;
            }
        }
        return jumps;
    }

    /**
     * 方法二：动态规划 - O(n^2) 时间，O(n) 空间（不推荐，但正确性更易理解）
     */
    public int jump_dp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                if (dp[i] != Integer.MAX_VALUE) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0045_JumpGameII solution = new P0045_JumpGameII();

        int[][] numss = {{2, 3, 1, 1, 4}, {2, 3, 0, 1, 4}, {1, 2, 3}, {2, 0, 0}};
        for (int[] nums : numss) {
            int result = solution.jump(nums);
            System.out.println("输入: " + java.util.Arrays.toString(nums) + " -> 最少跳跃: " + result);
        }
    }
}
