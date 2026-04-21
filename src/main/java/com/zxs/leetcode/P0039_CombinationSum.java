package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * P0039 组合总和
 *
 * 题目描述：
 * 给你一个无重复元素的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的所有不同组合。
 * candidates 中的数字可以无限制重复被选取。
 * 如果至少一个数字组合的和为 target，则返回这些组合。
 * 解集不能包含重复的组合。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：2 和 3 可以用来组成 7（2+2+3=7），7 本身也是候选
 *
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8
 * 输出：[[2,2,2,2],[2,3,3],[3,5]]
 *
 * 示例 3：
 * 输入：candidates = [2], target = 1
 * 输出：[]
 *
 * 约束条件：
 * - 1 <= candidates.length <= 30
 * - 2 <= candidates[i] <= 40
 * - candidates 中的每个元素都独一无二
 * - 1 <= target <= 40
 *
 * 解题思路：
 * 【方法一：回溯（推荐）】O(N^T) 其中N为candidates数，T=target/min(candidates)，实际剪枝后快很多
 * 关键点：
 * 1. candidates 已排序，递归时从当前索引开始（可重复取当前及之后元素）
 * 2. target < 0 时剪枝，target == 0 时记录解
 * 3. 若 candidates[i] > target，直接跳过
 *
 * 【方法二：动态规划】预处理所有可能的组合，但不如回溯直接
 *
 * @Author 郑晓胜
 */
public class P0039_CombinationSum {

    /**
     * 方法一：回溯（推荐）
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 排序便于剪枝
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * 回溯核心
     * @param candidates 候选数组（已排序）
     * @param remaining  剩余需要凑的目标
     * @param start      本轮搜索起始索引（可重复取当前及之后的数）
     * @param path       当前路径（已选中的数）
     * @param result     结果集
     */
    private void backtrack(int[] candidates, int remaining, int start,
                          List<Integer> path, List<List<Integer>> result) {
        if (remaining == 0) {
            // 找到解，添加副本
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝：如果当前数大于剩余目标，跳过
            if (candidates[i] > remaining) {
                break; // 因为数组已排序，后面的数更大，无需继续
            }

            // 做选择
            path.add(candidates[i]);

            // 递归：注意起始索引仍为 i（可重复取当前数）
            backtrack(candidates, remaining - candidates[i], i, path, result);

            // 撤销选择（回溯）
            path.remove(path.size() - 1);
        }
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0039_CombinationSum solution = new P0039_CombinationSum();

        int[][] candidates = {{2, 3, 6, 7}, {2, 3, 5}, {2}};
        int[] targets = {7, 8, 1};

        for (int i = 0; i < candidates.length; i++) {
            List<List<Integer>> result = solution.combinationSum(candidates[i], targets[i]);
            System.out.println("target=" + targets[i] + " -> " + result);
        }
    }
}
