package com.zxs.leetcode;

/**
 * LeetCode 0024 - 两两交换链表中的节点 (Swap Nodes in Pairs)
 *
 * 【题目描述】
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即只能进行节点交换）。
 *
 * 示例 1：输入 head = [1,2,3,4] → 输出 [2,1,4,3]
 * 示例 2：输入 head = [] → 输出 []
 * 示例 3：输入 head = [1] → 输出 [1]
 *
 * 约束：链表中节点的数目在范围 [0, 100] 内, 0 <= Node.val <= 100
 *
 * 【解题思路】
 * 方法一：递归（O(n) 时间，O(n) 空间）
 *   先交换前两个节点，再递归处理剩余链表。
 *
 * 方法二：迭代（O(n) 时间，O(1) 空间）✅ 推荐
 *   使用 dummy 哑节点，每次处理两个相邻节点的交换。
 *
 * @Author 郑晓胜
 */
public class P0024_SwapNodesInPairs {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 迭代法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;       // 第一个节点
            ListNode second = prev.next.next;  // 第二个节点

            // 交换：prev -> second -> first -> first.next
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // prev 移动到 first（即下一轮的前驱）
            prev = first;
        }

        return dummy.next;
    }

    /**
     * 递归法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)（递归栈深度）
     */
    public ListNode swapPairsRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode second = head.next;
        // 递归交换后续节点
        head.next = swapPairsRecursive(second.next);
        second.next = head;

        return second;
    }
}
