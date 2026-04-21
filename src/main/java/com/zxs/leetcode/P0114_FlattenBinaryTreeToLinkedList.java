package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 *
 * 题目：二叉树展开为链表（Flatten Binary Tree to Linked List）
 *
 * 给你二叉树的根节点 root ，将其展开为一个单链表（按前序遍历顺序）。
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个节点，左子指针始终为 null 。
 *
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 *
 * 约束：
 * - 树中节点数目在范围 [0, 2000] 内
 * - -100 <= Node.val <= 100
 *
 * 解题思路：
 * 方法一：递归 + 拼接（推荐）
 *   - 递归将左右子树分别展开
 *   - 将左子树接到根节点右，将原右子树接到左子树最右节点的右
 *   - 时间 O(n)，空间 O(h)
 *
 * 方法二：前序遍历 + 重建
 *   - 先前序遍历得到顺序，再重建链表
 *   - 需要额外 O(n) 空间存遍历结果
 *
 * 推荐方法：递归 + 原地拼接，不需要额外空间
 */
public class P0114_FlattenBinaryTreeToLinkedList {

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
     * 【推荐解法】递归 + 原地拼接
     * 核心：先递归处理左右子树，再做拼接
     */
    public void flatten(TreeNode root) {
        if (root == null) return;

        // 递归展开左右子树
        flatten(root.left);
        flatten(root.right);

        // 保存原右子树
        TreeNode rightSubtree = root.right;

        // 左子树接到根的右孩子
        root.right = root.left;
        root.left = null; // 左子树置空

        // 找到当前右子树的最右节点（即原左子树的最右节点）
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }

        // 将原右子树接到最右节点
        cur.right = rightSubtree;
    }
}
