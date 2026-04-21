package com.zxs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 【LeetCode 第2题】两数相加
 *
 * <p>题目描述：
 * 给你两个非空链表，表示两个非负整数。它们按照逆序存储，每个节点包含一位数字。
 * 将这两个数相加并以链表形式返回。
 *
 * <p>示例：
 * 输入: l1 = [2,4,3], l2 = [5,6,4]
 * 输出: [7,0,8]
 * 解释: 342 + 465 = 807
 *
 * <p>约束条件：
 * - 每个链表中的节点数范围为 [1, 100]
 * - 节点值范围为 [0, 9]
 * - 链表不包含前导零（除了数字0本身）
 *
 * <p>解题思路：
 *
 * 【方法一】逐位相加法（推荐）⭐
 * - 使用哑节点简化头节点处理
 * - 遍历两个链表，逐位相加，记录进位
 * - 时间复杂度: O(max(m,n))，空间复杂度: O(1)
 *
 * 【方法二】递归法
 * - 递归处理两个链表的对应节点
 * - 需要处理进位和长度不一致的情况
 * - 代码简洁但递归深度受链表长度限制
 *
 * @Author 郑晓胜
 */
public class P0002_AddTwoNumbers {

    /**
     * 链表节点定义
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 【方法一】逐位相加法（推荐）⭐
     * 使用哑节点(dummy)简化链表构建过程
     *
     * @param l1 第一个链表（逆序存储数字）
     * @param l2 第二个链表（逆序存储数字）
     * @return 相加结果链表
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 哑节点，简化头节点处理
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        // 进位标志
        int carry = 0;

        // 遍历两个链表，直到全部处理完毕
        while (l1 != null || l2 != null || carry != 0) {
            // 获取当前位值，若链表已遍历完则为0
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            // 相加当前位 + 进位
            int sum = x + y + carry;
            carry = sum / 10;           // 更新进位
            curr.next = new ListNode(sum % 10); // 当前位结果

            // 移动指针
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }

    /**
     * 【方法二】递归法
     * 递归处理链表节点相加
     */
    public static ListNode addTwoNumbersRecursive(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2, 0);
    }

    private static ListNode addTwoNumbersHelper(ListNode l1, ListNode l2, int carry) {
        // 递归终止条件：两链表都遍历完且无进位
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        int x = (l1 != null) ? l1.val : 0;
        int y = (l2 != null) ? l2.val : 0;
        int sum = x + y + carry;

        ListNode node = new ListNode(sum % 10);
        node.next = addTwoNumbersHelper(
                (l1 != null) ? l1.next : null,
                (l2 != null) ? l2.next : null,
                sum / 10
        );
        return node;
    }

    // ========== 辅助工具方法 ==========

    /** 从数组构建链表 */
    public static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int val : arr) {
            curr.next = new ListNode(val);
            curr = curr.next;
        }
        return dummy.next;
    }

    /** 打印链表 */
    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(",");
            head = head.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    // ========== 测试用例 ==========
    public static void main(String[] args) {
        // 示例1: 342 + 465 = 807
        ListNode l1 = buildList(new int[]{2, 4, 3});
        ListNode l2 = buildList(new int[]{5, 6, 4});
        System.out.print("示例1 输入: l1=");
        printList(l1);
        System.out.print("          l2=");
        printList(l2);
        System.out.print("输出: ");
        printList(addTwoNumbers(l1, l2));

        // 示例2: 9999999 + 9999 = 10009998
        System.out.println();
        l1 = buildList(new int[]{9, 9, 9, 9, 9, 9, 9});
        l2 = buildList(new int[]{9, 9, 9, 9});
        System.out.print("示例2 输入: l1=");
        printList(l1);
        System.out.print("          l2=");
        printList(l2);
        System.out.print("输出: ");
        printList(addTwoNumbers(l1, l2));
    }
}
