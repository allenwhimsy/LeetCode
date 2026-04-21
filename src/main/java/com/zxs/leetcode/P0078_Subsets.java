package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【P0078】子集
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0078_Subsets {

    /**
     * 题目描述：
     * 给你一个整数数组 nums，数组中的元素互不相同，返回该数组所有可能的子集。
     * 子集可以按任意顺序排列。
     *
     * 示例：
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]
     *
     * 约束：
     * 1 <= nums.length <= 10
     *
     * 解题思路：
     * 方法1：回溯（选或不选）—— 逐个元素决策，最终 O(2^n) 个子集 —— 【推荐】标准回溯
     * 方法2：迭代（逐个加入）—— 对每个数，将其加入已有所有子集，时间同上
     * 核心：n 个元素的子集数量为 2^n，遍历每个元素时对已有子集进行扩展。
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // 空集是所有集合的子集

        for (int num : nums) {
            int curSize = result.size(); // 当前已有的子集数
            for (int i = 0; i < curSize; i++) {
                List<Integer> newSubset = new ArrayList<>(result.get(i));
                newSubset.add(num); // 在已有子集基础上加入新元素
                result.add(newSubset);
            }
        }
        return result;
    }

    // 回溯版本（等效）
    public List<List<Integer>> subsetsBacktrack(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path)); // 每个节点都是子集
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(i + 1, nums, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        P0078_Subsets solution = new P0078_Subsets();
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
    }
}
