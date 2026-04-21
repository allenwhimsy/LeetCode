package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * P0046 全排列
 *
 * 题目描述：
 * 给定一个不含重复数字的整数数组 nums，返回其所有可能的全排列。
 * 顺序无关紧要，但你也可以选择任意顺序返回。
 *
 * 示例 1：
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [1,2,3], [1,3,2],
 *   [2,1,3], [2,3,1],
 *   [3,1,2], [3,2,1]
 * ]
 *
 * 约束条件：
 * - 1 <= nums.length <= 6
 * - -10 <= nums[i] <= 10
 * - nums 中的所有整数 互不相同
 *
 * 解题思路：
 * 【方法一：回溯 + used 数组（推荐）】O(n!*n) 时间
 * 用 used[] 标记已使用的元素，回溯构建排列。
 * 每次从 0 开始选未用过的数加入路径，到达叶子节点时记录解。
 *
 * 【方法二：交换法】O(n!*n) 时间
 * 固定第 i 位，递归交换后面的元素。
 *
 * @Author 郑晓胜
 */
public class P0046_Permutations {

    /**
     * 方法一：回溯 + used 数组（推荐）
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> path, boolean[] used,
                          List<List<Integer>> result) {
        // 找到完整排列
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // 已用过，跳过

            // 做选择
            used[i] = true;
            path.add(nums[i]);

            // 递归
            backtrack(nums, path, used, result);

            // 撤销选择（回溯）
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    /**
     * 方法二：交换法
     */
    public List<List<Integer>> permute_swap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSwap(nums, 0, result);
        return result;
    }

    private void backtrackSwap(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length) {
            // 转为 ArrayList 添加
            List<Integer> list = new ArrayList<>();
            for (int num : nums) list.add(num);
            result.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            backtrackSwap(nums, start + 1, result);
            swap(nums, start, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0046_Permutations solution = new P0046_Permutations();

        int[][] numss = {{1, 2, 3}, {0, 1}, {1}};
        for (int[] nums : numss) {
            List<List<Integer>> result = solution.permute(nums);
            System.out.println("输入: " + java.util.Arrays.toString(nums) + " -> " + result.size() + " 个排列:");
            for (List<Integer> perm : result) {
                System.out.println("  " + perm);
            }
        }
    }
}
