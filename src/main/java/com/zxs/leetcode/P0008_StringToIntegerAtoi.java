package com.zxs.leetcode;

/**
 * 【LeetCode 第8题】字符串转换整数(atoi)
 *
 * <p>题目描述：
 * 请你实现一个 myAtoi(string s) 函数，将字符串转换为 32 位有符号整数。
 * 其算法与 C/C++ 的 atoi 函数类似。
 *
 * <p>示例：
 * 输入: s = "42"
 * 输出: 42
 *
 * 输入: s = "   -42"
 * 输出: -42
 *
 * 输入: s = "4193 with words"
 * 输出: 4193
 *
 * 输入: s = "words and 987"
 * 输出: 0
 *
 * <p>约束条件：
 * - 0 <= s.length <= 200
 * - s 由英文字母、数字、空格、'+'、'-' 组成
 *
 * <p>解题思路：
 *
 * 【方法一】确定有限状态机（DFA）（推荐）⭐
 * - 用状态机严格定义所有可能的字符处理路径
 * - 消除 if-else 的大量分支判断，代码优雅
 * - 时间复杂度: O(n)，空间复杂度: O(1)
 *
 * 【方法二】自动机模拟（直观写法）
 * - 手动维护状态，逐字符处理
 * - 跳过空格 → 处理符号 → 读取数字
 * - 同时进行溢出检测
 * - 时间复杂度: O(n)，空间复杂度: O(1)
 *
 * @Author 郑晓胜
 */
public class P0008_StringToIntegerAtoi {

    // ========== 方法一：确定有限状态机（DFA） ==========

    /**
     * 【方法一】DFA 状态机解法（推荐）⭐
     *
     * 状态定义：
     * - START:    初始状态
     * - SIGNED:   已读到符号（+或-）
     * - IN_NUM:   正在读取数字
     * - END:      已结束（遇到非数字字符）
     *
     * 状态转换规则由 transitions 表定义
     */
    public static int myAtoiDFA(String s) {
        if (s == null || s.isEmpty()) return 0;

        // 状态枚举
        enum State { START, SIGNED, IN_NUM, END }
        // 字符类型
        enum CharType { SPACE, SIGN, NUM, OTHER }

        // 状态转换表: transitions[state][charType] = newState
        State[][] transitions = {
            // SPACE  SIGN   NUM    OTHER
            {State.START,  State.SIGNED,  State.IN_NUM,  State.END},  // START
            {State.END,    State.END,     State.IN_NUM,  State.END},  // SIGNED
            {State.END,    State.END,     State.IN_NUM,  State.END},  // IN_NUM
            {State.END,    State.END,     State.END,     State.END},  // END
        };

        State state = State.START;
        int sign = 1;     // 符号：1为正，-1为负
        long result = 0;  // 用 long 检测溢出

        for (char ch : s.toCharArray()) {
            CharType type = getCharType(ch);
            state = transitions[state.ordinal()][type.ordinal()];

            if (state == State.END) break;

            if (state == State.SIGNED) {
                sign = (ch == '-') ? -1 : 1;
            } else if (state == State.IN_NUM) {
                result = result * 10 + (ch - '0');
                // 溢出检测
                if (sign == 1 && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (sign == -1 && -result < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
        }

        return (int) (sign * result);
    }

    private static CharType getCharType(char ch) {
        if (ch == ' ') return CharType.SPACE;
        if (ch == '+' || ch == '-') return CharType.SIGN;
        if (ch >= '0' && ch <= '9') return CharType.NUM;
        return CharType.OTHER;
    }

    // ========== 方法二：自动机模拟 ==========

    /**
     * 【方法二】自动机模拟（直观写法）
     *
     * @param s 输入字符串
     * @return 转换后的整数
     */
    public static int myAtoi(String s) {
        if (s == null || s.isEmpty()) return 0;

        int i = 0;           // 遍历指针
        int n = s.length();
        long result = 0;
        int sign = 1;

        // 1. 跳过前导空格
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. 处理符号
        if (i < n) {
            if (s.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (s.charAt(i) == '+') {
                i++;
            }
        }

        // 3. 读取数字
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            result = result * 10 + (s.charAt(i) - '0');
            i++;

            // 提前溢出检测（避免 long 也溢出）
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return (int) (sign * result);
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        test("42", 42);
        test("   -42", -42);
        test("4193 with words", 4193);
        test("words and 987", 0);
        test("+-12", 0);
        test("00000-42a123", 0);
        test("2147483648", 2147483647);
        test("-2147483649", -2147483648);
        test("   +0 123", 0);
        test("123-456", 123);
    }

    private static void test(String s, int expected) {
        int result = myAtoi(s);
        int resultDFA = myAtoiDFA(s);
        System.out.printf("输入: \"%s\"%n", s);
        System.out.printf("  自动机模拟: %d (期望: %d) %s%n",
                result, expected, result == expected ? "✓" : "✗");
        System.out.printf("  DFA状态机法: %d %s%n",
                resultDFA, resultDFA == expected ? "✓" : "✗");
    }
}
