package com.zxs.leetcode;

/**
 * 【P0062】不同路径
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0062_UniquePaths {

    /**
     * 题目描述：
     * 一个机器人位于 m x n 的网格左上角。机器人每次只能向下或向右移动一步。
     * 请问从左上角到右下角有多少条不同的路径？
     *
     * 示例：
     * 输入：m = 3, n = 7
     * 输出：28
     *
     * 约束：
     * 1 <= m, n <= 100
     *
     * 解题思路：
     * 方法1：动态规划 —— dp[i][j] = dp[i-1][j] + dp[i][j-1] —— 【推荐】O(m*n)时间，O(n)空间（滚动数组）
     * 方法2：数学公式 —— 组合数 C(m+n-2, m-1)，需大数支持
     *
     * 本实现使用方法1的滚动数组版本。
     */
    public int uniquePaths(int m, int n) {
        // 滚动数组：dp[j] 表示到达当前行第 j 列的路径数
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 1); // 第一行所有格子都只有一种走法（只能从左边来）

        // 从第二行开始逐行处理
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] 是当前行第j列（还没更新），表示从上方来
                // dp[j-1] 是当前行第j-1列（已更新），表示从左方来
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        P0062_UniquePaths solution = new P0062_UniquePaths();
        System.out.println(solution.uniquePaths(3, 7)); // 28
        System.out.println(solution.uniquePaths(3, 2)); // 3
    }
}
