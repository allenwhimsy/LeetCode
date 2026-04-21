package com.zxs.leetcode;

/**
 * P0031 下一个排列
 *
 * 题目描述：
 * 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1]。
 * 下一个排列是指一个数组中字典序紧邻的下一个排列。
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2]。
 * 如果数组已经是所有排列中字典序最大的，则将其按字典序最小排列（即将其反转为升序）。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 * 约束条件：
 * - 1 <= nums.length <= 100
 * - 1 <= nums[i] <= 100
 *
 * 解题思路：
 * 【方法一：两遍扫描（推荐）】O(n)
 * 1. 从右向左找到第一个升序对 nums[i] < nums[i+1]，即从后往前找第一个比后面小的位置 i
 * 2. 再从右向左找第一个比 nums[i] 大的数 nums[j]，交换它们
 * 3. 反转 i+1 到末尾的部分，使其升序排列
 * 时间复杂度 O(n)，空间复杂度 O(1)
 *
 * 【方法二：库函数】直接使用 Arrays.sort()，但效率不如方法一
 *
 * @Author 郑晓胜
 */
public class P0031_NextPermutation {

    /**
     * 下一个排列
     * @param nums 输入数组，直接原地修改
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;

        // 第一步：从右向左找到第一个升序对 nums[i] < nums[i+1]
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 第二步：如果找到了（不是全部降序的情况），从右向左找第一个比 nums[i] 大的数并交换
        if (i >= 0) {
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // 交换 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // 第三步：反转 i+1 到末尾的部分，使其升序排列
        reverse(nums, i + 1, n - 1);
    }

    /**
     * 交换数组中两个位置的值
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 反转数组指定区间 [left, right]
     */
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0031_NextPermutation solution = new P0031_NextPermutation();

        // 测试用例 1
        int[] nums1 = {1, 2, 3};
        solution.nextPermutation(nums1);
        System.out.print("测试1: [1,2,3] -> ");
        printArray(nums1); // 预期: [1,3,2]

        // 测试用例 2
        int[] nums2 = {3, 2, 1};
        solution.nextPermutation(nums2);
        System.out.print("测试2: [3,2,1] -> ");
        printArray(nums2); // 预期: [1,2,3]

        // 测试用例 3
        int[] nums3 = {1, 1, 5};
        solution.nextPermutation(nums3);
        System.out.print("测试3: [1,1,5] -> ");
        printArray(nums3); // 预期: [1,5,1]

        // 测试用例 4：包含重复数字
        int[] nums4 = {1, 5, 1};
        solution.nextPermutation(nums4);
        System.out.print("测试4: [1,5,1] -> ");
        printArray(nums4); // 预期: [5,1,1]
    }

    private static void printArray(int[] nums) {
        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i < nums.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }
}
