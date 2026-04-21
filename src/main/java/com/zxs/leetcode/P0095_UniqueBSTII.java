package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 【P0095】不同的二叉搜索树 II
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0095_UniqueBSTII {

    /**
     * 题目描述：
     * 给定整数 n，请生成并返回一个由 1 到 n 不同节点值组成的、且节点值不重复的所有可能的二叉搜索树。
     *
     * 示例：
     * 输入：n = 3
     * 输出：[
     *        [1,null,3,null,null,2,null],
     *        [3,2,null,1,null,null],
     *        [3,1,null,null,2,null],
     *        [2,null,1,null,3,null],
     *        [1,null,2,null,3]
     *      ]
     *
     * 约束：
     * 1 <= n <= 8
     *
     * 解题思路：
     * 递归分治：以每个数 i (1~n) 作为根节点，递归生成左右子树，再组合 —— 【推荐】O(Catalan(n))时间
     * 关键：存储子问题结果，避免重复计算。
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return buildTrees(1, n);
    }

    /**
     * 递归构建 [start, end] 范围内所有可能的 BST
     */
    private List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        if (start > end) {
            result.add(null); // 空树是一种可能
            return result;
        }

        // 枚举每个数作为根
        for (int i = start; i <= end; i++) {
            // 递归构建左右子树
            List<TreeNode> leftTrees = buildTrees(start, i - 1);
            List<TreeNode> rightTrees = buildTrees(i + 1, end);

            // 组合左右子树
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    // 辅助打印
    private static void printTree(TreeNode root) {
        System.out.print("[");
        java.util.Deque<TreeNode> q = new java.util.LinkedList<>();
        q.offer(root);
        boolean first = true;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                System.out.print("null,");
            } else {
                System.out.print(node.val + ",");
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        P0095_UniqueBSTII solution = new P0095_UniqueBSTII();
        List<TreeNode> trees = solution.generateTrees(3);
        System.out.println("Total: " + trees.size()); // 5
        for (TreeNode t : trees) {
            printTree(t);
        }
    }
}
