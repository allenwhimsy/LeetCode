package com.zxs.leetcode;

/**
 * 【P0085】最大矩形
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0085_MaximalRectangle {

    /**
     * 题目描述：
     * 给定一个二维二进制矩阵 matrix，只有 0 和 1 两种字符。
     * 请你找出只包含 1 的最大矩形面积，并返回面积。
     *
     * 示例：
     * 输入：matrix = [
     *          ["1","0","1","0","0"],
     *          ["1","0","1","1","1"],
     *          ["1","1","1","1","1"],
     *          ["1","0","0","1","0"]
     *        ]
     * 输出：6
     *
     * 约束：
     * matrix.length <= 200, matrix[i].length <= 10
     *
     * 解题思路：
     * 将二维问题转化为一维问题：把每行视为柱状图的基底，逐行累加高度，然后用 P0084 的单调栈算法求最大矩形 —— 【推荐】O(m*n)时间，O(n)空间
     * 方法2：暴力枚举四个角 —— O(m^2 * n^2)，不推荐。
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n]; // 每列的高度（以当前行为底的连续1的高度）
        int maxArea = 0;

        for (int row = 0; row < m; row++) {
            // 更新每列的高度
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == '1') {
                    heights[col] += 1;
                } else {
                    heights[col] = 0; // 断开，重置高度
                }
            }
            // 对当前行对应的柱状图求最大矩形（套用 P0084）
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    // P0084 单调栈算法（复制以便独立运行）
    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;
        int[] extended = new int[n + 2];
        extended[0] = 0;
        System.arraycopy(heights, 0, extended, 1, n);
        extended[n + 1] = 0;

        java.util.Deque<Integer> stack = new java.util.LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i < extended.length; i++) {
            while (!stack.isEmpty() && extended[i] < extended[stack.peek()]) {
                int height = extended[stack.pop()];
                int width = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        P0085_MaximalRectangle solution = new P0085_MaximalRectangle();
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println(solution.maximalRectangle(matrix)); // 6
    }
}
