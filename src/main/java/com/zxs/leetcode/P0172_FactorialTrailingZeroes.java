package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 172. 阶乘后的零
 *
 * 给定一个整数 n，返回 n! 中尾随零的个数。
 *
 * 示例 1：
 * 输入: n = 3
 * 输出: 0
 * 解释: 3! = 6，没有尾随零
 *
 * 示例 2：
 * 输入: n = 5
 * 输出: 1
 * 解释: 5! = 120，有一个尾随零
 *
 * 示例 3：
 * 输入: n = 20
 * 输出: 4
 * 解释: 20! = 2432902008176640000，有4个尾随零
 *
 * 提示：
 * 0 <= n <= 10^4
 *
 * 解题思路：
 * 方法一：数学分析（推荐）
 * 尾随零由因子 2*5=10 产生
 * 统计 n! 中因子 5 的个数即可（因为因子 2 远比因子 5 多）
 * count = n/5 + n/25 + n/125 + ...
 * 时间复杂度 O(log_5 n)，空间复杂度 O(1)
 *
 * 方法二：模拟阶乘（不可行）
 * n! 会溢出，且效率极低
 */
public class P0172_FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            n /= 5;
            count += n;
        }
        return count;
    }

    public static void main(String[] args) {
        P0172_FactorialTrailingZeroes solution = new P0172_FactorialTrailingZeroes();
        System.out.println(solution.trailingZeroes(3));   // 0
        System.out.println(solution.trailingZeroes(5));   // 1
        System.out.println(solution.trailingZeroes(20));  // 4
        System.out.println(solution.trailingZeroes(100));  // 24
    }
}
