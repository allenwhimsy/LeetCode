package com.zxs.leetcode;

/**
 * LeetCode 0200 - 岛屿数量 (Number of Islands)
 *
 * 【题目描述】
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 *   输入：grid = [
 *     ["1","1","1","1","0"],
 *     ["1","1","0","1","0"],
 *     ["1","1","0","0","0"],
 *     ["0","0","0","0","0"]
 *   ]
 *   输出：1
 *
 * 示例 2：
 *   输入：grid = [
 *     ["1","1","0","0","0"],
 *     ["1","1","0","0","0"],
 *     ["0","0","1","0","0"],
 *     ["0","0","0","1","1"]
 *   ]
 *   输出：3
 *
 * 约束：
 *   - m == grid.length
 *   - n == grid[i].length
 *   - 1 <= m, n <= 300
 *   - grid[i][j] 的值为 '0' 或 '1'
 *
 * 【解题思路】
 * 方法一：深度优先搜索 DFS（O(mn) 时间，O(mn) 空间）✅ 推荐
 *   遍历网格，遇到 '1' 时岛屿数量 +1，然后用 DFS 将整片岛屿标记为已访问（改为 '0'）。
 *   这样每个格子最多被访问一次，时间复杂度 O(mn)。
 *
 * 方法二：广度优先搜索 BFS（O(mn) 时间，O(min(m,n)) 空间）
 *   同样的思路，用队列实现 BFS 代替递归 DFS，避免递归栈溢出。
 *
 * 方法三：并查集 Union-Find（O(mn·α(mn)) 时间）
 *   将所有 '1' 相邻格子合并为同一集合，最终集合数即为岛屿数。
 *
 * @Author 郑晓胜
 */
public class P0200_NumberOfIslands {

    /**
     * DFS 解法（推荐）
     * 时间复杂度：O(mn)，每个格子最多访问一次
     * 空间复杂度：O(mn)，最坏情况递归深度为 mn
     *
     * @param grid 二维字符网格
     * @return 岛屿数量
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;      // 行数
        int cols = grid[0].length;    // 列数
        int count = 0;               // 岛屿计数

        // 遍历每个格子
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;              // 发现新岛屿，计数 +1
                    dfs(grid, i, j);     // DFS 沉没整片岛屿
                }
            }
        }

        return count;
    }

    /**
     * DFS 深度优先搜索 - 将当前格子及所有相连陆地标记为已访问
     *
     * @param grid 网格
     * @param i    当前行号
     * @param j    当前列号
     */
    private void dfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        // 边界检查 + 是否为陆地
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0'; // 标记为已访问（沉没）

        // 向四个方向扩展
        dfs(grid, i - 1, j); // 上
        dfs(grid, i + 1, j); // 下
        dfs(grid, i, j - 1); // 左
        dfs(grid, i, j + 1); // 右
    }

    /**
     * BFS 解法（避免递归栈溢出）
     * 时间复杂度：O(mn)
     * 空间复杂度：O(min(m,n))，队列最大长度为 min(m,n)
     *
     * @param grid 二维字符网格
     * @return 岛屿数量
     */
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';
                    // 使用数组模拟队列（避免创建链表对象开销）
                    int[] queue = new int[rows * cols];
                    int head = 0, tail = 0;
                    queue[tail++] = i * cols + j;

                    while (head < tail) {
                        int pos = queue[head++];
                        int curI = pos / cols;
                        int curJ = pos % cols;

                        for (int[] dir : directions) {
                            int newI = curI + dir[0];
                            int newJ = curJ + dir[1];
                            if (newI >= 0 && newI < rows && newJ >= 0 && newJ < cols && grid[newI][newJ] == '1') {
                                grid[newI][newJ] = '0';
                                queue[tail++] = newI * cols + newJ;
                            }
                        }
                    }
                }
            }
        }

        return count;
    }
}
