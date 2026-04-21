package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/reorder-list/
 *
 * 题目：重排链表（Reorder List）
 *
 * 给定一个单链表 L：L0 → L1 → ... → Ln-1 → Ln，
 * 将其重新排列为：L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
 * 不能只改变节点内部的值，而要改变实际的节点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 * 约束：
 * - 链表节点数目在范围 [1, 5 * 10^4] 内
 * - 1 <= Node.val <= 1000
 *
 * 解题思路：
 * 方法一：三步法（推荐）
 *   - 1) 找链表中点（快慢指针）
 *   - 2) 反转后半部分链表
 *   - 3) 交替合并前半部分和反转后的后半部分
 *   - 时间 O(n)，空间 O(1)
 *
 * 推荐方法：三步法
 */
public class P0143_ReorderList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 【推荐解法】三步法
     * 步骤：中点 → 反转后半 → 合并
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // 第一步：快慢指针找中点（奇数长度时中点为中间节点）
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow; // slow 指向中点（或左中点）

        // 第二步：反转后半部分链表
        ListNode prev = null;
        ListNode cur = mid.next;
        mid.next = null; // 断开链表
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        // 第三步：交替合并前半和反转后的后半
        ListNode first = head;  // 前半链表
        ListNode second = prev;  // 反转后的后半链表
        while (first != null && second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;
            first.next = second;
            second.next = firstNext;
            first = firstNext;
            second = secondNext;
        }
    }
}
