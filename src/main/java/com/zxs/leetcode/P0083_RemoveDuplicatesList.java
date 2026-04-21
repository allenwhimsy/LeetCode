package com.zxs.leetcode;

/**
 * 【P0083】移除元素（链表版）
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0083_RemoveDuplicatesList {

    /**
     * 题目描述：
     * 给你一个已排序的链表的头节点 head，删除所有重复的元素，使每个元素只出现一次。
     * 返回已排序的链表。
     *
     * 示例：
     * 输入：head = [1,1,2]
     * 输出：[1,2]
     *
     * 约束：
     * 链表中的节点数在 [0, 300] 范围内
     *
     * 解题思路：
     * 简单双指针：cur 遍历，cur.val == cur.next.val 则跳过，否则前进 —— 【推荐】O(n)时间，O(1)空间
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next; // 跳过重复节点
            } else {
                cur = cur.next; // 前进
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        P0083_RemoveDuplicatesList solution = new P0083_RemoveDuplicatesList();
        ListNode result = solution.deleteDuplicates(head);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
