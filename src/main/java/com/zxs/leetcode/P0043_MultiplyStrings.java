package com.zxs.leetcode;

/**
 * P0043 字符串相乘
 *
 * 题目描述：
 * 给定两个以字符串形式表示的非负整数 num1 和 num2 ，
 * 返回 num1 和 num2 的乘积，它们的乘积也表示为字符串。
 * 注意：不能使用任何内置的大整数库，也不能直接将输入转换为整数。
 *
 * 示例 1：
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2：
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 约束条件：
 * - 1 <= num1.length, num2.length <= 200
 * - num1 和 num2 只包含数字 0-9
 * - num1 和 num2 都不能以任何前导零开头，除了数字 "0" 本身
 *
 * 解题思路：
 * 【方法一：模拟竖式乘法（推荐）】O(m*n) 时间，O(m+n) 空间
 * 核心：num1[i] * num2[j] 的结果累加到 result[i+j] 和 result[i+j+1] 上。
 * num1 的第 i 位（从右往左）和 num2 的第 j 位（从右往左）相乘，结果加到结果数组的 i+j 位置。
 * 最后处理进位，转为字符串。
 *
 * 【方法二：Karatsuba 算法】O(n^1.585) - 大数乘法优化，但实现较复杂
 *
 * @Author 郑晓胜
 */
public class P0043_MultiplyStrings {

    /**
     * 方法一：模拟竖式乘法（推荐）
     * num1[i] * num2[j] -> result[i+j] 和 result[i+j+1]
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return "";
        if (num1.equals("0") || num2.equals("0")) return "0";

        int m = num1.length();
        int n = num2.length();

        // 结果最多 m+n 位
        int[] result = new int[m + n];

        // 从右向左遍历
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int n1 = num1.charAt(i) - '0';
                int n2 = num2.charAt(j) - '0';

                // 乘积累加到对应位置
                int p1 = i + j;     // 十位位置
                int p2 = i + j + 1; // 个位位置
                int sum = result[p2] + n1 * n2;

                result[p2] = sum % 10;         // 当前位（保留个位）
                result[p1] += sum / 10;        // 进位（累加到十位）
            }
        }

        // 转为字符串，跳过前导0
        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0043_MultiplyStrings solution = new P0043_MultiplyStrings();

        String[][] tests = {{"2", "3"}, {"123", "456"}, {"999", "99"}, {"12345", "0"}};
        for (String[] test : tests) {
            String result = solution.multiply(test[0], test[1]);
            System.out.println(test[0] + " * " + test[1] + " = " + result);
        }
    }
}
