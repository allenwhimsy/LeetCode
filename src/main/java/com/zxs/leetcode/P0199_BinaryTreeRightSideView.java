package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 199. 二叉树的右视图
 *
 * 给定一个二叉树的根节点 root，想象你站在它的右侧，按照从顶部到底部的顺序，
 * 返回从右侧看到的节点值。
 *
 * 示例 1：
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 *
 * 示例 2：
 * 输入: [1,null,3]
 * 输出: [1,3]
 *
 * 示例 3：
 * 输入: []
 * 输出: []
 *
 * 提示：
 * 二叉树的节点个数范围是 [0, 100]
 * -100 <= Node.val <= 100
 *
 * 解题思路：
 * 方法一：层序遍历（推荐）
 * BFS 遍历二叉树，每一层的最后一个节点即为右视图看到的节点
 * 用队列实现层序遍历，用 size 控制每层
 * 时间复杂度 O(n)，空间复杂度 O(n)
 *
 * 方法二：DFS（前序遍历变体）
 * 按根->右->左的顺序遍历
 * 记录每个深度第一个访问到的节点（因为右子树先访问）
 * 时间复杂度 O(n)，空间复杂度 O(h)（h为树高）
 */
public class P0199_BinaryTreeRightSideView {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    // 方法一：层序遍历
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 每层最后一个节点即为右视图节点
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }

    // 方法二：DFS（右子树优先）
    private List<Integer> dfsResult = new ArrayList<>();

    public List<Integer> rightSideViewDFS(TreeNode root) {
        dfsResult.clear();
        dfs(root, 0);
        return dfsResult;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;
        // 如果是当前深度的第一个节点，加入结果
        if (depth == dfsResult.size()) {
            dfsResult.add(node.val);
        }
        dfs(node.right, depth + 1);  // 先遍历右子树
        dfs(node.left, depth + 1);   // 再遍历左子树
    }

    public static void main(String[] args) {
        // 构建示例树: [1,2,3,null,5,null,4]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        P0199_BinaryTreeRightSideView solution = new P0199_BinaryTreeRightSideView();
        System.out.println(solution.rightSideView(root));       // [1,3,4]
        System.out.println(solution.rightSideViewDFS(root));     // [1,3,4]
    }
}
