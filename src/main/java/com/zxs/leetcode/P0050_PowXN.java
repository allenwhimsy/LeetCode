package com.zxs.leetcode;

/**
 * P0050 Pow(x, n)
 *
 * 题目描述：
 * 实现 pow(x, n)，即计算 x 的整数 n 次幂函数。
 * 即计算 x 的 n 次方（n 为整数）。
 *
 * 示例 1：
 * 输入: x = 2.00000, n = 10
 * 输出: 1024.00000
 *
 * 示例 2：
 * 输入: x = 2.10000, n = 3
 * 输出: 9.26100
 *
 * 示例 3：
 * 输入: x = 2.00000, n = -2
 * 输出: 0.25000
 * 解释: 2^-2 = 1/2^2 = 1/4 = 0.25
 *
 * 约束条件：
 * - -100.0 < x < 100.0
 * - -2^31 <= n <= 2^31-1
 * - n 是一个 32 位有符号整数，其绝对值 <= 10^4
 *
 * 解题思路：
 * 【方法一：快速幂 + 递归（推荐）】O(log n) 时间
 * x^n = (x^(n/2))^2，当 n 为偶数
 * x^n = (x^(n/2))^2 * x，当 n 为奇数
 * 递归版本需要注意 n 可能为负数：x^(-n) = 1 / x^n
 *
 * 【方法二：快速幂 + 迭代】O(log n) 时间，O(1) 空间
 * 用二进制分解：n = b_k * 2^k + ... + b_1 * 2 + b_0
 * x^n = x^(b_0*2^0) * x^(b_1*2^1) * ...
 *
 * @Author 郑晓胜
 */
public class P0050_PowXN {

    /**
     * 方法一：快速幂 + 递归（推荐）
     */
    public double myPow(double x, long n) {
        if (n == 0) return 1.0;
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return fastPow(x, n);
    }

    private double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;

        // 二分递归
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    /**
     * 方法二：快速幂 + 迭代
     * 二进制视角：n 的每个 bit 位代表乘以 x^(2^i)
     */
    public double myPow_iterative(double x, long n) {
        if (n == 0) return 1.0;
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }

        double result = 1.0;
        double base = x;

        while (n > 0) {
            if (n % 2 == 1) {
                result *= base;
            }
            base *= base;    // base 翻倍，对应 x^(2^i)
            n /= 2;
        }
        return result;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0050_PowXN solution = new P0050_PowXN();

        double[][] tests = {{2.0, 10}, {2.1, 3}, {2.0, -2}, {1.0, Integer.MAX_VALUE}};
        for (double[] test : tests) {
            double x = test[0];
            long n = (long) test[1];
            double result = solution.myPow(x, n);
            System.out.printf("x=%.5f, n=%d -> %.5f%n", x, n, result);
        }
    }
}
