package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 【P0090】子集 II
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0090_SubsetsII {

    /**
     * 题目描述：
     * 给你一个整数数组 nums，数组中可能包含重复元素，返回该数组所有可能的子集。
     * 子集按非递减顺序排列，去重。
     *
     * 示例：
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[2],[1,2],[2,2],[1,2,2]]
     *
     * 约束：
     * 1 <= nums.length <= 10
     *
     * 解题思路：
     * 回溯 + 剪枝：在 P0078 基础上，对相同元素进行剪枝（跳过本轮与上一个相同的候选）—— 【推荐】O(n*2^n)时间
     * 关键：先对 nums 排序，遍历时跳过与前一个相同的候选，避免重复。
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序，便于去重
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums,
                          List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path)); // 每个节点都是子集

        for (int i = start; i < nums.length; i++) {
            // 剪枝：跳过与上一个相同的候选，避免重复子集
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtrack(i + 1, nums, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        P0090_SubsetsII solution = new P0090_SubsetsII();
        System.out.println(solution.subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(solution.subsetsWithDup(new int[]{0}));
    }
}
