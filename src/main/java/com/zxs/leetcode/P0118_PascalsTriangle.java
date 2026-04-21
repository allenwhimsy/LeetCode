package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/pascals-triangle/
 *
 * 题目：杨辉三角（Pascal's Triangle）
 *
 * 给定一个非负整数 numRows ，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 示例 1：
 * 输入：numRows = 5
 * 输出：
 * [
 *     [1],
 *    [1,1],
 *   [1,2,1],
 *  [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 *
 * 约束：
 * - 1 <= numRows <= 30
 *
 * 解题思路：
 * 方法一：逐行生成（推荐）
 *   - 每行根据上一行计算：row[j] = prevRow[j-1] + prevRow[j]
 *   - 头尾元素为 1
 *   - 时间 O(n^2)，空间 O(n^2)
 *
 * 推荐方法：逐行生成，经典 DP
 */
public class P0118_PascalsTriangle {

    /**
     * 【推荐解法】逐行生成
     * 核心：杨辉三角第 i 行（0-indexed）有 i+1 个元素
     *       每个元素 = 上一行同列 + 上一行前一列
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1); // 头尾元素为 1
                } else {
                    // 中间元素 = 上一行 j-1 + 上一行 j
                    List<Integer> prevRow = triangle.get(i - 1);
                    row.add(prevRow.get(j - 1) + prevRow.get(j));
                }
            }
            triangle.add(row);
        }
        return triangle;
    }
}
