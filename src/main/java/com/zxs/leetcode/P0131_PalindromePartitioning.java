package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/palindrome-partitioning/
 *
 * 题目：分割回文串（Palindrome Partitioning）
 *
 * 给你一个字符串 s ，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回所有可能的分割方案。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["aa","b"],["a","a","b"]]
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 * 约束：
 * - 1 <= s.length <= 16
 * - s 仅由小写英文字母组成
 *
 * 解题思路：
 * 方法一：回溯法（推荐）
 *   - 逐字符尝试切分，判断子串是否为回文
 *   - 回文则递归处理剩余部分
 *   - 时间 O(n * 2^n)，空间 O(n)
 *
 * 方法二：预计算回文 + 回溯
 *   - 预处理每个子串是否为回文，减少重复判断
 *   - 时间略优，空间换时间
 *
 * 推荐方法：回溯法
 */
public class P0131_PalindromePartitioning {

    private List<List<String>> result = new ArrayList<>();

    /**
     * 【推荐解法】回溯法
     * 核心：枚举所有可能的切分位置，剪枝非回文子串
     */
    public List<List<String>> partition(String s) {
        List<String> path = new ArrayList<>();
        backtrack(s, 0, path);
        return result;
    }

    private void backtrack(String s, int start, List<String> path) {
        // 到达字符串末尾，收集一个完整方案
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 枚举切分点 start..i
        for (int i = start; i < s.length(); i++) {
            // 剪枝：如果 s[start..i] 不是回文，跳过
            if (!isPalindrome(s, start, i)) continue;

            // 做选择
            path.add(s.substring(start, i + 1));
            // 递归处理剩余部分
            backtrack(s, i + 1, path);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }

    // 判断 s[l..r] 是否为回文
    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}
