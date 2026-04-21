package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 156. 上下翻转二叉树
 *
 * 给你一个棵二叉树的根节点 root，翻转后这棵二叉树的形状如下：
 *            1
 *          /   \
 *         2     3
 *        / \
 *       4   5
 *      / \
 *     6   7
 * 上下翻转后变成：
 *        6
 *       / \
 *      7   4
 *          / \
 *         5   2
 *            / \
 *           3   1
 *
 * 注意：此题可能不在国内版 LeetCode 中，属于 LeetCode 会员题
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5]
 * 输出：[6,7,null,null,4,5,null,null,2,3,null,null,1]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 二叉树中节点数目的范围是 [0, 100]
 * 0 <= Node.val <= 100
 * 二叉树 有且仅有 0 到 100 个节点
 *
 * 解题思路：
 * 方法一：递归（推荐）
 * 翻转的规律：
 * - 最左叶子节点会成为新的根节点
 * - 原先的根节点会成为新树的最右叶子节点
 * - 原先的右子节点会成为新树根节点的右子节点
 * 递归处理：将左子树递归翻转，然后将翻转后的左子树作为新的根
 * 时间复杂度 O(n)，空间复杂度 O(n)（递归栈）
 *
 * 方法二：迭代
 * 用循环模拟递归过程
 * 时间复杂度 O(n)，空间复杂度 O(n)
 */
public class P0156_UpsideDownBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // 递归终止条件
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        // 递归处理左子树，左子树的翻转结果成为新的根
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        // 将当前节点的左子节点的右指针指向当前节点的右子节点
        root.left.right = root.right;
        // 将当前节点的左子节点的左指针指向当前节点
        root.left.left = root;
        // 当前节点的左右子树置空
        root.left = null;
        root.right = null;
        return newRoot;
    }

    // 辅助方法：中序遍历打印
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        P0156_UpsideDownBinaryTree solution = new P0156_UpsideDownBinaryTree();
        // 构建示例树: [1,2,3,4,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        TreeNode result = solution.upsideDownBinaryTree(root);
        System.out.println("翻转后中序遍历：");
        inorder(result);  // 预期: 6 7 4 5 2 3 1
    }
}
