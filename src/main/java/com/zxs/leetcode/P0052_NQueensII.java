package com.zxs.leetcode;

/**
 * 【P0052】N皇后 II
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0052_NQueensII {

    /**
     * 题目描述：
     * 给定整数 n，返回 n 皇后问题中不同解决方案的数量。
     *
     * 示例：
     * 输入：n = 4
     * 输出：2
     *
     * 约束：
     * 1 <= n <= 9
     *
     * 解题思路：
     * 与 P0051 完全相同，只是把收集棋盘改成计数 —— 【推荐】O(n!)时间，O(n)空间
     */

    private boolean[] cols;
    private boolean[] diag1;
    private boolean[] diag2;
    private int count = 0;

    public int totalNQueens(int n) {
        cols = new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];
        int[] queenCols = new int[n];
        backtrack(0, n, queenCols);
        return count;
    }

    private void backtrack(int row, int n, int[] queenCols) {
        if (row == n) {
            count++; // 找到一个解就+1
            return;
        }
        for (int col = 0; col < n; col++) {
            int d1 = row + col;
            int d2 = row - col + n - 1;
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }
            queenCols[row] = col;
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            backtrack(row + 1, n, queenCols);
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    public static void main(String[] args) {
        P0052_NQueensII solution = new P0052_NQueensII();
        System.out.println("n=4: " + solution.totalNQueens(4)); // 2
        System.out.println("n=1: " + solution.totalNQueens(1)); // 1
    }
}
