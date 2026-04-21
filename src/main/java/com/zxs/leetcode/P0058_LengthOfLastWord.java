package com.zxs.leetcode;

/**
 * 【P0058】最后一个单词的长度
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0058_LengthOfLastWord {

    /**
     * 题目描述：
     * 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。
     * 单词是指仅由字母组成、不含任何空格的字符序列。
     *
     * 示例：
     * 输入：s = "Hello World"
     * 输出：5
     * 解释：最后一个单词是 "World"，长度为 5。
     *
     * 约束：
     * 1 <= s.length <= 10^4
     * s 由英文字母和空格组成
     * s 中至少有一个单词
     *
     * 解题思路：
     * 方法1：从末尾向前跳过空格，再逐个计数 —— 【推荐】O(n)时间，O(1)空间
     * 方法2：split 分割 —— 简单但效率稍低
     */
    public int lengthOfLastWord(String s) {
        int len = 0;
        int i = s.length() - 1;

        // 从末尾开始，跳过空格
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // 计数最后一个单词的字符
        while (i >= 0 && s.charAt(i) != ' ') {
            len++;
            i--;
        }
        return len;
    }

    public static void main(String[] args) {
        P0058_LengthOfLastWord solution = new P0058_LengthOfLastWord();
        System.out.println(solution.lengthOfLastWord("Hello World"));       // 5
        System.out.println(solution.lengthOfLastWord("   fly me   to   the moon  ")); // 4
    }
}
