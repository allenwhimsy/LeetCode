package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * P0047 全排列II
 *
 * 题目描述：
 * 给定一个可包含重复数字的整数数组 nums，
 * 按任意顺序返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入: nums = [1,1,2]
 * 输出:
 * [[1,1,2], [1,2,1], [2,1,1]]
 *
 * 示例 2：
 * 输入: nums = [1,2,2]
 * 输出:
 * [[1,2,2], [2,1,2], [2,2,1]]
 *
 * 约束条件：
 * - 1 <= nums.length <= 8
 * - -10 <= nums[i] <= 10
 *
 * 解题思路：
 * 【方法一：回溯 + used 数组 + 同层去重（推荐）】O(n!*n)
 * 1. 先排序，让相同数字相邻
 * 2. 回溯时，若 nums[i] == nums[i-1] 且 nums[i-1] 尚未被使用过，则跳过
 *    原因：同层递归中，第一个相同数已经穷尽了所有排列，第二个相同数无需再尝试
 *
 * @Author 郑晓胜
 */
public class P0047_PermutationsII {

    /**
     * 方法一：回溯 + 去重（推荐）
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        Arrays.sort(nums); // 排序，使相同数字相邻
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> path, boolean[] used,
                          List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // 已用过的，跳过

            // 去重：若当前数与前一个相同，且前一个在同层未被使用，跳过
            // 解释：同层中 nums[i-1] 和 nums[i] 是相同的选择分支
            // 如果 nums[i-1] 还没被用过，说明 nums[i] 属于同一层递归，应跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // 做选择
            used[i] = true;
            path.add(nums[i]);

            // 递归
            backtrack(nums, path, used, result);

            // 回溯
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0047_PermutationsII solution = new P0047_PermutationsII();

        int[][] numss = {{1, 1, 2}, {1, 2, 2}, {3, 3, 0, 0, 1, 1}};
        for (int[] nums : numss) {
            List<List<Integer>> result = solution.permuteUnique(nums);
            System.out.println("输入: " + Arrays.toString(nums) + " -> " + result.size() + " 个不同排列:");
            for (List<Integer> perm : result) {
                System.out.println("  " + perm);
            }
        }
    }
}
