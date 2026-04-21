package com.zxs.leetcode;

/**
 * 【LeetCode 第10题】正则表达式匹配
 *
 * <p>题目描述：
 * 给你一个字符串 s 和一个字符规律 p，请你实现正则表达式匹配。
 * - '.' 匹配任意单个字符
 * - '*' 匹配零个或多个前面的那一个元素
 *
 * <p>示例：
 * 输入: s = "aa", p = "a"
 * 输出: false
 *
 * 输入: s = "aa", p = "a*"
 * 输出: true
 *
 * 输入: s = "ab", p = ".*"
 * 输出: true
 *
 * <p>约束条件：
 * - 1 <= s.length <= 20
 * - 1 <= p.length <= 20
 * - s 和 p 都是小写英文字母
 * - 保证每次 * 出现时，都有一个前面的字符与之匹配
 *
 * <p>解题思路：
 *
 * 【方法一】动态规划（推荐）⭐
 * - dp[i][j]: s[0..i-1] 与 p[0..j-1] 是否匹配
 * - 状态转移分两种情况：
 *   1. p[j-1] 为普通字符或 '.'：dp[i][j] = dp[i-1][j-1] && match(s[i-1], p[j-1])
 *   2. p[j-1] 为 '*'：
 *      - 匹配0个前字符：dp[i][j] = dp[i][j-2]
 *      - 匹配1+个前字符：dp[i][j] = dp[i-1][j] && match(s[i-1], p[j-2])
 * - 时间复杂度: O(mn)，空间复杂度: O(mn)
 *
 * 【方法二】递归（记忆化搜索）
 * - 自顶向下，递归 + 备忘录
 * - 逻辑清晰，但递归深度受栈限制
 *
 * @Author 郑晓胜
 */
public class P0010_RegularExpressionMatching {

    /**
     * 【方法一】动态规划（推荐）⭐
     *
     * dp 状态定义：
     * dp[i][j] = true 表示 s[0..i-1] 能被 p[0..j-1] 匹配
     *
     * 状态转移：
     * 1. p[j-1] 是字母或 '.'：dp[i][j] = dp[i-1][j-1] && (s[i-1] == p[j-1] || p[j-1] == '.')
     * 2. p[j-1] 是 '*'：
     *    a) '*' 匹配0个前字符（p[j-2]出现0次）：dp[i][j] = dp[i][j-2]
     *    b) '*' 匹配1+个前字符（p[j-2]与s[i-1]匹配）：dp[i][j] = dp[i-1][j] && match(s[i-1], p[j-2])
     *
     * @param s 字符串
     * @param p 模式串
     * @return 是否匹配
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j]: s[0..i-1] 与 p[0..j-1] 的匹配结果
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 初始化：空字符串与空模式的匹配
        dp[0][0] = true;

        // 初始化：空字符串 s 与模式 p 的匹配
        // 处理 "a*b*" 这种可以匹配空串的情况
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 填表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);

                if (pc == '*') {
                    // case 1: '*' 匹配0个前字符，跳过 "x*"
                    dp[i][j] = dp[i][j - 2];

                    // case 2: '*' 匹配1+个前字符（p[j-2] 与 s[i-1] 匹配）
                    if (match(s.charAt(i - 1), p.charAt(j - 2))) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    // 普通字符或 '.'：当前字符匹配且之前的状态匹配
                    if (match(s.charAt(i - 1), pc)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[m][n];
    }

    /**
     * 判断字符 c 是否与模式字符 pMatch 匹配
     *
     * @param c       待匹配字符
     * @param pMatch  模式字符（普通字符或 '.'）
     * @return 是否匹配
     */
    private static boolean match(char c, char pMatch) {
        return pMatch == '.' || c == pMatch;
    }

    // ========== 方法二：递归 + 记忆化 ==========

    /**
     * 【方法二】递归（记忆化搜索）
     *
     * @param s 字符串
     * @param p 模式串
     * @return 是否匹配
     */
    public static boolean isMatchRecursive(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j]: s[i..] 与 p[j..] 的匹配结果，-1表示未计算
        int[][] memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            java.util.Arrays.fill(row, -1);
        }
        return dp(s, p, 0, 0, memo);
    }

    private static boolean dp(String s, String p, int i, int j, int[][] memo) {
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }

        boolean result;
        if (j == p.length()) {
            result = (i == s.length());
        } else {
            boolean firstMatch = (i < s.length()) && (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j));

            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                // '*' 情况：跳过 "x*" 或 消耗一个字符
                result = dp(s, p, i, j + 2, memo) || (firstMatch && dp(s, p, i + 1, j, memo));
            } else {
                result = firstMatch && dp(s, p, i + 1, j + 1, memo);
            }
        }

        memo[i][j] = result ? 1 : 0;
        return result;
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        test("aa", "a", false);
        test("aa", "a*", true);
        test("ab", ".*", true);
        test("aab", "c*a*b", true);
        test("mississippi", "mis*is*p*.", false);
        test("", "", true);
        test("", "a*", true);
        test("", ".*", true);
        test("a", "ab*", true);
        test("ab", ".*c", false);
    }

    private static void test(String s, String p, boolean expected) {
        boolean r1 = isMatch(s, p);
        boolean r2 = isMatchRecursive(s, p);
        System.out.printf("输入: s=\"%s\", p=\"%s\"%n", s, p);
        System.out.printf("  动态规划: %s (期望: %s) %s%n",
                r1, expected, r1 == expected ? "✓" : "✗");
        System.out.printf("  递归记忆化: %s%n", r2);
    }
}
