package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 *
 * 题目：将有序数组转换为二叉搜索树（Convert Sorted Array to Binary Search Tree）
 *
 * 给定一个升序数组，将其转换为一棵 高度平衡 的二叉搜索树（BST）。
 * 高度平衡 的二叉树是一棵满足"每个节点的左右两个子树的高度差的绝对值不超过1"的二叉树。
 *
 * 示例 1：
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]（或另一个有效树）
 *
 * 约束：
 * - 1 <= nums.length <= 10^4
 * - -10^4 < nums[i] <= 10^4
 * - nums 按严格升序排列
 *
 * 解题思路：
 * 方法一：递归 + 取中间数为根（推荐）
 *   - 数组中间元素作为根节点，保证左右子树节点数平衡
 *   - 递归处理左右子数组，时间 O(n)，空间 O(log n)（递归栈）
 *
 * 方法二：迭代（较复杂，不推荐）
 *
 * 推荐方法：递归，以中间数为根，自然平衡
 */
public class P0108_ConvertSortedArrayToBinarySearchTree {

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
     * 【推荐解法】递归：每次取中间数为根
     * 核心：平衡 BST 的中序遍历正好是升序数组
     *      以中间数为根，左右子数组递归处理，自然得到平衡树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2; // 取中间索引作为根节点
        TreeNode root = new TreeNode(nums[mid]);

        root.left = build(nums, left, mid - 1);   // 左子数组递归
        root.right = build(nums, mid + 1, right); // 右子数组递归

        return root;
    }
}
