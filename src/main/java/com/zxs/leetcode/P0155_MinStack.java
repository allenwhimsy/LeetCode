package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 155. 最小栈
 *
 * 设计一个支持 push、pop、top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类：
 * - MinStack() 初始化堆栈对象。
 * - void push(int val) 将元素 val 推入堆栈。
 * - void pop() 删除堆栈顶部的元素。
 * - int top() 获取堆栈顶部的元素。
 * - int getMin() 获取堆栈中的最小元素。
 *
 * 示例 1：
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3
 * minStack.pop();
 * minStack.top();      --> 返回 0
 * minStack.getMin();   --> 返回 -2
 *
 * 提示：
 * -2^31 <= val <= 2^31 - 1
 * pop、top 和 getMin 操作总是在非空栈上被调用
 * 最多调用 3 * 10^4 次 push、pop、top 和 getMin
 *
 * 解题思路：
 * 方法一：辅助栈（推荐）
 * 使用两个栈：主栈和辅助栈
 * 辅助栈保存当前栈中的最小值
 * push时：主栈正常push，辅助栈push当前最小值（Math.min(当前值, 辅助栈顶)）
 * pop时：两个栈同步pop
 * getMin：返回辅助栈顶
 * 时间复杂度 O(1)，空间复杂度 O(n)
 *
 * 方法二：差值法
 * 用long类型记录当前值与最小值的差，省空间但有溢出风险
 * 不推荐
 */
public class P0155_MinStack {

    private Stack<Integer> stack;    // 主栈
    private Stack<Integer> minStack;   // 辅助栈，保存当前最小值

    public P0155_MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        // 如果最小栈为空，或当前值更小，则push当前值；否则push栈顶（最小值）
        if (minStack.isEmpty() || val < minStack.peek()) {
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
            minStack.pop();
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        throw new RuntimeException("Stack is empty");
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        throw new RuntimeException("Stack is empty");
    }

    public static void main(String[] args) {
        P0155_MinStack minStack = new P0155_MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());  // -3
        minStack.pop();
        System.out.println(minStack.top());    // 0
        System.out.println(minStack.getMin()); // -2
    }
}
