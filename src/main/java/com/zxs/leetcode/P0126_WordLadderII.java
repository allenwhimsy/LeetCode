package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/word-ladder-ii/
 *
 * 题目：单词接龙 II（Word Ladder II）
 *
 * 按如下字典 wordList ，从 beginWord 到 endWord 转换。
 * 一次转换是指改变一个字母，变换后得到的目标单词必须在字典中。
 * 所有单词长度相同，字典中不包含重复的单词。
 * 找出所有从 beginWord 到 endWord 的最短转换序列。
 *
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog",
 *       wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 *
 * 约束：
 * - 1 <= beginWord.length <= 10
 * - wordList.length >= 1 且不包含重复单词
 * - 1 <= wordList[i].length <= 10
 * - beginWord, wordList[i], endWord 均由小写字母组成
 *
 * 解题思路：
 * 方法一：BFS 建图 + DFS 回溯（推荐）
 *   - 第一步 BFS：从 beginWord 出发，找到所有到 endWord 的最短路径长度
 *     同时记录每层可达的单词
 *   - 第二步 DFS：从 beginWord 回溯，构造所有最短路径
 *   - 时间 O(N * L^2) 其中 N 为单词数，L 为单词长度，空间 O(N*L)
 *
 * 方法二：双向 BFS
 *   - 从两端同时搜索，减小搜索空间
 *   - 较为复杂
 *
 * 推荐方法：BFS 建图 + DFS 回溯
 */
public class P0126_WordLadderII {

    /**
     * 【推荐解法】BFS 建图 + DFS 回溯
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return result;

        // BFS：构建每层单词的父子关系
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        boolean found = false;

        // key: 当前单词, value: 其父单词列表（可能有多个父节点）
        java.util.Map<String, List<String>> parents = new java.util.HashMap<>();

        while (!found && !visited.isEmpty()) {
            Set<String> nextLevel = new HashSet<>();
            for (String word : visited) {
                parents.put(word, new ArrayList<>());
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char original = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        chars[i] = c;
                        String newWord = new String(chars);
                        if (wordSet.contains(newWord)) {
                            if (!visited.contains(newWord)) {
                                nextLevel.add(newWord);
                            }
                            parents.get(word).add(newWord);
                            if (newWord.equals(endWord)) found = true;
                        }
                    }
                    chars[i] = original;
                }
            }
            visited = nextLevel;
        }

        if (!found) return result;

        // DFS 回溯构造路径
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(endWord, beginWord, parents, path, result);
        return result;
    }

    private void dfs(String word, String beginWord,
                    java.util.Map<String, List<String>> parents,
                    List<String> path, List<List<String>> result) {
        if (word.equals(beginWord)) {
            List<String> reversed = new ArrayList<>(path);
            java.util.Collections.reverse(reversed);
            result.add(reversed);
            return;
        }
        for (String parent : parents.get(word)) {
            path.add(parent);
            dfs(parent, beginWord, parents, path, result);
            path.remove(path.size() - 1);
        }
    }
}
