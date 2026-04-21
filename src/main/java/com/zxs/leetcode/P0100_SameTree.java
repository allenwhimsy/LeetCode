package com.zxs.leetcode;

/**
 * 【P0100】相同的树
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0100_SameTree {

    /**
     * 题目描述：
     * 给你两棵二叉树的根节点 p 和 q，写一个函数来判断这两棵树是否相同。
     * 两棵树相同，当且仅当它们的结构相同且对应位置上的节点值相同。
     *
     * 示例：
     * 输入：p = [1,2,3], q = [1,2,3]
     * 输出：true
     *
     * 示例：
     * 输入：p = [1,2], q = [1,null,2]
     * 输出：false
     *
     * 约束：
     * 树中节点数目在 [0, 100] 范围内
     *
     * 解题思路：
     * 方法1：深度优先搜索（递归）—— 【推荐】简洁直观，O(p+q)时间
     * 方法2：广度优先搜索（队列）—— 逐层比较，O(p+q)时间
     * 方法3：迭代（栈）—— 模拟递归
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 递归版本（推荐）
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 两树都为空，相等
        if (p == null && q == null) return true;
        // 一棵树空另一棵非空，不等
        if (p == null || q == null) return false;
        // 节点值不同，不等
        if (p.val != q.val) return false;
        // 递归比较左右子树
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // 迭代版本（队列）
    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) continue;
            if (node1 == null || node2 == null) return false;
            if (node1.val != node2.val) return false;
            queue.offer(node1.left);
            queue.offer(node2.left);
            queue.offer(node1.right);
            queue.offer(node2.right);
        }
        return true;
    }

    public static void main(String[] args) {
        // 构建两棵相同的树
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        P0100_SameTree solution = new P0100_SameTree();
        System.out.println(solution.isSameTree(p, q)); // true
    }
}
