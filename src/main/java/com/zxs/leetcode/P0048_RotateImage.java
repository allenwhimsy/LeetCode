package com.zxs.leetcode;

/**
 * P0048 旋转图像
 *
 * 题目描述：
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。
 * 你要将图像顺时针旋转 90 度。
 * 你必须在原地直接修改输入矩阵（即不能创建另一个矩阵来存储结果）。
 *
 * 示例 1：
 * 输入：
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ]
 * 输出：
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * 示例 2：
 * 输入：
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12, 2]
 * ]
 * 输出：
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [ 2, 7,10,11]
 * ]
 *
 * 约束条件：
 * - matrix.length == n
 * - matrix[i].length == n
 * - 1 <= n <= 20
 * - -1000 <= matrix[i][j] <= 1000
 *
 * 解题思路：
 * 【方法一：先转置后镜像（推荐）】O(n^2) 时间，O(1) 空间
 * 顺时针旋转90°等价于：
 * Step 1: 先沿主对角线转置（行变列）
 * Step 2: 再沿垂直中线镜像（左右翻转）
 *
 * 【方法二：四次旋转】O(n^2) 时间，O(1) 空间
 * 每次将四个对应位置的元素顺时针交换。
 *
 * @Author 郑晓胜
 */
public class P0048_RotateImage {

    /**
     * 方法一：先转置后镜像（推荐）- 直观好记
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) return;

        int n = matrix.length;

        // 第一步：沿主对角线转置（i < j 才交换，保证只交换一次）
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 第二步：沿垂直中线左右翻转（每行的 j 从 0 到 n/2-1）
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    /**
     * 方法二：原地四次旋转
     * 每次将 matrix[i][j] -> matrix[j][n-1-i] -> matrix[n-1-i][n-1-j] -> matrix[n-1-j][i] -> matrix[i][j]
     */
    public void rotate_fourSwap(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) return;

        int n = matrix.length;
        for (int i = 0; i < (n / 2); i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0048_RotateImage solution = new P0048_RotateImage();

        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("测试 3x3 矩阵:");
        solution.rotate(matrix1);
        printMatrix(matrix1);

        int[][] matrix2 = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 2}
        };
        System.out.println("\n测试 4x4 矩阵:");
        solution.rotate(matrix2);
        printMatrix(matrix2);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j] + (j < row.length - 1 ? " " : ""));
            }
            System.out.println();
        }
    }
}
