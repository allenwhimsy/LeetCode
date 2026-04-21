package com.zxs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * 题目：从中序与后序遍历序列构造二叉树（Construct Binary Tree from Inorder and Postorder Traversal）
 *
 * 给定两个整数数组 inorder 和 postorder ，
 * 其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历。
 * 请构造二叉树并返回其根节点。
 *
 * 示例 1：
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 *
 * 约束：
 * - 1 <= inorder.length <= 3000
 * - postorder.length == inorder.length
 * - -3000 <= inorder[i], postorder[i] <= 3000
 * - inorder 和 postorder 均 无重复 元素
 *
 * 解题思路：
 * 方法一：递归 + HashMap（推荐）
 *   - 后序遍历最后一个元素是根节点
 *   - 在中序遍历中定位根，左边是左子树，右边是右子树
 *   - 注意：先构建右子树（后序遍历顺序：左右根）
 *   - 用 HashMap 优化查找，时间 O(n)，空间 O(n)
 *
 * 推荐方法：递归 + HashMap
 */
public class P0106_ConstructBinaryTreeFromInorderAndPostorderTraversal {

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

    private Map<Integer, Integer> inorderIndexMap = new HashMap<>();
    private int postorderIndex = 0;

    /**
     * 【推荐解法】递归 + HashMap
     * 核心：后序最后一个是根，中序分割左右；注意先建右子树
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        postorderIndex = postorder.length - 1;
        return buildHelper(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode buildHelper(int[] inorder, int[] postorder, int left, int right) {
        if (left > right) return null;

        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);

        int inorderIndex = inorderIndexMap.get(rootVal);

        // 关键：先构建右子树，再构建左子树（因为后序是左右根）
        root.right = buildHelper(inorder, postorder, inorderIndex + 1, right);
        root.left = buildHelper(inorder, postorder, left, inorderIndex - 1);

        return root;
    }
}
