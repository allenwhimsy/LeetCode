package com.zxs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/max-points-on-a-line/
 *
 * 题目：直线上最多的点数（Max Points on a Line）
 *
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。
 * 求最多有多少个点落在同一条直线上。
 *
 * 示例 1：
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 *
 * 示例 2：
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 *
 * 约束：
 * - 1 <= points.length <= 300
 * - points[i].length == 2
 * - -10^4 <= xi, yi <= 10^4
 * - points 中所有坐标各不相同
 *
 * 解题思路：
 * 方法一：哈希表 + 斜率约分（推荐）
 *   - 以每个点为基准，统计与其他点组成的斜率
 *   - 斜率用分数 (dy/gcd, dx/gcd) 约分后做 key（考虑 dx=0 和 dy=0 的特殊情况）
 *   - 时间 O(n^2)，空间 O(n)
 *
 * 方法二：枚举所有点对
 *   - 对每个点对计算直线，时间 O(n^2 log n)
 *
 * 推荐方法：斜率哈希表
 */
public class P0149_MaxPointsOnALine {

    /**
     * 【推荐解法】以每个点为基准，统计斜率
     * 核心：用最大公约数约分斜率为最简分数，处理重复点和垂直线
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int duplicate = 0; // 与基准点重合的点数

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++; // 与 points[i] 重合
                    continue;
                }

                // 约分斜率：用 gcd 化简
                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;

                // 用 dx 的符号统一斜率表示（避免 dx 正负不同导致同一斜率被当作不同）
                String key = dx + "/" + dy;
                slopeCount.put(key, slopeCount.getOrDefault(key, 0) + 1);
            }

            int localMax = duplicate; // 基准点自身
            for (int count : slopeCount.values()) {
                localMax = Math.max(localMax, duplicate + count);
            }
            maxCount = Math.max(maxCount, localMax);
        }
        return maxCount;
    }

    // 计算最大公约数
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
