package com.zxs.leetcode;

/**
 * 【P0051】N皇后
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0051_NQueens {

    /**
     * 题目描述：
     * n 皇后问题 是将 n 个皇后放置在 n×n 的国际象棋棋盘上，使得任意两个皇后都不处于同一条行、列或斜线上。
     *
     * 示例：
     * 输入：n = 4
     * 输出：[
     *        [".Q..","...Q","Q...","..Q."],
     *        ["..Q.",".Q..","...Q","Q..."]
     *      ]
     *
     * 约束：
     * 1 <= n <= 9
     *
     * 解题思路：
     * 方法1：回溯 + 三个哈希数组记录列、主对角线、副对角线占用情况 —— 【推荐】O(n!)时间，O(n)空间
     * 方法2：位运算回溯 —— 更高效但代码较复杂
     *
     * 本实现使用方法1，逐行放置皇后，用三个集合快速判断冲突。
     */

    // 记录列是否已有皇后
    private boolean[] cols;
    // 记录主对角线（行+列）是否已有皇后
    private boolean[] diag1;
    // 记录副对角线（行-列+n-1）是否已有皇后
    private boolean[] diag2;

    public java.util.List<java.util.List<String>> solveNQueens(int n) {
        cols = new boolean[n];
        diag1 = new boolean[2 * n - 1]; // 主对角线共 2n-1 条
        diag2 = new boolean[2 * n - 1]; // 副对角线共 2n-1 条
        java.util.List<java.util.List<String>> result = new java.util.ArrayList<>();
        // each row's queen column index
        int[] queenCols = new int[n];
        backtrack(0, n, queenCols, result);
        return result;
    }

    /**
     * 回溯递归
     *
     * @param row           当前行
     * @param n             棋盘大小
     * @param queenCols     记录每行皇后所在列
     * @param result        结果集
     */
    private void backtrack(int row, int n, int[] queenCols,
                          java.util.List<java.util.List<String>> result) {
        // 所有行都放置完，找到一个解
        if (row == n) {
            result.add(buildBoard(queenCols, n));
            return;
        }
        // 枚举当前行的每一列
        for (int col = 0; col < n; col++) {
            int d1 = row + col;          // 主对角线索引
            int d2 = row - col + n - 1;  // 副对角线索引
            // 判断列、主对角线、副对角线是否冲突
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue; // 冲突，跳过
            }
            // 放置皇后
            queenCols[row] = col;
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            // 递归下一行
            backtrack(row + 1, n, queenCols, result);
            // 撤销皇后（回溯）
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    /**
     * 将 queenCols 数组转换为题目要求的棋盘格式
     *
     * @param queenCols 每行皇后所在列
     * @param n         棋盘大小
     * @return 棋盘的字符串列表表示
     */
    private java.util.List<String> buildBoard(int[] queenCols, int n) {
        java.util.List<String> board = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            java.util.Arrays.fill(row, '.');
            row[queenCols[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    // 测试
    public static void main(String[] args) {
        P0051_NQueens solution = new P0051_NQueens();
        System.out.println("n=4: " + solution.solveNQueens(4));
        System.out.println("n=1: " + solution.solveNQueens(1));
    }
}
