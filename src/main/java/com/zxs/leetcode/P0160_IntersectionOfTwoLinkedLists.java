package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 160. 相交链表
 *
 * 给你两个单链表的头节点 headA 和 headB 。
 * 请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，则返回 null。
 *
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5]
 * 输出：Reference of the node with value = 8
 *
 * 示例 2：
 * 输入：intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4]
 * 输出：Reference of the node with value = 2
 *
 * 示例 3：
 * 输入：listA = [2,6,4], listB = [1,5]
 * 输出：null
 * 解释：两个链表不相交
 *
 * 提示：
 * listA 中节点数为 n
 * listB 中节点数为 m
 * 0 <= n, m <= 3 * 10^4
 * 1 <= Node.val <= 10^5
 *
 * 解题思路：
 * 方法一：双指针法（推荐）
 * 设 A 长度为 a+c，B 长度为 b+c（c 为公共部分）
 * 指针 pA 从 A 出发，走到尾部后从 B 头部开始
 * 指针 pB 从 B 出发，走到尾部后从 A 头部开始
 * 两指针会在：1. 公共节点处相遇（a+b+c） 2. 均为 null（不相交）
 * 时间复杂度 O(a+b)，空间复杂度 O(1)
 *
 * 方法二：哈希集合
 * 将 A 节点加入 HashSet，遍历 B 查找
 * 时间复杂度 O(a+b)，空间复杂度 O(a)
 */
public class P0160_IntersectionOfTwoLinkedLists {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 方法一：双指针
        if (headA == null || headB == null) return null;

        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            // pA 走完 A 走 B，pB 走完 B 走 A
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;  // 若不相交则为 null
    }

    // 方法二：哈希集合
    public ListNode getIntersectionNodeHashSet(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        java.util.Set<ListNode> set = new java.util.HashSet<>();
        ListNode p = headA;
        while (p != null) {
            set.add(p);
            p = p.next;
        }
        p = headB;
        while (p != null) {
            if (set.contains(p)) return p;
            p = p.next;
        }
        return null;
    }

    public static void main(String[] args) {
        // 构建测试用例
        P0160_IntersectionOfTwoLinkedLists solution = new P0160_IntersectionOfTwoLinkedLists();
        ListNode intersect = new ListNode(8);
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = intersect;
        intersect.next = new ListNode(4);
        intersect.next.next = new ListNode(5);

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = intersect;

        ListNode result = solution.getIntersectionNode(headA, headB);
        System.out.println(result == null ? "null" : result.val);  // 8
    }
}
