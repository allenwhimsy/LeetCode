package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/pascals-triangle-ii/
 *
 * 题目：杨辉三角 II（Pascal's Triangle II）
 *
 * 给定一个非负整数 rowIndex ，返回「杨辉三角」的第 rowIndex 行。
 * 从 0 开始计数，所以第 0 行是 [1]。
 *
 * 示例 1：
 * 输入：rowIndex = 3
 * 输出：[1,3,3,1]
 *
 * 示例 2：
 * 输入：rowIndex = 0
 * 输出：[1]
 *
 * 约束：
 * - 0 <= rowIndex <= 33
 *
 * 解题思路：
 * 方法一：DP 原地修改（推荐）
 *   - 从后往前更新：row[j] = row[j] + row[j-1]
 *   - 从后往前避免覆盖未使用的元素
 *   - 时间 O(n^2)，空间 O(k)（k=rowIndex+1）
 *
 * 方法二：公式法
 *   - 杨辉三角第 n 行第 k 列 = C(n, k)
 *   - 组合数计算，数学方法
 *
 * 推荐方法：DP 原地修改，空间最优
 */
public class P0119_PascalsTriangleII {

    /**
     * 【推荐解法】DP 原地修改（从后往前）
     * 核心：row[j] = row[j] + row[j-1]，从后往前避免覆盖
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        // 初始化第一行
        for (int i = 0; i <= rowIndex; i++) {
            row.add(1);
        }

        // 从第2行开始，每行原地更新
        // 关键：从后往前遍历，避免 row[j-1] 被覆盖
        for (int i = 1; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }
}
