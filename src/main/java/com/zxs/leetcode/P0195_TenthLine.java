package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 195. 第十行
 *
 * Shell 题目：使用 Java 读取文件的第十行
 *
 * 题目描述：
 * 给定一个文件 file.txt，取其第十行并输出。
 * 假设文件至少有 10 行。
 *
 * Bash 原句：
 * sed -n '10p' file.txt
 * 或
 * awk 'NR==10' file.txt
 *
 * 提示：
 * 如果文件少于10行，应输出空
 *
 * 解题思路：
 * 方法一：流式读取（推荐）
 * 按行读取文件，当行号为10时输出并停止
 * 使用 BufferedReader 逐行读取
 * 时间复杂度 O(n)，空间复杂度 O(1)
 *
 * 方法二：全量读取
 * 将所有行存入数组，按索引取第10行
 * 时间复杂度 O(n)，空间复杂度 O(n)
 */
import java.io.*;
import java.util.*;

public class P0195_TenthLine {

    /**
     * 读取文件的第十行（行号从1开始计数）
     * @param content 文件内容（多行）
     * @return 第十行内容，不存在则返回 null
     */
    public String tenthLine(String content) {
        if (content == null || content.isEmpty()) return null;
        String[] lines = content.split("\n");
        if (lines.length < 10) return null;
        return lines[9];  // 数组下标从0开始
    }

    /**
     * 流式读取文件的第十行（内存更省）
     */
    public String tenthLineStream(String content) {
        BufferedReader reader = new BufferedReader(new StringReader(content));
        String line;
        int lineNumber = 0;
        try {
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (lineNumber == 10) {
                    return line;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return null;  // 文件少于10行
    }

    public static void main(String[] args) {
        String content = "Line 1\nLine 2\nLine 3\nLine 4\nLine 5\n" +
                         "Line 6\nLine 7\nLine 8\nLine 9\nLine 10\n" +
                         "Line 11\nLine 12";
        P0195_TenthLine solution = new P0195_TenthLine();
        System.out.println(solution.tenthLine(content));         // Line 10
        System.out.println(solution.tenthLineStream(content));   // Line 10
    }
}
