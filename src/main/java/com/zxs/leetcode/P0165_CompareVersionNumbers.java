package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 165. 比较版本号
 *
 * 给你两个版本号字符串 version1 和 version2，请你比较它们。
 * 版本号由被点 '.' 分隔的修订号组成。当修订号相等时，省略前置零。
 * 比较规则如下：
 * - 从左到右依次比较修订号
 * - 修订号默认填充为 0
 * - 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1
 *
 * 示例 1：
 * 输入: version1 = "1.01", version2 = "1.001"
 * 输出: 0
 * 解释: 忽略前导零，都等于 "1" 和 "1"
 *
 * 示例 2：
 * 输入: version1 = "1.0.1", version2 = "1"
 * 输出: 1
 *
 * 示例 3：
 * 输入: version1 = "0.0.0", version2 = "0.0.0"
 * 输出: 0
 *
 * 提示：
 * 1 <= version1.length, version2.length <= 500
 * version1 和 version2 只包含数字和 '.'
 * version1 和 version2 都是有效的版本号
 * 各个修订号以无符号整数表示，不含前导零
 *
 * 解题思路：
 * 方法一：双指针分割（推荐）
 * 按 '.' 分割版本号为修订号数组
 * 从左到右依次比较对应修订号（需要转为整数去除前导零影响）
 * 较短的版本号在末尾填充 0
 * 时间复杂度 O(n)，空间复杂度 O(n)
 *
 * 方法二：一次遍历
 * 同时遍历两个版本号，每次取一个修订号进行比较
 * 更节省空间
 */
public class P0165_CompareVersionNumbers {

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int maxLen = Math.max(v1.length, v2.length);
        for (int i = 0; i < maxLen; i++) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (num1 > num2) return 1;
            if (num1 < num2) return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        P0165_CompareVersionNumbers solution = new P0165_CompareVersionNumbers();
        System.out.println(solution.compareVersion("1.01", "1.001"));  // 0
        System.out.println(solution.compareVersion("1.0.1", "1"));     // 1
        System.out.println(solution.compareVersion("0.0.0", "0.0.0"));  // 0
    }
}
