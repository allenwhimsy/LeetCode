package com.zxs.leetcode;

/**
 * P0042 接雨水
 *
 * 题目描述：
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，
 * 计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * 解释：6 个单位的雨水被接住（见题目图示）
 *
 * 示例 2：
 * 输入: height = [4,2,0,3,2,5]
 * 输出: 9
 *
 * 约束条件：
 * - n == height.length
 * - 0 <= n <= 3 * 10^4
 * - 0 <= height[i] <= 10^5
 *
 * 解题思路：
 * 【方法一：双指针（推荐）】O(n) 时间，O(1) 空间
 * 从左右两端向中间遍历，维护左右最大高度。
 * 对于每个位置，能接的水量取决于「左右最高柱子的较小值」与「自身高度」的差。
 *
 * 【方法二：动态规划（预处理）】O(n) 时间，O(n) 空间
 * 预先计算每个位置左右的最大高度，再逐个计算。
 *
 * 【方法三：单调栈】O(n) 时间，O(n) 空间
 * 用栈维护高度递减的柱子，遇到比栈顶高的柱子时计算雨水。
 *
 * 【方法四：暴力】O(n^2) 时间 - 不推荐
 *
 * @Author 郑晓胜
 */
public class P0042_TrappingRainWater {

    /**
     * 方法一：双指针（推荐）- O(n) 时间，O(1) 空间
     * 核心：每个位置的水量 = max(min(leftMax, rightMax) - height[i], 0)
     */
    public int trap_twoPointer(int[] height) {
        if (height == null || height.length < 3) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                // 左边最大较小，以左边为准
                if (height[left] >= leftMax) {
                    leftMax = height[left]; // 更新左最大
                } else {
                    water += leftMax - height[left]; // 能接水
                }
                left++;
            } else {
                // 右边最大较小（或相等），以右边为准
                if (height[right] >= rightMax) {
                    rightMax = height[right]; // 更新右最大
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }

    /**
     * 方法二：动态规划 - O(n) 时间，O(n) 空间
     */
    public int trap_dp(int[] height) {
        if (height == null || height.length < 3) return 0;

        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int water = 0;

        // 预处理左最大
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 预处理右最大
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        // 计算每个位置的水量
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return water;
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0042_TrappingRainWater solution = new P0042_TrappingRainWater();

        int[][] heights = {
            {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1},
            {4, 2, 0, 3, 2, 5},
            {4, 2, 3}
        };
        for (int[] h : heights) {
            int result = solution.trap_twoPointer(h);
            System.out.println("height=" + java.util.Arrays.toString(h) + " -> 接水量: " + result);
        }
    }
}
