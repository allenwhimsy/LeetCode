package com.zxs.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/word-ladder/
 *
 * 题目：单词接龙（Word Ladder）
 *
 * 按如下字典 wordList ，从 beginWord 到 endWord 转换。
 * 一次转换是指改变一个字母，变换后得到的目标单词必须在字典中。
 * 返回从 beginWord 到 endWord 的最短转换序列的 长度。
 *
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog",
 *       wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：最短变换序列为 "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 *       返回序列长度 5
 *
 * 约束：
 * - 1 <= beginWord.length <= 10
 * - wordList.length >= 1
 * - 1 <= wordList[i].length <= 10
 * - 单词仅由小写字母组成
 *
 * 解题思路：
 * 方法一：BFS 逐层遍历（推荐）
 *   - 从 beginWord 出发，每次改变一个字符，找到字典中匹配的单词
 *   - 被访问过的单词从字典中移除（防止重复访问）
 *   - 找到 endWord 即为最短路径
 *   - 时间 O(N * L^2)，空间 O(N * L)
 *
 * 推荐方法：BFS 层序遍历
 */
public class P0127_WordLadder {

    /**
     * 【推荐解法】BFS 层序遍历
     * 核心：逐层展开，一旦遇到 endWord 即为最短路径
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordSet.remove(beginWord); // 防止回退

        int steps = 1; // beginWord 本身算第1步

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                // 检查是否到达 endWord
                if (word.equals(endWord)) return steps;

                // 尝试改变 word 的每个字符
                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[j] = c;
                        String newWord = new String(chars);
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord); // 移除防止重复
                        }
                    }
                    chars[j] = original;
                }
            }
            steps++;
        }
        return 0;
    }
}
