package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 187. 重复的 DNA 序列
 *
 * 给定一个字符串 s 表示 DNA 序列，每个字符都是 'A', 'C', 'G', 'T' 之一。
 * 返回在 DNA 序列中出现了不只一次的 长度为 10 的子序列（子串）。
 *
 * 示例 1：
 * 输入: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出: ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 * 示例 2：
 * 输入: s = "AAAAAAAAAAAAA"
 * 输出: ["AAAAAAAAAA"]
 *
 * 提示：
 * 0 <= s.length <= 10^5
 *
 * 解题思路：
 * 方法一：滑动窗口 + 哈希表（推荐）
 * 维护一个长度为10的滑动窗口
 * 用 HashMap 统计每个长度为10的子串出现次数
 * 最后筛选出出现次数 > 1 的子串
 * 时间复杂度 O(n)，空间复杂度 O(n)
 *
 * 方法二：位编码优化
 * 将 ACGT 编码为 2 位二进制数（00,01,10,11），将字符串压缩为整数
 * 用整数的 HashMap 代替字符串 HashMap
 * 时间复杂度 O(n)，空间复杂度 O(n)，但常数更小
 */
public class P0187_RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) return result;

        Map<String, Integer> countMap = new HashMap<>();
        int windowSize = 10;

        // 滑动窗口
        for (int i = 0; i <= s.length() - windowSize; i++) {
            String sub = s.substring(i, i + windowSize);
            countMap.put(sub, countMap.getOrDefault(sub, 0) + 1);
        }

        // 筛选出现次数 > 1 的子串
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    // 方法二：位编码优化（更高效）
    public List<String> findRepeatedDnaSequencesBit(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) return result;

        // ACGT -> 2位编码
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 0);  // 00
        map.put('C', 1); // 01
        map.put('G', 2); // 10
        map.put('T', 3); // 11

        Set<Integer> seen = new HashSet<>();
        Set<Integer> repeated = new HashSet<>();

        int mask = 0x7FFFFFF;  // 低27位掩码（10个字符 * 3位）
        int hash = 0;

        for (int i = 0; i < s.length(); i++) {
            hash = ((hash << 3) & mask) | map.get(s.charAt(i));
            if (i >= 9) {  // 窗口满了
                if (!seen.add(hash)) {
                    repeated.add(hash);
                }
            }
        }

        // 从 repeated 的 hash 值还原子串（按原始位置）
        for (int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            int h = 0;
            for (int j = 0; j < 10; j++) {
                h = ((h << 3) & mask) | map.get(sub.charAt(j));
            }
            if (repeated.contains(h)) {
                result.add(sub);
                repeated.remove(h);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P0187_RepeatedDNASequences solution = new P0187_RepeatedDNASequences();
        System.out.println(solution.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(solution.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
    }
}
