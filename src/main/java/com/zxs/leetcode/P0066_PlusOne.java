package com.zxs.leetcode;

/**
 * 【P0066】加一
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0066_PlusOne {

    /**
     * 题目描述：
     * 给定一个非负整数数组表示的非负整数，在该数的基础上加一。
     * 最高位数字放在数组的首位，数组的每个元素存储数字的单个位。
     *
     * 示例：
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     *
     * 约束：
     * 1 <= digits.length <= 100
     * 0 <= digits[i] <= 9
     *
     * 解题思路：
     * 从后往前遍历，处理进位。若遍历完所有位仍有进位，则在数组开头插入1 —— 【推荐】O(n)时间，O(1)空间（除输出数组外）
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // 从最低位开始处理
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++; // 无需进位，直接+1返回
                return digits;
            }
            digits[i] = 0; // 当前位=9，进位置0，继续处理高位
        }
        // 所有位都是9，需要扩展数组（最高位进位）
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        P0066_PlusOne solution = new P0066_PlusOne();
        int[] r1 = solution.plusOne(new int[]{1, 2, 3});
        System.out.println(java.util.Arrays.toString(r1)); // [1, 2, 4]
        int[] r2 = solution.plusOne(new int[]{9, 9, 9});
        System.out.println(java.util.Arrays.toString(r2)); // [1, 0, 0, 0]
    }
}
