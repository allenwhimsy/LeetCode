package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/binary-tree-level-order-traversal/
 *
 * 题目：二叉树的层序遍历（Binary Tree Level Order Traversal）
 *
 * 给你二叉树的根节点 root ，返回其节点值的层序遍历。（即从左到右，逐层访问）。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 *
 * 约束：
 * - 树中节点数目在范围 [0, 2000] 内
 * - -1000 <= Node.val <= 1000
 *
 * 解题思路：
 * 方法一：BFS 队列（推荐）
 *   - 利用队列按层访问，每层结束后记录当前队列大小
 *   - 时间 O(n)，空间 O(n)
 *
 * 方法二：DFS 递归
 *   - 按深度将节点值放入对应层级的 list
 *   - 时间 O(n)，空间 O(h)
 *
 * 推荐方法：BFS 队列，最符合层序遍历语义
 */
public class P0102_BinaryTreeLevelOrderTraversal {

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
     * 【推荐解法】BFS 队列：按层访问二叉树
     * 关键：每次处理队列中当前层的所有节点（通过 size 控制）
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // 当前层节点数
            List<Integer> level = new ArrayList<>();

            // 遍历当前层所有节点
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                // 将下一层子节点加入队列
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }
}
