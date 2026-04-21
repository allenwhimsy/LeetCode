package com.zxs.leetcode;

/**
 * 【P0082】删除排序链表中的重复元素 II
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0082_RemoveDuplicatesListII {

    /**
     * 题目描述：
     * 给定一个已排序的链表，删除所有存在重复的节点，使每个值只出现一次。
     *
     * 示例：
     * 输入：head = [1,2,3,3,4,4,5]
     * 输出：[1,2,5]
     *
     * 约束：
     * 链表中的节点数在 [0, 300] 范围内
     *
     * 解题思路：
     * 哑节点dummy统一处理：pre指向无重复节点的尾部，cur扫描 —— 【推荐】O(n)时间，O(1)空间
     * 若发现重复（cur.next != null && cur.val == cur.next.val），则跳过所有重复节点。
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        // 哑节点：统一处理头节点可能被删除的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;  // pre 指向已确定无重复的节点尾部
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                // 跳过所有值等于 cur.val 的节点
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                // cur 指向最后一个重复节点，pre.next 跳过它们
                pre.next = cur.next;
                cur = cur.next;
            } else {
                // 无重复，pre 和 cur 同时前移
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        // 构建链表 1->2->3->3->4->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        P0082_RemoveDuplicatesListII solution = new P0082_RemoveDuplicatesListII();
        ListNode result = solution.deleteDuplicates(head);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
