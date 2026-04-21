package com.zxs.leetcode;

/**
 * LeetCode 0006 - Z 字形变换 (Zigzag Conversion)
 *
 * 【题目描述】
 * 将一个给定字符串 s 根据给定的行数 numRows 以 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING"，行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 示例 1：
 *   输入：s = "PAYPALISHIRING", numRows = 3
 *   输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 *   输入：s = "PAYPALISHIRING", numRows = 4
 *   输出："PINALSIGYAHRPI"
 *
 * 约束：
 *   - 1 <= s.length <= 1000
 *   - s 由英文字母（小写和大写）、',' 和 '.' 组成
 *   - 1 <= numRows <= 1000
 *
 * 【解题思路】
 * 方法一：按行模拟（O(n) 时间，O(n) 空间）✅ 推荐
 *   创建 numRows 个 StringBuilder，模拟 Z 字形遍历：
 *   方向向下走到第 numRows-1 行后反转方向向上，到第 0 行后再反转方向向下。
 *   最后按行拼接所有 StringBuilder。
 *
 * @Author 郑晓胜
 */
public class P0006_ZigzagConversion {

    /**
     * 按行模拟法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param s       输入字符串
     * @param numRows 行数
     * @return Z字形变换后的字符串
     */
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currentRow].append(c);
            // 到达顶部或底部时反转方向
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            currentRow += goingDown ? 1 : -1;
        }

        // 按行拼接结果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }
}
