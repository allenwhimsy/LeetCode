package com.zxs.leetcode;

/**
 * 【P0087】扰乱字符串
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0087_ScrambleString {

    /**
     * 题目描述：
     * 使用下面规则可以将字符串 s1 扰乱成字符串 s2：
     * 若字符串长度大于1，执行以下操作：
     * - 将字符串分成两个非空子字符串
     * - 若交换两个子字符串的顺序后，能用递归操作将 s1 变成 s3，
     *   或者两个子字符串保持顺序分别通过递归变成 s2 的两个子字符串，则 s1 和 s2 互为扰乱字符串。
     *
     * 示例：
     * 输入：s1 = "great", s2 = "rgeat"
     * 输出：true
     *
     * 约束：
     * s1.length == s2.length, 1 <= length <= 30
     *
     * 解题思路：
     * 方法1：递归 + 剪枝（字符串哈希）—— 【推荐】O(n!)时间，指数级，但加排序剪枝后实际效率可接受
     * 方法2：三维DP —— dp[l1][l2][len]，O(n^3)时间，O(n^3)空间，较复杂
     *
     * 核心：枚举分割点 i，若字符集匹配（排序后相同），则递归判断是否扰乱。
     */
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) return s1 == s2;
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        // 提前剪枝：若字符集不同，直接返回 false
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        java.util.Arrays.sort(c1);
        java.util.Arrays.sort(c2);
        if (!new String(c1).equals(new String(c2))) return false;

        int n = s1.length();
        // 枚举分割点（1 到 n-1）
        for (int i = 1; i < n; i++) {
            // 情况1：不交换顺序，s1[0,i) vs s2[0,i)，s1[i,n) vs s2[i,n)
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
             && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            // 情况2：交换顺序，s1[0,i) vs s2[n-i,n)，s1[i,n) vs s2[0,n-i)
            if (isScramble(s1.substring(0, i), s2.substring(n - i))
             && isScramble(s1.substring(i), s2.substring(0, n - i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P0087_ScrambleString solution = new P0087_ScrambleString();
        System.out.println(solution.isScramble("great", "rgeat")); // true
        System.out.println(solution.isScramble("abcde", "caebd")); // false
    }
}
