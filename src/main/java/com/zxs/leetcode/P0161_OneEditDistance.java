package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 161. 相隔为 1 的编辑距离
 *
 * 给定两个字符串 s 和 t ，如果它们恰好相差一个字符、或者相差一个插入/删除操作，
 * 则返回 true，否则返回 false。
 *
 * 示例 1：
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 在 t 中插入 'c' 即可得到 s
 *
 * 示例 2：
 * 输入: s = "ab", t = "cab"
 * 输出: true
 * 解释: 在 s 中插入 'c' 即可得到 t
 *
 * 示例 3：
 * 输入: s = "1203", t = "1213"
 * 输出: false
 * 解释: 替换了一个字符，但需要恰好一个操作
 *
 * 示例 4：
 * 输入: s = "", t = ""
 * 输出: false
 *
 * 提示：
 * 1 <= s.length, t.length <= 10^4
 * s 和 t 由小写英文字母组成
 *
 * 解题思路：
 * 方法一：分情况讨论（推荐）
 * 设 |s| = n, |t| = m
 * - 如果 |n - m| > 1，一定不是编辑距离为1
 * - 如果 n == m：遍历检查恰好有一个字符不同
 * - 如果 n < m：检查 s 是否可以通过在任意位置插入一个字符得到 t
 * - 如果 n > m：检查 s 是否可以通过删除一个字符得到 t
 * 时间复杂度 O(n)，空间复杂度 O(1)
 */
public class P0161_OneEditDistance {

    public boolean isOneEditDistance(String s, String t) {
        int n = s.length();
        int m = t.length();

        // 如果长度差大于1，一定不是编辑距离为1
        if (Math.abs(n - m) > 1) return false;

        // 如果长度相等，检查恰好一个字符不同
        if (n == m) {
            int diffCount = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    diffCount++;
                    if (diffCount > 1) return false;
                }
            }
            return diffCount == 1;  // 恰好一个字符不同
        }

        // 确保 s 更短，t 更长
        if (n > m) return isOneEditDistance(t, s);

        // n < m，s 短一个字符，检查是否能通过插入一个字符使两者相等
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                // s[i+1..] == t[i+1..]
                return s.substring(i + 1).equals(t.substring(i + 1));
            }
        }
        // 前面所有字符都相同，说明末尾插入一个字符即可
        return true;
    }

    public static void main(String[] args) {
        P0161_OneEditDistance solution = new P0161_OneEditDistance();
        System.out.println(solution.isOneEditDistance("ab", "acb"));   // true
        System.out.println(solution.isOneEditDistance("ab", "cab"));   // true
        System.out.println(solution.isOneEditDistance("1203", "1213")); // false
        System.out.println(solution.isOneEditDistance("", ""));         // false
    }
}
