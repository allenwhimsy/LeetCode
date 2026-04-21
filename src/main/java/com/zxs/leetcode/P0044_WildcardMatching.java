package com.zxs.leetcode;

/**
 * P0044 通配符匹配
 *
 * 题目描述：
 * 给定一个字符串 s 和一个字符模式 p ，实现通配符匹配算法。
 * - '?' 匹配任意单个字符
 * - '*' 匹配任意序列（包括空字符串）
 * 函数需判断 s 是否能完全匹配 p
 *
 * 示例 1：
 * 输入: s = "aa", p = "a"
 * 输出: false
 * 解释: 'a' 无法匹配 'aa'（'*' 才能匹配多个字符）
 *
 * 示例 2：
 * 输入: s = "aa", p = "*"
 * 输出: true
 *
 * 示例 3：
 * 输入: s = "cb", p = "?a"
 * 输出: false
 *
 * 示例 4：
 * 输入: s = "adceb", p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 匹配 "adc"，第二个 '*' 匹配 "e"
 *
 * 约束条件：
 * - 0 <= s.length, p.length <= 2000
 * - s 仅包含小写字母
 * - p 仅包含小写字母、'?' 或 '*'
 *
 * 解题思路：
 * 【方法一：动态规划（推荐）】O(m*n) 时间，O(m*n) 空间
 * dp[i][j] = s 的前 i 个字符能否匹配 p 的前 j 个字符。
 * 状态转移：
 * - 若 p[j-1] == '*'：dp[i][j] = dp[i-1][j]（'*' 匹配1个字符）|| dp[i][j-1]（'*' 匹配0个字符）
 * - 若 p[j-1] == '?' 或 p[j-1] == s[i-1]：dp[i][j] = dp[i-1][j-1]
 * - 其他：dp[i][j] = false
 *
 * 【方法二：贪心 + 双指针】O(m*n) 时间，O(1) 空间
 * 用两个指针遍历，记录 '*' 的位置和最后一个成功匹配的位置。
 *
 * @Author 郑晓胜
 */
public class P0044_WildcardMatching {

    /**
     * 方法一：动态规划（推荐）- 清晰可靠
     */
    public boolean isMatch_dp(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] = s 前 i 个字符能否匹配 p 前 j 个字符
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // 空字符串匹配空模式

        // 初始化：s 为空时，p 中的 '*' 可以匹配空字符串
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                char sc = s.charAt(i - 1);

                if (pc == '*') {
                    // '*' 匹配0个字符（跳过p中的'*'）|| '*' 匹配1个或多个（跳过s中的字符）
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (pc == '?' || pc == sc) {
                    // '?' 匹配任意单个字符，或字符相同
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 方法二：贪心 + 双指针 - O(1) 空间
     * 核心思想：遇到 '*' 记录位置，尽可能让 '*' 多匹配，后续不匹配时回溯。
     */
    public boolean isMatch_greedy(String s, String p) {
        int m = s.length(), n = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1;    // 记录最后一个 '*' 的位置（p 中）
        int sTmpIdx = -1;    // 记录当遇到 '*' 时，s 的匹配位置

        while (sIdx < m) {
            if (pIdx < n && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                // 字符匹配或 '?'，同步前进
                sIdx++;
                pIdx++;
            } else if (pIdx < n && p.charAt(pIdx) == '*') {
                // 遇到 '*'，记录位置，'*' 先尝试匹配0个字符
                starIdx = pIdx;
                sTmpIdx = sIdx;
                pIdx++;
            } else if (starIdx != -1) {
                // 不匹配但有 '*' 可回溯：让 '*' 多匹配一个字符
                pIdx = starIdx + 1;
                sTmpIdx++;
                sIdx = sTmpIdx;
            } else {
                // 无 '*' 可回溯，匹配失败
                return false;
            }
        }

        // 检查 p 剩余部分是否全为 '*'
        while (pIdx < n && p.charAt(pIdx) == '*') {
            pIdx++;
        }

        return pIdx == n;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0044_WildcardMatching solution = new P0044_WildcardMatching();

        String[][] tests = {
            {"aa", "a"},    // false
            {"aa", "*"},   // true
            {"cb", "?a"},  // false
            {"adceb", "*a*b"}, // true
            {"", "*"},     // true
            {"", ""}       // true
        };

        for (String[] test : tests) {
            boolean result = solution.isMatch_dp(test[0], test[1]);
            System.out.println("s=\"" + test[0] + "\", p=\"" + test[1] + "\" -> " + result);
        }
    }
}
