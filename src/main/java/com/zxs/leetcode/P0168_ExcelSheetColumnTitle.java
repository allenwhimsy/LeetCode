package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 168. Excel 表列名称
 *
 * 给你一个整数 columnNumber，返回它对应的 Excel 表列名称。
 *
 * Excel 表列规则：
 * A -> 1
 * B -> 2
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 * 示例 1：
 * 输入: columnNumber = 1
 * 输出: "A"
 *
 * 示例 2：
 * 输入: columnNumber = 28
 * 输出: "AB"
 *
 * 示例 3：
 * 输入: columnNumber = 701
 * 输出: "ZY"
 *
 * 示例 4：
 * 输入: columnNumber = 2147483647
 * 输出: "FXSHRXW"
 *
 * 提示：
 * 1 <= columnNumber <= 2^31 - 1
 *
 * 解题思路：
 * 方法一：进制转换（推荐）
 * 这是 26 进制，但注意没有 0，对应关系为：0->Z, 1->A, ..., 25->Y, 26->AA
 * 因此不能直接使用 n % 26
 * 正确做法：n-- 后再取模，这样 26 -> 0(Z)，27 -> 1(A)...26(AZ)
 * 时间复杂度 O(log_26 n)，空间复杂度 O(log_26 n)
 *
 * 方法二：循环除法
 * 与方法一同理，每次将 columnNumber 减1，再除以26
 */
public class P0168_ExcelSheetColumnTitle {

    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;  // 关键：先减1，26->0, 1->0
            sb.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        P0168_ExcelSheetColumnTitle solution = new P0168_ExcelSheetColumnTitle();
        System.out.println(solution.convertToTitle(1));        // A
        System.out.println(solution.convertToTitle(28));       // AB
        System.out.println(solution.convertToTitle(701));     // ZY
        System.out.println(solution.convertToTitle(2147483647)); // FXSHRXW
    }
}
