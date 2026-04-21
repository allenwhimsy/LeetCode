package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 174. 地下城游戏
 *
 * 恶魔们抓到了公主并将她囚禁于地下城的地牢右下角房间里。
 * 地牢是一个 m x n 的二维网格 rooms，恶魔和公主都位于右下角 (m,n)。
 * 骑士从左上角的房间出发，公主被囚禁的房间为 (m-1, n-1)。
 * 骑士每次可以向右下、左下、左上、右上四个方向移动。
 * 骑士必须找到一条能够营救公主的路径，使得骑士的初始生命值至少是正数。
 * 为了展示他不畏惧恶魔，骑士选择初始生命值最小（且仍然为正）的方式出发。
 *
 * 每个房间中都有一个能量点数（若为负值则代表骑士会损失生命值）。
 * 骑士在进入房间时，房间中的能量点值会与他的当前生命值相加。
 * 骑士的初始生命值必须至少为 1（当能量点总和为负时，则需要更多初始生命值来存活）。
 *
 * 示例 1：
 * 输入: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * 输出: 7
 * 解释: 骑士的最低初始生命值至少为 7 才能存活
 *
 * 提示：
 * m == dungeon.length
 * n == dungeon[i].length
 * 1 <= m, n <= 200
 * -1000 <= dungeon[i][j] <= 1000
 *
 * 解题思路：
 * 方法一：动态规划（推荐）
 * 从终点倒推，计算每个位置需要的最小生命值
 * dp[i][j] = 从(i,j)到终点所需的最小初始生命值
 * dp[i][j] = max(1, min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j])
 * 从右下向左上填表
 * 时间复杂度 O(m*n)，空间复杂度 O(m*n)
 *
 * 方法二：空间优化
 * 滚动数组，将二维 dp 压缩为一维
 * 时间复杂度 O(m*n)，空间复杂度 O(n)
 */
public class P0174_DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // dp[i][j] = 从(i,j)到终点所需的最小初始生命值
        int[][] dp = new int[m + 1][n + 1];
        // 初始化为极大值，右下角终点的下一格设为1（保证最小值）
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[m][n - 1] = 1;  // 终点的右侧初始化为1
        dp[m - 1][n] = 1;  // 终点的下方初始化为1

        // 从右下向左上填表
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int need = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = Math.max(1, need);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        P0174_DungeonGame solution = new P0174_DungeonGame();
        int[][] dungeon = {
            {-2, -3, 3},
            {-5, -10, 1},
            {10, 30, -5}
        };
        System.out.println(solution.calculateMinimumHP(dungeon)); // 7
    }
}
