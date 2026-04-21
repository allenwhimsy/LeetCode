package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 151. 反转字符串中的单词
 *
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s 中可能会存在前导空格、尾随空格或者单词间的多个空格。
 * 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 *
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：返回的字符串中单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：返回的字符串中单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 *
 * 解题思路：
 * 方法一：双端队列（推荐）
 * - 依次遍历字符串，用双端队列存储每个单词
 * - 跳过空格，遇到非空字符则开始收集单词
 * - 遍历完成后，从左到右依次弹出队列中的单词拼接
 * 时间复杂度 O(n)，空间复杂度 O(n)
 *
 * 方法二：API法
 * - 使用 String.split() 按空格分割，再用 StringBuilder 逆序拼接
 * - 代码简洁，但需要处理空字符串
 * 时间复杂度 O(n)，空间复杂度 O(n)
 *
 * 方法三：原地分割翻转
 * - 先去除多余空格，再分别翻转整个字符串和每个单词
 * 最优，但实现较复杂
 */
import java.util.*;

public class P0151_ReverseWordsInAString {

    // 方法一：双端队列法（推荐）
    public String reverseWords(String s) {
        int left = 0, right = s.length() - 1;
        // 去除首尾空格
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }
        while (left <= right && s.charAt(right) == ' ') {
            right--;
        }

        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                word.append(c); // 收集单词字符
            } else if (word.length() > 0) {
                deque.offerFirst(word.toString()); // 单词结束，加入队列头部
                word.setLength(0); // 重置 word
            }
            left++;
        }
        // 最后一个单词
        if (word.length() > 0) {
            deque.offerFirst(word.toString());
        }

        return String.join(" ", deque);
    }

    public static void main(String[] args) {
        P0151_ReverseWordsInAString solution = new P0151_ReverseWordsInAString();
        System.out.println(solution.reverseWords("the sky is blue"));
        System.out.println(solution.reverseWords("  hello world  "));
        System.out.println(solution.reverseWords("a good   example"));
    }
}
