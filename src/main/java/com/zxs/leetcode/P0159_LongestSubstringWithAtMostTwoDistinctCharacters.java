package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 159. 至多包含两个不同字符的最长子串
 *
 * 给你一个字符串 s ，请你找出 至多 包含两个不同字符的最长子串 c 的长度。
 *
 * 示例 1：
 * 输入: s = "eceba"
 * 输出: 3
 * 解释: 子串 "ece" 包含两个不同字符 'e' 和 'c'
 *
 * 示例 2：
 * 输入: s = "ccaabbb"
 * 输出: 5
 * 解释: 子串 "aabbb" 包含两个不同字符 'a' 和 'b'
 *
 * 示例 3：
 * 输入: s = "a"
 * 输出: 1
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 由英文字母、数字、符号和空格组成
 *
 * 解题思路：
 * 方法一：滑动窗口 + 哈希表（推荐）
 * 用 HashMap 记录每个字符最后出现的位置
 * 维护一个窗口 [left, right]，右指针不断右移
 * 当窗口中不同字符超过2个时，收缩左指针
 * 用 maxLength 记录最大长度
 * 时间复杂度 O(n)，空间复杂度 O(1)（字符集有限，最多256个）
 *
 * 方法二：双指针 + 计数数组
 * 用数组代替 HashMap，效率更高
 * 时间复杂度 O(n)，空间复杂度 O(256)=O(1)
 */
import java.util.*;

public class P0159_LongestSubstringWithAtMostTwoDistinctCharacters {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.isEmpty()) return 0;
        if (s.length() <= 2) return s.length();

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            // 收缩左指针直到窗口内只有2个不同字符
            while (map.size() > 2) {
                char leftChar = s.charAt(left);
                int count = map.get(leftChar);
                if (count == 1) {
                    map.remove(leftChar);
                } else {
                    map.put(leftChar, count - 1);
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        P0159_LongestSubstringWithAtMostTwoDistinctCharacters solution = new P0159_LongestSubstringWithAtMostTwoDistinctCharacters();
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("eceba"));   // 3
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("ccaabbb"));  // 5
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("a"));        // 1
    }
}
