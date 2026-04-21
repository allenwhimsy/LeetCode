package com.zxs.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/copy-list-with-random-pointer/
 *
 * 题目：复制带随机指针的链表（Copy List with Random Pointer）
 *
 * 给你一个长度为 n 的链表，每个节点包含两个指针：
 *   - next：指向下一个节点
 *   - random：指向链表中任意节点或 null
 * 请返回链表的深拷贝。
 *
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 约束：
 * - 0 <= n <= 1000
 * - -10^4 <= Node.val <= 10^4
 * - Node.random 为 null 或指向链表中的节点
 *
 * 解题思路：
 * 方法一：哈希表（推荐）
 *   - 第一次遍历：创建所有节点，建立 原节点 -> 克隆节点 的映射
 *   - 第二次遍历：根据映射设置 next 和 random
 *   - 时间 O(n)，空间 O(n)
 *
 * 方法二：原地穿插法
 *   - 将克隆节点穿插在原节点之间（原1→克隆1→原2→克隆2...）
 *   - 利用相邻关系设置 random，再拆分
 *   - 空间 O(1)，但会修改原链表
 *
 * 推荐方法：哈希表，清晰易懂，不修改原链表
 */
public class P0138_CopyListWithRandomPointer {

    public class Node {
        int val;
        Node next;
        Node random;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

    /**
     * 【推荐解法】哈希表：两次遍历
     * 第一次：创建所有克隆节点，建立映射
     * 第二次：根据映射设置 next 和 random
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();

        // 第一次遍历：创建所有克隆节点
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        // 第二次遍历：设置 next 和 random
        cur = head;
        while (cur != null) {
            Node clone = map.get(cur);
            clone.next = map.get(cur.next);     // cur.next 可能为 null
            clone.random = map.get(cur.random); // cur.random 可能为 null
            cur = cur.next;
        }
        return map.get(head);
    }
}
