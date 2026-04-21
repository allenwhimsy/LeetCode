package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 *
 * 题目：二叉树中的最大路径和（Binary Tree Maximum Path Sum）
 *
 * 二叉树中的路径被定义为一条节点序列，序列中每对相邻节点之间都有一条边相连。
 * 路径不一定经过根节点。
 * 路径和是路径中所有节点值之和。
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 *
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 *
 * 约束：
 * - 树中节点数目在范围 [1, 3 * 10^4] 内
 * - -1000 <= Node.val <= 1000
 *
 * 解题思路：
 * 方法一：递归后序遍历（推荐）
 *   - 返回值：经过该节点向下（往左或往右）的最大贡献值（不能同时走两边）
 *   - 贡献值 = max(0, 左子树贡献, 右子树贡献) + 节点值
 *   - 全局最大路径和 = max(全局最大, 左贡献 + 节点 + 右贡献)
 *   - 时间 O(n)，空间 O(h)
 *
 * 推荐方法：递归后序遍历
 */
public class P0124_BinaryTreeMaximumPathSum {

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

    private int maxSum = Integer.MIN_VALUE; // 全局最大路径和

    /**
     * 【推荐解法】递归后序遍历
     * 关键：返回的是经过当前节点往下走的最大贡献（只能选左或右一边）
     *      路径可以同时走左右两边（此时不返回，作为全局最大参与比较）
     */
    public int maxPathSum(TreeNode root) {
        gain(root);
        return maxSum;
    }

    private int gain(TreeNode node) {
        if (node == null) return 0;

        // 左/右子树的最大贡献（若为负数则不选，选0）
        int leftGain = Math.max(gain(node.left), 0);
        int rightGain = Math.max(gain(node.right), 0);

        // 经过当前节点的路径和（左右子树都可以选）
        int pathThroughNode = leftGain + node.val + rightGain;

        // 更新全局最大
        maxSum = Math.max(maxSum, pathThroughNode);

        // 返回经过该节点往下走的最大贡献（只能选一边）
        return Math.max(leftGain, rightGain) + node.val;
    }
}
