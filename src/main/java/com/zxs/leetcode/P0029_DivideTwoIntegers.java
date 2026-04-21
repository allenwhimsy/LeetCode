package com.zxs.leetcode;

/**
 * LeetCode 0029 - 两数相除 (Divide Two Integers)
 *
 * 【题目描述】
 * 给你两个整数 dividend 和 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回商的整数部分。结果截断为整数部分（向零截断）。
 * 假设环境不支持存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：输入 dividend = 10, divisor = 3 → 输出 3
 * 示例 2：输入 dividend = 7, divisor = -3 → 输出 -2
 *
 * 约束：-2^31 <= dividend, divisor <= 2^31 - 1, divisor != 0
 *
 * 【解题思路】
 * 位运算/倍增减法（O(log² n) 时间，O(1) 空间）✅ 推荐
 *   将除法转换为减法，但通过倍增（左移）加速：每次尝试减去 divisor * 2^k。
 *   用负数运算避免 INT_MIN 取反溢出的问题。
 *
 * @Author 郑晓胜
 */
public class P0029_DivideTwoIntegers {

    /**
     * 倍增减法（推荐）
     * 时间复杂度：O(log² n)
     * 空间复杂度：O(1)
     */
    public int divide(int dividend, int divisor) {
        // 特殊情况：除数为 1 或 -1 时直接处理（避免溢出）
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 统一转为负数运算，避免正数转负数溢出
        boolean negative = (dividend < 0) ^ (divisor < 0);
        int dvd = dividend < 0 ? dividend : -dividend;
        int dvs = divisor < 0 ? divisor : -divisor;

        int result = 0;
        while (dvd <= dvs) {
            int temp = dvs;
            int count = 1;
            // 倍增：每次将 temp 翻倍，直到超过 dvd
            while (dvd - temp <= temp) {
                // 防止 temp * 2 溢出
                if (temp < -(Integer.MAX_VALUE / 2)) break;
                temp += temp; // temp *= 2
                count += count; // count *= 2
            }
            dvd -= temp;
            result += count;
        }

        return negative ? -result : result;
    }
}
