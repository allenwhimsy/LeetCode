package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/distinct-subsequences/
 *
 * 题目：不同的子序列（Distinct Subsequences）
 *
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中，
 * t 出现的个数。
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 示例 1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * s 中有以下 3 个 "rabbit" 的子序列：
 * rabbbit（删除第2、3、6个字符）、rabbbit（删除第3、5、6个）、rabbbit（删除第2、5、6个）
 *
 * 示例 2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 *
 * 约束：
 * - 1 <= s.length, t.length <= 1000
 * - s 和 t 由小写英文字母组成
 *
 * 解题思路：
 * 方法一：动态规划（推荐）
 *   - dp[i][j]：s[0..i-1] 中 t[0..j-1] 出现的次数
 *   - 若 s[i-1] == t[j-1]：dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
 *     （选s[i-1]匹配 + 不选s[i-1]）
 *   - 否则：dp[i][j] = dp[i-1][j]（不选s[i-1]）
 *   - 时间 O(n*m)，空间 O(n*m)，可优化到 O(m)
 *
 * 推荐方法：DP 一维数组优化空间
 */
public class P0115_DistinctSubsequences {

    /**
     * 【推荐解法】DP 一维数组
     * dp[j]：s[0..i-1] 中 t[0..j-1] 出现的次数
     * 关键：字符匹配时 dp[j] += dp[j-1]
     */
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1; // 空串 t 是所有空串 s 的子序列

        for (int i = 1; i <= m; i++) {
            // 从后往前遍历，防止 dp[j-1] 被提前覆盖
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n];
    }
}
