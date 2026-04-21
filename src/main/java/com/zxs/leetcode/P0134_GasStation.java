package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/gas-station/
 *
 * 题目：加油站（Gas Station）
 *
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有 gas[i] 升油。
 * 你有一辆油箱容量无限大的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗 cost[i] 升油。
 * 请你选择开始的加油站编号，使得能够走完一圈返回该加油站。
 * 如果不存在这样的起始站点，返回 -1 。
 *
 * 示例 1：
 * 输入：gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出：3
 * 解释：从第3站（index=3）出发，可获得 4 升油，花费 2 升，剩余 2 升
 *       开到第4站：获得 5 升，花费 1 升，剩余 6 升... 成功返回起点
 *
 * 约束：
 * - gas.length == n
 * - cost.length == n
 * - 1 <= n <= 10^5
 * - 0 <= gas[i], cost[i] <= 10^4
 *
 * 解题思路：
 * 方法一：贪心（推荐）
 *   - 若 totalGas >= totalCost，则必有解（LeetCode 定理）
 *   - 若从 i 出发无法到达 j，则 i..j-1 都不是解，起始点设为 j+1
 *   - 时间 O(n)，空间 O(1)
 *
 * 方法二：暴力 O(n^2)
 *   - 从每个点尝试，O(n^2) 超时
 *
 * 推荐方法：贪心，O(n) 时间复杂度
 */
public class P0134_GasStation {

    /**
     * 【推荐解法】贪心
     * 关键定理：
     * 1. 若 totalGas >= totalCost，则必然存在解
     * 2. 若从 i 出发无法到达 j，则 i..j-1 都不可能是起点
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;     // 全局油量差
        int totalCost = 0;
        int currentGas = 0;   // 从起点出发当前剩余油量
        int start = 0;        // 候选起点

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            currentGas += gas[i] - cost[i];

            // 从 start 无法到达 i
            if (currentGas < 0) {
                start = i + 1; // 下一个加油站作为新起点
                currentGas = 0;
            }
        }

        // 总油量 >= 总消耗，必然有解
        return totalGas >= totalCost ? start : -1;
    }
}
