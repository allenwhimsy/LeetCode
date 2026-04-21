package com.zxs.leetcode;

/**
 * 【P0070】爬楼梯
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0070_ClimbingStairs {

    /**
     * 题目描述：
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。
     * 你有多少种不同的方法可以爬到楼顶？
     *
     * 示例：
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法爬到楼顶：1. 1阶 + 1阶  2. 2阶
     *
     * 约束：
     * 1 <= n <= 45
     *
     * 解题思路：
     * 方法1：动态规划 —— dp[i] = dp[i-1] + dp[i-2]，斐波那契数列 —— 【推荐】O(n)时间，O(1)空间
     * 方法2：矩阵快速幂 —— O(log n)时间，但实现较复杂，适用于超大 n
     * 方法3：通项公式（斐波那契通项）—— O(log n)
     *
     * 本实现使用方法1的滚动变量版本。
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2; // a=dp[i-2], b=dp[i-1]
        for (int i = 3; i <= n; i++) {
            int c = a + b; // dp[i] = dp[i-1] + dp[i-2]
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        P0070_ClimbingStairs solution = new P0070_ClimbingStairs();
        System.out.println(solution.climbStairs(2));  // 2
        System.out.println(solution.climbStairs(3));  // 3
        System.out.println(solution.climbStairs(5));  // 8
    }
}
