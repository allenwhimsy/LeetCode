package com.zxs.leetcode;

/**
 * P0036 有效的数独
 *
 * 题目描述：
 * 请你判断一个 9x9 的数独是否有效。
 * 根据以下规则，验证已经填入的数字是否有效即可：
 * 1. 每一行必须包含数字 1-9，且不能重复
 * 2. 每一列必须包含数字 1-9，且不能重复
 * 3. 每一个 3x3 的小宫格必须包含数字 1-9，且不能重复
 *
 * 注意：一个有效的数独：
 * - 不一定是已经填满的（可以有空位）
 * - 已经填入的数字必须满足上述三个条件
 *
 * 示例 1：
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
 * 输出：true
 *
 * 约束条件：
 * - board.length == 9
 * - board[i].length == 9
 * - board[i][j] 是数字 '1'-'9' 或 '.'
 *
 * 解题思路：
 * 【方法一：三次遍历 + 哈希集合（推荐）】O(1) 时间（数独大小固定 9x9）
 * 分别检查行、列、3x3 宫格，时间 O(9*9) 空间 O(9)
 *
 * 【方法二：一次遍历 + 三个哈希表】O(1) 时间
 * 在遍历过程中同时检查行、列、宫格，更高效。
 *
 * @Author 郑晓胜
 */
public class P0036_ValidSudoku {

    /**
     * 方法一：三次遍历（行、列、宫格各检查一次）- 直观易懂
     * 时间：O(9*9) = O(1)，空间：O(9)
     */
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }

        // 检查每行
        for (int row = 0; row < 9; row++) {
            boolean[] seen = new boolean[9];
            for (int col = 0; col < 9; col++) {
                if (!checkAndMark(board[row][col], seen)) {
                    return false;
                }
            }
        }

        // 检查每列
        for (int col = 0; col < 9; col++) {
            boolean[] seen = new boolean[9];
            for (int row = 0; row < 9; row++) {
                if (!checkAndMark(board[row][col], seen)) {
                    return false;
                }
            }
        }

        // 检查每个 3x3 宫格
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                boolean[] seen = new boolean[9];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int row = blockRow * 3 + i;
                        int col = blockCol * 3 + j;
                        if (!checkAndMark(board[row][col], seen)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * 检查当前字符是否有效（未出现过且非 '.'）
     * @param ch     当前字符
     * @param seen   标记数组，记录 1-9 是否已出现
     * @return 是否有效
     */
    private boolean checkAndMark(char ch, boolean[] seen) {
        if (ch == '.') {
            return true; // 空位跳过
        }
        int digit = ch - '0'; // 转换为 0-8 的索引
        if (digit < 1 || digit > 9 || seen[digit - 1]) {
            // 数字重复或超出范围
            return false;
        }
        seen[digit - 1] = true;
        return true;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0036_ValidSudoku solution = new P0036_ValidSudoku();

        // 有效数独
        char[][] board1 = {
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
        System.out.println("测试1 (有效数独): " + solution.isValidSudoku(board1));

        // 无效数独（第三行重复 '8'）
        char[][] board2 = {
            {'8','3','.', '7','.','.', '.','.','.'},
            {'6','.','.', '1','9','5', '.','.','.'},
            {'.','9','8', '8','.','.', '.','6','.'},
            {'8','.','.', '.','6','.', '.','.','3'},
            {'4','.','.', '8','.','3', '.','.','1'},
            {'7','.','.', '.','2','.', '.','.','6'},
            {'.','6','.', '.','.','.', '2','8','.'},
            {'.','.','.', '4','1','9', '.','.','5'},
            {'.','.','.', '.','8','.', '.','7','9'}
        };
        System.out.println("测试2 (无效数独): " + solution.isValidSudoku(board2));
    }
}
