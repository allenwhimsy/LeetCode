package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 191. 位1的个数
 *
 * 编写一个函数，输入是一个无符号整数（以二进制字符串形式），
 * 返回其二进制表达式中 '1' 的个数（也称为汉明权重）。
 *
 * 示例 1：
 * 输入: n = 00000000000000000000000000001011
 * 输出: 3
 * 解释: 二进制 00000000000000000000000000001011 中有 3 个 '1'
 *
 * 示例 2：
 * 输入: n = 00000000000000000000000010000000
 * 输出: 1
 * 解释: 二进制 00000000000000000000000010000000 中有 1 个 '1'
 *
 * 示例 3：
 * 输入: n = 11111111111111111111111111111101
 * 输出: 31
 *
 * 提示：
 * 注意，在某些语言中（如 JavaScript），n 是有符号整数。
 * 但本题的测试用例中，n 的范围是 [0, 2^32-1]。
 *
 * 解题思路：
 * 方法一：位运算 n & (n-1)（推荐）
 * n & (n-1) 会消除 n 最右边的 1
 * 统计消除次数即得到 1 的个数
 * 时间复杂度 O(k)，k 为 1 的个数，最多32次
 * 空间复杂度 O(1)
 *
 * 方法二：逐位检查
 * 遍历每一位，检查是否为1并累加
 * 时间复杂度 O(32)，空间复杂度 O(1)
 *
 * 方法三：Java 内置 API
 * Integer.bitCount(n)
 * 时间复杂度 O(1)
 */
public class P0191_NumberOf1Bits {

    // 方法一：n & (n-1) 消除最低位的1
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);  // 消除最右边的1
            count++;
        }
        return count;
    }

    // 方法二：逐位检查
    public int hammingWeightByShift(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);  // 检查最低位是否为1
            n >>>= 1;           // 无符号右移
        }
        return count;
    }

    // 方法三：Java 内置
    public int hammingWeightBuiltin(int n) {
        return Integer.bitCount(n);
    }

    public static void main(String[] args) {
        P0191_NumberOf1Bits solution = new P0191_NumberOf1Bits();
        System.out.println(solution.hammingWeight(11));   // 3
        System.out.println(solution.hammingWeight(128));   // 1
        System.out.println(solution.hammingWeight(-3));    // 31 (视为无符号)
    }
}
