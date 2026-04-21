package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/single-number-ii/
 *
 * 题目：只出现一次的数字 II（Single Number II）
 *
 * 给你一个整数数组 nums ，除某个元素只出现一次外，其余每个元素都恰好出现三次。
 * 请你找出并返回那个只出现了一次的元素。
 *
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 *
 * 约束：
 * - 1 <= nums.length <= 3 * 10^4
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - 除某个元素外，其他每个元素都出现 3 次
 *
 * 解题思路：
 * 方法一：位运算统计（推荐）
 *   - 统计每个比特位 1 出现的次数，%3 得到唯一数对应位
 *   - 时间 O(n)，空间 O(1)
 *
 * 方法二：有限状态机（数字电路思路）
 *   - 用 two 和 one 两个变量表示状态
 *   - bit 位出现 3 次归零，较为巧妙
 *
 * 方法三：哈希表
 *   - 时间 O(n)，空间 O(n)，不推荐
 *
 * 推荐方法：位运算统计，通俗易懂
 */
public class P0137_SingleNumberII {

    /**
     * 【推荐解法】位运算统计每一位出现次数
     * 核心：统计每一位上 1 出现的次数，%3 得到唯一数对应位
     */
    public int singleNumber(int[] nums) {
        int result = 0;

        // 遍历每一位（int 范围 32 位）
        for (int i = 0; i < 32; i++) {
            int bitSum = 0;
            int mask = 1 << i; // 当前位掩码

            // 统计 nums 中所有数在当前位的 1 之和
            for (int num : nums) {
                if ((num & mask) != 0) {
                    bitSum++;
                }
            }

            // 若当前位 1 出现次数不是 3 的倍数，则唯一数在该位为 1
            if (bitSum % 3 != 0) {
                result |= mask;
            }
        }
        return result;
    }
}
