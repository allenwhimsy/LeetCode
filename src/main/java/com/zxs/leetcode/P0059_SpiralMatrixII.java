package com.zxs.leetcode;

/**
 * 【P0059】螺旋矩阵 II
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0059_SpiralMatrixII {

    /**
     * 题目描述：
     * 给你一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵。
     *
     * 示例：
     * 输入：n = 3
     * 输出：[
     *        [1,2,3],
     *        [8,9,4],
     *        [7,6,5]
     *      ]
     *
     * 约束：
     * 1 <= n <= 20
     *
     * 解题思路：
     * 与 P0054 思路完全一致，只是反过来：模拟填数而非读取 —— 【推荐】O(n^2)时间，O(1)空间
     * 维护四条边界，每填完一条边后缩小边界。
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int num = 1;
        int total = n * n;

        while (num <= total) {
            // 从左到右，填上层
            for (int col = left; col <= right; col++) {
                matrix[top][col] = num++;
            }
            top++;

            // 从上到下，填右侧
            for (int row = top; row <= bottom; row++) {
                matrix[row][right] = num++;
            }
            right--;

            // 从右到左，填下层（需判断是否还有行）
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    matrix[bottom][col] = num++;
                }
                bottom--;
            }

            // 从下到上，填左侧（需判断是否还有列）
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    matrix[row][left] = num++;
                }
                left++;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        P0059_SpiralMatrixII solution = new P0059_SpiralMatrixII();
        int[][] matrix = solution.generateMatrix(3);
        for (int[] row : matrix) {
            for (int v : row) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
