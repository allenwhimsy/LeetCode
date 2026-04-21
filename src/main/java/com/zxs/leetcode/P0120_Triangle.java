package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/triangle/
 *
 * 题目：三角形最小路径和（Triangle）
 *
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * 每一步可以移动到下一行中相邻数字的下方。
 * 换句话说，如果正踩在下方行的第 i 个数字，则下一步可以移动到下方行第 i 或 i+1 个数字。
 *
 * 示例：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：路径 2→3→5→1 的和最小，为 11。
 *
 * 约束：
 * - 1 <= triangle.length <= 200
 * - triangle[i] 长度为 i+1（0-indexed）
 * - -10^4 <= triangle[i][j] <= 10^4
 *
 * 解题思路：
 * 方法一：DP 原地修改（推荐）
 *   - 从底部向上递推：dp[j] = min(dp[j], dp[j+1]) + triangle[i][j]
 *   - 最终 dp[0] 即为答案
 *   - 时间 O(n^2)，空间 O(m)（原地修改）
 *
 * 方法二：自顶向下 DP + 额外空间
 *   - dp[i][j] 表示到第 i 行第 j 列的最小路径和
 *   - dp[i][j] = min(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j]
 *
 * 推荐方法：DP 原地修改，从底向上
 */
public class P0120_Triangle {

    /**
     * 【推荐解法】DP 原地修改，从底向上
     * 核心：dp[j] = min(当前行j, 下一行j) + 当前值
     *       从最后一行开始往上推，最终 dp[0] 为答案
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 从倒数第二行开始，逐行往上更新
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 下一行相邻两个元素的较小值 + 当前元素
                int newVal = Math.min(triangle.get(i + 1).get(j),
                                      triangle.get(i + 1).get(j + 1))
                           + triangle.get(i).get(j);
                triangle.get(i).set(j, newVal);
            }
        }
        return triangle.get(0).get(0);
    }
}
