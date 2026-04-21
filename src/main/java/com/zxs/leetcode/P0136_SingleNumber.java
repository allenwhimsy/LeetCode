package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/single-number/
 *
 * 题目：只出现一次的数字（Single Number）
 *
 * 给定一个非空整数数组，除了某个元素只出现一次外，其余每个元素均出现两次。
 * 找出那个只出现了一次的元素。
 *
 * 示例 1：
 * 输入：[2,2,1]
 * 输出：1
 *
 * 示例 2：
 * 输入：[4,1,2,1,2]
 * 输出：4
 *
 * 约束：
 * - 1 <= nums.length <= 3 * 10^4
 * - -3 * 10^4 <= nums[i] <= 3 * 10^4
 *
 * 解题思路：
 * 方法一：异或运算（推荐）
 *   - a ^ a = 0，a ^ 0 = a，异或满足交换律和结合律
 *   - 所有出现两次的数异或结果为 0，0 与唯一数异或得到唯一数
 *   - 时间 O(n)，空间 O(1)
 *
 * 方法二：哈希集合
 *   - 遍历，集合中有则移除，无则加入，最后集合中剩余即为唯一数
 *   - 时间 O(n)，空间 O(n)
 *
 * 推荐方法：异或运算，最优解
 */
public class P0136_SingleNumber {

    /**
     * 【推荐解法】异或运算
     * 核心性质：a ^ a = 0, a ^ 0 = a，异或满足交换律和结合律
     * 思路：所有数异或，出现两次的抵消，只剩唯一数
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
