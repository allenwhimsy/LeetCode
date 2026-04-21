package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【P0054】螺旋矩阵
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0054_SpiralMatrix {

    /**
     * 题目描述：
     * 给你一个 m 行 n 列的矩阵 matrix，请以顺时针螺旋顺序返回矩阵中的所有元素。
     *
     * 示例：
     * 输入：matrix = [
     *          [1,2,3],
     *          [4,5,6],
     *          [7,8,9]
     *        ]
     * 输出：[1,2,3,6,9,8,7,4,5]
     *
     * 约束：
     * m == matrix.length, n == matrix[0].length
     * 1 <= m, n <= 10
     *
     * 解题思路：
     * 模拟遍历：维护四条边界（上右下左），每次遍历一条边后缩小边界 —— 【推荐】O(m*n)时间，O(1)空间
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int m = matrix.length;
        int n = matrix[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;

        while (top <= bottom && left <= right) {
            // 从左到右，遍历上层
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;

            // 从上到下，遍历右侧
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            // 从右到左，遍历下层（需判断是否还有行）
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }

            // 从下到上，遍历左侧（需判断是否还有列）
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P0054_SpiralMatrix solution = new P0054_SpiralMatrix();
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println(solution.spiralOrder(matrix));
    }
}
