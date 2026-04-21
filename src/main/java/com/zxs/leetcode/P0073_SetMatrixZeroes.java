package com.zxs.leetcode;

/**
 * 【P0073】矩阵置零
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0073_SetMatrixZeroes {

    /**
     * 题目描述：
     * 给定一个 m x n 的整数矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。
     * 请使用原地算法。
     *
     * 示例：
     * 输入：matrix = [
     *          [1,1,1],
     *          [1,0,1],
     *          [1,1,1]
     *        ]
     * 输出：[
     *        [1,0,1],
     *        [0,0,0],
     *        [1,0,1]
     *      ]
     *
     * 约束：
     * m == matrix.length, n == matrix[0].length
     * 1 <= m, n <= 200
     *
     * 解题思路：
     * 方法1：用 O(m+n) 额外空间记录哪些行/列需要置零 —— 【推荐】O(m*n)时间，O(m+n)空间
     * 方法2：用矩阵第一行和第一列作为标记数组（原地），但需额外变量记录第一行/列是否全零
     * 核心：第一行/列若有0则整行/列置零，但第一行/列本身的置零操作要最后单独处理。
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        // 第一遍：找出所有需要置零的行和列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // 第二遍：将需要置零的行全部设为0
        for (int i = 0; i < m; i++) {
            if (row[i]) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 第三遍：将需要置零的列全部设为0
        for (int j = 0; j < n; j++) {
            if (col[j]) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        P0073_SetMatrixZeroes solution = new P0073_SetMatrixZeroes();
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        solution.setZeroes(matrix);
        for (int[] row : matrix) {
            System.out.println(java.util.Arrays.toString(row));
        }
    }
}
