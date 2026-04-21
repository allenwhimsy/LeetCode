package com.zxs.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 *
 * 题目：二叉树的最小深度（Minimum Depth of Binary Tree）
 *
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * 约束：
 * - 树中节点数目在范围 [0, 10^4] 内
 * - -10^4 <= Node.val <= 10^4
 *
 * 解题思路：
 * 方法一：BFS 层序遍历（推荐）
 *   - 按层遍历，一旦遇到叶子节点（左右子树都为空）即为最小深度
 *   - 时间 O(n)，空间 O(n)
 *
 * 方法二：DFS 递归
 *   - 需要小心处理：最小深度 = min(左深度, 右深度) + 1
 *   - 但当根节点只有一棵子树时，不能取 min（会导致错误结果）
 *   - 需要单独处理单侧子树为空的情况
 *
 * 推荐方法：BFS，找到第一个叶子节点即返回
 */
public class P0111_MinimumDepthOfBinaryTree {

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
     * 【推荐解法】BFS 层序遍历
     * 核心：遇到第一个叶子节点就返回当前层深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++; // 当前层深度

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                // 找到叶子节点：左右子树都为空
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return depth;
    }
}
