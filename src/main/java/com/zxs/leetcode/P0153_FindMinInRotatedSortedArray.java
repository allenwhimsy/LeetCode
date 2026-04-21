package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 153. 寻找旋转排序数组中的最小值
 *
 * 已知一个长度为 n 的数组，预先按照某一个 pivot（支点）进行了旋转。
 * 例如，数组 [0,1,2,4,5,6,7] 可能变成 [4,5,6,7,0,1,2] 。
 * 给你一个元素值互不相同的数组 nums ，它原为一个升序排列的数组，并按上述定义了旋转。
 * 请你找出数组中的最小元素。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入: nums = [3,4,5,1,2]
 * 输出: 1
 * 解释: 原数组为 [1,2,3,4,5]，旋转 3 次得到 [3,4,5,1,2]
 *
 * 示例 2：
 * 输入: nums = [4,5,6,7,0,1,2]
 * 输出: 0
 * 解释: 原数组为 [0,1,2,4,5,6,7]，旋转 4 次得到 [4,5,6,7,0,1,2]
 *
 * 示例 3：
 * 输入: nums = [11,13,15,17]
 * 输出: 11
 * 解释: 原数组为 [11,13,15,17]，旋转 0 次得到 [11,13,15,17]
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数互不相同
 * nums 原来是一个升序数组，但进行了旋转
 *
 * 解题思路：
 * 方法一：二分查找（推荐）
 * 旋转数组一定有一半是排好序的
 * 如果 nums[mid] > nums[right]，说明最小值在右半边，left = mid + 1
 * 否则，最小值在左半边（包括mid），right = mid
 * 时间复杂度 O(log n)，空间复杂度 O(1)
 *
 * 方法二：线性扫描
 * 找数组中的最小值，遍历即可
 * 时间复杂度 O(n)，不符合要求
 */
public class P0153_FindMinInRotatedSortedArray {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                // 最小值在右半边
                left = mid + 1;
            } else {
                // 最小值在左半边（包括mid）
                right = mid;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        P0153_FindMinInRotatedSortedArray solution = new P0153_FindMinInRotatedSortedArray();
        System.out.println(solution.findMin(new int[]{3, 4, 5, 1, 2}));  // 1
        System.out.println(solution.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));  // 0
        System.out.println(solution.findMin(new int[]{11, 13, 15, 17}));  // 11
    }
}
