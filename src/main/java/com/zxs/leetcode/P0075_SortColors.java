package com.zxs.leetcode;

/**
 * 【P0075】颜色分类
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0075_SortColors {

    /**
     * 题目描述：
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums，
     * 原地对它们进行分类，使得相同颜色的元素相邻，并按照红、白、蓝的顺序排列。
     * 这里使用整数 0、1 和 2 分别表示红、白、蓝。
     * 只能用常数空间的一趟扫描算法解决。
     *
     * 示例：
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     *
     * 约束：
     * n == nums.length
     * 1 <= n <= 300
     * nums[i] 为 0、1 或 2
     *
     * 解题思路：
     * 三路快排（Dutch National Flag）：用两个指针 zero（已排好0的最右边界）和 two（已排好2的最左边界），
     * i 从 0 遍历到 two，将元素分为三段 —— 【推荐】O(n)时间，O(1)空间，一趟遍历
     */
    public void sortColors(int[] nums) {
        // zero: [0..zero] 为 0（已就位），two: [two..n-1] 为 2（已就位）
        int zero = -1;      // zero 指针，指向最后一个 0 的位置
        int two = nums.length; // two 指针，指向第一个 2 的位置
        int i = 0;

        while (i < two) {
            if (nums[i] == 0) {
                // 将 0 换到 zero+1 的位置
                swap(nums, ++zero, i);
                i++;
            } else if (nums[i] == 2) {
                // 将 2 换到 two-1 的位置（注意 i 不前进，因为换过来的元素还需要判断）
                swap(nums, --two, i);
                // i 不++
            } else {
                // nums[i] == 1，只需前移
                i++;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a]; nums[a] = nums[b]; nums[b] = t;
    }

    public static void main(String[] args) {
        P0075_SortColors solution = new P0075_SortColors();
        int[] nums = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        System.out.println(java.util.Arrays.toString(nums)); // [0,0,1,1,2,2]
    }
}
