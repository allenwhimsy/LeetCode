package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/binary-tree-preorder-traversal/
 *
 * 题目：二叉树的前序遍历（Binary Tree Preorder Traversal）
 *
 * 给你二叉树的根节点 root ，返回它的前序遍历。
 * 前序遍历：根节点 → 左子树 → 右子树
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 约束：
 * - 树中节点数目在范围 [0, 100] 内
 * - -100 <= Node.val <= 100
 *
 * 解题思路：
 * 方法一：递归（推荐）
 *   - 时间 O(n)，空间 O(h)
 *
 * 方法二：迭代 + 栈
 *   - 用栈模拟递归过程
 *   - 时间 O(n)，空间 O(h)
 *
 * 方法三：Morris 前序遍历
 *   - O(1) 空间，利用叶子节点空指针记录前驱
 *   - 较复杂
 *
 * 推荐方法：递归，最简洁
 */
public class P0144_BinaryTreePreorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 【推荐解法】递归
     * 前序：根 → 左 → 右
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.val);    // 根
        dfs(node.left, result);  // 左
        dfs(node.right, result); // 右
    }
}
