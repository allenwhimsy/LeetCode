package com.zxs.leetcode;

/**
 * 【P0088】合并两个有序数组
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0088_MergeSortedArray {

    /**
     * 题目描述：
     * 给你两个按非递减顺序排列的整数数组 nums1 和 nums2，和两个整数 m 和 n，
     * 分别表示 nums1 和 nums2 中的元素个数。
     * 请你将 nums2 合并到 nums1 中，使合并后的数组也是非递减顺序。
     * 要求原地修改 nums1。
     *
     * 示例：
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     *
     * 约束：
     * nums1.length == m + n, nums2.length == n
     * 0 <= m, n <= 200
     *
     * 解题思路：
     * 双指针从后往前合并：利用 nums1 有预留空间（尾部为0），从大到小填充 —— 【推荐】O(m+n)时间，O(1)空间
     * 优势：无需额外数组，直接在 nums1 上操作。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;       // nums1 有效数据末尾
        int j = n - 1;       // nums2 末尾
        int k = m + n - 1;   // nums1 末尾（从后往前填充的位置）

        // 从后往前取较大的元素放入 nums1[k]
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        // 若 nums2 还有剩余，直接复制（nums1 剩余已经是正确位置）
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    public static void main(String[] args) {
        P0088_MergeSortedArray solution = new P0088_MergeSortedArray();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        solution.merge(nums1, 3, new int[]{2, 5, 6}, 3);
        System.out.println(java.util.Arrays.toString(nums1)); // [1,2,2,3,5,6]
    }
}
