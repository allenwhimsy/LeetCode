package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 166. 分数到小数
 *
 * 给定两个整数，分别代表分子 numerator 和分母 denominator，返回其相除的商的小数形式。
 * 如果小数部分有循环节，则用括号括起来。
 * 如果小数部分为 0，则只需要返回整数部分。
 * 不需要考虑输出浮点数的精度问题，自行计算即可。
 *
 * 示例 1：
 * 输入: numerator = 1, denominator = 2
 * 输出: "0.5"
 *
 * 示例 2：
 * 输入: numerator = 2, denominator = 1
 * 输出: "2"
 *
 * 示例 3：
 * 输入: numerator = 2, denominator = 3
 * 输出: "0.(6)"
 *
 * 示例 4：
 * 输入: numerator = 4, denominator = 333
 * 输出: "0.(012)"
 *
 * 提示：
 * -2^31 <= numerator <= 2^31 - 1
 * -2^31 <= denominator <= 2^31 - 1
 * denominator != 0
 *
 * 解题思路：
 * 方法一：模拟长除法 + 哈希表（推荐）
 * 1. 处理符号和边界情况（MIN_VALUE/-1 溢出）
 * 2. 计算整数部分
 * 3. 用长除法模拟小数部分，用 HashMap 记录每个余数出现的位置
 * 4. 当余数为0时，无循环；余数重复时，其对应的小数位置到当前位置为循环节
 * 时间复杂度 O(n)，空间复杂度 O(n)
 */
import java.util.*;

public class P0166_FractionToRecurringDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) return "";
        if (numerator == 0) return "0";

        StringBuilder sb = new StringBuilder();

        // 处理符号
        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append('-');
        }

        // 使用 long 避免溢出
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // 整数部分
        sb.append(num / den);
        num %= den;
        if (num == 0) return sb.toString();

        sb.append('.');
        // 小数部分
        Map<Long, Integer> map = new HashMap<>();  // 余数 -> 小数位数位置
        map.put(num, sb.length());
        while (num != 0) {
            num *= 10;
            sb.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                // 找到循环节
                int idx = map.get(num);
                sb.insert(idx, '(');
                sb.append(')');
                return sb.toString();
            } else {
                map.put(num, sb.length());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        P0166_FractionToRecurringDecimal solution = new P0166_FractionToRecurringDecimal();
        System.out.println(solution.fractionToDecimal(1, 2));   // "0.5"
        System.out.println(solution.fractionToDecimal(2, 1));    // "2"
        System.out.println(solution.fractionToDecimal(2, 3));   // "0.(6)"
        System.out.println(solution.fractionToDecimal(4, 333)); // "0.(012)"
    }
}
