package com.zxs.leetcode;

/**
 * 【P0080】删除有序数组中的重复项 II
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0080_RemoveDuplicatesII {

    /**
     * 题目描述：
     * 给你一个有序数组 nums，请你原地删除重复出现的元素，使每个元素最多出现两次。
     * 不要使用额外的数组空间，必须在原地修改输入数组，并在使用 O(1) 额外空间的条件下完成。
     *
     * 示例：
     * 输入：nums = [1,1,1,2,2,3]
     * 输出：5
     * 解释：数组变为 [1,1,2,2,3]，长度为 5
     *
     * 约束：
     * 1 <= nums.length <= 10^5
     *
     * 解题思路：
     * 快慢指针：slow 维护有效数组末尾，fast 扫描。当 nums[fast] != nums[slow-2] 时才将其加入 —— 【推荐】O(n)时间，O(1)空间
     * 核心：有序数组中，若某元素已出现2次，第3次及以后需要跳过。
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;

        // slow 指向当前有效数组的最后位置（从索引2开始）
        int slow = 2;
        for (int fast = 2; fast < nums.length; fast++) {
            // 只有当 nums[fast] != nums[slow-2] 时才保留
            // 这样保证同一元素最多出现两次
            if (nums[fast] != nums[slow - 2]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        P0080_RemoveDuplicatesII solution = new P0080_RemoveDuplicatesII();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int len = solution.removeDuplicates(nums);
        System.out.println("len=" + len);
        for (int i = 0; i < len; i++) System.out.print(nums[i] + " ");
    }
}
