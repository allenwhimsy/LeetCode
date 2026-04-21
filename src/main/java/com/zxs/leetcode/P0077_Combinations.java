package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【P0077】组合
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0077_Combinations {

    /**
     * 题目描述：
     * 给定两个整数 n 和 k，返回 [1..n] 中所有可能的 k 个数的组合。
     *
     * 示例：
     * 输入：n = 4, k = 2
     * 输出：[
     *        [1,2],[1,3],[1,4],[2,3],[2,4],[3,4]
     *      ]
     *
     * 约束：
     * 1 <= n <= 20
     *
     * 解题思路：
     * 回溯法：从 1 到 n 中选 k 个数字，不允许重复，且结果按字典序排列 —— 【推荐】O(C(n,k)*k)时间
     * 剪枝：若剩余数字不足以填满 k 个，直接剪枝。
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(1, n, k, path, result);
        return result;
    }

    private void backtrack(int start, int n, int k,
                          List<Integer> path, List<List<Integer>> result) {
        // 路径长度达到 k，记录一个解
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 剪枝：剩余可选数字不够时停止
        // 最多能选到 n，还需 (k - path.size()) 个，所以 start 不能超过 n - (k - path.size()) + 1
        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);          // 选择 i
            backtrack(i + 1, n, k, path, result); // 从 i+1 开始选下一个
            path.remove(path.size() - 1); // 撤销选择
        }
    }

    public static void main(String[] args) {
        P0077_Combinations solution = new P0077_Combinations();
        System.out.println(solution.combine(4, 2));
        System.out.println(solution.combine(1, 1));
    }
}
