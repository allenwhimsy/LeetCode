package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 0022 - 括号生成 (Generate Parentheses)
 *
 * 【题目描述】
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
 *
 * 示例 1：输入 n = 3 → 输出 ["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：输入 n = 1 → 输出 ["()"]
 *
 * 约束：1 <= n <= 8
 *
 * 【解题思路】
 * 回溯法（O(4^n / √n) 时间，O(n) 空间）✅ 推荐
 *   在回溯过程中维护两个计数器：left（已使用的左括号数）和 right（已使用的右括号数）。
 *   约束：left < n 时可以添加左括号；right < left 时可以添加右括号。
 *
 * @Author 郑晓胜
 */
public class P0022_GenerateParentheses {

    /**
     * 回溯法（推荐）
     * 时间复杂度：O(4^n / √n)（第 n 个卡特兰数）
     * 空间复杂度：O(n)（递归深度）
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    /**
     * 回溯生成括号
     * @param result 结果列表
     * @param current 当前构建的字符串
     * @param left   已使用的左括号数
     * @param right  已使用的右括号数
     * @param n      目标对数
     */
    private void backtrack(List<String> result, StringBuilder current, int left, int right, int n) {
        // 当构建的字符串长度达到 2n 时，加入结果
        if (current.length() == 2 * n) {
            result.add(current.toString());
            return;
        }

        // 可以添加左括号的条件：已使用的左括号数 < n
        if (left < n) {
            current.append('(');
            backtrack(result, current, left + 1, right, n);
            current.deleteCharAt(current.length() - 1); // 回溯
        }

        // 可以添加右括号的条件：已使用的右括号数 < 左括号数
        if (right < left) {
            current.append(')');
            backtrack(result, current, left, right + 1, n);
            current.deleteCharAt(current.length() - 1); // 回溯
        }
    }
}
