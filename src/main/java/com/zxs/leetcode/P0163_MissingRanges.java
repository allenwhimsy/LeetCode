package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 163. 缺失的区间
 *
 * 给你一个闭区间 [lo, hi]（lo <= hi）和一个整数数组 nums，
 * 其中数组元素互不相同，且已按升序排列。
 * 找出在 [lo, hi] 范围内但不在 nums 中的所有整数，并将其作为列表返回。
 * 你需要按照从小到大的顺序返回列表。
 *
 * 示例 1：
 * 输入: nums = [0, 1, 3, 50, 75], lo = 0, hi = 99
 * 输出: ["2", "4->49", "51->74", "76->99"]
 *
 * 示例 2：
 * 输入: nums = [], lo = 1, hi = 1
 * 输出: ["1"]
 *
 * 示例 3：
 * 输入: nums = [], lo = -3, hi = -1
 * 输出: ["-3->-1"]
 *
 * 提示：
 * -10^9 <= lo <= hi <= 10^9
 * 0 <= nums.length <= 100
 * -10^9 <= nums[i] <= 10^9
 * nums 中的所有值互不相同
 *
 * 解题思路：
 * 方法一：双指针扫描（推荐）
 * 用 prev 记录上一个已知的数（初始为 lo-1）
 * 遍历 nums，对每个数 num：
 *   - 如果 num > lo，说明 lo 到 num-1 有缺失区间
 *   - 如果 num == lo，说明 lo 存在，无缺失
 *   - 更新 prev = num
 *   - 移动 lo 到 num+1（因为 num 已处理）
 * 遍历结束后，如果 lo <= hi，还有 lo 到 hi 的缺失区间
 * 时间复杂度 O(n)，空间复杂度 O(1)
 */
import java.util.*;

public class P0163_MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lo, int hi) {
        List<String> result = new ArrayList<>();
        // prev 初始化为 lo-1，表示上一个已处理的数字
        long prev = (long) lo - 1;
        long cur;

        for (int i = 0; i <= nums.length; i++) {
            // 最后一个边界用 hi+1 表示遍历结束
            cur = (i < nums.length) ? nums[i] : (long) hi + 1;

            // 如果当前数字比 lo 大，说明有缺失区间
            if (prev + 1 <= cur - 1) {
                String range = formatRange(prev + 1, cur - 1);
                result.add(range);
            }
            prev = cur;
        }
        return result;
    }

    // 格式化区间
    private String formatRange(long lo, long hi) {
        if (lo == hi) {
            return String.valueOf(lo);
        }
        return lo + "->" + hi;
    }

    public static void main(String[] args) {
        P0163_MissingRanges solution = new P0163_MissingRanges();
        System.out.println(solution.findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99));
        System.out.println(solution.findMissingRanges(new int[]{}, 1, 1));
        System.out.println(solution.findMissingRanges(new int[]{}, -3, -1));
    }
}
