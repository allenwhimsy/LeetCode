package com.zxs.leetcode;

/**
 * 【P0097】交错字符串
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0097_InterleavingString {

    /**
     * 题目描述：
     * 给定三个字符串 s1、s2 和 s3，判断 s3 是否由 s1 和 s2 交错组成。
     * 交错：s3 由 s1 和 s2 混合而成，保持各自字符的相对顺序。
     *
     * 示例：
     * 输入：s1 = "aab", s2 = "axy", s3 = "aaxaby"
     * 输出：false
     *
     * 约束：
     * 0 <= s1.length, s2.length <= 100
     *
     * 解题思路：
     * 动态规划：dp[i][j] = s3前i+j个字符能否由 s1前i个 和 s2前j个 交错组成 —— 【推荐】O(m*n)时间，O(n)空间
     * 转移：dp[i][j] = (dp[i-1][j] && s1[i-1]==s3[i+j-1]) || (dp[i][j-1] && s2[j-1]==s3[i+j-1])
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;

        // 滚动数组优化空间
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        // 初始化第一行（只用 s1）
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= m; i++) {
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1); // 更新第一列
            for (int j = 1; j <= n; j++) {
                char c3 = s3.charAt(i + j - 1);
                dp[j] = (dp[j] && s1.charAt(i - 1) == c3)   // s1[i-1] 匹配
                      || (dp[j - 1] && s2.charAt(j - 1) == c3); // s2[j-1] 匹配
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        P0097_InterleavingString solution = new P0097_InterleavingString();
        System.out.println(solution.isInterleave("aab", "axy", "aaxaby")); // false
        System.out.println(solution.isInterleave("aab", "axy", "aaabxy")); // true
    }
}
