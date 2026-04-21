package com.zxs.leetcode;

/**
 * 【P0092】反转链表 II
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0092_ReverseLinkedListII {

    /**
     * 题目描述：
     * 给你链表的头节点 head 和两个整数 left 和 right，
     * 反转从位置 left 到位置 right 的链表节点，返回反转后的链表。
     *
     * 示例：
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     *
     * 约束：
     * 链表中的节点数为 n，1 <= left <= right <= n <= 100
     *
     * 解题思路：
     * 穿针引线法（区间反转）：先用 dummy 节点找到 left 前一个节点，
     * 然后对 [left, right] 区间执行区间反转，最后重新连接 —— 【推荐】O(n)时间，O(1)空间
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 哑节点：处理 left=1 的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 第一步：找到 left 前一个节点
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第二步：对 [left, right] 区间执行反转
        ListNode cur = pre.next;
        ListNode then;
        for (int i = 0; i < right - left; i++) {
            then = cur.next;          // 记录要反转的节点
            cur.next = then.next;     // 穿针：cur 的 next 跳过 then
            then.next = pre.next;     // 头插：then 插入到 pre.next 之前
            pre.next = then;          // 更新 pre.next
        }
        // 此时 pre.next 指向反转后的链表头，cur 指向 right 位置的节点
        return dummy.next;
    }

    public static void main(String[] args) {
        // 构建链表 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        P0092_ReverseLinkedListII solution = new P0092_ReverseLinkedListII();
        ListNode result = solution.reverseBetween(head, 2, 4);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
