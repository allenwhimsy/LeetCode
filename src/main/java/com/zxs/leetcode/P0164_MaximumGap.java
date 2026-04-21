package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 164. 最大间距
 *
 * 给定一个无序的整数数组 nums，找到其中相邻元素在排序后形式的最大差值。
 * 如果数组中元素少于 2 个，则返回 0。
 * 你必须编写一个能在线性时间内解决此问题的算法。
 *
 * 示例 1：
 * 输入: nums = [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9]，相邻元素之间的最大差值是 3
 *
 * 示例 2：
 * 输入: nums = [10]
 * 输出: 0
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 *
 * 解题思路：
 * 方法一：桶排序思想（推荐）
 * 设数组长度为 n，最大值为 max，最小值为 min
 * 最大间距一定大于等于 (max-min)/(n-1)
 * 将范围划分为 n-1 个桶，每个桶的大小为 (max-min)/(n-1)
 * 每个桶只记录其内部的最大值和最小值
 * 最大间距只可能出现在桶与桶之间（桶内间距已被桶大小覆盖）
 * 时间复杂度 O(n)，空间复杂度 O(n)
 *
 * 方法二：排序
 * 先排序，再找最大差值
 * 时间复杂度 O(n log n)，不符合进阶要求
 */
import java.util.*;

public class P0164_MaximumGap {

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int n = nums.length;
        int minVal = nums[0], maxVal = nums[0];
        // 找出最大最小值
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        if (minVal == maxVal) return 0;

        // 桶数量 = n-1（至少需要n-1个桶）
        int bucketCount = n - 1;
        int bucketSize = Math.max(1, (maxVal - minVal) / (bucketCount));
        int bucketNum = (maxVal - minVal) / bucketSize + 1;

        // 每个桶只记录最大值和最小值
        int[] bucketMin = new int[bucketNum];
        int[] bucketMax = new int[bucketNum];
        Arrays.fill(bucketMin, -1);
        Arrays.fill(bucketMax, -1);

        // 将元素放入对应桶
        for (int num : nums) {
            int idx = (num - minVal) / bucketSize;
            if (bucketMin[idx] == -1) {
                bucketMin[idx] = bucketMax[idx] = num;
            } else {
                bucketMin[idx] = Math.min(bucketMin[idx], num);
                bucketMax[idx] = Math.max(bucketMax[idx], num);
            }
        }

        // 扫描桶找最大间距
        int maxGap = 0;
        int prevMax = -1;
        for (int i = 0; i < bucketNum; i++) {
            if (bucketMin[i] == -1) continue;  // 跳过空桶
            if (prevMax != -1) {
                maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            }
            prevMax = bucketMax[i];
        }
        return maxGap;
    }

    public static void main(String[] args) {
        P0164_MaximumGap solution = new P0164_MaximumGap();
        System.out.println(solution.maximumGap(new int[]{3, 6, 9, 1}));  // 3
        System.out.println(solution.maximumGap(new int[]{10}));           // 0
    }
}
