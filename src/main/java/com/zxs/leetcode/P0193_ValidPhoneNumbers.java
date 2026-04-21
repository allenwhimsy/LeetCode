package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 193. 有效电话号码
 *
 * Shell 题目：使用 Java 实现有效的电话号码验证
 *
 * 题目描述：
 * 给定一个文本文件 file.txt，其中每行包含一个电话号码，
 * 筛选出符合以下格式的有效电话号码：
 * - 格式一：(xxx) xxx-xxxx
 * - 格式二：xxx-xxx-xxxx
 * (xxx) xxx-xxxx 格式也符合，即区号部分用括号括起来
 *
 * Bash 原句（grep）：
 * grep -E '^([0-9]{3}-|\\([0-9]{3}\\) )[0-9]{3}-[0-9]{4}$' file.txt
 *
 * 假设 file.txt 内容示例：
 * 987-123-4567
 * 123 456 7890
 * (123) 456-7890
 * 123.456.7890
 * 123-4567-890
 *
 * 输出应为：
 * 987-123-4567
 * (123) 456-7890
 *
 * 解题思路：
 * 方法一：正则表达式（推荐）
 * 模式一：xxx-xxx-xxxx -> ^[0-9]{3}-[0-9]{3}-[0-9]{4}$
 * 模式二：(xxx) xxx-xxxx -> ^\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}$
 * 用 | 连接两个模式
 * 时间复杂度 O(n)，空间复杂度 O(1)
 *
 * 方法二：手动解析
 * 逐行检查每种格式的字符是否合法
 */
public class P0193_ValidPhoneNumbers {

    /**
     * 从文本中筛选出符合格式的电话号码
     * @param lines 每行文本
     * @return 有效的电话号码列表
     */
    public List<String> validPhoneNumbers(String[] lines) {
        List<String> result = new ArrayList<>();

        // 正则表达式：
        // ^([0-9]{3}-|\\([0-9]{3}\\) )[0-9]{3}-[0-9]{4}$
        // 模式一：xxx-xxx-xxxx
        // 模式二：(xxx) xxx-xxxx
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
            "^([0-9]{3}-|\\([0-9]{3}\\) )[0-9]{3}-[0-9]{4}$"
        );

        for (String line : lines) {
            java.util.regex.Matcher matcher = pattern.matcher(line.trim());
            if (matcher.matches()) {
                result.add(line.trim());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] lines = {
            "987-123-4567",
            "123 456 7890",
            "(123) 456-7890",
            "123.456.7890",
            "123-4567-890"
        };
        P0193_ValidPhoneNumbers solution = new P0193_ValidPhoneNumbers();
        for (String p : solution.validPhoneNumbers(lines)) {
            System.out.println(p);
        }
        // 输出:
        // 987-123-4567
        // (123) 456-7890
    }
}
