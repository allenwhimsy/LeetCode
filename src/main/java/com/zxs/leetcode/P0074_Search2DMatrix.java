package com.zxs.leetcode;

/**
 * 【P0074】搜索二维矩阵
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0074_Search2DMatrix {

    /**
     * 题目描述：
     * 给你一个满足以下条件的 m x n 整数矩阵：
     * - 每行的整数从左到右按升序排列
     * - 每行的第一个整数大于上一行的最后一个整数
     * 编写一个高效的算法搜索值为 target 的元素。
     *
     * 示例：
     * 输入：matrix = [
     *          [1,3,5,7],
     *          [10,11,16,20],
     *          [23,30,34,60]
     *        ], target = 3
     * 输出：true
     *
     * 约束：
     * m == matrix.length, n == matrix[0].length
     * 1 <= m, n <= 100
     * -10^4 <= matrix[i][j] <= 10^4
     *
     * 解题思路：
     * 方法1：两次二分 —— 【推荐】先对第一列二分找到行，再对那一行二分找元素。O(log m + log n)
     * 方法2：一次二分（将二维坐标映射为一维）：midIndex / n 确定行，midIndex % n 确定列。O(log(m*n))
     * 方法3：Z字形搜索（从右上角开始，比大小决定向左或向下）
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;

        // 第一次二分：在第一列中找目标所在的行
        int top = 0, bottom = m - 1;
        while (top < bottom) {
            int mid = top + (bottom - top + 1) / 2; // 偏上取整，避免死循环
            if (matrix[mid][0] <= target) {
                top = mid; // mid 行首 <= target，target 可能在 mid 行
            } else {
                bottom = mid - 1;
            }
        }
        int row = top; // 最终行号

        // 第二次二分：在确定的行中搜索
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P0074_Search2DMatrix solution = new P0074_Search2DMatrix();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(solution.searchMatrix(matrix, 3));   // true
        System.out.println(solution.searchMatrix(matrix, 13));  // false
    }
}
