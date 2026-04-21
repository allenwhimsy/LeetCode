package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 *
 * 题目：二叉树的最大深度（Maximum Depth of Binary Tree）
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 *
 * 约束：
 * - 树中节点数目在范围 [0, 10^4] 内
 * - -100 <= Node.val <= 100
 *
 * 解题思路：
 * 方法一：DFS 递归（推荐）
 *   - 最大深度 = max(左子树深度, 右子树深度) + 1
 *   - 时间 O(n)，空间 O(h)（递归栈深度）
 *
 * 方法二：BFS 层序遍历
 *   - 遍历所有层，计数器累加
 *   - 时间 O(n)，空间 O(n) 最大队列大小
 *
 * 推荐方法：DFS 递归，最简洁
 */
public class P0104_MaximumDepthOfBinaryTree {

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
     * 【推荐解法】DFS 递归
     * 递归公式：depth(node) = max(depth(node.left), depth(node.right)) + 1
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
