package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * 题目：买卖股票的最佳时机 III（Best Time to Buy and Sell Stock III）
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你能获取的最大利润。你最多可以完成 两笔 交易。
 * 你不能同时参与多笔交易（必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1：
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第4天（price=0）买入，第6天（price=3）卖出，利润=3
 *       在第7天（price=1）买入，第8天（price=4）卖出，利润=3
 *       总利润 = 6
 *
 * 约束：
 * - 1 <= prices.length <= 10^5
 * - 0 <= prices[i] <= 10^4
 *
 * 解题思路：
 * 方法一：动态规划（推荐）
 *   - 四状态 DP：
 *     buy1：第一次买入后的最大利润
 *     sell1：第一次卖出后的最大利润
 *     buy2：第二次买入后的最大利润
 *     sell2：第二次卖出后的最大利润
 *   - 空间优化：4个变量即可，时间 O(n)，空间 O(1)
 *
 * 方法二：枚举分割点
 *   - 以第 i 天为分割点，左边最多一次交易，右边最多一次交易
 *   - 时间 O(n)，空间 O(n)
 *
 * 推荐方法：四状态 DP，O(1) 空间
 */
public class P0123_BestTimeToBuyAndSellStockIII {

    /**
     * 【推荐解法】四状态 DP
     * 核心：依次经历 买1→卖1→买2→卖2，每个状态取最优
     */
    public int maxProfit(int[] prices) {
        // buy1: 第一次买入后手中剩余的最大现金（负数表示已支出）
        // sell1: 第一次卖出后手中剩余的最大现金
        // buy2: 第二次买入后手中剩余的最大现金
        // sell2: 第二次卖出后手中剩余的最大现金
        int buy1 = -prices[0];
        int sell1 = 0;
        int buy2 = -prices[0];
        int sell2 = 0;

        for (int i = 1; i < prices.length; i++) {
            // 第一次买入：max(之前买入, 今天买入)
            buy1 = Math.max(buy1, -prices[i]);
            // 第一次卖出：max(之前卖出, 今天卖出)
            sell1 = Math.max(sell1, buy1 + prices[i]);
            // 第二次买入：max(之前第二次买入, 今天作为第二次买入)
            buy2 = Math.max(buy2, sell1 - prices[i]);
            // 第二次卖出：max(之前第二次卖出, 今天作为第二次卖出)
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
