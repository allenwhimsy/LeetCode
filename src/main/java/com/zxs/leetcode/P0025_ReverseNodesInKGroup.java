package com.zxs.leetcode;

/**
 * LeetCode 0025 - K个一组翻转链表 (Reverse Nodes in k-Group)
 *
 * 【题目描述】
 * 给你链表的头节点 head，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么最后剩余的节点保持原有顺序。
 * 你不能只是单纯地改变节点内部的值。
 *
 * 示例 1：输入 head = [1,2,3,4,5], k = 2 → 输出 [2,1,4,3,5]
 * 示例 2：输入 head = [1,2,3,4,5], k = 3 → 输出 [3,2,1,4,5]
 *
 * 约束：链表节点总数在范围 [0, 5000] 内, 0 <= Node.val <= 1000, 1 <= k <= 2000
 *
 * 【解题思路】
 * 迭代翻转（O(n) 时间，O(1) 空间）✅ 推荐
 *   1. 先检查剩余节点是否够 k 个
 *   2. 够则翻转这 k 个节点，将翻转后的子链表接回
 *   3. 不够则直接返回剩余部分
 *
 * @Author 郑晓胜
 */
public class P0025_ReverseNodesInKGroup {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 迭代翻转法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode prevGroupEnd = dummy;

        while (true) {
            // 检查剩余节点是否够 k 个
            ListNode kth = getKthNode(prevGroupEnd, k);
            if (kth == null) break;

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            // 断开 kth 的 next，隔离当前组
            kth.next = null;

            // 翻转当前组
            prevGroupEnd.next = reverse(groupStart);
            groupStart.next = nextGroupStart;

            // 移动 prevGroupEnd 到当前组的末尾（即原来的 groupStart）
            prevGroupEnd = groupStart;
        }

        return dummy.next;
    }

    /**
     * 翻转链表
     */
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /**
     * 获取从 start 的 next 开始的第 k 个节点
     */
    private ListNode getKthNode(ListNode start, int k) {
        ListNode current = start;
        for (int i = 0; i < k; i++) {
            current = current.next;
            if (current == null) return null;
        }
        return current;
    }
}
