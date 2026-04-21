package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 190. 颠倒二进制位
 *
 * 颠倒给定的 32 位无符号整数的二进制位。
 *
 * 示例 1：
 * 输入: n = 00000010100101000001111010011100
 * 输出: 964176192 (00111001011110000010100101000000)
 *
 * 示例 2：
 * 输入: n = 11111111111111111111111111111101
 * 输出: 3221225471 (10111111111111111111111111111111)
 *
 * 提示：
 * 在 Java 中，假设我们的环境只能存储 32 位有符号整数范围内的整数。
 * 具体来说，第一个二进制位表示符号，其他位表示数值。
 * 对于这个问题，我们需要将 32 位无符号整数视为二进制串。
 *
 * 解题思路：
 * 方法一：逐位翻转（推荐）
 * 从低位到高位，每次取出 n 的最低位，拼接到 result 的对应位置
 * result 左移一位，n 右移一位
 * 时间复杂度 O(1)（固定32次循环），空间复杂度 O(1)
 *
 * 方法二：位运算分治
 * 使用掩码交换相邻位 -> 交换相邻对 -> ...
 * 更高效但实现较复杂
 */
public class P0190_ReverseBits {

    // 方法一：逐位翻转
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;        // result 左移，为新位腾出位置
            result |= (n & 1);  // 取出 n 的最低位，拼接到 result
            n >>= 1;             // n 右移
        }
        return result;
    }

    // 方法二：位运算分治
    public int reverseBitsDivide(int n) {
        n = (n >>> 16) | (n << 16);                    // 交换高低16位
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);  // 相邻字节交换
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);  // 相邻半字节交换
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);  // 相邻位对交换
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);  // 相邻单个位交换
        return n;
    }

    public static void main(String[] args) {
        P0190_ReverseBits solution = new P0190_ReverseBits();
        System.out.println(solution.reverseBits(43261596)); // 964176192
        System.out.println(solution.reverseBits(4294967293L >>> 0)); // 用 long 处理
    }
}
