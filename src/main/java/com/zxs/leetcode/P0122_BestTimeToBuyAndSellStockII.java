package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * 题目：买卖股票的最佳时机 II（Best Time to Buy and Sell Stock II）
 *
 * 给你一个整数数组 prices ，其中 prices[i] 是某只股票第 i 天的价格。
 * 设计一个算法来计算你能获取的最大利润。
 * 可以尽可能地完成更多的交易（多次买卖股票）。
 * 但是，你不能同时参与多笔交易（必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第2天（price=1）买入，第3天（price=5）卖出，利润=4
 *       在第4天（price=3）买入，第5天（price=6）卖出，利润=3
 *       总利润 = 7
 *
 * 约束：
 * - 1 <= prices.length <= 3 * 10^4
 * - 0 <= prices[i] <= 10^4
 *
 * 解题思路：
 * 方法一：贪心（推荐）
 *   - 只要今天价格比昨天高，就昨天买今天卖（累加利润）
 *   - 等价于所有上升段的利润之和
 *   - 时间 O(n)，空间 O(1)
 *
 * 方法二：峰谷法
 *   - 找到所有谷底买入，峰顶卖出
 *   - 与方法一等价
 *
 * 推荐方法：贪心，所有上升段利润累加
 */
public class P0122_BestTimeToBuyAndSellStockII {

    /**
     * 【推荐解法】贪心：累加所有上升段利润
     * 核心：只要 prices[i] > prices[i-1]，就累加差值
     *       等价于：所有上涨交易日利润之和 = 最大利润
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // 今天比昨天贵，昨买今卖，累加利润
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
