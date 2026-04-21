package com.zxs.leetcode;

/**
 * 【P0067】二进制求和
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0067_AddBinary {

    /**
     * 题目描述：
     * 给你两个二进制字符串 a 和 b，以二进制字符串的形式返回它们的和。
     *
     * 示例：
     * 输入：a = "11", b = "1"
     * 输出："100"
     *
     * 约束：
     * 1 <= a.length, b.length <= 10^4
     * a 和 b 仅由字符 '0' 或 '1' 组成
     * 字符串如果不是 "0"，就不含前导零
     *
     * 解题思路：
     * 双指针从后往前逐位相加，记录进位 —— 【推荐】O(max(a,b))时间，O(1)额外空间（不含输出）
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0; // 进位

        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sb.append(sum % 2); // 当前位
            carry = sum / 2;    // 进位
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        P0067_AddBinary solution = new P0067_AddBinary();
        System.out.println(solution.addBinary("11", "1"));         // "100"
        System.out.println(solution.addBinary("1010", "1011"));   // "10101"
    }
}
