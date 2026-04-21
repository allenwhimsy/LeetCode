package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 186. 翻转字符串里的单词 II
 *
 * 给定一个字符串数组，逐个翻转每个单词。
 * 字符串中的每个单词由单个空格分隔。
 * 假设字符串前后都没有空格。
 * 注意：此题与 151 不同，不需要去除多余空格，原地翻转。
 *
 * 示例 1：
 * 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 是英文字母或空格 ' '
 * s 保证组成至少一个单词
 *
 * 解题思路：
 * 方法一：先整体翻转，再逐个单词翻转（推荐）
 * 第一步：将整个字符数组翻转
 * 第二步：遍历字符数组，对每个单词再次翻转
 * 时间复杂度 O(n)，空间复杂度 O(1)
 *
 * 方法二：逐个单词提取后翻转
 * 将每个单词提取出来存入临时数组，最后再翻转
 * 时间复杂度 O(n)，空间复杂度 O(n)
 */
public class P0186_ReverseWordsInAStringII {

    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) return;

        // 第一步：整体翻转
        reverse(s, 0, s.length - 1);

        // 第二步：逐个单词翻转
        int start = 0;
        for (int i = 0; i <= s.length; i++) {
            // 遇到空格或末尾，单词[start..i-1]翻转
            if (i == s.length || s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
    }

    private void reverse(char[] s, int left, int right) {
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        P0186_ReverseWordsInAStringII solution = new P0186_ReverseWordsInAStringII();
        char[] s1 = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        solution.reverseWords(s1);
        System.out.println(new String(s1));  // "blue is sky the"

        char[] s2 = {'a'};
        solution.reverseWords(s2);
        System.out.println(new String(s2));  // "a"
    }
}
