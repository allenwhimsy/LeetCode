package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 【LeetCode 第15题】三数之和
 *
 * <p>题目描述：
 * 给你一个整数数组 nums，判断 nums 中是否存在三个元素 a, b, c，
 * 使得 a + b + c = 0。请你找出所有和为 0 的不重复三元组。
 *
 * <p>示例：
 * 输入: nums = [-1,0,1,2,-1,-4]
 * 输出: [[-1,-1,2], [-1,0,1]]
 * 解释:
 * - nums[0] + nums[1] + nums[2] = -1 + 0 + 1 = 0
 * - nums[0] + nums[3] + nums[4] = -1 + 2 + -1 = 0
 * - nums[1] + nums[2] + nums[4] = 0 + 1 + -1 = 0（与第一个重复）
 *
 * <p>约束条件：
 * - 0 <= nums.length <= 3000
 * - -10^5 <= nums[i] <= 10^5
 *
 * <p>解题思路：
 *
 * 【方法一】排序 + 双指针（推荐）⭐
 * - 先对数组排序（O(n log n)）
 * - 固定一个数 nums[i]，在右侧区间用双指针找两数之和 = -nums[i]
 * - 关键：跳过重复元素，避免产生重复三元组
 * - 时间复杂度: O(n^2)，空间复杂度: O(1)（排序开销除外）
 *
 * 【方法二】哈希表（不推荐）
 * - 两数之和的哈希表方法推广
 * - 但去重逻辑复杂，且难以保证结果顺序
 *
 * 【方法三】排序 + 哈希（折中）
 * - 排序后，对每对 (i,j) 用哈希表查找 -(nums[i]+nums[j])
 * - 时间复杂度: O(n^2)，空间复杂度: O(n)
 *
 * @Author 郑晓胜
 */
public class P0015_ThreeSum {

    /**
     * 【方法一】排序 + 双指针（推荐）⭐
     *
     * 核心思想：
     * 1. 排序：使相同值相邻，便于去重
     * 2. 固定 i：用双指针 left=i+1, right=n-1 在右侧找 nums[left]+nums[right] = -nums[i]
     * 3. 找完后收缩指针并跳过重复元素
     *
     * 去重关键：
     * - nums[i] 与 nums[i-1] 相同时，跳过（避免重复固定值）
     * - 找到一组后，left/right 跳过重复值
     *
     * @param nums 输入数组
     * @return 所有不重复的三元组列表
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return result;
        }

        // 1. 排序
        Arrays.sort(nums);
        int n = nums.length;

        // 2. 固定第一个数
        for (int i = 0; i < n - 2; i++) {
            // 关键优化：若 nums[i] > 0，三数之和不可能为 0（已排序，后面都更大）
            if (nums[i] > 0) {
                break;
            }

            // 跳过重复的 nums[i]
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 3. 双指针查找 nums[left] + nums[right] = -nums[i]
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // 找到一组有效解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 收缩双指针，跳过重复元素
                    int leftVal = nums[left];
                    int rightVal = nums[right];
                    while (left < right && nums[left] == leftVal) left++;
                    while (left < right && nums[right] == rightVal) right--;

                } else if (sum < 0) {
                    // sum < 0：需要更大的和，左指针右移
                    left++;
                } else {
                    // sum > 0：需要更小的和，右指针左移
                    right--;
                }
            }
        }

        return result;
    }

    // ========== 方法二：排序 + 哈希表 ==========

    /**
     * 【方法二】排序 + 哈希表
     *
     * 对每对 (i, j)，在 j 右侧范围内用哈希表查找 -(nums[i]+nums[j])
     * 用 set 去重
     */
    public static List<List<Integer>> threeSumHash(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            java.util.Set<Integer> seen = new java.util.HashSet<>();

            for (int j = i + 1; j < n - 1; j++) {
                int complement = -nums[i] - nums[j];
                if (seen.contains(complement)) {
                    result.add(Arrays.asList(nums[i], complement, nums[j]));
                    while (j + 1 < n && nums[j] == nums[j + 1]) j++;
                }
                seen.add(nums[j]);
            }
        }

        return result;
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        test(new int[]{-1, 0, 1, 2, -1, -4});
        test(new int[]{0, 1, 1});
        test(new int[]{0, 0, 0});
        test(new int[]{-2, 0, 0, 2, 2});
        test(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3});
        test(new int[]{});
        test(new int[]{1});
    }

    private static void test(int[] nums) {
        System.out.printf("输入: %s%n", Arrays.toString(nums));
        List<List<Integer>> result = threeSum(nums);
        System.out.print("输出: [");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println();
    }
}
