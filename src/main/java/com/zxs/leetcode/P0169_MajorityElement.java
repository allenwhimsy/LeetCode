package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 169. 多数元素
 *
 * 给定一个大小为 n 的数组 nums，返回其中的多数元素。
 * 多数元素是指在数组中出现次数 大于 ⌊n/2⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 * 输入: nums = [3,2,3]
 * 输出: 3
 *
 * 示例 2：
 * 输入: nums = [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 *
 * 解题思路：
 * 方法一： Boyer-Moore 投票算法（推荐）
 * 维护一个候选人和票数
 * 遇到相同元素票数+1，不同则-1
 * 票数为0时换候选人
 * 因为多数元素超过一半，最终候选人一定是多数元素
 * 时间复杂度 O(n)，空间复杂度 O(1)
 *
 * 方法二：哈希表
 * 统计每个元素出现次数，找到超过 n/2 的元素
 * 时间复杂度 O(n)，空间复杂度 O(n)
 *
 * 方法三：排序
 * 排序后中位数一定是多数元素
 * 时间复杂度 O(n log n)，空间复杂度 O(1) 或 O(n)
 */
public class P0169_MajorityElement {

    // 方法一： Boyer-Moore 投票算法
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = nums[i];
                    count = 1;
                }
            }
        }
        return candidate;
    }

    // 方法二：分治（递归）
    public int majorityElementDivide(int[] nums) {
        return divideHelper(nums, 0, nums.length - 1);
    }

    private int divideHelper(int[] nums, int lo, int hi) {
        if (lo == hi) return nums[lo];
        int mid = lo + (hi - lo) / 2;
        int left = divideHelper(nums, lo, mid);
        int right = divideHelper(nums, mid + 1, hi);
        if (left == right) return left;
        // 合并：多数元素一定在左右两部分之一
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        P0169_MajorityElement solution = new P0169_MajorityElement();
        System.out.println(solution.majorityElement(new int[]{3, 2, 3}));             // 3
        System.out.println(solution.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2})); // 2
    }
}
