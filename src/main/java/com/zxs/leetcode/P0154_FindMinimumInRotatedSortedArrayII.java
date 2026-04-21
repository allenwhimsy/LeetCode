package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 154. 寻找旋转排序数组中的最小值 II
 *
 * 已知一个长度为 n 的数组，预先按照某一个 pivot（支点）进行了旋转。
 * 例如，数组 [0,1,4,4,5,6,7] 可能变成 [4,5,6,7,0,1,4] 。
 * 给你一个可能存在 重复 元素值的数组 nums ，它原为一个升序排列的数组，并按上述定义了旋转。
 * 请你找出数组中的最小元素。
 * 注意：数组中的元素 可能会出现重复。
 *
 * 示例 1：
 * 输入: nums = [1,3,5]
 * 输出: 1
 *
 * 示例 2：
 * 输入: nums = [2,2,2,0,1]
 * 输出: 0
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序数组，但进行了旋转
 *
 * 解题思路：
 * 方法一：二分查找 + 去重（推荐）
 * 与153题类似，但当 nums[mid] == nums[right] 时，无法判断最小值在哪边
 * 此时 right-- 收缩右侧边界（最坏情况下退化为线性查找）
 * 时间复杂度 平均 O(log n)，最坏 O(n)，空间复杂度 O(1)
 *
 * 方法二：直接遍历
 * 找数组中的最小值，遍历即可
 * 时间复杂度 O(n)，不符合进阶要求
 */
public class P0154_FindMinimumInRotatedSortedArrayII {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                // 最小值在右半边
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // 最小值在左半边
                right = mid;
            } else {
                // nums[mid] == nums[right]，无法判断，去掉右端重复值
                right--;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        P0154_FindMinimumInRotatedSortedArrayII solution = new P0154_FindMinimumInRotatedSortedArrayII();
        System.out.println(solution.findMin(new int[]{1, 3, 5}));      // 1
        System.out.println(solution.findMin(new int[]{2, 2, 2, 0, 1}));  // 0
        System.out.println(solution.findMin(new int[]{3, 3, 1, 3}));   // 1
    }
}
