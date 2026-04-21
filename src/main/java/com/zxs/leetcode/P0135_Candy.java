package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/candy/
 *
 * 题目：分发糖果（Candy）
 *
 * n 个孩子站成一排。每个孩子有一个评分。
 * 如果一个孩子的评分比他相邻的孩子高，那么他必须获得比相邻孩子更多的糖果。
 * 请给所有孩子分发糖果，返回最少需要多少糖果。
 *
 * 示例 1：
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：三个孩子分别得到 2、1、2 颗糖果
 *
 * 示例 2：
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：孩子们分别得到 1、2、1 颗糖果
 *
 * 约束：
 * - n == ratings.length
 * - 1 <= n <= 2 * 10^4
 * - 0 <= ratings[i] <= 2 * 10^4
 *
 * 解题思路：
 * 方法一：两次扫描贪心（推荐）
 *   - 第一遍从左到右：若 ratings[i] > ratings[i-1]，candies[i] = candies[i-1] + 1
 *   - 第二遍从右到左：若 ratings[i] > ratings[i+1]，candies[i] = max(candies[i], candies[i+1] + 1)
 *   - 时间 O(n)，空间 O(n)，可优化到 O(1)
 *
 * 方法二：分段处理
 *   - 找到递增段、递减段、平台段分别计算
 *   - 较复杂
 *
 * 推荐方法：两次扫描，满足所有约束
 */
public class P0135_Candy {

    /**
     * 【推荐解法】两次扫描贪心
     * 核心：分别满足左右邻居约束后取最大值
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n <= 1) return n;

        int[] candies = new int[n];
        // 第一遍从左到右：满足左约束
        candies[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {
                candies[i] = 1;
            }
        }

        // 第二遍从右到左：满足右约束（同时满足取较大值）
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // 求和
        int sum = 0;
        for (int candy : candies) {
            sum += candy;
        }
        return sum;
    }
}
