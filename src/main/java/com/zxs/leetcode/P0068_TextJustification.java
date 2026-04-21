package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【P0068】文本左右对齐
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0068_TextJustification {

    /**
     * 题目描述：
     * 给定一个单词数组和一个最大行宽 maxWidth，按左右对齐的规则排版文本。
     * 规则：
     * 1. 每行应尽可能多地放置单词；
     * 2. 除最后一行外，行中的单词之间应插入尽可能多的空格；
     * 3. 行中的空格应均匀分布。若一行中的空格不能均匀分布，则左侧的空格必须比右侧多；
     * 4. 最后一行的单词之间只插入一个空格，其余空格均匀放在句末。
     *
     * 示例：
     * 输入：words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
     * 输出：[
     *        "This    is    an example  of text justification.",
     *        "is   to  lemonade  to  be and  to   palindrome"
     *      ]
     *
     * 约束：
     * 1 <= words.length <= 300
     * 1 <= maxWidth <= 25
     *
     * 解题思路：
     * 贪心分词 + 按规则构造每行 —— 【推荐】O(n)时间
     * 关键：计算每行能放多少个单词，然后根据是否最后一行按不同规则分配空格。
     */
    public List<String> fullJustify(List<String> words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.size();
        int i = 0;

        while (i < n) {
            // 第一步：贪心选取当前行能放下的单词
            int totalLen = 0;      // 单词总长度（不含空格）
            int j = i;
            while (j < n && totalLen + words.get(j).length() + (j - i) <= maxWidth) {
                totalLen += words.get(j).length();
                j++;
            }
            // [i, j) 为当前行单词，j-i 个单词

            // 第二步：构造当前行
            StringBuilder line = new StringBuilder();
            int wordCount = j - i;
            int spaceCount = maxWidth - totalLen; // 当前行总空格数

            if (j == n) {
                // 最后一行：单词左对齐，词间1空格，末尾补空格
                for (int k = i; k < j; k++) {
                    line.append(words.get(k));
                    if (k < j - 1) line.append(' ');
                }
                while (line.length() < maxWidth) line.append(' ');
            } else {
                // 非最后一行：均匀分配空格
                // 平均空格 = spaceCount / (wordCount - 1)，多余空格给左边
                int avgSpace = wordCount > 1 ? spaceCount / (wordCount - 1) : spaceCount;
                int extraSpace = wordCount > 1 ? spaceCount % (wordCount - 1) : 0;

                for (int k = i; k < j; k++) {
                    line.append(words.get(k));
                    if (k < j - 1) {
                        // 基础空格 + 可能的额外空格
                        int spaces = avgSpace + (extraSpace-- > 0 ? 1 : 0);
                        for (int s = 0; s < spaces; s++) line.append(' ');
                    }
                }
            }
            result.add(line.toString());
            i = j;
        }
        return result;
    }

    public static void main(String[] args) {
        P0068_TextJustification solution = new P0068_TextJustification();
        List<String> words = java.util.Arrays.asList(
            "This", "is", "an", "example", "of", "text", "justification."
        );
        List<String> lines = solution.fullJustify(words, 16);
        for (String line : lines) {
            System.out.println("\"" + line + "\"");
        }
    }
}
