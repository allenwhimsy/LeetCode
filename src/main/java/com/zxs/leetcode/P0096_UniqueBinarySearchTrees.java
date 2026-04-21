package com.zxs.leetcode;

/**
 * 【P0096】不同的二叉搜索树
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0096_UniqueBinarySearchTrees {

    /**
     * 题目描述：
     * 给定一个整数 n，求 1 到 n 之间能形成多少个不同的二叉搜索树。
     *
     * 示例：
     * 输入：n = 3
     * 输出：5
     * 解释：给定 n=3，共有 5 种不同结构的 BST：
     *      1         3     3       2     1
     *       \       /     /       / \     \
     *        3     2     1       1   3     2
     *       /     /       \               \
     *      2     1         2               3
     *
     * 约束：
     * 1 <= n <= 19
     *
     * 解题思路：
     * 动态规划（卡特兰数）：dp[n] = Σ dp[i-1] * dp[n-i]，i=1..n —— 【推荐】O(n^2)时间，O(n)空间
     * dp[i] 表示 i 个节点能构成的 BST 数量。
     * 也可以用卡特兰数公式直接计算：C_n = C(2n,n)/(n+1)。
     */
    public int numTrees(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1; // 0个节点的空树有1种
        dp[1] = 1; // 1个节点有1种

        for (int i = 2; i <= n; i++) {
            dp[i] = 0;
            // 枚举根节点位置 i，左子树 i-1 个节点，右子树 n-i 个节点
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    // 卡特兰数公式版（数学解法）
    public int numTreesCatalan(int n) {
        long c = 1; // 用 long 防止溢出
        for (int i = 0; i < n; i++) {
            c = c * 2 * (2 * i + 1) / (i + 2); // 卡特兰数递推
        }
        return (int) c;
    }

    public static void main(String[] args) {
        P0096_UniqueBinarySearchTrees solution = new P0096_UniqueBinarySearchTrees();
        System.out.println(solution.numTrees(3));  // 5
        System.out.println(solution.numTrees(19)); // 1767263190
    }
}
