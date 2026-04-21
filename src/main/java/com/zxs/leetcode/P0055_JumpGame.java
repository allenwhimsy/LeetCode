package com.zxs.leetcode;

/**
 * 【P0055】跳跃游戏
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0055_JumpGame {

    /**
     * 题目描述：
     * 给定一个非负整数数组 nums，你最初位于数组的起始位置。判断你是否能够跳到数组的最后一个位置。
     *
     * 示例：
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步到索引 1，然后跳 3 步到达最后一个索引。
     *
     * 约束：
     * 1 <= nums.length <= 3 * 10^4
     * 0 <= nums[i] <= 10^5
     *
     * 解题思路：
     * 方法1：贪心 —— 维护能到达的最远位置，逐个更新 —— 【推荐】O(n)时间，O(1)空间
     * 方法2：动态规划（从后往前）—— dp[i] 表示能否到达i，但需要O(n)空间
     * 方法3：BFS/DFS —— 会超时，不推荐
     *
     * 核心思想：遍历每个位置，维护"从起点能到达的最远索引"，若最远位置 >= n-1 则可达。
     */
    public boolean canJump(int[] nums) {
        // furthest 表示从起点出发，当前能到达的最远索引
        int furthest = 0;
        for (int i = 0; i < nums.length; i++) {
            // 如果当前位置已经超出了最远可达范围，则无法继续前进
            if (i > furthest) {
                return false;
            }
            // 更新最远可达位置：之前的最远 or 从i出发的新最远
            furthest = Math.max(furthest, i + nums[i]);
            // 提前终止：如果最远已能到达终点
            if (furthest >= nums.length - 1) {
                return true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        P0055_JumpGame solution = new P0055_JumpGame();
        System.out.println(solution.canJump(new int[]{2, 3, 1, 1, 4})); // true
        System.out.println(solution.canJump(new int[]{3, 2, 1, 0, 4})); // false
    }
}
