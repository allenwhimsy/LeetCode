package com.zxs.leetcode;

/**
 * 【P0086】分隔链表
 *
 * @Author 郑晓胜
 * @Date 2026/4/21
 */
public class P0086_PartitionList {

    /**
     * 题目描述：
     * 给定链表的头节点 head 和一个值 x，请对链表进行分隔，
     * 使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
     * 保留两个分区中每个节点的初始相对位置。
     *
     * 示例：
     * 输入：head = [1,4,3,2,5,2], x = 3
     * 输出：[1,2,2,4,3,5]
     *
     * 约束：
     * 链表中的节点数在 [0, 200] 范围内
     *
     * 解题思路：
     * 双指针构建两个子链表：将小于 x 的节点接在 small 链表上，其余接在 large 链表，最后合并 —— 【推荐】O(n)时间，O(1)空间
     * 关键：必须保持相对顺序，所以使用尾插法而非头插法。
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode partition(ListNode head, int x) {
        // 哑节点分别作为两个分区链表的头
        ListNode smallDummy = new ListNode(0);
        ListNode largeDummy = new ListNode(0);
        ListNode smallTail = smallDummy;
        ListNode largeTail = largeDummy;

        // 遍历原链表，分配到对应分区
        while (head != null) {
            if (head.val < x) {
                smallTail.next = head; // 尾插
                smallTail = head;
            } else {
                largeTail.next = head;
                largeTail = head;
            }
            head = head.next;
        }

        // 关键：largeTail.next 必须置 null，避免成环
        largeTail.next = null;
        // 合并两个分区
        smallTail.next = largeDummy.next;
        return smallDummy.next;
    }

    public static void main(String[] args) {
        // 构建链表 1->4->3->2->5->2
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        P0086_PartitionList solution = new P0086_PartitionList();
        ListNode result = solution.partition(head, 3);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
