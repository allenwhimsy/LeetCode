package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 *
 * 题目：买卖股票的最佳时机（Best Time to Buy and Sell Stock）
 *
 * 给定一个数组 prices ，其中 prices[i] 是某只股票第 i 天的价格。
 * 你只能选择某一天买入这只股票，并选择在未来的某一天卖出该股票。
 * 设计一个算法来找到所能获得的最大利润。
 *
 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：5
 * 解释：在第2天（price=1）买入，第5天（price=6）卖出，利润最大 = 6-1=5
 *
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下，没有交易完成（利润为0），所以最大利润为 0
 *
 * 约束：
 * - 1 <= prices.length <= 10^5
 * - 0 <= prices[i] <= 10^4
 *
 * 解题思路：
 * 方法一：一次遍历（推荐）
 *   - 维护最低价格 minPrice，遇到更低价格则更新
 *   - 每天计算 prices[i] - minPrice，维护最大利润
 *   - 时间 O(n)，空间 O(1)
 *
 * 方法二：动态规划（前缀最小值）
 *   - 与方法一本质相同
 *
 * 推荐方法：一次遍历，维护最低价格
 */
public class P0121_BestTimeToBuyAndSellStock {

    /**
     * 【推荐解法】一次遍历
     * 核心：维护历史最低价格，每天计算潜在最大利润
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // 历史最低价格
        int maxProfit = 0;                 // 历史最大利润

        for (int price : prices) {
            // 更新最低价格
            if (price < minPrice) {
                minPrice = price;
            }
            // 计算当前卖出最大利润
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }
}
