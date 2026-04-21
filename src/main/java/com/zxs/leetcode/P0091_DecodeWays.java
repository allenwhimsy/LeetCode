package com.zxs.leetcode;

/**
 * 【P0091】解码方法
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0091_DecodeWays {

    /**
     * 题目描述：
     * 一条包含字母 A-Z 的消息通过以下映射进行了加密：
     * 'A' -> 1, 'B' -> 2, ..., 'Z' -> 26
     * 给定一个数字字符串 s，请计算其解码方法的总数。
     * 若字符串中某位为 '0'，则它无法单独解码。
     *
     * 示例：
     * 输入：s = "12"
     * 输出：2
     * 解释：可解码为 "AB"(1,2) 或 "L"(12)
     *
     * 约束：
     * 1 <= s.length <= 100
     *
     * 解题思路：
     * 动态规划：dp[i] = s前i个字符的解码方法数 —— 【推荐】O(n)时间，O(1)空间
     * dp[i] 由 dp[i-1]（单字符解码）和 dp[i-2]（两字符解码，10~26）之和决定。
     * 注意：'0' 无法单独解码，只能与前一个字符组成 "10" 或 "20"。
     */
    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;
        int n = s.length();

        // dp[i] 表示 s[0..i-1] 的解码方法数（dp[0]=1 为空串的解码数）
        int dp0 = 1; // dp[i-2]
        int dp1 = 1; // dp[i-1]

        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            char p = s.charAt(i - 1);

            int ways = 0;
            if (c != '0') {
                ways += dp1; // 单独解码 c
            }
            // 判断能否与前一个字符组成 10~26
            if (p == '1' || (p == '2' && c <= '6')) {
                ways += dp0; // 合并解码
            }
            // 更新滚动变量
            dp0 = dp1;
            dp1 = ways;
        }
        return dp1;
    }

    public static void main(String[] args) {
        P0091_DecodeWays solution = new P0091_DecodeWays();
        System.out.println(solution.numDecodings("12"));        // 2
        System.out.println(solution.numDecodings("226"));      // 3
        System.out.println(solution.numDecodings("0"));        // 0
        System.out.println(solution.numDecodings("10"));       // 1
    }
}
