package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/word-break-ii/
 *
 * 题目：单词拆分 II（Word Break II）
 *
 * 给定一个字符串 s 和一个字符串字典 wordDict ，
 * 在字符串中增加空格来构建一个句子，使句子中每个单词都在字典中。
 * 返回所有可能的句子。
 *
 * 示例 1：
 * 输入：s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * 输出：["cats and dog","cat sand dog"]
 *
 * 约束：
 * - 1 <= s.length <= 500
 * - 1 <= wordDict.length <= 1000
 * - wordDict[i].length <= 10
 * - s 和 wordDict[i] 仅由小写字母组成
 *
 * 解题思路：
 * 方法一：DP + 回溯（推荐）
 *   - DP 预计算哪些子串可以拆分（剪枝，减少递归量）
 *   - 回溯枚举所有拆分方案
 *   - 时间 O(n^2 + 输出大小)，空间 O(n + 输出大小)
 *
 * 方法二：纯回溯（会超时）
 *   - 不做预剪枝，时间复杂度较高
 *
 * 推荐方法：DP + 回溯
 */
public class P0140_WordBreakII {

    private List<String> result = new ArrayList<>();

    /**
     * 【推荐解法】DP 预计算 + 回溯
     * 关键：先 DP 找出哪些位置可到达，剪掉不可行的分支
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();

        // dp[i]：s[i..n-1] 是否可拆分（逆向预计算）
        boolean[] canBreak = new boolean[n + 1];
        canBreak[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (dict.contains(s.substring(i, j + 1)) && canBreak[j + 1]) {
                    canBreak[i] = true;
                    break;
                }
            }
        }

        // 回溯枚举所有拆分
        backtrack(s, 0, dict, canBreak, new StringBuilder());
        return result;
    }

    private void backtrack(String s, int start, Set<String> dict,
                           boolean[] canBreak, StringBuilder path) {
        if (start == s.length()) {
            result.add(path.toString().trim());
            return;
        }

        // 剪枝：当前位置不可达则直接返回
        if (!canBreak[start]) return;

        for (int end = start; end < s.length(); end++) {
            String word = s.substring(start, end + 1);
            if (!dict.contains(word)) continue;

            int lenBefore = path.length();
            if (path.length() > 0) path.append(" ");
            path.append(word);
            backtrack(s, end + 1, dict, canBreak, path);
            path.setLength(lenBefore); // 撤销
        }
    }
}
