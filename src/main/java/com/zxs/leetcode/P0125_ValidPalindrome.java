package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/valid-palindrome/
 *
 * 题目：验证回文串（Valid Palindrome）
 *
 * 如果在将所有大写字符转换小写字符、并移除非字母数字字符后，
 * 一个序列在正向和反向读时都相同，则该序列是回文。
 * 给定一个字符串 s ，如果它是回文串，返回 true ；否则返回 false 。
 *
 * 示例 1：
 * 输入：s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文
 *
 * 示例 2：
 * 输入：s = "race a car"
 * 输出：false
 *
 * 约束：
 * - 1 <= s.length <= 2 * 10^5
 * - s 仅由可打印 ASCII 字符组成
 *
 * 解题思路：
 * 方法一：双指针（推荐）
 *   - 左右指针向中间收敛，跳过非字母数字字符
 *   - 逐字符比较，时间 O(n)，空间 O(1)
 *
 * 方法二：字符串清理 + 反转比较
 *   - 先过滤掉非字母数字字符，转小写，再反转比较
 *   - 时间 O(n)，空间 O(n)，不推荐
 *
 * 推荐方法：双指针，原地判断
 */
public class P0125_ValidPalindrome {

    /**
     * 【推荐解法】双指针
     * 核心：左右指针向中间靠拢，跳过无效字符，逐字符比较
     */
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // 左指针跳过非字母数字
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // 右指针跳过非字母数字
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // 比较（统一转小写）
            if (Character.toLowerCase(s.charAt(left)) !=
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
