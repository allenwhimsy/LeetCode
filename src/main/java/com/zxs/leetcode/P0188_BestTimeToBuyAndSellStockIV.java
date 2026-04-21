package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 188. 买卖股票的最佳时机 IV
 *
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1：
 * 输入: k = 2, prices = [2,4,1]
 * 输出: 2
 * 解释: 在第 1 天（股票价格 = 2）买入，第 2 天（股票价格 = 4）卖出，利润 = 2
 *
 * 示例 2：
 * 输入: k = 2, prices = [3,2,6,5,0,3]
 * 输出: 7
 * 解释:
 * 买入 (day 1 = 2), 卖出 (day 2 = 6), 利润 = 4
 * 再买入 (day 3 = 0), 卖出 (day 4 = 3), 利润 = 3
 *
 * 提示：
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 *
 * 解题思路：
 * 方法一：动态规划（推荐）
 * 状态：dp[i][j][k] = 第 i 天，进行了 j 笔交易，持有(k=0)或不持有(k=1)股票的最大利润
 * 转移：
 *   dp[i][j][0] = max(dp[i-1][j][0], dp[i-1][j][1] + prices[i])  // 保持/卖出
 *   dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i]) // 保持/买入
 * 边界：dp[0][j][0]=0, dp[0][j][1]=-prices[0]
 * 时间复杂度 O(n*k)，空间复杂度 O(k)（滚动数组优化）
 *
 * 方法二：贪心（适用于 k >= n/2 的情况）
 * 所有正收益的交易相加
 */
public class P0188_BestTimeToBuyAndSellStockIV {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) return 0;
        int n = prices.length;

        // k >= n/2 时，退化为无限次交易
        if (k >= n / 2) {
            int profit = 0;
            for (int i = 1; i < n; i++) {
                profit += Math.max(0, prices[i] - prices[i - 1]);
            }
            return profit;
        }

        // 动态规划：滚动数组优化空间
        int[] buy = new int[k + 1];  // 持有股票的最大利润
        int[] sell = new int[k + 1]; // 不持有股票的最大利润

        for (int i = 0; i <= k; i++) {
            buy[i] = -prices[0];
            sell[i] = 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                // 持有股票：之前持有 or 今日买入（使用 sell[j-1] - prices[i]）
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                // 不持有股票：之前不持有 or 今日卖出（使用 buy[j] + prices[i]）
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        return sell[k];
    }

    public static void main(String[] args) {
        P0188_BestTimeToBuyAndSellStockIV solution = new P0188_BestTimeToBuyAndSellStockIV();
        System.out.println(solution.maxProfit(2, new int[]{2, 4, 1}));              // 2
        System.out.println(solution.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));    // 7
        System.out.println(solution.maxProfit(1, new int[]{3, 2, 6, 5, 0, 3}));    // 6
    }
}
