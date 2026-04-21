package com.zxs.leetcode;

/**
 * 【P0098】验证二叉搜索树
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0098_ValidateBinarySearchTree {

    /**
     * 题目描述：
     * 给你一个二叉树的根节点 root，判断其是否是一个有效的二叉搜索树。
     * 定义：
     * - 节点的左子树只包含小于该节点的数
     * - 节点的右子树只包含大于该节点的数
     * - 左右子树也必须是二叉搜索树
     *
     * 示例：
     * 输入：root = [2,1,3]
     * 输出：true
     *
     * 约束：
     * 树中节点数目在 [1, 10^4] 范围内
     *
     * 解题思路：
     * 方法1：中序遍历验证递增序列 —— 【推荐】BST 的中序遍历是严格递增的，O(n)时间
     * 方法2：递归（上下界）—— 传递每个节点允许的最小值和最大值，O(n)时间
     * 方法3：闭区间递归 —— 本质与方法2相同
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 中序遍历版本（推荐：利用 BST 中序序列严格递增的性质）
    private Integer prev = null; // 用 Integer 区分"未初始化"和"值为0"
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        // 中序遍历左子树
        if (!isValidBST(root.left)) return false;

        // 访问根：与上一个节点比较
        if (prev != null && root.val <= prev) return false;
        prev = root.val;

        // 中序遍历右子树
        return isValidBST(root.right);
    }

    // 递归版本（上下界约束）
    public boolean isValidBST2(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val)
            && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {
        // 构建树： 2
        //         / \
        //        1   3
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        P0098_ValidateBinarySearchTree solution = new P0098_ValidateBinarySearchTree();
        System.out.println(solution.isValidBST(root));      // true
        System.out.println(solution.isValidBST2(root));     // true
    }
}
