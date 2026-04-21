package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 【P0056】合并区间
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0056_MergeIntervals {

    /**
     * 题目描述：
     * 给出一个区间的集合，合并所有重叠的区间。
     *
     * 示例：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠，合并为 [1,6]。
     *
     * 约束：
     * 1 <= intervals.length <= 10^4
     * intervals[i].length == 2, 0 <= start <= end <= 10^4
     *
     * 解题思路：
     * 方法1：排序后线性合并 —— 【推荐】O(n log n)时间，O(1)额外空间（不含输出）
     * 关键：对起点排序后，遍历比较当前区间起点与上一个区间终点。
     * 方法2：使用合并区间 API（若有现成库）
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        // 按起点升序排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        int[] cur = intervals[0]; // 当前合并后的区间

        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            // 若当前区间的起点 <= 上一区间的终点，则合并
            if (interval[0] <= cur[1]) {
                // 合并：起点取较小值，终点取较大值
                cur[1] = Math.max(cur[1], interval[1]);
            } else {
                // 不重叠，保存当前区间，开始新区间
                merged.add(cur);
                cur = interval;
            }
        }
        // 最后一个区间加入结果
        merged.add(cur);
        return merged.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        P0056_MergeIntervals solution = new P0056_MergeIntervals();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = solution.merge(intervals);
        for (int[] r : result) {
            System.out.print("[" + r[0] + "," + r[1] + "] ");
        }
    }
}
