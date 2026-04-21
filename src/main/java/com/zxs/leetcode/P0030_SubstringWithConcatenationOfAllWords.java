package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 0030 - 串联所有单词的子串 (Substring with Concatenation of All Words)
 *
 * 【题目描述】
 * 给定一个字符串 s 和一个字符串数组 words。words 中所有字符串长度相同。
 * s 中的一个串联子串是指一个包含 words 中所有字符串以任意顺序排列连接起来的子串。
 * 返回所有串联子串在 s 中的起始索引。
 *
 * 示例 1：输入 s = "barfoothefoobarman", words = ["foo","bar"] → 输出 [0,9]
 * 示例 2：输入 s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"] → 输出 []
 *
 * 约束：1 <= s.length <= 10^4, 1 <= words.length <= 5000, 1 <= words[i].length <= 30
 *
 * 【解题思路】
 * 滑动窗口 + 哈希表（O(n * m * wordLen) 时间，O(k * wordLen) 空间）✅ 推荐
 *   将 words 中每个词的出现次数记入哈希表。由于每个词长度相同，可以在 s 上以 wordLen 为步长滑动窗口。
 *   为处理偏移问题，需对 0 ~ wordLen-1 每种起始偏移分别处理。
 *
 * @Author 郑晓胜
 */
public class P0030_SubstringWithConcatenationOfAllWords {

    /**
     * 滑动窗口 + 哈希表（推荐）
     * 时间复杂度：O(n * m * wordLen)
     * 空间复杂度：O(k * wordLen)
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;

        if (s.length() < totalLen) return result;

        // 记录 words 中每个词的频率
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // 对每种起始偏移分别处理
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int count = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                String word = s.substring(j, j + wordLen);

                if (wordCount.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;

                    // 当前词出现次数超过 words 中的次数，收缩左边界
                    while (seen.get(word) > wordCount.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 找到一个合法的串联子串
                    if (count == numWords) {
                        result.add(left);
                        // 左边界右移，继续搜索
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    // 遇到不在 words 中的词，重置窗口
                    seen.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return result;
    }
}
