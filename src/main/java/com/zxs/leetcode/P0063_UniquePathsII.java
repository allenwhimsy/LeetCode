package com.zxs.leetcode;

/**
 * 【P0063】不同路径 II
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0063_UniquePathsII {

    /**
     * 题目描述：
     * 一个机器人位于 m x n 的网格左上角。网格中有些格子是障碍物。
     * 机器人每次只能向下或向右移动一步。请问从左上角到右下角有多少条不同的路径？
     *
     * 示例：
     * 输入：obstacleGrid = [
     *          [0,0,0],
     *          [0,1,0],
     *          [0,0,0]
     *        ]
     * 输出：2
     *
     * 约束：
     * obstacleGrid[i][j] 为 0 或 1
     * 1 <= m, n <= 100
     *
     * 解题思路：
     * 在 P0062 基础上增加障碍物判断：若格子为障碍，则 dp[i][j]=0 —— 【推荐】O(m*n)时间，O(n)空间
     * 注意：起点或终点为障碍时直接返回0。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1; // 起点障碍则无路

        // 处理第一行
        for (int j = 1; j < n; j++) {
            dp[j] = obstacleGrid[0][j] == 1 ? 0 : dp[j - 1];
        }

        // 处理后续行
        for (int i = 1; i < m; i++) {
            // 处理每行的第一个格子（只能从上方来）
            dp[0] = obstacleGrid[i][0] == 1 ? 0 : dp[0];
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0; // 障碍，无路可达
                } else {
                    dp[j] = dp[j] + dp[j - 1]; // 从上或从左来
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        P0063_UniquePathsII solution = new P0063_UniquePathsII();
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(solution.uniquePathsWithObstacles(grid)); // 2
    }
}
