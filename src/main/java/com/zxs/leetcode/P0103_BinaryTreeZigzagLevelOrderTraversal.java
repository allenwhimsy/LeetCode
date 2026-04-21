package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 *
 * 题目：二叉树的锯齿形层序遍历（Binary Tree Zigzag Level Order Traversal）
 *
 * 给你二叉树的根节点 root ，返回其节点值的锯齿形层序遍历。
 * （即先从左往右，下一层再从右往左，层间交替进行）。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 *
 * 约束：
 * - 树中节点数目在范围 [0, 2000] 内
 * - -100 <= Node.val <= 100
 *
 * 解题思路：
 * 方法一：BFS + 奇偶层反转（推荐）
 *   - 与普通层序遍历相同，额外维护层号
 *   - 偶数层（从0计数）reverse 当层结果
 *   - 时间 O(n)，空间 O(n)
 *
 * 方法二：双端队列
 *   - 偶数层从右往左遍历，从左端offer
 *   - 奇数层从左往右遍历，从右端offer
 *   - 较为复杂，不推荐
 *
 * 推荐方法：BFS + 奇偶层反转，简单有效
 */
public class P0103_BinaryTreeZigzagLevelOrderTraversal {

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
     * 【推荐解法】BFS + 奇偶层反转
     * 思路：与普通层序相同，维护层号，偶数层反转
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isReverse = false; // 标记是否反转

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 偶数层从左往右（0,2,4...），奇数层从右往左（1,3,5...）
                if (!isReverse) {
                    level.addLast(node.val);
                } else {
                    level.addFirst(node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(new ArrayList<>(level));
            isReverse = !isReverse; // 层间交替
        }
        return result;
    }
}
