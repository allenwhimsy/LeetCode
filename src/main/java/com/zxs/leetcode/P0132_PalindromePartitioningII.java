package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/palindrome-partitioning-ii/
 *
 * 题目：分割回文串 II（Palindrome Partitioning II）
 *
 * 给你一个字符串 s ，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回最少需要分割的次数。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需分割一次，得到 "aa" 和 "b" 两个回文子串
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 *
 * 约束：
 * - 1 <= s.length <= 2000
 * - s 仅由小写英文字母组成
 *
 * 解题思路：
 * 方法一：DP（推荐）
 *   - dp[i]：s[0..i] 的最少分割次数
 *   - 若 s[0..i] 本身是回文，dp[i] = 0
 *   - 否则 dp[i] = min(dp[j] + 1)，其中 j < i 且 s[j+1..i] 是回文
 *   - 需要预计算回文，时间 O(n^2)，空间 O(n^2) 或 O(n)
 *
 * 方法二：中心扩展优化
 *   - 枚举回文中心，向两侧扩展，同时更新 dp
 *   - 时间 O(n^2)，空间 O(n)
 *
 * 推荐方法：DP + 中心扩展预计算
 */
public class P0132_PalindromePartitioningII {

    /**
     * 【推荐解法】DP + 中心扩展预计算回文
     * dp[i]：s[0..i] 的最少分割次数
     */
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        int[] dp = new int[n]; // dp[i] = s[0..i] 最少分割次数
        for (int i = 0; i < n; i++) {
            dp[i] = i; // 最坏情况：每个字符一个分割
        }

        // 中心扩展：枚举回文中心，同时更新 dp
        for (int center = 0; center < n; center++) {
            // 奇数长度回文：center 为中心
            expandAroundCenter(s, center, center, dp);
            // 偶数长度回文：center 和 center+1 为中心
            expandAroundCenter(s, center, center + 1, dp);
        }
        return dp[n - 1];
    }

    // 以 [left, right] 为回文中心，向两侧扩展
    private void expandAroundCenter(String s, int left, int right, int[] dp) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (left == 0) {
                // s[0..right] 是回文，不需要分割
                dp[right] = 0;
            } else {
                // s[0..left-1] 需要 dp[left-1] 次，+1 次切分得到 s[left..right]
                dp[right] = Math.min(dp[right], dp[left - 1] + 1);
            }
            left--;
            right++;
        }
    }
}
