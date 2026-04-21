package com.zxs.leetcode;

/**
 * LeetCode 0021 - 合并两个有序链表 (Merge Two Sorted Lists)
 *
 * 【题目描述】
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：输入 l1 = [1,2,4], l2 = [1,3,4] → 输出 [1,1,2,3,4,4]
 * 示例 2：输入 l1 = [], l2 = [] → 输出 []
 * 示例 3：输入 l1 = [], l2 = [0] → 输出 [0]
 *
 * 约束：两个链表的节点数目范围是 [0, 50], -100 <= Node.val <= 100
 *
 * 【解题思路】
 * 方法一：递归（O(m+n) 时间，O(m+n) 空间）
 * 方法二：迭代（O(m+n) 时间，O(1) 空间）✅ 推荐
 *   使用 dummy 哑节点，依次比较两个链表的头节点，将较小的接到结果链表后面。
 *
 * @Author 郑晓胜
 */
public class P0021_MergeTwoSortedLists {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 迭代法（推荐）
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(1)
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // 接上剩余部分
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }

    /**
     * 递归法
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(m + n)（递归调用栈）
     */
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }
}
