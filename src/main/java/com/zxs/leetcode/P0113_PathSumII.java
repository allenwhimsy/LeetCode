package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/path-sum-ii/
 *
 * 题目：路径总和 II（Path Sum II）
 *
 * 给你二叉树的根节点 root 和一个整数目标 sum ，
 * 找出所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 *
 * 约束：
 * - 树中节点数目在范围 [0, 5000] 内
 * - -1000 <= Node.val <= 1000
 * - -1000 <= targetSum <= 1000
 *
 * 解题思路：
 * 方法一：回溯法（推荐）
 *   - 使用路径列表记录当前路径，递归回溯
 *   - 到达叶子节点且路径和等于目标时，复制路径加入结果
 *   - 时间 O(n)，空间 O(h)（递归栈 + 路径）
 *
 * 推荐方法：回溯法，标准树路径枚举模板
 */
public class P0113_PathSumII {

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

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 【推荐解法】回溯法
     * 核心：维护当前路径 + 当前累计和，到叶子节点判断
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> path = new ArrayList<>();
        backtrack(root, targetSum, path);
        return result;
    }

    private void backtrack(TreeNode node, int targetSum, List<Integer> path) {
        if (node == null) return;

        path.add(node.val); // 做选择
        targetSum -= node.val;

        // 到达叶子节点且路径和等于目标
        if (node.left == null && node.right == null && targetSum == 0) {
            result.add(new ArrayList<>(path)); // 复制路径（重要！）
        }

        backtrack(node.left, targetSum, path);
        backtrack(node.right, targetSum, path);

        path.remove(path.size() - 1); // 撤销选择
    }
}
