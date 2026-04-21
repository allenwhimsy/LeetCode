package com.zxs.leetcode;

import java.util.PriorityQueue;

/**
 * LeetCode 0023 - 合并K个升序链表 (Merge k Sorted Lists)
 *
 * 【题目描述】
 * 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：输入 lists = [[1,4,5],[1,3,4],[2,6]] → 输出 [1,1,2,3,4,4,5,6]
 * 示例 2：输入 lists = [] → 输出 []
 * 示例 3：输入 lists = [[]] → 输出 []
 *
 * 约束：k == lists.length, 0 <= k <= 10^4, 0 <= lists[i].length <= 500
 *
 * 【解题思路】
 * 方法一：逐一合并（O(k²n) 时间）- 效率低
 * 方法二：分治合并（O(kn log k) 时间，O(log k) 空间）
 *
 * 方法三：优先队列/最小堆（O(kn log k) 时间，O(k) 空间）✅ 推荐
 *   维护一个大小为 k 的最小堆，每次取出最小节点接入结果链表，
 *   然后将该节点的下一个节点（如果有）放入堆中。
 *
 * @Author 郑晓胜
 */
public class P0023_MergeKSortedLists {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 优先队列法（推荐）
     * 时间复杂度：O(kn log k)
     * 空间复杂度：O(k)（堆的大小）
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 最小堆，按节点值排序
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 将所有链表的头节点加入堆
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (!minHeap.isEmpty()) {
            // 取出堆顶最小节点
            ListNode smallest = minHeap.poll();
            current.next = smallest;
            current = current.next;

            // 将该节点的下一个节点加入堆
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }
}
