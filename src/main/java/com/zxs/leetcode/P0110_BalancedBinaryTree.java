package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/balanced-binary-tree/
 *
 * 题目：平衡二叉树（Balanced Binary Tree）
 *
 * 给定一个二叉树，判断它是否是高度平衡的。
 * 高度平衡二叉树：任意节点的左右两个子树的高度差绝对值不超过 1。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 约束：
 * - 树中节点数目在范围 [0, 10^4] 内
 * - -10^4 <= Node.val <= 10^4
 *
 * 解题思路：
 * 方法一：自底向上递归（推荐）
 *   - 后序遍历（左右根），先计算子树高度
 *   - 一旦发现不平衡（差值>1），立即返回 -1 剪枝
 *   - 时间 O(n)，空间 O(h)
 *
 * 方法二：自顶向下（暴力）
 *   - 对每个节点分别计算左右子树高度，时间 O(n log n)
 *   - 大量重复计算，不推荐
 *
 * 推荐方法：自底向上递归，提前剪枝
 */
public class P0110_BalancedBinaryTree {

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
     * 【推荐解法】自底向上递归，返回树高度
     * 关键：用 -1 表示不平衡，任何子树不平衡则整棵树不平衡
     */
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode node) {
        if (node == null) return 0; // 空树高度为 0

        // 递归计算左右子树高度
        int leftHeight = height(node.left);
        if (leftHeight == -1) return -1; // 左子树不平衡

        int rightHeight = height(node.right);
        if (rightHeight == -1) return -1; // 右子树不平衡

        // 当前节点不平衡：左右子树高度差 > 1
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
