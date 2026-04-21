package com.zxs.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 【P0084】柱状图中最大的矩形
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0084_LargestRectangleHistogram {

    /**
     * 题目描述：
     * 给定 n 个非负整数表示柱状图的高度，每个柱子的宽度为 1，
     * 求在柱状图中能够勾勒出的最大矩形面积。
     *
     * 示例：
     * 输入：heights = [2,1,5,6,2,3]
     * 输出：10（5和6两根柱子，高度为5，宽度为2）
     *
     * 约束：
     * 1 <= heights.length <= 10^5
     * 0 <= heights[i] <= 10^4
     *
     * 解题思路：
     * 方法1：单调栈（哨兵）—— 【推荐】O(n)时间，O(n)空间
     * 在数组首尾各加一个 0，简化边界处理：遍历时维护递增栈，弹出时计算以弹出高度为最小值的矩形面积。
     * 方法2：分治（按最低柱分治）—— O(n log n)
     * 方法3：暴力（按每个柱子向左右扩展）—— O(n^2)
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;

        // 首尾各加一个 0 作为哨兵，简化边界处理
        int[] extended = new int[n + 2];
        extended[0] = 0;
        System.arraycopy(heights, 0, extended, 1, n);
        extended[n + 1] = 0;

        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;

        for (int i = 0; i < extended.length; i++) {
            // 栈中存索引，栈顶对应的高度始终 >= 当前高度（递增栈）
            while (!stack.isEmpty() && extended[i] < extended[stack.peek()]) {
                int height = extended[stack.pop()]; // 弹出高度（其高度已确定为矩形高度）
                // 宽度 = 右边界(i) - 左边界(stack.peek()) - 1
                int width = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        P0084_LargestRectangleHistogram solution = new P0084_LargestRectangleHistogram();
        System.out.println(solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3})); // 10
        System.out.println(solution.largestRectangleArea(new int[]{2, 4}));            // 4
    }
}
