package com.zxs.leetcode;

/**
 * P0037 解数独
 *
 * 题目描述：
 * 编写一个程序，通过填充空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * 1. 数字 1-9 在每一行只能出现一次
 * 2. 数字 1-9 在每一列只能出现一次
 * 3. 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
 *
 * 示例：
 * 输入：
 * [
 *   ['5','3','.',  '7','.','.',  '.','.','.'],
 *   ['6','.','.',  '1','9','5',  '.','.','.'],
 *   ['.', '9','8', '.','.','.',  '.','6','.'],
 *   ['8','.','.',  '.','6','.',  '.','.','3'],
 *   ['4','.','.',  '8','.','3',  '.','.','1'],
 *   ['7','.','.',  '.','2','.',  '.','.','6'],
 *   ['.', '6','.', '.','.','.',  '2','8','.'],
 *   ['.','.','.',  '4','1','9',  '.','.','5'],
 *   ['.','.','.',  '.','8','.',  '.','7','9']
 * ]
 * 输出：（有唯一解）
 *
 * 约束条件：
 * - board.length == 9
 * - board[i].length == 9
 * - board[i][j] 是数字 '1'-'9' 或 '.'
 * - 题目数据 保证 输入数独仅有一个解
 *
 * 解题思路：
 * 【方法一：回溯 + 三大约束剪枝（推荐）】O(9^m) m为空格数
 * 用三个 boolean 数组记录每行、每列、每宫格已使用的数字：
 *   rows[i][d]  = 第 i 行数字 d+1 是否已使用
 *   cols[j][d]  = 第 j 列数字 d+1 是否已使用
 *   blocks[b][d] = 第 b 个宫格数字 d+1 是否已使用
 * 对每个空格尝试填入未使用的数字，递归回溯。
 * 找到解即返回（题目保证唯一解）。
 *
 * @Author 郑晓胜
 */
public class P0037_SudokuSolver {

    // 三大约束：行、列、宫格
    private boolean[][] rows = new boolean[9][9];
    private boolean[][] cols = new boolean[9][9];
    private boolean[][] blocks = new boolean[9][9];

    /**
     * 解数独（推荐：回溯 + 约束记录）
     */
    public void solveSudoku(char[][] board) {
        // 第一步：初始化约束
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int d = num - 1;
                    rows[i][d] = true;
                    cols[j][d] = true;
                    blocks[(i / 3) * 3 + j / 3][d] = true;
                }
            }
        }
        // 第二步：回溯求解
        backtrack(board, 0, 0);
    }

    /**
     * 回溯核心
     */
    private boolean backtrack(char[][] board, int row, int col) {
        // 如果到了行末，转到下一行
        if (col == 9) {
            return backtrack(board, row + 1, 0);
        }
        // 所有行处理完毕，说明找到解
        if (row == 9) {
            return true;
        }

        // 如果已是数字，跳过
        if (board[row][col] != '.') {
            return backtrack(board, row, col + 1);
        }

        // 尝试填入 1-9
        for (int num = 1; num <= 9; num++) {
            int d = num - 1;
            int blockIndex = (row / 3) * 3 + col / 3;

            // 剪枝：如果当前数字违反任一约束，跳过
            if (rows[row][d] || cols[col][d] || blocks[blockIndex][d]) {
                continue;
            }

            // 填入并更新约束
            board[row][col] = (char) ('0' + num);
            rows[row][d] = cols[col][d] = blocks[blockIndex][d] = true;

            // 递归
            if (backtrack(board, row, col + 1)) {
                return true; // 找到解，一路返回 true
            }

            // 回溯：撤销填入
            board[row][col] = '.';
            rows[row][d] = cols[col][d] = blocks[blockIndex][d] = false;
        }
        return false;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0037_SudokuSolver solution = new P0037_SudokuSolver();

        char[][] board = {
            {'5','3','.', '7','.','.', '.','.','.'},
            {'6','.','.', '1','9','5', '.','.','.'},
            {'.','9','8', '.','.','.', '.','6','.'},
            {'8','.','.', '.','6','.', '.','.','3'},
            {'4','.','.', '8','.','3', '.','.','1'},
            {'7','.','.', '.','2','.', '.','.','6'},
            {'.','6','.', '.','.','.', '2','8','.'},
            {'.','.','.', '4','1','9', '.','.','5'},
            {'.','.','.', '.','8','.', '.','7','9'}
        };

        solution.solveSudoku(board);

        // 打印结果
        System.out.println("数独解：");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + (j % 3 == 2 ? "  " : " "));
            }
            System.out.println(i % 3 == 2 ? "\n" : "");
        }
    }
}
