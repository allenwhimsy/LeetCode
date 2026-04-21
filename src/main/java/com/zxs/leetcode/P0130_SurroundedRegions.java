package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/surrounded-regions/
 *
 * 题目：被围绕的区域（Surrounded Regions）
 *
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成。
 * 捕获所有未被包围的区域：将所有 'O' 用 'X' 填充。
 * 被包围的区域是那些完全被 'X' 包围的区域，任何边界上的 'O' 都不会被填充为 'X'。
 *
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 *
 * 约束：
 * - m == board.length
 * - n == board[0].length
 * - 1 <= m, n <= 200
 *
 * 解题思路：
 * 方法一：从边界 DFS/BFS（推荐）
 *   - 所有与边界 'O' 相连的 'O' 都是不被包围的（活区域）
 *   - 从边界上的 'O' 出发，DFS/BFS 标记所有相连 'O' 为保留
 *   - 最后将所有未被标记的 'O' 变为 'X'
 *   - 时间 O(m*n)，空间 O(m*n)
 *
 * 方法二：并查集
 *   - 将所有边界 'O' 连接到虚拟节点，再合并相邻 'O'
 *   - 最后将不与虚拟节点相连的 'O' 变 'X'
 *   - 较复杂
 *
 * 推荐方法：从边界 DFS 标记
 */
public class P0130_SurroundedRegions {

    /**
     * 【推荐解法】从边界 DFS 标记不被包围的区域
     * 核心：边界上的 O 及其连通区域不会被 X 包围，标记保留
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int m = board.length;
        int n = board[0].length;

        // 从四条边上的 'O' 出发，DFS 标记所有不会被包围的区域
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0, m, n);         // 左列
            dfs(board, i, n - 1, m, n);     // 右列
        }
        for (int j = 0; j < n; j++) {
            dfs(board, 0, j, m, n);         // 顶行
            dfs(board, m - 1, j, m, n);     // 底行
        }

        // 遍历 board，将所有 'O' 变为 'X'（被包围），'#' 变回 'O'（保留）
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X'; // 被包围
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O'; // 边界连通区域，保留
                }
            }
        }
    }

    // DFS：将与边界相连的 O 标记为 #（表示不会被包围）
    private void dfs(char[][] board, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '#'; // 标记为保留
        dfs(board, i + 1, j, m, n);
        dfs(board, i - 1, j, m, n);
        dfs(board, i, j + 1, m, n);
        dfs(board, i, j - 1, m, n);
    }
}
