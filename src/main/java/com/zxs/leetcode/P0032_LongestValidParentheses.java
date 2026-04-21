package com.zxs.leetcode;

/**
 * P0032 最长有效括号
 *
 * 题目描述：
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 *
 * 约束条件：
 * - 0 <= s.length <= 3 * 10^4
 * - s[i] 为 '(' 或 ')'
 *
 * 解题思路：
 * 【方法一：栈（推荐）】O(n) 时间，O(n) 空间
 * 用栈记录上一个不合法位置，初始栈中放入 -1。
 * 遇到 '(' 入栈；遇到 ')' 出栈，若栈空则将当前下标入栈（作为新的基准）。
 * 否则更新最长长度 = i - 栈顶。
 *
 * 【方法二：双指针】O(n) 时间，O(1) 空间
 * 两次遍历：从左到右和从右到左，分别处理 '(' 多余和 ')' 多余的情况。
 *
 * 【方法三：动态规划】O(n) 时间，O(n) 空间
 * dp[i] 表示以 i 结尾的最长有效括号长度。
 *
 * @Author 郑晓胜
 */
public class P0032_LongestValidParentheses {

    /**
     * 方法一：栈（推荐）- 清晰直观，易于理解
     */
    public int longestValidParentheses_stack(String s) {
        if (s == null || s.isEmpty()) return 0;

        int maxLen = 0;
        // 栈中存放下标，初始化放入 -1 作为基准
        java.util.ArrayDeque<Integer> stack = new java.util.ArrayDeque<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // '(' 入栈
                stack.push(i);
            } else {
                // ')' 出栈
                stack.pop();
                if (stack.isEmpty()) {
                    // 栈空，说明当前 ')' 是新的基准点，入栈
                    stack.push(i);
                } else {
                    // 更新最长长度：当前下标 - 栈顶下标
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }

    /**
     * 方法二：双指针 - O(1) 空间
     */
    public int longestValidParentheses_twoPointers(String s) {
        if (s == null || s.isEmpty()) return 0;

        int left = 0, right = 0, maxLen = 0;

        // 从左到右：处理 '(' 数量多于 ')' 的情况
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (right > left) {
                // 右括号多了，重置
                left = right = 0;
            }
        }

        // 从右到左：处理 ')' 数量多于 '(' 的情况
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left > right) {
                // 左括号多了，重置
                left = right = 0;
            }
        }
        return maxLen;
    }

    /**
     * 方法三：动态规划
     */
    public int longestValidParentheses_dp(String s) {
        if (s == null || s.isEmpty()) return 0;

        int n = s.length();
        int[] dp = new int[n];
        int maxLen = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // 形如 (...) ：dp[i] = dp[i-2] + 2
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // 形如 (...)) ：前面有配对的 (...))
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0032_LongestValidParentheses solution = new P0032_LongestValidParentheses();

        String[] tests = {"(()", ")()())", "", "()(()", "(()())", ")()())()()("};
        for (String s : tests) {
            int result = solution.longestValidParentheses_stack(s);
            System.out.println("输入: \"" + s + "\" -> 最大长度: " + result);
        }
    }
}
