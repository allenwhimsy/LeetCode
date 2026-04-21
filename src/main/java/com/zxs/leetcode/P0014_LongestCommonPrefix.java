package com.zxs.leetcode;

/**
 * 【LeetCode 第14题】最长公共前缀
 *
 * <p>题目描述：
 * 编写一个函数来查找字符串数组中最长的公共前缀字符串。
 *
 * <p>示例：
 * 输入: strs = ["flower","flow","flight"]
 * 输出: "fl"
 *
 * 输入: strs = ["dog","racecar","car"]
 * 输出: ""
 * 解释: 不存在公共前缀
 *
 * <p>约束条件：
 * - 1 <= strs.length <= 200
 * - 0 <= strs[i].length <= 200
 * - strs[i] 由小写英文字母组成
 *
 * <p>解题思路：
 *
 * 【方法一】横向扫描（推荐）⭐
 * - 依次遍历每个字符串，与当前结果比较，更新公共前缀
 * - 每步 O(n*m)，但实际为 O(S)（S为所有字符总数）
 * - 空间复杂度: O(1)
 *
 * 【方法二】字典树（Trie）
 * - 将所有字符串插入 Trie，路径分叉前的公共部分即为答案
 * - 适合大量字符串频繁查询的场景
 * - 时间复杂度: O(S)，空间复杂度: O(S)
 *
 * 【方法三】纵向扫描
 * - 按列比较，从左到右检查每个字符在所有字符串中是否相同
 * - 时间复杂度: O(S)，空间复杂度: O(1)
 *
 * 【方法四】排序后比较首尾
 * - 按字典序排序，最长公共前缀 = 第一个和最后一个的最长公共前缀
 * - 时间复杂度: O(n log n * m)，空间复杂度: O(m)
 *
 * @Author 郑晓胜
 */
public class P0014_LongestCommonPrefix {

    /**
     * 【方法一】横向扫描（推荐）⭐
     *
     * 核心思想：
     * - 初始取第一个字符串作为公共前缀
     * - 遍历剩余字符串，逐个与当前前缀比较，缩小范围
     * - 比较过程：取较短字符串长度，逐一比较字符
     *
     * @param strs 字符串数组
     * @return 最长公共前缀，若无则返回空字符串
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 以第一个字符串为初始前缀
        String prefix = strs[0];

        // 与剩余字符串依次比较
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            // 如果前缀已经为空，无需继续
            if (prefix.isEmpty()) {
                break;
            }
        }

        return prefix;
    }

    /**
     * 求两个字符串的最长公共前缀
     */
    private static String longestCommonPrefix(String s1, String s2) {
        int len = Math.min(s1.length(), s2.length());

        // 找到第一个不匹配的位置
        int i = 0;
        while (i < len && s1.charAt(i) == s2.charAt(i)) {
            i++;
        }

        return s1.substring(0, i);
    }

    // ========== 方法二：字典树（Trie） ==========

    /**
     * 【方法二】字典树（Trie）
     *
     * 核心思想：
     * - 构建 Trie，统计每层节点数量
     * - 某层只有1个节点时，说明是所有字符串共有的，继续
     * - 遇到分叉点（节点数>1）时停止
     */
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int count = 0;  // 经过该节点的字符串数量
    }

    public static String longestCommonPrefixTrie(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 构建 Trie
        TrieNode root = new TrieNode();
        for (String s : strs) {
            TrieNode node = root;
            for (char ch : s.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
                node.count++;
            }
        }

        // 顺着 Trie 走，直到某个节点只有一条路径
        StringBuilder prefix = new StringBuilder();
        TrieNode node = root;
        while (node.count > 0 && node.children.length > 0) {
            int nonNullCount = 0;
            int nonNullIdx = -1;
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    nonNullCount++;
                    nonNullIdx = i;
                }
            }
            // 只有唯一子节点时才添加
            if (nonNullCount == 1) {
                prefix.append((char) ('a' + nonNullIdx));
                node = node.children[nonNullIdx];
            } else {
                break;
            }
        }

        return prefix.toString();
    }

    // ========== 方法三：纵向扫描 ==========

    /**
     * 【方法三】纵向扫描
     *
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public static String longestCommonPrefixVertical(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 以最短字符串为上限
        int minLen = Integer.MAX_VALUE;
        for (String s : strs) {
            minLen = Math.min(minLen, s.length());
        }

        // 按列扫描
        for (int col = 0; col < minLen; col++) {
            char c = strs[0].charAt(col);
            for (int row = 1; row < strs.length; row++) {
                if (strs[row].charAt(col) != c) {
                    return strs[0].substring(0, col);
                }
            }
        }

        return strs[0].substring(0, minLen);
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        test(new String[]{"flower", "flow", "flight"}, "fl");
        test(new String[]{"dog", "racecar", "car"}, "");
        test(new String[]{"a"}, "a");
        test(new String[]{"ab", "a"}, "a");
        test(new String[]{"ab", "ab", "ab"}, "ab");
        test(new String[]{"aca", "cba"}, "");
        test(new String[]{"flower", "flower", "flower"}, "flower");
    }

    private static void test(String[] strs, String expected) {
        String r1 = longestCommonPrefix(strs);
        String r2 = longestCommonPrefixTrie(strs);
        String r3 = longestCommonPrefixVertical(strs);
        System.out.printf("输入: %s%n", java.util.Arrays.toString(strs));
        System.out.printf("  横向扫描: \"%s\" (期望: \"%s\") %s%n",
                r1, expected, r1.equals(expected) ? "✓" : "✗");
        System.out.printf("  字典树法: \"%s\" %s%n", r2, r2.equals(expected) ? "✓" : "✗");
        System.out.printf("  纵向扫描法: \"%s\" %s%n", r3, r3.equals(expected) ? "✓" : "✗");
    }
}
