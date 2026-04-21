package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/sort-list/
 *
 * 题目：排序链表（Sort List）
 *
 * 给你链表的头节点 head ，请将其按升序排列并返回排序后的链表。
 * 要求：必须在 O(n log n) 时间复杂度和 O(1) 空间复杂度下解决。
 *
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 约束：
 * - 链表节点数目在范围 [1, 5 * 10^4] 内
 * - -10^5 <= Node.val <= 10^5
 *
 * 解题思路：
 * 方法一：归并排序（推荐）
 *   - 1) 快慢指针找链表中点
 *   - 2) 递归对左右两半排序
 *   - 3) 合并两个有序链表
 *   - 时间 O(n log n)，空间 O(log n)（递归栈）
 *
 * 方法二：自底向上归并排序
 *   - 将链表切成多个长度为 1 的子链表，两两合并
 *   - 空间 O(1)，但实现复杂
 *
 * 推荐方法：递归归并排序，O(n log n) 时间
 */
public class P0148_SortList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 【推荐解法】归并排序（递归版）
     * 核心：分治思想：分割（找中点）→ 递归排序 → 合并
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 第一步：快慢指针找链表中点
        ListNode slow = head;
        ListNode fast = head.next; // 奇数时 slow 指向中点左，偶数时指向中点前
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null; // 断开链表

        // 第二步：递归排序左右两半
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        // 第三步：合并两个有序链表
        return merge(left, right);
    }

    // 合并两个有序链表
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}
