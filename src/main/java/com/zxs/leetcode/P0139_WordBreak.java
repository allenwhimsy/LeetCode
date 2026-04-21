package com.zxs.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/word-break/
 *
 * 题目：单词拆分（Word Break）
 *
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。
 * 判断是否可以通过字典中的单词拼接成 s。
 * 注意：字典中重复的单词可以忽略。
 *
 * 示例 1：
 * 输入：s = "leetcode", wordDict = ["leet","code"]
 * 输出：true
 * 解释：返回 true，因为 "leetcode" 可以被拆分为 "leet" + "code"
 *
 * 示例 2：
 * 输入：s = "applepenapple", wordDict = ["apple","pen"]
 * 输出：true
 *
 * 约束：
 * - 1 <= s.length <= 300
 * - 1 <= wordDict.length <= 1000
 * - 1 <= wordDict[i].length <= 20
 * - s 和 wordDict[i] 仅由小写字母组成
 *
 * 解题思路：
 * 方法一：DP（推荐）
 *   - dp[i]：s[0..i-1] 是否可以被拆分
 *   - dp[i] = true 若存在 j < i，使 dp[j] = true 且 s[j..i-1] 在字典中
 *   - 时间 O(n * L * n)，空间 O(n)
 *
 * 方法二：BFS 遍历
 *   - 从起点出发，每次尝试一个字典单词，能到终点则返回 true
 *
 * 推荐方法：DP
 */
public class P0139_WordBreak {

    /**
     * 【推荐解法】DP
     * dp[i]：s[0..i-1] 是否可拆分
     */
    public boolean wordBreak(String s, Set<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // 空串可拆分

        for (int i = 1; i <= n; i++) {
            // 枚举切分点 j
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // 找到一个可行切分即可
                }
            }
        }
        return dp[n];
    }
}
