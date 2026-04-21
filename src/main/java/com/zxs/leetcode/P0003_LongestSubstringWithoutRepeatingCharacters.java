package com.zxs.leetcode;

/**
 * 【LeetCode 第3题】无重复字符的最长子串
 *
 * <p>题目描述：
 * 给定一个字符串 s，请你找出其中不含有重复字符的最长子串的长度。
 *
 * <p>示例：
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 答案 是 "abc"，长度为 3
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 答案 是 "b"，长度为 1
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 答案 是 "wke"，长度为 3（注意答案必须是一个子串，"pwke" 是子序列而非子串）
 *
 * <p>约束条件：
 * - 0 <= s.length <= 5 * 10^4
 * - s 由英文字母、数字、符号和空格组成
 *
 * <p>解题思路：
 *
 * 【方法一】滑动窗口 + 哈希表（推荐）⭐
 * - 使用左右指针维护一个无重复字符的窗口
 * - 右指针扩展时，用哈希表记录字符最后出现的位置
 * - 遇到重复字符时，左指针直接跳到重复字符的下一个位置
 * - 时间复杂度: O(n)，空间复杂度: O(min(m, σ)) 其中m为字符集大小
 *
 * 【方法二】滑动窗口 + 集合
 * - 使用 HashSet 存储当前窗口的字符
 * - 收缩窗口直到没有重复
 * - 时间复杂度: O(2n) = O(n)，空间复杂度: O(min(m, σ))
 *
 * 【方法三】优化的滑动窗口（桶思想）
 * - 使用数组替代哈希表，性能更优
 * - 适合字符集较小的场景
 *
 * @Author 郑晓胜
 */
public class P0003_LongestSubstringWithoutRepeatingCharacters {

    /**
     * 【方法一】滑动窗口 + 哈希表（推荐）⭐
     * 右指针不断扩展，遇到重复字符时左指针收缩
     *
     * @param s 输入字符串
     * @return 无重复字符的最长子串长度
     */
    public static int lengthOfLongestSubstring(String s) {
        // 边界情况
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // 哈希表：存储字符 -> 最后出现的位置（索引）
        java.util.HashMap<Character, Integer> map = new java.util.HashMap<>();

        int maxLen = 0;           // 最大长度
        int left = 0;            // 左指针

        // 遍历字符串，右指针逐个扩展
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            // 如果当前字符已存在于窗口中，需要收缩左指针
            // 取 max 是因为 left 可能已经跳到了更后面的位置
            if (map.containsKey(ch)) {
                // left 跳到重复字符的下一位
                // 但不能往回跳，所以取 max(left, map.get(ch) + 1)
                left = Math.max(left, map.get(ch) + 1);
            }

            // 更新字符最后出现的位置
            map.put(ch, right);

            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    /**
     * 【方法二】滑动窗口 + 数组（字符集优化）
     * 适用于 ASCII 字符集，用数组替代哈希表
     */
    public static int lengthOfLongestSubstringArray(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // ASCII 字符共128个，用数组记录最后出现位置
        int[] lastSeen = new int[128];
        java.util.Arrays.fill(lastSeen, -1);

        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            int ascii = (int) ch;

            // 如果字符在当前窗口[left, right)中已出现
            if (lastSeen[ascii] >= left) {
                left = lastSeen[ascii] + 1;
            }

            lastSeen[ascii] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    /**
     * 【方法三】暴力枚举（不推荐，仅作对比）
     * 枚举所有子串并检查是否有重复字符
     * 时间复杂度: O(n^3)，会超时
     */
    public static int lengthOfLongestSubstringBrute(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            java.util.Set<Character> seen = new java.util.HashSet<>();
            for (int j = i; j < n; j++) {
                if (seen.contains(s.charAt(j))) {
                    break;  // 发现重复，停止扩展
                }
                seen.add(s.charAt(j));
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }

        return maxLen;
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        // 示例1
        test("abcabcbb", 3);

        // 示例2
        test("bbbbb", 1);

        // 示例3
        test("pwwkew", 3);

        // 额外测试
        test("", 0);
        test(" ", 1);
        test("au", 2);
        test("dvdf", 3);
    }

    private static void test(String s, int expected) {
        int result = lengthOfLongestSubstring(s);
        System.out.printf("输入: \"%s\" -> 输出: %d (期望: %d) %s%n",
                s, result, expected, result == expected ? "✓" : "✗");

        // 方法对比
        int resultArray = lengthOfLongestSubstringArray(s);
        System.out.printf("  [数组优化版]: %d %s%n", resultArray, resultArray == expected ? "✓" : "✗");
    }
}
