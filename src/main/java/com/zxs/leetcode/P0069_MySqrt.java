package com.zxs.leetcode;

/**
 * 【P0069】x 的平方根
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0069_MySqrt {

    /**
     * 题目描述：
     * 给你一个非负整数 x，计算并返回 x 的算术平方根。
     * 由于返回类型是整数，结果只保留整数部分，小数部分将被舍去。
     *
     * 示例：
     * 输入：x = 4
     * 输出：2
     * 输入：x = 8
     * 输出：2（因为 2^2=4, 3^2=9>8，故取2）
     *
     * 约束：
     * 0 <= x <= 2^31 - 1
     *
     * 解题思路：
     * 方法1：二分查找 —— 在 [0, x] 范围内找最大的 mid，使 mid^2 <= x —— 【推荐】O(log x)时间
     * 方法2：牛顿迭代法 —— 收敛更快，O(log x)时间但常数更小
     * 方法3：袖珍计算器公式（不适用于本题要求）
     *
     * 本实现使用方法1（二分），边界清晰，适合面试。
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        long left = 1, right = x;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long sq = mid * mid;
            if (sq == x) {
                return (int) mid;
            } else if (sq < x) {
                left = mid + 1; // mid 太小，收缩右边界
            } else {
                right = mid - 1; // mid 太大，收缩左边界
            }
        }
        // right 为小于等于 sqrt(x) 的最大整数
        return (int) right;
    }

    // 牛顿迭代法（更高效）
    public int mySqrtNewton(int x) {
        if (x == 0) return 0;
        long r = x;
        while (r > x / r) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    public static void main(String[] args) {
        P0069_MySqrt solution = new P0069_MySqrt();
        System.out.println(solution.mySqrt(4));   // 2
        System.out.println(solution.mySqrt(8));   // 2
        System.out.println(solution.mySqrt(2147395599)); // 46339
    }
}
