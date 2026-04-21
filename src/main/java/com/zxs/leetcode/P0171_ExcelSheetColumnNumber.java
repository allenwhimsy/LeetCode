package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 171. Excel 表列序号
 *
 * 给你一个字符串 columnTitle，表示 Excel 表格中的列名称，返回它对应的列序号。
 *
 * A -> 1
 * B -> 2
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 * 示例 1：
 * 输入: columnTitle = "A"
 * 输出: 1
 *
 * 示例 2：
 * 输入: columnTitle = "AB"
 * 输出: 28
 *
 * 示例 3：
 * 输入: columnTitle = "ZY"
 * 输出: 701
 *
 * 示例 4：
 * 输入: columnTitle = "FXSHRXW"
 * 输出: 2147483647
 *
 * 提示：
 * 1 <= columnTitle.length <= 1000
 * columnTitle 仅由大写英文字母组成
 * columnTitle 范围在 ["A", "FXSHRXW"] 之间
 *
 * 解题思路：
 * 方法一：26进制转换（推荐）
 * 与168题互为逆操作
 * 从左到右遍历，每个字符对应 A=1, B=2, ..., Z=26
 * 结果 = 结果 * 26 + 当前字符值
 * 时间复杂度 O(n)，空间复杂度 O(1)
 */
public class P0171_ExcelSheetColumnNumber {

    public int titleToNumber(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            result = result * 26 + (c - 'A' + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        P0171_ExcelSheetColumnNumber solution = new P0171_ExcelSheetColumnNumber();
        System.out.println(solution.titleToNumber("A"));       // 1
        System.out.println(solution.titleToNumber("AB"));      // 28
        System.out.println(solution.titleToNumber("ZY"));     // 701
        System.out.println(solution.titleToNumber("FXSHRXW")); // 2147483647
    }
}
