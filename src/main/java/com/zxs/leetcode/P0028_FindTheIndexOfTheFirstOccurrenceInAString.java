package com.zxs.leetcode;

/**
 * LeetCode 0028 - 找出字符串中第一个匹配项的下标 (Find the Index of the First Occurrence in a String)
 *
 * 【题目描述】
 * 给你两个字符串 haystack 和 needle，请你在 haystack 字符串中找出 needle 字符串
 * 的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回 -1。
 *
 * 示例 1：输入 haystack = "sadbutsad", needle = "sad" → 输出 0
 * 示例 2：输入 haystack = "leetcode", needle = "leeto" → 输出 -1
 * 示例 3：输入 haystack = "hello", needle = "ll" → 输出 2
 *
 * 约束：1 <= haystack.length, needle.length <= 10^4
 *
 * 【解题思路】
 * 方法一：内置函数（O(n*m) 时间，实际较快）
 *
 * 方法二：KMP 算法（O(n+m) 时间，O(m) 空间）✅ 推荐
 *   先构建前缀函数（next 数组），然后利用 next 数组避免不必要的回溯。
 *   核心思想：当匹配失败时，利用已匹配部分的信息确定下一次匹配的起始位置。
 *
 * @Author 郑晓胜
 */
public class P0028_FindTheIndexOfTheFirstOccurrenceInAString {

    /**
     * KMP 算法（推荐）
     * 时间复杂度：O(n + m)
     * 空间复杂度：O(m)
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int n = haystack.length(), m = needle.length();
        if (m > n) return -1;

        // 构建 next 数组（前缀函数）
        int[] next = buildNext(needle);

        // KMP 匹配
        for (int i = 0, j = 0; i < n; i++) {
            // 匹配失败时，根据 next 数组回退 j
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            // 匹配成功，j 前进
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            // 完全匹配
            if (j == m) {
                return i - m + 1;
            }
        }

        return -1;
    }

    /**
     * 构建前缀函数（next 数组）
     * next[i] 表示 needle[0..i] 的最长相等前后缀长度
     */
    private int[] buildNext(String needle) {
        int m = needle.length();
        int[] next = new int[m];
        int len = 0;

        for (int i = 1; i < m; i++) {
            while (len > 0 && needle.charAt(i) != needle.charAt(len)) {
                len = next[len - 1];
            }
            if (needle.charAt(i) == needle.charAt(len)) {
                len++;
            }
            next[i] = len;
        }
        return next;
    }
}
