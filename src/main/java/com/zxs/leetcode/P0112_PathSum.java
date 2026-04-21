package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/path-sum/
 *
 * 题目：路径总和（Path Sum）
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，
 * 判断该树中是否存在从根节点到叶子节点的路径，使路径上所有节点值相加等于 targetSum 。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 *
 * 约束：
 * - 树中节点数目在范围 [0, 5000] 内
 * - -1000 <= Node.val <= 1000
 * - -1000 <= targetSum <= 1000
 *
 * 解题思路：
 * 方法一：递归 DFS（前序遍历）（推荐）
 *   - 从根节点向下累加，每次减去当前节点值
 *   - 到达叶子节点时判断是否等于 0
 *   - 时间 O(n)，空间 O(h)
 *
 * 方法二：BFS + 队列
 *   - 队列同时存储节点和当前路径和
 *   - 时间 O(n)，空间 O(n)
 *
 * 推荐方法：递归 DFS，简洁直接
 */
public class P0112_PathSum {

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
     * 【推荐解法】递归 DFS
     * 核心：targetSum 每次减去当前节点值，到叶子节点判断是否为 0
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        // 叶子节点：左右子树都为空
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        // 递归检查左右子树
        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
    }
}
