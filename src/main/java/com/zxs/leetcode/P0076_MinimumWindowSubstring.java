package com.zxs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 【P0076】最小覆盖子串
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0076_MinimumWindowSubstring {

    /**
     * 题目描述：
     * 给你两个字符串 s 和 t，返回 s 中涵盖 t 所有字符（不重复）的最小子串。
     * 若 s 中不存在这样的子串，返回空串 ""。
     *
     * 示例：
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     *
     * 约束：
     * 1 <= s.length, t.length <= 10^5
     *
     * 解题思路：
     * 滑动窗口：右指针扩展找可行解，左指针收缩找最优解 —— 【推荐】O(|s|+|t|)时间，O(1)空间（字符集大小为128的数组）
     * 维护 need（还需要的字符数）和 counter（当前窗口中各字符出现次数）。
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        // 字符集为 ASCII，数组模拟哈希表效率更高
        int[] need = new int[128]; // t 中各字符的需求量
        int[] window = new int[128]; // 窗口中各字符的数量

        // 统计 t 中各字符的需求
        for (char c : t.toCharArray()) {
            need[c]++;
        }

        int left = 0, right = 0;
        int valid = 0;           // 已满足的字符种类数
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need[c] > 0) {
                window[c]++;
                if (window[c] == need[c]) {
                    valid++;
                }
            }

            // 当所有字符需求都满足时，收缩左边界找最优解
            while (valid == need.length() - countZero(need)) {
                // 更新最优结果
                if (right - left < minLen) {
                    minLen = right - left;
                    minLeft = left;
                }

                char d = s.charAt(left);
                left++;
                if (need[d] > 0) {
                    if (window[d] == need[d]) {
                        valid--; // 移出后不再满足
                    }
                    window[d]--;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }

    // 统计 need 数组中非零（有效字符）的个数
    private int countZero(int[] need) {
        int count = 0;
        for (int v : need) {
            if (v == 0) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        P0076_MinimumWindowSubstring solution = new P0076_MinimumWindowSubstring();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC")); // "BANC"
        System.out.println(solution.minWindow("a", "a"));                // "a"
    }
}
