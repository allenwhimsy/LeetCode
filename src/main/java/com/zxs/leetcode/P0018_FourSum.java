package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 【LeetCode 第 18 题】四数之和
 *
 * <p>题目描述：
 * 给你一个整数数组 nums，判断 nums 中是否存在四个元素 a, b, c, d，
 * 使得 a + b + c + d 的值等于目标值 target，找出所有满足条件且不重复的四元组。
 *
 * <p>示例：
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 * <p>约束条件：
 * - 1 <= nums.length <= 200
 * - -10^9 <= nums[i] <= 10^9
 * - -10^9 <= target <= 10^9
 *
 * <p>解题思路：
 * 【方法一：排序 + 双指针（推荐）】O(n³)
 * - 先排序
 * - 固定前两个数（双层循环），后两个数用双指针
 * - 使用剪枝优化，避免重复计算
 *
 * 【方法二：哈希表】O(n²)，但代码复杂度高
 *
 * <p>推荐方法一，需要特别注意去重和剪枝。
 *
 * @Author 郑晓胜
 */
public class P0018_FourSum {

    /**
     * 排序 + 双指针解法
     *
     * @param nums   输入数组
     * @param target 目标值
     * @return 所有不重复的四元组列表
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 4) {
            return result;
        }

        // 先排序
        Arrays.sort(nums);
        int n = nums.length;

        // 第一层循环：固定第一个数
        for (int i = 0; i < n - 3; i++) {
            // 去重：跳过重复的第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 剪枝1：如果最小的四个数之和都大于 target，直接跳出
            // 注意：target 可能为负数，不能用简单的 nums[i] > target 判断
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target
                    && nums[i] >= 0) {
                break;
            }

            // 剪枝2：如果当前数加上最大的三个数之和都小于 target，跳过
            if (nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3] < target
                    && nums[i] <= 0) {
                continue;
            }

            // 第二层循环：固定第二个数
            for (int j = i + 1; j < n - 2; j++) {
                // 去重：跳过重复的第二个数
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 剪枝3：类似上面的优化
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target
                        && nums[i] + nums[j] >= 0) {
                    break;
                }

                if (nums[i] + nums[j] + nums[n - 1] + nums[n - 2] < target
                        && nums[i] + nums[j] <= 0) {
                    continue;
                }

                // 双指针：left 从 j+1 开始，right 从末尾开始
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        // 找到一个解，加入结果
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 跳过重复的左指针
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 跳过重复的右指针
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        // 移动指针继续寻找
                        left++;
                        right--;
                    } else if (sum < target) {
                        // sum 太小，left 右移
                        left++;
                    } else {
                        // sum 太大，right 左移
                        right--;
                    }
                }
            }
        }

        return result;
    }

    // ====== 测试代码 ======
    public static void main(String[] args) {
        P0018_FourSum solution = new P0018_FourSum();

        // 测试用例 1
        System.out.println("测试1: " + solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));

        // 测试用例 2
        System.out.println("测试2: " + solution.fourSum(new int[]{2, 2, 2, 2, 2}, 8));

        // 测试用例 3：负数 target
        System.out.println("测试3: " + solution.fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11));
    }
}
