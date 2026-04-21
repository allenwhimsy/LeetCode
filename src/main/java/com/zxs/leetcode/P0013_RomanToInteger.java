package com.zxs.leetcode;

/**
 * 【LeetCode 第13题】罗马数字转整数
 *
 * <p>题目描述：
 * 给定一个罗马数字，将其转换为整数。
 *
 * <p>罗马数字规则：
 * - I (1), V (5), X (10), L (50), C (100), D (500), M (1000)
 * - 特殊规则：小的数字在大的数字前面表示减（如 IV=4, IX=9）
 * - 罗马数字中同一字符最多连续出现3次
 *
 * <p>示例：
 * 输入: s = "III"
 * 输出: 3
 *
 * 输入: s = "LVIII"
 * 输出: 58
 * 解释: L=50, V=5, III=3
 *
 * 输入: s = "MCMXCIV"
 * 输出: 1994
 * 解释: M=1000, CM=900, XC=90, IV=4
 *
 * <p>约束条件：
 * - 1 <= s.length <= 15
 * - s 仅包含字符 ('I','V','X','L','C','D','M')
 * - 保证 s 是有效的罗马数字
 *
 * <p>解题思路：
 *
 * 【方法一】模拟遍历（推荐）⭐
 * - 从左到右遍历罗马数字
 * - 规则：若当前字符值 < 下一个字符值，则减去当前值；否则加上当前值
 * - 例如 "IV"：I(1) < V(5)，所以减去 I；"VI"：V(5) >= I(1)，所以加上 V
 * - 时间复杂度: O(n)，空间复杂度: O(1)
 *
 * 【方法二】哈希表 + 特殊组合处理
 * - 用哈希表存储所有字符和特殊组合的值
 * - 遍历时优先匹配两字符组合，再匹配单字符
 * - 时间复杂度: O(n)，空间复杂度: O(1)
 *
 * @Author 郑晓胜
 */
public class P0013_RomanToInteger {

    /**
     * 【方法一】模拟遍历（推荐）⭐
     *
     * 核心思想：左到右扫描，O(1) 空间
     * - 如果当前字符值 < 下一个字符值 → 减（如 IV, IX, XL...）
     * - 否则 → 加
     *
     * 为什么这样正确？
     * 罗马数字的本质是：值 = Σ(每个字符基础值) - 2*(小的在大的前面的差的绝对值之和)
     * 简化为遍历规则就是：小的在大的前面则减，否则则加
     *
     * @param s 罗马数字字符串
     * @return 对应的整数
     */
    public static int romanToInt(String s) {
        // 罗马字符对应值
        int total = 0;

        for (int i = 0; i < s.length(); i++) {
            int current = romanCharValue(s.charAt(i));

            // 如果存在下一个字符，且当前值 < 下一个值，则减去当前值
            if (i + 1 < s.length() && current < romanCharValue(s.charAt(i + 1))) {
                total -= current;
            } else {
                total += current;
            }
        }

        return total;
    }

    /**
     * 获取单个罗马字符的数值
     */
    private static int romanCharValue(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    // ========== 方法二：哈希表 + 特殊组合 ==========

    /**
     * 【方法二】哈希表 + 特殊组合处理
     *
     * 预先定义所有可能的罗马数字表示（包含两字符特殊组合）
     * 从左到右优先匹配最长（两字符）组合
     *
     * @param s 罗马数字字符串
     * @return 对应的整数
     */
    public static int romanToIntHash(String s) {
        java.util.HashMap<String, Integer> map = new java.util.HashMap<>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);

        int result = 0;
        int i = 0;

        while (i < s.length()) {
            // 优先尝试两字符组合
            if (i + 1 < s.length()) {
                String twoChars = s.substring(i, i + 2);
                if (map.containsKey(twoChars)) {
                    result += map.get(twoChars);
                    i += 2;
                    continue;
                }
            }
            // 处理单字符
            result += map.get(s.substring(i, i + 1));
            i++;
        }

        return result;
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        test("III", 3);
        test("LVIII", 58);
        test("MCMXCIV", 1994);
        test("IV", 4);
        test("IX", 9);
        test("XL", 40);
        test("XC", 90);
        test("CD", 400);
        test("CM", 900);
        test("MMMCMXCIX", 3999);
        test("DCXXI", 621);
    }

    private static void test(String s, int expected) {
        int r1 = romanToInt(s);
        int r2 = romanToIntHash(s);
        System.out.printf("输入: \"%s\"%n", s);
        System.out.printf("  模拟遍历: %d (期望: %d) %s%n",
                r1, expected, r1 == expected ? "✓" : "✗");
        System.out.printf("  哈希表法: %d %s%n",
                r2, r2 == expected ? "✓" : "✗");
    }
}
