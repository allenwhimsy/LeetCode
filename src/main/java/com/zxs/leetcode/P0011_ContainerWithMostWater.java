package com.zxs.leetcode;

/**
 * 【LeetCode 第11题】盛最多水的容器
 *
 * <p>题目描述：
 * 给定一个长度为 n 的整数数组 height，有 n 条垂线。
 * 第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器能够容纳最多的水。
 *
 * <p>示例：
 * 输入: height = [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * 解释: 两条线 indices 1 和 8，容器面积 = min(8,7) * 8 = 49
 *
 * <p>约束条件：
 * - n == height.length
 * - 2 <= n <= 10^5
 * - 0 <= height[i] <= 10^4
 *
 * <p>解题思路：
 *
 * 【方法一】双指针（推荐）⭐
 * - 左指针从0开始，右指针从末尾开始
 * - 每次移动较矮一侧的指针（因为高度由短板决定）
 * - 移动矮侧才有可能找到更大的面积（宽度减小时需提高高度才可能弥补）
 * - 时间复杂度: O(n)，空间复杂度: O(1)
 *
 * 【方法二】暴力枚举（不推荐）
 * - 枚举所有两两组合
 * - 时间复杂度: O(n^2)，无法处理大规模数据
 *
 * @Author 郑晓胜
 */
public class P0011_ContainerWithMostWater {

    /**
     * 【方法一】双指针（推荐）⭐
     *
     * 核心思想（对撞指针）：
     * - 初始时 left=0, right=n-1，此时宽度最大
     * - 面积 = min(height[left], height[right]) * (right - left)
     * - 关键洞察：若移动较高端，宽度减小而高度不会增加，面积必然变小
     *             所以只有移动较矮侧，才有可能找到更大的面积
     * - 每步都计算当前面积并更新最大值
     *
     * 为什么移动矮侧？
     * 假设 left < right，面积为 A = left * (right-left)
     * - 移动 right（高侧）：新面积 <= 当前 left * (right-left-1) < A
     * - 移动 left（矮侧）：新面积有可能 > A（如果找到更高的 left）
     *
     * @param height 每条垂线的高度数组
     * @return 能容纳的最大水量
     */
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // 计算当前容器面积
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, width * h);

            // 移动较矮一侧的指针
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * 【方法二】暴力枚举（不推荐，仅作对比）
     * 时间复杂度: O(n^2)，空间复杂度: O(1)
     */
    public static int maxAreaBrute(int[] height) {
        int maxArea = 0;
        int n = height.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        test(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}, 49);
        test(new int[]{1, 1}, 1);
        test(new int[]{4, 3, 2, 1, 4}, 16);
        test(new int[]{1, 2, 1}, 2);
        test(new int[]{2, 3, 10, 5, 2, 8, 6}, 49);
        test(new int[]{1, 8, 6, 2, 5, 4, 8, 25, 7}, 56);
    }

    private static void test(int[] height, int expected) {
        int result = maxArea(height);
        int resultBrute = maxAreaBrute(height);
        System.out.printf("输入: %s%n", java.util.Arrays.toString(height));
        System.out.printf("  双指针: %d (期望: %d) %s%n",
                result, expected, result == expected ? "✓" : "✗");
        System.out.printf("  暴力枚举: %d %s%n",
                resultBrute, resultBrute == expected ? "✓" : "✗");
    }
}
