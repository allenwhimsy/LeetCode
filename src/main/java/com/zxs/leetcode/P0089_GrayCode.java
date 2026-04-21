package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【P0089】格雷编码
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0089_GrayCode {

    /**
     * 题目描述：
     * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数仅相差一位。
     * 给定整数 n，返回从 0 到 2^n - 1 的格雷码序列。
     *
     * 示例：
     * 输入：n = 2
     * 输出：[0,1,3,2]
     * 解释：00,01,11,10 每相邻两个数仅差一位
     *
     * 约束：
     * 1 <= n <= 16
     *
     * 解题思路：
     * 方法1：镜射（对称）构造 —— 第 i 个格雷码 = (i >> 1) ^ i，时间 O(2^n) —— 【推荐】最简洁
     * 方法2：递归构造：n-1 的格雷码镜像，前半部分不变，后半部分加前缀 1
     * 方法3：二进制模拟进位（较复杂）
     *
     * 本实现使用方法1，公式法最直接。
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int total = 1 << n; // 2^n
        for (int i = 0; i < total; i++) {
            // 第 i 个格雷码 = i ^ (i >> 1)
            result.add(i ^ (i >> 1));
        }
        return result;
    }

    public static void main(String[] args) {
        P0089_GrayCode solution = new P0089_GrayCode();
        System.out.println(solution.grayCode(2)); // [0,1,3,2]
        System.out.println(solution.grayCode(3)); // [0,1,3,2,6,7,5,4]
    }
}
