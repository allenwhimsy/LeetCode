package com.zxs.leetcode;

/**
 * P0038 外观数列
 *
 * 题目描述：
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * 以下是前几项：
 * 1.     1
 * 2.     11   （1 个 1）
 * 3.     21   （2 个 1）
 * 4.     1211 （1 个 2，1 个 1）
 * 5.     111221 （1 个 1，1 个 2，2 个 1）
 * 求外观数列的第 n 项。
 *
 * 示例 1：
 * 输入: n = 1
 * 输出: "1"
 * 解释：这是基本用例
 *
 * 示例 2：
 * 输入: n = 4
 * 输出: "1211"
 * 解释：第4项是"1211"
 *
 * 约束条件：
 * - 1 <= n <= 30
 *
 * 解题思路：
 * 【方法一：迭代生成（推荐）】O(n * M) M为最终字符串长度
 * 从第 1 项开始，依次生成到第 n 项。
 * 每一项：遍历上一项的字符串，统计连续相同字符的数量，拼接 "count + digit"。
 *
 * 【方法二：递归】O(n * M)，空间多一层递归栈，不推荐
 *
 * @Author 郑晓胜
 */
public class P0038_CountAndSay {

    /**
     * 方法一：迭代生成（推荐）
     */
    public String countAndSay(int n) {
        if (n <= 0) return "";

        // 第1项
        String current = "1";

        // 从第2项迭代到第n项
        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();
            int count = 1;

            // 遍历当前项，统计连续相同字符
            for (int j = 0; j < current.length(); j++) {
                // 如果和下一个字符相同，计数+1
                if (j + 1 < current.length() && current.charAt(j) == current.charAt(j + 1)) {
                    count++;
                } else {
                    // 遇到不同的字符，追加 "count + digit"
                    next.append(count).append(current.charAt(j));
                    count = 1; // 重置计数
                }
            }
            current = next.toString();
        }
        return current;
    }

    /**
     * 方法二：递归版本
     */
    public String countAndSay_recursive(int n) {
        if (n == 1) return "1";

        String prev = countAndSay_recursive(n - 1);
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int j = 0; j < prev.length(); j++) {
            if (j + 1 < prev.length() && prev.charAt(j) == prev.charAt(j + 1)) {
                count++;
            } else {
                sb.append(count).append(prev.charAt(j));
                count = 1;
            }
        }
        return sb.toString();
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0038_CountAndSay solution = new P0038_CountAndSay();

        // 打印前10项
        for (int n = 1; n <= 10; n++) {
            String result = solution.countAndSay(n);
            System.out.println("n=" + n + " -> " + result);
        }
    }
}
