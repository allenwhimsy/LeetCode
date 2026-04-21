package com.zxs.leetcode;

import java.util.*;

/**
 * P0049 字母异位词分组
 *
 * 题目描述：
 * 给你一个字符串数组 strs，按字母异位词的要求分组。
 * 字母异位词是指由相同字母重排列形成的字符串（包括顺序不同的同一字符串）。
 * 返回所有分组，按任意顺序返回。
 *
 * 示例 1：
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]
 *
 * 示例 2：
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3：
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * 约束条件：
 * - 1 <= strs.length <= 10^4
 * - 0 <= strs[i].length <= 100
 * - strs[i] 由小写英文字母组成
 *
 * 解题思路：
 * 【方法一：排序字符串作为 key（推荐）】O(n * k log k) 时间
 * 将每个字符串排序后的结果作为哈希表的 key，字母异位词会得到相同的 key。
 *
 * 【方法二：字符计数作为 key】O(n * k) 时间，O(n * k) 空间
 * 用 int[26] 统计每个字符串中各字符出现次数，转为字符串作为 key。
 *
 * @Author 郑晓胜
 */
public class P0049_GroupAnagrams {

    /**
     * 方法一：排序字符串作为 key（推荐）- 实现简单，逻辑清晰
     */
    public List<List<String>> groupAnagrams_sort(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        // key: 排序后的标准形式，value: 同组的原字符串列表
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // 将字符串转为字符数组并排序，作为 key
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 方法二：字符计数作为 key - O(n * k) 更优
     * 适用于字符串较长的场景
     */
    public List<List<String>> groupAnagrams_count(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            // 用 int[26] 统计字符频率
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            // 将计数数组转为 key（如 "aabbc" -> "#1#2#1#0#..."）
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                key.append('#').append(count[i]);
            }

            map.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    // ==================== 简单测试 ====================
    public static void main(String[] args) {
        P0049_GroupAnagrams solution = new P0049_GroupAnagrams();

        String[][] testss = {
            {"eat", "tea", "tan", "ate", "nat", "bat"},
            {""},
            {"a"}
        };

        for (String[] strs : testss) {
            List<List<String>> result = solution.groupAnagrams_sort(strs);
            System.out.println("输入: " + Arrays.toString(strs));
            System.out.println("分组结果:");
            for (List<String> group : result) {
                System.out.println("  " + group);
            }
            System.out.println();
        }
    }
}
