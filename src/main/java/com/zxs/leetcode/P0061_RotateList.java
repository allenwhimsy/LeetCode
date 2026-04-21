package com.zxs.leetcode;

/**
 * 【P0061】旋转链表
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0061_RotateList {

    /**
     * 题目描述：
     * 给你一个链表的头节点 head，旋转链表，将链表中的每个节点向右移动 k 个位置。
     *
     * 示例：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     *
     * 约束：
     * 链表中节点数目在范围 [0, 500] 内
     * -10^4 <= k <= 10^4
     *
     * 解题思路：
     * 闭环法：先统计长度，让链表成环，然后在合适位置断开 —— 【推荐】O(n)时间，O(1)空间
     * 先让 fast 走 k % len 步，再同时移动 fast/slow 到断开点。
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // 第一步：统计链表长度，并找到尾节点
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }

        // 取模：有效旋转次数
        k = k % len;
        if (k == 0) return head; // 无需旋转

        // 第二步：将链表成环（尾节点指向头）
        tail.next = head;

        // 第三步：找到新的尾节点（倒数第 k+1 个节点）
        // 需走 len - k - 1 步
        int steps = len - k - 1;
        ListNode newTail = head;
        for (int i = 0; i < steps; i++) {
            newTail = newTail.next;
        }

        // 第四步：断开环，新的头节点为 newTail.next
        head = newTail.next;
        newTail.next = null;

        return head;
    }

    // 辅助打印
    public static void print(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val).append(",");
            head = head.next;
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 1);
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // 构建链表 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        P0061_RotateList solution = new P0061_RotateList();
        print(solution.rotateRight(head, 2)); // [4,5,1,2,3]
    }
}
