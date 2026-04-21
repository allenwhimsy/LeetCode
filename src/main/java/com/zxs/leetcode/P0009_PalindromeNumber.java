package com.zxs.leetcode;

/**
 * 【LeetCode 第9题】回文数
 *
 * <p>题目描述：
 * 给你一个整数 x，如果 x 是一个回文整数，则返回 true。
 * 否则返回 false。
 * 回文数：正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * <p>示例：
 * 输入: x = 121
 * 输出: true
 * 解释: 121 读作 "121"，从左向右和从右向左读都是一样的。
 *
 * 输入: x = -121
 * 输出: false
 * 解释: 从左向右读为 -121。从右向左读为 121-。不相同。
 *
 * 输入: x = 10
 * 输出: false
 * 解释: 从右向左读为 01。仅整数部分可视，不含前导零。
 *
 * <p>约束条件：
 * - -2^31 <= x <= 2^31 - 1
 *
 * <p>解题思路：
 *
 * 【方法一】反转后半部分数字（推荐）⭐
 * - 不使用额外字符串，直接数学运算
 * - 将数字后半部分反转，与前半部分比较
 * - 优点：O(1) 空间，不溢出的数字直接比较
 * - 时间复杂度: O(log n)，空间复杂度: O(1)
 *
 * 【方法二】字符串比较
 * - 转成字符串，双指针从两端比较
 * - 简单直观，但需要 O(n) 额外空间
 *
 * 【方法三】全部数字反转
 * - 将原数完全反转，比较是否相等
 * - 需注意溢出问题
 *
 * @Author 郑晓胜
 */
public class P0009_PalindromeNumber {

    /**
     * 【方法一】反转后半部分数字（推荐）⭐
     *
     * 核心思想：
     * - 对于回文数，反转后半部分应该等于前半部分
     * - 对于奇数位数字，中间位无需比较（自动相等）
     * - 例如 121：reverted = 12 → 1，x = 1，比较 1==1
     * - 例如 12321：reverted = 123 → 12，x = 12，停止（x <= reverted）
     *
     * @param x 输入整数
     * @return 是否为回文数
     */
    public static boolean isPalindrome(int x) {
        // 负数一定不是回文数
        // 末尾为0的正数（除0外）一定不是回文数（如10, 20, 30...）
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reverted = 0;

        // 只反转数字的后半部分，直到 x <= reverted
        while (x > reverted) {
            reverted = reverted * 10 + x % 10;
            x = x / 10;
        }

        // 偶数位：x == reverted
        // 奇数位：x == reverted / 10（中间位在 reverted 的末尾）
        return x == reverted || x == reverted / 10;
    }

    /**
     * 【方法二】字符串比较（双指针）
     */
    public static boolean isPalindromeString(int x) {
        String s = Integer.toString(x);
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 【方法三】完全数字反转
     * 需注意溢出处理
     */
    public static boolean isPalindromeReverse(int x) {
        if (x < 0) return false;

        int original = x;
        int reversed = 0;

        while (x != 0) {
            int pop = x % 10;
            x = x / 10;

            // 溢出检测（Java中 int 超限会变成负数）
            if (reversed > Integer.MAX_VALUE / 10 ||
               (reversed == Integer.MAX_VALUE / 10 && pop > 7)) {
                return false;
            }

            reversed = reversed * 10 + pop;
        }

        return original == reversed;
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        test(121, true);
        test(-121, false);
        test(10, false);
        test(0, true);
        test(12321, true);
        test(12332, false);
        test(1000021, false);
        test(1001, true);
        test(Integer.MAX_VALUE, false);
        test(Integer.MIN_VALUE, false);
    }

    private static void test(int x, boolean expected) {
        boolean r1 = isPalindrome(x);
        boolean r2 = isPalindromeString(x);
        System.out.printf("输入: %d%n", x);
        System.out.printf("  反转后半部法: %s (期望: %s) %s%n",
                r1, expected, r1 == expected ? "✓" : "✗");
        System.out.printf("  字符串比较法: %s%n", r2);
        System.out.printf("  完全反转法: %s%n", isPalindromeReverse(x));
    }
}
