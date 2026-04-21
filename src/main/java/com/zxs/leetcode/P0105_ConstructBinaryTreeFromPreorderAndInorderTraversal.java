package com.zxs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * 题目：从前序与中序遍历序列构造二叉树（Construct Binary Tree from Preorder and Inorder Traversal）
 *
 * 给定两个整数数组 preorder 和 inorder ，
 * 其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历。
 * 请构造二叉树并返回其根节点。
 *
 * 示例 1：
 * 输入：preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出：[3,9,20,null,null,15,7]
 *
 * 约束：
 * - 1 <= preorder.length <= 3000
 * - inorder.length == preorder.length
 * - -3000 <= preorder[i], inorder[i] <= 3000
 * - preorder 和 inorder 均 无重复 元素
 *
 * 解题思路：
 * 方法一：递归 + HashMap（推荐）
 *   - 先序遍历第一个元素是根节点
 *   - 在中序遍历中找到根节点，左边是左子树，右边是右子树
 *   - 递归构建左右子树
 *   - 用 HashMap 记录中序遍历中每个值的位置，时间 O(n)，空间 O(n)
 *
 * 推荐方法：递归 + HashMap 优化查找
 */
public class P0105_ConstructBinaryTreeFromPreorderAndInorderTraversal {

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
    private int preorderIndex = 0;

    /**
     * 【推荐解法】递归 + HashMap
     * 核心：先序确定根，中序分割左右子树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 预处理：建立中序遍历值到下标的映射（O(n)）
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildHelper(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildHelper(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        // 在中序遍历中定位根节点位置
        int inorderIndex = inorderIndexMap.get(rootVal);

        // 递归构建左子树（中序: [left, inorderIndex-1]）
        root.left = buildHelper(preorder, left, inorderIndex - 1);
        // 递归构建右子树（中序: [inorderIndex+1, right]）
        root.right = buildHelper(preorder, inorderIndex + 1, right);

        return root;
    }
}
