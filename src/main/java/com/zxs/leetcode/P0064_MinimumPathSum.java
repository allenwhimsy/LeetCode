package com.zxs.leetcode;

/**
 * 【P0064】最小路径和
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0064_MinimumPathSum {

    /**
     * 题目描述：
     * 给定一个包含非负整数的 m x n 网格 grid，请找出一条从左上角到右下角的路径，
     * 使得路径上所有数字之和最小。只能向下或向右移动。
     *
     * 示例：
     * 输入：grid = [
     *          [1,3,1],
     *          [1,5,1],
     *          [4,2,1]
     *        ]
     * 输出：7
     * 解释：路径 1→3→1→1→1 的和最小。
     *
     * 约束：
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 100
     *
     * 解题思路：
     * 动态规划：dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1]) —— 【推荐】O(m*n)时间，O(n)空间
     * 第一行和第一列需要单独处理（只能从左或从上走来）。
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = grid[0][0]; // 起点
                } else if (i == 0) {
                    // 第一行：只能从左边来
                    dp[j] = dp[j - 1] + grid[i][j];
                } else if (j == 0) {
                    // 第一列：只能从上边来
                    dp[j] = dp[j] + grid[i][j];
                } else {
                    // 其他格子：取从上或从左来的较小值
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        P0064_MinimumPathSum solution = new P0064_MinimumPathSum();
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(solution.minPathSum(grid)); // 7
    }
}
