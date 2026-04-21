package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/
 *
 * 题目：有序链表转换二叉搜索树（Convert Sorted List to Binary Search Tree）
 *
 * 给定一个单链表的头节点 head ，其中的元素按升序排列，
 * 将其转换为一棵 高度平衡 的二叉搜索树（BST）。
 *
 * 示例 1：
 * 输入：head = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]（或另一个有效树）
 *
 * 约束：
 * - 链表节点数目在范围 [1, 10^4] 内
 * - -10^4 <= Node.val <= 10^4
 *
 * 解题思路：
 * 方法一：递归 + 快慢指针找中点（推荐）
 *   - 每次用快慢指针找到链表中点作为根
 *   - 递归处理左右子链表，时间 O(n log n)，空间 O(log n)
 *
 * 方法二：转换为数组 + 取中点（简单有效）
 *   - 先将链表转为数组，再按 P0108 方法构建
 *   - 时间 O(n)，空间 O(n)，实现更简单
 *
 * 推荐方法：快慢指针递归，O(1) 额外空间（不计递归栈）
 */
public class P0109_ConvertSortedListToBinarySearchTree {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

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

    private ListNode globalHead;

    /**
     * 【推荐解法】快慢指针找中点 + 递归构建
     * 关键：找到中点后将其断开，左半部分递归构建左子树
     */
    public TreeNode sortedListToBST(ListNode head) {
        globalHead = head;
        int size = getListSize(head);
        return buildBST(0, size - 1);
    }

    // 计算链表长度
    private int getListSize(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    // 在索引范围 [left, right] 内构建 BST
    private TreeNode buildBST(int left, int right) {
        if (left > right) return null;

        int mid = left + (right - left) / 2;

        // 先递归构建左子树（整体链表指针跟随移动）
        TreeNode leftNode = buildBST(left, mid - 1);

        // 中点作为根节点（链表指针此时正好指向中点）
        TreeNode root = new TreeNode(globalHead.val);
        globalHead = globalHead.next; // 指针右移

        root.left = leftNode;
        root.right = buildBST(mid + 1, right);

        return root;
    }
}
