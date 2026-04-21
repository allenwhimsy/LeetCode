package com.zxs.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/
 *
 * 题目：填充每个节点的下一个右侧节点指针（Populating Next Right Pointers in Each Node）
 *
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果没有下一个右侧节点，则将 next 指针设置为 null 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 *
 * 约束：
 * - 树中节点数目在范围 [0, 10^4] 内
 * - -100 <= Node.val <= 100
 *
 * 解题思路：
 * 方法一：层序遍历（推荐）
 *   - 利用 next 指针按层连接同一层的节点
 *   - 每层从左到右遍历，curr.next = queue 中的下一个
 *   - 时间 O(n)，空间 O(1) 不计队列
 *
 * 方法二：已建立的 next 指针（常数空间）
 *   - 利用每层已建立的 next 关系，O(1) 空间
 *   - 较为复杂，理解难度高
 *
 * 推荐方法：层序遍历，简单直接
 */
public class P0116_PopulatingNextRightPointersInEachNode {

    public class Node {
        int val;
        Node left;
        Node right;
        Node next;
        Node() {}
        Node(int val) { this.val = val; }
        Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    /**
     * 【推荐解法】层序遍历连接同一层节点
     */
    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            Node prev = null; // 记录前一个节点
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                if (prev != null) {
                    prev.next = curr; // 前一个节点的 next 指向当前节点
                }
                prev = curr;

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            // 每层最后一个节点不需要额外处理，next 默认为 null
        }
        return root;
    }
}
