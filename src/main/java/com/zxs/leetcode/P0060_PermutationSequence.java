package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【P0060】排列序列
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0060_PermutationSequence {

    /**
     * 题目描述：
     * 给出集合 [1, 2, 3, ..., n]，所有排列按字典序排列。
     * 给定 n 和 k，返回第 k 个排列。
     *
     * 示例：
     * 输入：n = 3, k = 3
     * 输出："213"
     *
     * 约束：
     * 1 <= n <= 9
     *
     * 解题思路：
     * 方法1：数学+阶乘确定每一位 —— 【推荐】O(n^2)时间（可用阶乘列表优化为O(n)）
     * 核心：第k个排列的第i位，由剩余数字中的第 k/(i-1)! 个决定。
     * 方法2：回溯生成到第k个 —— 不如方法1直接
     */
    public String getPermutation(int n, int k) {
        // 将 k 从 1-indexed 转为 0-indexed，方便计算
        k--;

        List<Integer> nums = new ArrayList<>();
        // 计算阶乘表 factorial[i] = i!
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            nums.add(i); // 初始数字列表 [1..n]
        }

        StringBuilder sb = new StringBuilder();
        // 依次确定每一位
        for (int i = n; i >= 1; i--) {
            // 每位有多少个选项（每组大小）
            int groupSize = factorial[i - 1];
            // 确定当前位是第几组
            int index = k / groupSize;
            sb.append(nums.get(index));
            nums.remove(index); // 移除已选数字
            k %= groupSize;      // 更新剩余的偏移
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        P0060_PermutationSequence solution = new P0060_PermutationSequence();
        System.out.println(solution.getPermutation(3, 3));  // "213"
        System.out.println(solution.getPermutation(4, 9));  // "2314"
    }
}
