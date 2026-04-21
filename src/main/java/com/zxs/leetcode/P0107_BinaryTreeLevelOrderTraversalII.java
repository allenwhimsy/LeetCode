package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 *
 * 题目：二叉树的层序遍历 II（Binary Tree Level Order Traversal II）
 *
 * 给你二叉树的根节点 root ，返回其节点值自底向上的层序遍历。
 * （即按从叶到根从左到右的顺序，同一层节点按从左到右的顺序）。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[15,7],[9,20],[3]]
 *
 * 约束：
 * - 树中节点数目在范围 [0, 2000] 内
 * - -1000 <= Node.val <= 1000
 *
 * 解题思路：
 * 方法一：BFS + 最后反转结果（推荐）
 *   - 与普通层序遍历完全相同，最后对结果 List 反转
 *   - 时间 O(n)，空间 O(n)
 *
 * 方法二：使用 LinkedList 头部插入
 *   - 每次遍历完一层，将结果插入到结果 list 的头部
 *   - 不需要最后反转，但代码稍复杂
 *
 * 推荐方法：BFS + 反转结果，简洁直观
 */
public class P0107_BinaryTreeLevelOrderTraversalII {

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
     * 【推荐解法】BFS 层序遍历 + 最后反转结果
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // 与普通层序不同：插入到结果的头部
            result.add(0, level);
        }
        return result;
    }
}
