package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 173. 二叉搜索树迭代器
 *
 * 请实现一个二叉搜索树（BST）迭代器类，用于对 BST 进行中序遍历。
 *
 * 实现以下两个方法：
 * - BSTIterator(TreeNode root): 初始化类，直接得到 BST 的根节点
 * - boolean hasNext(): 返回是否还有下一个节点（true/false）
 * - int next(): 返回下一个节点的整数值
 *
 * 示例 1：
 * 输入: ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 *       [[[7, 3, 15, null, null, 9, 20]]]
 * 输出: [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * 提示：
 * 1. next() 和 hasNext() 的操作均摊时间复杂度为 O(1)
 * 2. 你可以假设 next() 操作总是有效的
 *
 * 解题思路：
 * 方法一：扁平化 + 指针（推荐）
 * 先对 BST 做中序遍历，存储到 ArrayList
 * 用指针记录当前位置
 * hasNext: O(1)，next: O(1)，空间复杂度 O(n)
 *
 * 方法二：栈模拟中序遍历（推荐）
 * 用栈模拟递归过程，每次只加载需要的节点
 * hasNext: O(1)（栈非空），next: 均摊 O(1)
 * 空间复杂度 O(h)，h 为树高
 */
public class P0173_BinarySearchTreeIterator {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // 方法二：栈模拟中序遍历
    private Stack<TreeNode> stack = new Stack<>();
    private TreeNode cur;

    public P0173_BinarySearchTreeIterator(TreeNode root) {
        cur = root;
    }

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int val = cur.val;
        cur = cur.right;
        return val;
    }

    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }

    // 方法一：扁平化（更简洁）
    private List<Integer> inorderList;
    private int index;

    public P0173_BinarySearchTreeIterator(TreeNode root, boolean useFlatten) {
        inorderList = new ArrayList<>();
        inorderFlatten(root);
        index = 0;
    }

    private void inorderFlatten(TreeNode root) {
        if (root == null) return;
        inorderFlatten(root.left);
        inorderList.add(root.val);
        inorderFlatten(root.right);
    }

    public int nextFlatten() {
        return inorderList.get(index++);
    }

    public boolean hasNextFlatten() {
        return index < inorderList.size();
    }

    public static void main(String[] args) {
        // 构建 BST: [7,3,15,null,null,9,20]
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        P0173_BinarySearchTreeIterator iter = new P0173_BinarySearchTreeIterator(root);
        System.out.println(iter.next());    // 3
        System.out.println(iter.next());    // 7
        System.out.println(iter.hasNext()); // true
        System.out.println(iter.next());    // 9
        System.out.println(iter.hasNext()); // true
        System.out.println(iter.next());    // 15
        System.out.println(iter.hasNext()); // true
        System.out.println(iter.next());    // 20
        System.out.println(iter.hasNext()); // false
    }
}
