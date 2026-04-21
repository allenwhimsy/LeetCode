package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 189. 轮转数组
 *
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 * 示例 1：
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例 2：
 * 输入: nums = [-1,-100,3,99], k = 2
 * 输出: [3,99,-1,-100]
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * 0 <= k <= 10^5
 *
 * 解题思路：
 * 方法一：三次翻转（推荐）
 * 关键：向右轮转 k 步 = 先翻转整个数组，再翻转前 n-k 个，最后翻转后 k 个
 * 例: [1,2,3,4,5,6,7], k=3 -> [5,6,7,1,2,3,4]
 * 时间复杂度 O(n)，空间复杂度 O(1)
 *
 * 方法二：环状替换
 * 从位置0开始，逐个元素放到正确位置，直到回到起点
 * 需要注意处理 k % n == 0 的情况
 * 时间复杂度 O(n)，空间复杂度 O(1)
 */
public class P0189_RotateArray {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;  // 处理 k > n 的情况
        if (k == 0) return;

        // 三次翻转
        reverse(nums, 0, n - 1);       // 翻转整个数组
        reverse(nums, 0, k - 1);       // 翻转前 k 个
        reverse(nums, k, n - 1);       // 翻转后 n-k 个
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        P0189_RotateArray solution = new P0189_RotateArray();

        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        solution.rotate(nums1, 3);
        System.out.println(java.util.Arrays.toString(nums1)); // [5,6,7,1,2,3,4]

        int[] nums2 = {-1, -100, 3, 99};
        solution.rotate(nums2, 2);
        System.out.println(java.util.Arrays.toString(nums2)); // [3,99,-1,-100]
    }
}
