package com.zxs.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 【P0071】简化路径
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0071_SimplifyPath {

    /**
     * 题目描述：
     * 给你一个字符串 path，表示指向某一文件或目录的 Unix 风格绝对路径，
     * 请将其转换为规范路径。在 Unix 文件系统中，一个点（.）表示当前目录本身；
     * 两个点（..）表示将目录切换到上一级（父目录）。路径中可能有多个斜杠 '/'
     * 和多个点（.）。返回简化后的规范路径。
     *
     * 示例：
     * 输入：path = "/home/"
     * 输出："/home"
     *
     * 约束：
     * 1 <= path.length <= 3000
     * path 由英文字母、数字、'.'、'/' 组成
     *
     * 解题思路：
     * 用栈模拟：从头遍历 path，按 '/' 分隔，对每个 token 做：
     * - 空或 "." → 忽略
     * - ".." → 弹出栈顶（若栈非空）
     * - 普通目录名 → 入栈
     * 最后用 "/" 连接栈中元素，首位补 "/" —— 【推荐】O(n)时间，O(n)空间
     */
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        // 按 '/' 分割，过滤空字符串
        for (String token : path.split("/")) {
            if (token.isEmpty() || token.equals(".")) {
                continue; // 忽略空串和当前目录
            } else if (token.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast(); // 弹出上一级目录
                }
            } else {
                stack.offerLast(token); // 普通目录名入栈
            }
        }

        // 构建规范路径
        StringBuilder sb = new StringBuilder("/");
        for (String dir : stack) {
            sb.append(dir).append("/");
        }
        // 去掉末尾多余的 '/'
        if (sb.length() > 1) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        P0071_SimplifyPath solution = new P0071_SimplifyPath();
        System.out.println(solution.simplifyPath("/home/"));                 // "/home"
        System.out.println(solution.simplifyPath("/../"));                     // "/"
        System.out.println(solution.simplifyPath("/a/./b/../../c/"));          // "/c"
    }
}
