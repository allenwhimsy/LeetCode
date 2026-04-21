package com.zxs.leetcode;

/**
 * LeetCode 0005 - 最长回文子串 (Longest Palindromic Substring)
 *
 * 【题目描述】
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * 示例 1：
 *   输入：s = "babad"
 *   输出："bab"
 *   解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 *   输入：s = "cbbd"
 *   输出："bb"
 *
 * 约束：
 *   - 1 <= s.length <= 1000
 *   - s 仅由数字和英文字母组成
 *
 * 【解题思路】
 * 方法一：动态规划（O(n²) 时间，O(n²) 空间）
 *   dp[i][j] 表示 s[i..j] 是否为回文串。状态转移：dp[i][j] = dp[i+1][j-1] && s[i]==s[j]。
 *
 * 方法二：中心扩展法（O(n²) 时间，O(1) 空间）✅ 推荐
 *   从每个字符（或相邻字符对）向两边扩展，找出以该位置为中心的最长回文子串。
 *   每个中心扩展的时间为 O(n)，共 2n-1 个中心。
 *
 * @Author 郑晓胜
 */
public class P0005_LongestPalindromicSubstring {

    /**
     * 中心扩展法（推荐）
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     * @param s 输入字符串
     * @return 最长回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0, maxLen = 1;

        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心，向两边扩展（奇数长度回文）
            int len1 = expandAroundCenter(s, i, i);
            // 以 s[i] 和 s[i+1] 为中心，向两边扩展（偶数长度回文）
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    /**
     * 从给定中心向两边扩展，返回最长回文子串的长度
     */
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 回文长度 = right - left - 1（因为退出时 left 和 right 各多走了一步）
        return right - left - 1;
    }
}
