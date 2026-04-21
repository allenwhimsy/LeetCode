package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * P0040 组合总和II
 *
 * 题目描述：
 * 给定一个候选人编号的数组 candidates 和一个目标编号 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 *
 * 示例 1：
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8
 * 输出: [[1,1,6],[1,2,5],[7,1,1,? No], [1,7]? ...]
 * 正确结果: [[1,1,6],[1,2,5],[7]]
 * 解释: [1,7] 不对因为有重复
 *
 * 实际正确输出：
 * [
 *   [1,1,6],
 *   [1,2,5],
 *   [7]
 * ]
 *
 * 示例 2：
 * 输入: candidates = [2,5,2,1,2], target = 5
 * 输出: [[1,2,2],[5]]
 *
 * 约束条件：
 * - 1 <= candidates.length <= 100
 * - 1 <= candidates[i] <= 50
 * - 1 <= target <= 30
 *
 * 解题思路：
 * 【方法一：回溯 + 去重（推荐）】O(N * 2^N)
 * 关键：candidates 有重复数字，需去除重复组合。
 * 1. 先排序：让相同数字相邻
 * 2. 回溯时，若 nums[i] == nums[i-1] 且 nums[i-1] 未被使用过，则跳过（避免重复）
 * 3. 或者用 used[] 数组标记本轮递归中已用过的元素
 *
 * @Author 郑晓胜
 */
public class P0040_CombinationSumII {

    /**
     * 方法一：回溯 + used 数组去重（推荐）
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序，使相同数字相邻
        backtrack(candidates, target, 0, new ArrayList<>(), result, new boolean[candidates.length]);
        return result;
    }

    private void backtrack(int[] candidates, int remaining, int start,
                          List<Integer> path, List<List<Integer>> result, boolean[] used) {
        if (remaining == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 剪枝
            if (candidates[i] > remaining) break;

            // 去重：相同元素若在前一轮未被使用，则跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }

            // 做选择
            used[i] = true;
            path.add(candidates[i]);

            // 递归：下一层从 i+1 开始（每个数字只能用一次）
            backtrack(candidates, remaining - candidates[i], i + 1, path, result, used);

            // 撤销选择
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    /**
     * 方法二：回溯 + 同层跳过去重（另一种去重写法）
     */
    public List<List<Integer>> combinationSum2_v2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack_v2(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack_v2(int[] candidates, int remaining, int start,
                             List<Integer> path, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) break;

            // 去重：同层如果之前用过相同元素，跳过
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            path.add(candidates[i]);
            backtrack_v2(candidates, remaining - candidates[i], i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0040_CombinationSumII solution = new P0040_CombinationSumII();

        int[][] candidates = {{10, 1, 2, 7, 6, 1, 5}, {2, 5, 2, 1, 2}};
        int[] targets = {8, 5};

        for (int i = 0; i < candidates.length; i++) {
            List<List<Integer>> result = solution.combinationSum2(candidates[i], targets[i]);
            System.out.println("target=" + targets[i] + " -> " + result);
        }
    }
}
