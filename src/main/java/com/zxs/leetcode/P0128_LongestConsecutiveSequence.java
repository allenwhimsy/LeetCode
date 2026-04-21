package com.zxs.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/longest-consecutive-sequence/
 *
 * 题目：最长连续序列（Longest Consecutive Sequence）
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长连续序列是 [1, 2, 3, 4]，所以返回长度为 4
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * 约束：
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 *
 * 解题思路：
 * 方法一：哈希集合 + 贪心（推荐）
 *   - 将所有数字加入 HashSet
 *   - 遍历数组，对每个数字，找到其连续序列的起点（num-1 不存在）
 *   - 从起点向后扩展，更新最长长度
 *   - 时间 O(n)，空间 O(n)
 *
 * 方法二：先排序后遍历
 *   - 时间 O(n log n)，不满足 O(n) 要求
 *
 * 推荐方法：哈希集合 + 贪心找起点
 */
public class P0128_LongestConsecutiveSequence {

    /**
     * 【推荐解法】哈希集合 + 贪心
     * 核心：只从每个连续序列的起点开始扩展，避免重复计算
     *       起点特征：num-1 不在集合中
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longest = 0;

        for (int num : numSet) {
            // 只从连续序列的起点开始（num-1 不存在）
            if (!numSet.contains(num - 1)) {
                int current = num;
                int count = 1;

                // 从起点向后扩展
                while (numSet.contains(current + 1)) {
                    current++;
                    count++;
                }
                longest = Math.max(longest, count);
            }
        }
        return longest;
    }
}
