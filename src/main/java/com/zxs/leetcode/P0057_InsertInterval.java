package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【P0057】插入区间
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0057_InsertInterval {

    /**
     * 题目描述：
     * 给定一个无重叠的、按照区间起始端点排序的区间列表。在列表中插入一个新的区间，
     * 你需要确保列表中的区间仍然有序且不重叠（如果需要，可以合并）。
     *
     * 示例：
     * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
     * 输出：[[1,5],[6,9]]
     *
     * 约束：
     * intervals[i][0] <= intervals[i][1]
     *
     * 解题思路：
     * 三步走：①加入所有在新区间之前的区间 ②合并所有与新区间重叠的区间 ③加入新区间后的所有区间
     * —— 【推荐】O(n)时间，O(1)额外空间
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 第一步：加入所有在新区间之前的区间（严格小于新区间起点）
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 第二步：合并所有与新区间重叠的区间
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // 更新新区间的起点和终点
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval); // 加入合并后的新区间

        // 第三步：加入所有在新区间之后的区间
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        P0057_InsertInterval solution = new P0057_InsertInterval();
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] result = solution.insert(intervals, newInterval);
        for (int[] r : result) {
            System.out.print("[" + r[0] + "," + r[1] + "] ");
        }
    }
}
