package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/linked-list-cycle/
 *
 * 题目：环形链表（Linked List Cycle）
 *
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始），
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到索引 1 的节点
 *
 * 约束：
 * - 0 <= n <= 10^4
 * - -10^5 <= Node.val <= 10^5
 * - pos 为 -1 或链表中的有效索引
 *
 * 解题思路：
 * 方法一：快慢指针（推荐）
 *   - 慢指针每次走一步，快指针每次走两步
 *   - 若链表有环，两者必然相遇；若没环，快指针先到终点
 *   - 时间 O(n)，空间 O(1)
 *
 * 方法二：哈希集合
 *   - 遍历链表，将节点加入集合，若再次遇到则有环
 *   - 时间 O(n)，空间 O(n)
 *
 * 推荐方法：快慢指针，空间最优
 */
public class P0141_LinkedListCycle {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 【推荐解法】快慢指针
     * 核心：有环时快慢指针必然相遇；无环时快指针先到 null
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // 慢指针走一步
            fast = fast.next.next;  // 快指针走两步
            if (slow == fast) return true; // 相遇，有环
        }
        return false; // 快指针到达终点，无环
    }
}
