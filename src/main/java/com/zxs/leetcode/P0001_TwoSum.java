package com.zxs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 0001 - 两数之和 (Two Sum)
 *
 * 【题目描述】
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *   输入：nums = [2,7,11,15], target = 9
 *   输出：[0,1]
 *   解释：因为 nums[0] + nums[1] == 9，返回 [0, 1]
 *
 * 示例 2：
 *   输入：nums = [3,2,4], target = 6
 *   输出：[1,2]
 *
 * 示例 3：
 *   输入：nums = [3,3], target = 6
 *   输出：[0,1]
 *
 * 约束：
 *   - 2 <= nums.length <= 10^4
 *   - -10^9 <= nums[i] <= 10^9
 *   - -10^9 <= target <= 10^9
 *   - 只会存在一个有效答案
 *
 * 【解题思路】
 * 方法一：暴力枚举（O(n²) 时间，O(1) 空间）
 *   双重循环枚举所有两数组合，找到和等于 target 的一对。
 *   缺点：时间复杂度高，数据量大时超时。
 *
 * 方法二：哈希表（O(n) 时间，O(n) 空间）✅ 推荐
 *   遍历数组时，对每个元素 nums[i]，计算其"补数" complement = target - nums[i]。
 *   若补数已在哈希表中，直接返回 [补数的下标, i]。
 *   否则将 nums[i] 及其下标存入哈希表，继续遍历。
 *   一次遍历即可完成，时间复杂度 O(n)。
 *
 * @Author 郑晓胜
 */
public class P0001_TwoSum {

    /**
     * 哈希表解法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums   整数数组
     * @param target 目标和
     * @return 两个下标组成的数组
     */
    public int[] twoSum(int[] nums, int target) {
        // key: 数组元素值, value: 该元素的下标
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 当前元素的补数

            // 若补数已存在于哈希表，直接返回结果
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // 否则将当前元素存入哈希表
            map.put(nums[i], i);
        }

        // 题目保证有解，理论上不会执行到此处
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 暴力枚举解法（仅供对比，不推荐）
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     * @param nums   整数数组
     * @param target 目标和
     * @return 两个下标组成的数组
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
