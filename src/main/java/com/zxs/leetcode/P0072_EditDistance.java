package com.zxs.leetcode;

/**
 * 【P0072】编辑距离
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0072_EditDistance {

    /**
     * 题目描述：
     * 给你两个字符串 word1 和 word2，请你计算出将 word1 转换成 word2 所需的最小操作数。
     * 你可以进行以下三种操作：
     * 1. 插入一个字符
     * 2. 删除一个字符
     * 3. 替换一个字符
     *
     * 示例：
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：horse -> rorse（将 'h' 替换为 'r'）-> rose（删除 'r'）-> ros（删除 'e'）
     *
     * 约束：
     * 0 <= word1.length, word2.length <= 500
     *
     * 解题思路：
     * 动态规划：dp[i][j] = word1前i个字符转为word2前j个字符的最小代价 —— 【推荐】O(m*n)时间，O(n)空间（滚动数组）
     * 转移方程：
     * - dp[i][j] = dp[i-1][j-1]，若 word1[i-1]==word2[j-1]
     * - dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])，分别对应删除、插入、替换
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // dp[j] 表示 word1 前 i 个转为 word2 前 j 个的最小代价
        int[] dp = new int[n + 1];

        // 初始化：word1 空串转为 word2[0..j]，需 j 次插入
        for (int j = 0; j <= n; j++) dp[j] = j;

        for (int i = 1; i <= m; i++) {
            int pre = dp[0]; // 保存 dp[i-1][0]，即 word1[0..i-1] 转为空串的代价（需 i 次删除）
            dp[0] = i;       // 第 i 行第 0 列：word1 前 i 个转为空串，需要 i 次删除
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // 保存 dp[i-1][j]，供下次循环使用
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 字符相同，无需操作，代价等于 dp[i-1][j-1]
                    dp[j] = pre;
                } else {
                    // 三种操作取最小：删除（dp[i-1][j]）+1、插入（dp[i][j-1]）+1、替换（pre）+1
                    dp[j] = 1 + Math.min(Math.min(dp[j], dp[j - 1]), pre);
                }
                pre = temp; // 更新 pre 为当前 dp[i-1][j]，供下一列使用
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        P0072_EditDistance solution = new P0072_EditDistance();
        System.out.println(solution.minDistance("horse", "ros")); // 3
        System.out.println(solution.minDistance("intention", "execution")); // 5
    }
}
