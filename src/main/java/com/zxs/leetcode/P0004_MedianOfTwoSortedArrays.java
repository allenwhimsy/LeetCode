package com.zxs.leetcode;

/**
 * LeetCode 0004 - 寻找两个正序数组的中位数 (Median of Two Sorted Arrays)
 *
 * 【题目描述】
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的中位数。
 * 算法的时间复杂度应该为 O(log (m+n))。
 *
 * 示例 1：
 *   输入：nums1 = [1,3], nums2 = [2]
 *   输出：2.00000
 *   解释：合并数组 = [1,2,3]，中位数 2
 *
 * 示例 2：
 *   输入：nums1 = [1,2], nums2 = [3,4]
 *   输出：2.50000
 *   解释：合并数组 = [1,2,3,4]，中位数 (2 + 3) / 2 = 2.5
 *
 * 约束：
 *   - nums1.length == m, nums2.length == n
 *   - 0 <= m <= 1000, 0 <= n <= 1000
 *   - 1 <= m + n <= 2000
 *   - -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * 【解题思路】
 * 方法一：合并排序后取中位数（O((m+n)log(m+n)) 时间）
 *   将两个数组合并后排序，直接取中间值。
 *   缺点：不满足 O(log(m+n)) 的时间要求。
 *
 * 方法二：二分查找（O(log(min(m,n))) 时间）✅ 推荐
 *   在较短数组上进行二分查找，找到合适的切分位置使得左半部分元素 <= 右半部分元素。
 *   关键：对较短数组二分，将切分转换为在较长数组上的互补切分。
 *   复杂度：O(log(min(m,n)))，满足题目要求。
 *
 * @Author 郑晓胜
 */
public class P0004_MedianOfTwoSortedArrays {

    /**
     * 二分查找解法（推荐）
     * 时间复杂度：O(log(min(m,n)))
     * 空间复杂度：O(1)
     *
     * @param nums1 有序数组1
     * @param nums2 有序数组2
     * @return 中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 确保 nums1 是较短的数组，在较短的数组上做二分
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;

        while (left <= right) {
            // 在 nums1 上的切分位置
            int i = (left + right) / 2;
            // 在 nums2 上的切分位置（互补）
            int j = (m + n + 1) / 2 - i;

            // 边界值处理：当切分位置在数组两端时，使用极值
            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 找到正确切分位置
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    return (Math.max(nums1LeftMax, nums2LeftMax)
                            + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            } else if (nums1LeftMax > nums2RightMin) {
                // nums1 左部分太大，切分点左移
                right = i - 1;
            } else {
                // nums1 左部分太小，切分点右移
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}
