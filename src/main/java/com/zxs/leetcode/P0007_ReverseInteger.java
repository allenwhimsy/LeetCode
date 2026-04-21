package com.zxs.leetcode;

/**
 * LeetCode 0007 - 整数反转 (Reverse Integer)
 *
 * 【题目描述】
 * 给你一个 32 位的有符号整数 x，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [-2^31, 2^31 - 1]，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 *   输入：x = 123
 *   输出：321
 *
 * 示例 2：
 *   输入：x = -123
 *   输出：-321
 *
 * 示例 3：
 *   输入：x = 120
 *   输出：21
 *
 * 约束：
 *   - -2^31 <= x <= 2^31 - 1
 *
 * 【解题思路】
 * 数学方法（O(log|x|) 时间，O(1) 空间）✅ 推荐
 *   依次取出 x 的末位数字，拼接到结果中。
 *   关键：每次拼接前检查是否会发生溢出。
 *   溢出判断：rev > Integer.MAX_VALUE/10 或 (rev == Integer.MAX_VALUE/10 且 pop > 7)
 *
 * @Author 郑晓胜
 */
public class P0007_ReverseInteger {

    /**
     * 数学反转法（推荐）
     * 时间复杂度：O(log|x|)
     * 空间复杂度：O(1)
     *
     * @param x 输入整数
     * @return 反转后的整数
     */
    public int reverse(int x) {
        int rev = 0;

        while (x != 0) {
            int pop = x % 10; // 取末位
            x /= 10;          // 去掉末位

            // 溢出检查：正数溢出
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            // 溢出检查：负数溢出
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop;
        }

        return rev;
    }
}
