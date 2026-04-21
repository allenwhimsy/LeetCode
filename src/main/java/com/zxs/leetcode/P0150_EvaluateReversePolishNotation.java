package com.zxs.leetcode;

import java.util.Stack;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/evaluate-reverse-polish-notation/
 *
 * 题目：逆波兰表达式求值（Evaluate Reverse Polish Notation）
 *
 * 给你一个字符串数组 tokens ，表示一个逆波兰表达式，求该表达式的值。
 * 有效的算符为 +、-、*、/ 。
 * 其中每个操作数都可以使用任意长度的 32 位整数。
 * 保证以下情况：
 *   - 逆波兰表达式总是有效的
 *   - 计算过程中所有中间结果的绝对值均不超过 2^31
 *
 * 示例 1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：((2 + 1) * 3) = 9
 *
 * 示例 2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：(4 + (13 / 5)) = 6
 *
 * 约束：
 * - 1 <= tokens.length <= 10^4
 * - tokens[i] 是一个算符或整数（整数值可能包含前导零）
 * - -2^31 <= integer <= 2^31 - 1
 *
 * 解题思路：
 * 方法一：栈（推荐）
 *   - 遇到数字则压栈，遇到操作符则弹出两个元素计算，将结果压栈
 *   - 减法和除法注意顺序：第二个弹出的数 作为被操作数
 *   - 时间 O(n)，空间 O(n)
 *
 * 推荐方法：栈
 */
public class P0150_EvaluateReversePolishNotation {

    /**
     * 【推荐解法】栈
     * 核心：数字压栈，操作符弹出两个数计算后压栈
     * 关键：减法和除法注意操作数顺序：先弹出的为右操作数
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    int b = stack.pop(); // 后弹出的是右操作数
                    int a = stack.pop(); // 先弹出的是左操作数
                    stack.push(a + b);
                }
                case "-" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b); // 注意顺序：a - b
                }
                case "*" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a * b);
                }
                case "/" -> {
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b); // 注意顺序：a / b（向零取整）
                }
                default -> stack.push(Integer.parseInt(token)); // 数字压栈
            }
        }
        return stack.pop();
    }
}
