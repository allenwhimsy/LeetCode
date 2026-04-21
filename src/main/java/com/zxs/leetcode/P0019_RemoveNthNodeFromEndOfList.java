package com.zxs.leetcode;

/**
 * LeetCode 0019 - 删除链表的倒数第N个节点 (Remove Nth Node From End of List)
 *
 * 【题目描述】
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：输入 head = [1,2,3,4,5], n = 2 → 输出 [1,2,3,5]
 * 示例 2：输入 head = [1], n = 1 → 输出 []
 * 示例 3：输入 head = [1,2], n = 1 → 输出 [1]
 *
 * 约束：链表中结点的数目为 sz, 1 <= sz <= 30, 0 <= Node.val <= 100, 1 <= n <= sz
 *
 * 【解题思路】
 * 方法一：两次遍历（先计算长度，再删除）- O(n) 时间
 *
 * 方法二：双指针/快慢指针（O(n) 时间，O(1) 空间）✅ 推荐
 *   使用 dummy 哑节点指向头节点，快指针先走 n 步，然后快慢指针同步走，
 *   当快指针到达末尾时，慢指针正好在待删除节点的前一个位置。
 *
 * @Author 郑晓胜
 */
public class P0019_RemoveNthNodeFromEndOfList {

    /**
     * 链表节点定义
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 双指针法（推荐）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 哑节点，处理删除头节点的特殊情况
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 快指针先走 n+1 步（多走一步，使得慢指针停在待删除节点的前一个）
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 快慢指针同步走
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除倒数第 n 个节点
        slow.next = slow.next.next;

        return dummy.next;
    }
}
