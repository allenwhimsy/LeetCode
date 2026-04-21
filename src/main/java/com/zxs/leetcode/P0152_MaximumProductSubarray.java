package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 152. 乘积最大子数组
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组对应的最大乘积。
 *
 * 示例 1：
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 的乘积最大为 6。
 *
 * 示例 2：
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能是 2，因为 [-2,-1] 不是子数组，而是子序列。
 *
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * 乘积的绝对值不超过 2^31
 *
 * 解题思路：
 * 方法一：动态规划（推荐）
 * 由于存在负数，所以需要同时维护最大乘积和最小乘积（最小乘积可能因负数翻转变为最大）
 * dpMax[i] = max(dpMax[i-1] * nums[i], nums[i], dpMin[i-1] * nums[i])
 * dpMin[i] = min(dpMin[i-1] * nums[i], nums[i], dpMax[i-1] * nums[i])
 * 用滚动变量优化空间
 * 时间复杂度 O(n)，空间复杂度 O(1)
 *
 * 方法二：枚举分割点
 * 统计负数个数，分别处理
 * 时间复杂度 O(n)，空间复杂度 O(1)
 */
public class P0152_MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int max = nums[0];      // 到当前位置的最大乘积
        int min = nums[0];      // 到当前位置的最小乘积
        int result = nums[0];    // 全局最大乘积

        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            // 当前最大乘积可能来自：当前元素本身、上一个最大乘积*当前元素、上一个最小乘积*当前元素（负数反转）
            int tmpMax = Math.max(cur, Math.max(max * cur, min * cur));
            int tmpMin = Math.min(cur, Math.min(min * cur, max * cur));
            max = tmpMax;
            min = tmpMin;
            result = Math.max(result, max);
        }
        return result;
    }

    public static void main(String[] args) {
        P0152_MaximumProductSubarray solution = new P0152_MaximumProductSubarray();
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));  // 6
        System.out.println(solution.maxProduct(new int[]{-2, 0, -1}));   // 0
        System.out.println(solution.maxProduct(new int[]{-2, 3, -4}));   // 24
    }
}
