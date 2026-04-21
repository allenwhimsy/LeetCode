package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/linked-list-cycle-ii/
 *
 * 题目：环形链表 II（Linked List Cycle II）
 *
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。
 * 如果链表无环，则返回 null。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的节点
 *
 * 约束：
 * - 0 <= n <= 10^4
 * - -10^5 <= Node.val <= 10^5
 *
 * 解题思路：
 * 方法一：快慢指针（推荐）
 *   - 第一步：快慢指针相遇，确认有环
 *   - 第二步：从头节点和相遇点同时出发，再次相遇即为环入口
 *   - 数学证明：设环前长度为 a，环入口到相遇点长度为 b，相遇点到入口为 c
 *     2(a+b) = a+b+k(b+c) => a = (k-1)(b+c) + c
 *     即从头和相遇点同时走 a 步会在入口相遇
 *   - 时间 O(n)，空间 O(1)
 *
 * 推荐方法：快慢指针两阶段法
 */
public class P0142_LinkedListCycleII {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 【推荐解法】快慢指针两阶段
     * 第一阶段：找到相遇点
     * 第二阶段：从头和相遇点同时出发，再次相遇即为入环点
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        // 第一步：快慢指针找相遇点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break; // 相遇，有环
        }

        // 无环
        if (fast == null || fast.next == null) return null;

        // 第二步：从头和相遇点同时出发，再次相遇即为入环点
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
