package com.zxs.leetcode;

/**
 * 【P0099】恢复二叉搜索树
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0099_RecoverBinarySearchTree {

    /**
     * 题目描述：
     * 给你二叉搜索树的根节点 root，该树恰好有两个节点的值被交换了。
     * 请在不改变树结构的情况下恢复这棵树。
     *
     * 示例：
     * 输入：root = [1,3,null,null,2]
     * 输出：[3,1,null,null,2]
     * 解释：节点3和节点2的值被交换，正确的BST应为 [3,1,null,null,2]
     *
     * 约束：
     * 树中节点数目在 [1, 100] 范围内
     *
     * 解题思路：
     * 方法1：中序遍历找两个错误节点（升序状态下发生降序的位置）—— 【推荐】O(n)时间，O(h)空间
     *   - 找到第一处降序：第一个错误节点（较大者）
     *   - 找到第二处降序：第二个错误节点（较小者）
     * 方法2：Morris 中序遍历 —— O(1)空间（无需栈），但实现复杂
     * 方法3：显式栈迭代 —— 常规中序遍历，O(h)空间
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private TreeNode first = null; // 第一个错误节点
    private TreeNode second = null; // 第二个错误节点
    private TreeNode prev = null;  // 中序遍历前一个节点

    public void recoverTree(TreeNode root) {
        inorder(root);
        // 交换两个错误节点的值
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);

        // 检测 BST 性质被破坏的位置
        if (prev != null && node.val < prev.val) {
            if (first == null) {
                first = prev;        // 第一处降序：first 为"较大"的错误节点
                second = node;       // second 先暂定，后续可能更新
            } else {
                second = node;       // 第二处降序：second 为"较小"的错误节点
            }
        }
        prev = node;
        inorder(node.right);
    }

    // 交换节点值的辅助方法（交换 TreeNode 对象，而非值）
    private void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    public static void main(String[] args) {
        // 构建错误 BST：root = [1,3,null,null,2]
        // 正确 BST 应为 [3,1,null,null,2]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        P0099_RecoverBinarySearchTree solution = new P0099_RecoverBinarySearchTree();
        solution.recoverTree(root);
        // 中序输出验证
        solution.printInorder(root);
    }

    // 中序遍历打印（验证）
    public void printInorder(TreeNode node) {
        if (node == null) return;
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}
