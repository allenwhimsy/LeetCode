package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 198. 打家劫舍
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金。
 * 房间的唯一约束条件是：相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组 nums，
 * 计算你不触动警报装置的情况下，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入: nums = [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)
 *       偷窃总金额 = 1 + 3 = 4
 *
 * 示例 2：
 * 输入: nums = [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2)，偷窃 3 号房屋 (金额 = 9)，偷窃 5 号房屋 (金额 = 1)
 *       偷窃总金额 = 2 + 9 + 1 = 12
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 * 解题思路：
 * 方法一：动态规划（推荐）
 * dp[i] = max(dp[i-1], dp[i-2] + nums[i])
 * dp[i] 表示偷到第 i 间房时的最高金额
 * 边界：dp[0]=nums[0]，dp[1]=max(nums[0], nums[1])
 * 时间复杂度 O(n)，空间复杂度 O(n)，可优化为 O(1)
 *
 * 方法二：滚动数组
 * 只保留前两个状态，节省空间
 */
public class P0198_HouseRobber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int n = nums.length;
        // dp[i] = 偷到第 i 间房的最高金额（i 从 0 开始）
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            // 不偷第 i 间房：dp[i-1]
            // 偷第 i 间房：dp[i-2] + nums[i]
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }

    // 空间优化版本
    public int robOptimized(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int prev2 = nums[0];                    // dp[i-2]
        int prev1 = Math.max(nums[0], nums[1]); // dp[i-1]
        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        P0198_HouseRobber solution = new P0198_HouseRobber();
        System.out.println(solution.rob(new int[]{1, 2, 3, 1}));    // 4
        System.out.println(solution.rob(new int[]{2, 7, 9, 3, 1})); // 12
    }
}
