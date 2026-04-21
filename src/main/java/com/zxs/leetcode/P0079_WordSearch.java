package com.zxs.leetcode;

/**
 * 【P0079】单词搜索
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0079_WordSearch {

    /**
     * 题目描述：
     * 给定一个 m x n 的二维字符网格 board 和一个字符串 word，判断 word 是否存在于网格中。
     * 搜索从任意单元格开始，每一步可以向四个方向移动，但同一单元格不能被重复使用。
     *
     * 示例：
     * 输入：board = [
     *          ['A','B','C','E'],
     *          ['S','F','C','S'],
     *          ['A','D','E','E']
     *        ], word = "ABCCED"
     * 输出：true
     *
     * 约束：
     * 1 <= m, n <= 6
     * 1 <= word.length <= 15
     *
     * 解题思路：
     * 回溯（DFS + 剪枝）：枚举每个起点，向四个方向递归搜索，路径不能重复（用已访问标记）—— 【推荐】O(m*n*4^L)时间
     * L 为单词长度，剪枝条件：越界、字符不匹配、已访问。
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtrack(board, word, i, j, 0, new boolean[m][n])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param board  字符网格
     * @param word   待搜索单词
     * @param i      当前行索引
     * @param j      当前列索引
     * @param idx    word 中已匹配的字符位置
     * @param visited 已访问单元格标记
     * @return 从 (i,j) 出发能否匹配剩余字符
     */
    private boolean backtrack(char[][] board, String word,
                              int i, int j, int idx, boolean[][] visited) {
        // 全部匹配成功
        if (idx == word.length()) return true;
        // 越界或字符不匹配或已访问
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
            || visited[i][j] || board[i][j] != word.charAt(idx)) {
            return false;
        }

        visited[i][j] = true;
        // 四个方向搜索
        boolean found = backtrack(board, word, i + 1, j, idx + 1, visited)
                      || backtrack(board, word, i - 1, j, idx + 1, visited)
                      || backtrack(board, word, i, j + 1, idx + 1, visited)
                      || backtrack(board, word, i, j - 1, idx + 1, visited);
        visited[i][j] = false; // 回溯：撤销访问标记
        return found;
    }

    public static void main(String[] args) {
        P0079_WordSearch solution = new P0079_WordSearch();
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        System.out.println(solution.exist(board, "ABCCED")); // true
        System.out.println(solution.exist(board, "SEE"));     // true
        System.out.println(solution.exist(board, "ABCB"));    // false
    }
}
