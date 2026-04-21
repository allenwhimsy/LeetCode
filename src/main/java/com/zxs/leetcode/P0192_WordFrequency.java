package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 192. 统计词频
 *
 * Shell 题目：使用 Java 实现相同的词频统计功能
 *
 * 题目描述：
 * 编写一个 bash 脚本来计算一个文本文件中每个单词出现的次数。
 * 原始 Bash 解法：
 * cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -r | awk '{print $2, $1}'
 *
 * 假设 words.txt 内容示例：
 * the day is sunny the the
 * the sunny is is
 *
 * 统计结果应为（按频率降序）：
 * the 4
 * is 3
 * sunny 2
 * day 1
 *
 * 提示：
 * 只需要考虑空格分隔的单词
 * 忽略大小写
 * 单词之间有多个空格
 *
 * 解题思路：
 * 方法一：HashMap 计数（推荐）
 * 按空格分割单词，统计每个单词出现次数
 * 用 StringBuilder 拼接结果，按频率降序输出
 * 时间复杂度 O(n log n)，空间复杂度 O(n)
 */
import java.util.*;

public class P0192_WordFrequency {

    /**
     * 统计文件中每个单词出现的次数，按频率降序输出
     * @param content 文件内容
     * @return 每行格式为 "word count"
     */
    public String wordFrequency(String content) {
        // 1. 预处理：按空格分割，转小写
        String[] words = content.trim().split("\\s+");
        Map<String, Integer> countMap = new HashMap<>();

        for (String word : words) {
            String w = word.toLowerCase();
            countMap.put(w, countMap.getOrDefault(w, 0) + 1);
        }

        // 2. 按频率降序排序
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(countMap.entrySet());
        entries.sort((a, b) -> b.getValue().compareTo(a.getValue()));  // 降序

        // 3. 拼接输出
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : entries) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Bash 等效命令解释：
     * cat words.txt          # 读取文件
     * tr -s ' ' '\n'          # 将空格替换为换行，并压缩连续空格
     * sort                    # 排序
     * uniq -c                 # 统计相邻相同行的次数
     * sort -r                 # 按数字降序
     * awk '{print $2, $1}'    # 交换列：单词在前，频次在后
     */

    public static void main(String[] args) {
        String content = "the day is sunny the the\nthe sunny is is";
        P0192_WordFrequency solution = new P0192_WordFrequency();
        System.out.println(solution.wordFrequency(content));
        // 输出:
        // the 4
        // is 3
        // sunny 2
        // day 1
    }
}
