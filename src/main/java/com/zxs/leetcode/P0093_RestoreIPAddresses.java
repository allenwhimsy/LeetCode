package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【P0093】复原 IP 地址
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0093_RestoreIPAddresses {

    /**
     * 题目描述：
     * 给定一个只包含数字的字符串 s，请返回它所有可能的有效 IP 地址。
     * 有效的 IP 地址由四个 0~255 的整数组成，整数间用 '.' 分隔，整数前不能有前导零（0本身除外）。
     *
     * 示例：
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     *
     * 约束：
     * 1 <= s.length <= 20
     *
     * 解题思路：
     * 回溯（DFS）：逐段取值，每段最多3个字符（避免超255），剪枝（不能有前导零，剩余字符不能太少）—— 【推荐】O(3^4)常数级，O(1)空间
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) return result;

        backtrack(s, 0, new StringBuilder(), result, 0);
        return result;
    }

    /**
     * @param s       原始字符串
     * @param start   当前处理的起始位置
     * @param sb      当前构建的 IP 地址
     * @param result  结果集
     * @param segment 第几段（0~3）
     */
    private void backtrack(String s, int start, StringBuilder sb,
                          List<String> result, int segment) {
        // 找到了4段，且用完所有字符
        if (segment == 4) {
            if (start == s.length()) {
                result.add(sb.toString());
            }
            return;
        }

        // 剪枝：若剩余字符不足以填满剩余段，或超过剩余段最大容量，直接返回
        // 至少每段1个字符，最多每段3个字符
        int remaining = s.length() - start;
        int needMin = 4 - segment;         // 剩余最少需要的字符数
        int needMax = (4 - segment) * 3;    // 剩余最多需要的字符数
        if (remaining < needMin || remaining > needMax) return;

        // 尝试取1~3个字符作为当前段
        for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
            String part = s.substring(start, start + len);
            // 排除前导零（0x形式，如 "01", "001"）
            if (part.length() > 1 && part.charAt(0) == '0') continue;
            // 排除超过255的数
            if (Integer.parseInt(part) > 255) continue;

            // 加入当前段
            int sbLen = sb.length();
            if (segment > 0) sb.append('.'); // 不是第一段，先加 '.'
            sb.append(part);

            backtrack(s, start + len, sb, result, segment + 1);

            sb.setLength(sbLen); // 撤销（恢复到加点之前）
        }
    }

    public static void main(String[] args) {
        P0093_RestoreIPAddresses solution = new P0093_RestoreIPAddresses();
        System.out.println(solution.restoreIpAddresses("25525511135"));
        System.out.println(solution.restoreIpAddresses("101023"));
    }
}
