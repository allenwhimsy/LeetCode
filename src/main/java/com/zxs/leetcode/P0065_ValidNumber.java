package com.zxs.leetcode;

/**
 * 【P0065】有效数字
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0065_ValidNumber {

    /**
     * 题目描述：
     * 给定字符串 s，判断它是否代表一个有效的数字。
     *
     * 示例：
     * 输入：s = "0"
     * 输出：true
     * 输入：s = "e"
     * 输出：false
     *
     * 约束：
     * s 由 ASCII 字符集中的可见字符组成
     *
     * 解题思路：
     * 方法1：确定有限状态自动机（DFA）—— 【推荐】状态转移清晰，时间 O(n)，空间 O(1)
     * 方法2：正则表达式 —— 简洁但不易理解
     * 方法3：逐字符模拟 —— 繁琐，容易遗漏边界
     *
     * 本实现使用 DFA，共 9 种状态，3 种字符类型：空格、数字、其他字符（含+/-、e/E、.）。
     */
    public boolean isNumber(String s) {
        if (s == null) return false;
        s = s.trim(); // 去除首尾空格（内部空格已由题约束排除）
        if (s.isEmpty()) return false;

        // 状态转移表：states[state][charType] = nextState，-1 表示非法
        // charType: 0=空格, 1=数字, 2=符号, 3=点号, 4=e/E, 5=其他
        int[][] table = {
            // 0空格 1数字 2符号 3点号 4e/E 5其他
            {-1,  1,   3,   2,  -1,  -1},  // 0: 开始（预期空格）
            {-1,  1,  -1,   2,   4,  -1},  // 1: 整数部分
            {-1,  7,  -1,  -1,   4,  -1},  // 2: 刚看到点号（.123）
            {-1,  7,  -1,  -1,   4,  -1},  // 3: 刚看到符号（+1/-1）
            {-1,  6,   5,  -1,  -1,  -1},  // 4: 刚看到 e/E
            {-1,  6,  -1,  -1,  -1,  -1},  // 5: 刚看到 e 后符号
            {-1,  6,  -1,  -1,  -1,  -1},  // 6: e 后整数部分
            {-1,  7,  -1,  -1,   4,  -1},  // 7: 小数部分（123.）
            {-1,  7,  -1,  -1,   4,  -1},  // 8: 小数点后数字（1.23）
        };
        int state = 0;

        for (char ch : s.toCharArray()) {
            int type;
            if (ch == ' ') type = 0;
            else if (ch >= '0' && ch <= '9') type = 1;
            else if (ch == '+' || ch == '-') type = 2;
            else if (ch == '.') type = 3;
            else if (ch == 'e' || ch == 'E') type = 4;
            else type = 5;

            state = table[state][type];
            if (state == -1) return false;
        }

        // 合法的终态：1（纯整数）、2（.数字）、6（e后整数）、7（1.）、8（1.x）
        return state == 1 || state == 7 || state == 6 || state == 8;
    }

    public static void main(String[] args) {
        P0065_ValidNumber solution = new P0065_ValidNumber();
        System.out.println(solution.isNumber("0"));        // true
        System.out.println(solution.isNumber("e"));        // false
        System.out.println(solution.isNumber("."));        // false
        System.out.println(solution.isNumber(".1"));       // true
        System.out.println(solution.isNumber("2e10"));     // true
        System.out.println(solution.isNumber(" -1e-3 "));  // true
    }
}
