package com.zxs.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 0020 - 有效的括号 (Valid Parentheses)
 *
 * 【题目描述】
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s，判断字符串是否有效。
 * 有效字符串需满足：左括号必须用相同类型的右括号闭合；左括号必须以正确的顺序闭合；
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * 示例 1：输入 s = "()" → 输出 true
 * 示例 2：输入 s = "()[]{}" → 输出 true
 * 示例 3：输入 s = "(]" → 输出 false
 * 示例 4：输入 s = "([)]" → 输出 false
 * 示例 5：输入 s = "{[]}" → 输出 true
 *
 * 约束：1 <= s.length <= 10^4, s 仅由括号组成
 *
 * 【解题思路】
 * 栈（O(n) 时间，O(n) 空间）✅ 推荐
 *   遇到左括号入栈，遇到右括号检查栈顶是否为对应的左括号。
 *   遍历结束后栈应为空。
 *
 * @Author 郑晓胜
 */
public class P0020_ValidParentheses {

    /**
     * 栈解法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                // 检查括号是否匹配
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        }

        return stack.isEmpty();
    }
}
