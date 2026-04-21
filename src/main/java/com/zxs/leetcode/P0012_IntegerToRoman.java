package com.zxs.leetcode;

import java.util.LinkedHashMap;

/**
 * 【LeetCode 第12题】整数转罗马数字
 *
 * <p>题目描述：
 * 将整数转换为罗马数字。
 * 罗马数字包含以下七种字符：I, V, X, L, C, D, M
 *
 * <p>示例：
 * 输入: num = 3
 * 输出: "III"
 *
 * 输入: num = 4
 * 输出: "IV"
 *
 * 输入: num = 9
 * 输出: "IX"
 *
 * 输入: num = 58
 * 输出: "LVIII"
 * 解释: L=50, V=5, III=3
 *
 * 输入: num = 1994
 * 输出: "MCMXCIV"
 * 解释: M=1000, CM=900, XC=90, IV=4
 *
 * <p>约束条件：
 * - 1 <= num <= 3999
 *
 * <p>解题思路：
 *
 * 【方法一】贪心模拟（推荐）⭐
 * - 预先定义所有可能出现的罗马数字组合（从大到小排序）
 * - 从大到小依次尝试减去，每次选取能减去的最大罗马表示
 * - 时间复杂度: O(1)（因为 num <= 3999，循环次数有限），空间复杂度: O(1)
 *
 * 【方法二】按位处理
 * - 分别处理千位、百位、十位、个位
 * - 每个位单独转换后拼接
 * - 代码较长但逻辑清晰
 *
 * @Author 郑晓胜
 */
public class P0012_IntegerToRoman {

    /**
     * 【方法一】贪心模拟（推荐）⭐
     *
     * 核心思想：
     * 罗马数字的特殊组合（如 IV=4, IX=9, XL=40, XC=90, CD=400, CM=900）
     * 都应作为独立单元参与贪心选择，而不是拆分成单独字符。
     *
     * 定义罗马数字的值→字符映射（从大到小排列）：
     * 1000->M, 900->CM, 500->D, 400->CD, 100->C, 90->XC,
     * 50->L, 40->XL, 10->X, 9->IX, 5->V, 4->IV, 1->I
     *
     * @param num 整数（1-3999）
     * @return 罗马数字字符串
     */
    public static String intToRoman(int num) {
        // 罗马数字映射（从大到小，包含所有特殊组合）
        int[] values = {
            1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
        };
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };

        StringBuilder sb = new StringBuilder();

        // 从大到小贪心选择
        for (int i = 0; i < values.length; i++) {
            // 反复使用当前最大的罗马数字
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }

        return sb.toString();
    }

    // ========== 方法二：按位处理 ==========

    private static final String[] THOUSANDS = {"", "M", "MM", "MMM"};
    private static final String[] HUNDREDS = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] TENS = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] ONES = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    /**
     * 【方法二】按位处理（直观看懂版）
     *
     * @param num 整数（1-3999）
     * @return 罗马数字字符串
     */
    public static String intToRomanByDigit(int num) {
        return THOUSANDS[num / 1000] +
               HUNDREDS[(num % 1000) / 100] +
               TENS[(num % 100) / 10] +
               ONES[num % 10];
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        test(3, "III");
        test(4, "IV");
        test(9, "IX");
        test(58, "LVIII");
        test(1994, "MCMXCIV");
        test(1, "I");
        test(3999, "MMMCMXCIX");
        test(3888, "MMMDCCCLXXXVIII");
        test(144, "CXLIV");
    }

    private static void test(int num, String expected) {
        String r1 = intToRoman(num);
        String r2 = intToRomanByDigit(num);
        System.out.printf("输入: %d%n", num);
        System.out.printf("  贪心模拟: %s (期望: %s) %s%n",
                r1, expected, r1.equals(expected) ? "✓" : "✗");
        System.out.printf("  按位处理: %s %s%n",
                r2, r2.equals(expected) ? "✓" : "✗");
    }
}
