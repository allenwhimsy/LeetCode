package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/sum-root-to-leaf-numbers/
 *
 * 题目：求根节点到叶节点数字之和（Sum Root to Leaf Numbers）
 *
 * 给你一个二叉树的根节点 root ，树中每个节点都存放一个 0-9 的数字。
 * 从根节点到叶节点的每条路径都代表一个数字。
 * 例如，路径 1 -> 2 -> 3 代表数字 123 。
 * 返回所有根到叶路径表示的数字之和。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 路径 1->2 代表数字 12
 * 路径 1->3 代表数字 13
 * 所以数字之和 = 12 + 13 = 25
 *
 * 约束：
 * - 树中节点数目在范围 [1, 1000] 内
 * - 0 <= Node.val <= 9
 * - 父节点与子节点之间的路径表示的数字不会是前导零（除数字 0 本身外）
 *
 * 解题思路：
 * 方法一：DFS 前序遍历（推荐）
 *   - 递归累加：当前和 = 父路径和 * 10 + 当前节点值
 *   - 叶子节点累加到结果，时间 O(n)，空间 O(h)
 *
 * 方法二：迭代前序遍历
 *   - 使用显式栈记录状态，时间 O(n)，空间 O(h)
 *
 * 推荐方法：递归前序遍历
 */
public class P0129_SumRootToLeafNumbers {

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
     * 【推荐解法】DFS 前序遍历
     * 核心：当前路径值 = 父路径值 * 10 + 当前节点值
     *       叶子节点时将路径值加入结果
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        if (node == null) return 0;

        // 累加当前节点值
        currentSum = currentSum * 10 + node.val;

        // 叶子节点：返回路径和
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // 递归左右子树
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}
