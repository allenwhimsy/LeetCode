package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 【P0094】二叉树的中序遍历
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0094_BinaryTreeInorder {

    /**
     * 题目描述：
     * 给定一个二叉树的根节点 root，返回它的中序遍历（左-根-右）序列。
     *
     * 示例：
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     *
     * 约束：
     * 树中节点数目在 [0, 100] 范围内
     *
     * 解题思路：
     * 方法1：递归 —— 最简洁，【推荐】
     * 方法2：迭代（显式栈）—— 模拟递归调用栈，【推荐】O(n)时间，O(h)空间（h为树高）
     * 方法3：Morris 中序遍历 —— O(1)空间（线索二叉树），代码复杂
     *
     * 本实现给出迭代版本（推荐面试）。
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 迭代版本（使用栈）
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            // 先遍历到最左节点，沿途压栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            // 弹出栈顶，访问根
            cur = stack.pop();
            result.add(cur.val);
            // 转向右子树
            cur = cur.right;
        }
        return result;
    }

    // 递归版本（简洁）
    public List<Integer> inorderRecursive(TreeNode root, List<Integer> result) {
        if (root == null) return result;
        inorderRecursive(root.left, result);
        result.add(root.val);
        inorderRecursive(root.right, result);
        return result;
    }

    public static void main(String[] args) {
        // 构建树：  1
        //            \
        //             2
        //            /
        //           3
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        P0094_BinaryTreeInorder solution = new P0094_BinaryTreeInorder();
        System.out.println(solution.inorderTraversal(root)); // [1,3,2]
    }
}
