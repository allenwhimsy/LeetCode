package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/symmetric-tree/
 *
 * 题目：对称二叉树（Symmetric Tree）
 *
 * 给你一个二叉树的根节点 root ，检查它是否轴对称。
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 约束：
 * - 树中节点数目在范围 [1, 100] 内
 * - -100 <= Node.val <= 100
 *
 * 解题思路：
 * 方法一：递归（推荐）
 *   - 递归比较左右子树是否互为镜像
 *   - 时间 O(n)，空间 O(h) 其中 h 为树高，最坏 O(n)
 *
 * 方法二：迭代（队列）
 *   - 将左右子树节点成对入队，逐对比较
 *   - 时间 O(n)，空间 O(n)
 *
 * 推荐方法：递归，简洁直观
 */
public class P0101_SymmetricTree {

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
     * 【推荐解法】递归：判断两棵树是否互为镜像
     * 核心：左子树的左孩子 与 右子树的右孩子 比较
     *       左子树的右孩子 与 右子树的左孩子 比较
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        // 两节点都为空，镜像成立
        if (left == null && right == null) return true;
        // 只有一个为空，不对称
        if (left == null || right == null) return false;
        // 值不同，不对称
        if (left.val != right.val) return false;
        // 递归检查内层和外层
        return check(left.left, right.right) && check(left.right, right.left);
    }

    /**
     * 迭代解法：使用队列成对比较
     */
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            // 成对入队：左左 vs 右右，左右 vs 右左
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
}
