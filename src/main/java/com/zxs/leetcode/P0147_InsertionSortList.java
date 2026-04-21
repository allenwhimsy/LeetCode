package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/insertion-sort-list/
 *
 * 题目：对链表进行插入排序（Insertion Sort List）
 *
 * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回排序后链表的头。
 * 插入排序算法：
 *   1. 插入排序的迭代方法：从第二个元素开始（默认第一个已排序）
 *   2. 每次将当前元素与已排序序列从后向前比较，找到插入位置并插入
 *
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 约束：
 * - 链表节点数目在范围 [1, 5000] 内
 * - -5000 <= Node.val <= 5000
 *
 * 解题思路：
 * 方法一：模拟插入排序（推荐）
 *   - 维护一个已排序的哑节点 dummy
 *   - 遍历原链表，对每个节点在已排序区间找到正确位置插入
 *   - 时间 O(n^2)，空间 O(1)
 *
 * 推荐方法：链表插入排序
 */
public class P0147_InsertionSortList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 【推荐解法】链表插入排序
     * 核心：为每个节点在已排序区间找到正确位置并插入
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 哑节点：dummy.next 指向已排序链表的头
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode cur = head; // 当前待插入节点

        while (cur != null) {
            ListNode next = cur.next; // 保存下一个待处理节点

            // 在已排序区间中找到 cur 的插入位置
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val < cur.val) {
                prev = prev.next;
            }

            // 将 cur 插入到 prev 之后
            cur.next = prev.next;
            prev.next = cur;

            cur = next;
        }
        return dummy.next;
    }
}
