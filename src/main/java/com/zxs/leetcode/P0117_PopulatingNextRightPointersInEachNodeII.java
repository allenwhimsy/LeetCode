package com.zxs.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * 题目：填充每个节点的下一个右侧节点指针 II（Populating Next Right Pointers in Each Node II）
 *
 * 给定一个二叉树，填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果没有下一个右侧节点，则将 next 指针设置为 null 。
 * 初始状态下，所有 next 指针均为 null 。
 *
 * 示例：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 *
 * 约束：
 * - 树中节点数目在范围 [0, 10^4] 内
 * - -100 <= Node.val <= 100
 *
 * 解题思路：
 * 方法一：层序遍历（推荐）
 *   - 与完美二叉树相同的方法，适用于任意二叉树
 *   - 时间 O(n)，空间 O(n)（队列）
 *
 * 方法二：已建立的 next 指针（常数空间）
 *   - 利用每层已建立的 next 关系找到下一层起始节点和每个节点的右侧节点
 *   - 较复杂但可达到 O(1) 空间
 *
 * 推荐方法：层序遍历，通用且简洁
 */
public class P0117_PopulatingNextRightPointersInEachNodeII {

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
     * 【推荐解法】层序遍历（与 P0116 相同方法，通用于任意二叉树）
     */
    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                if (prev != null) {
                    prev.next = curr;
                }
                prev = curr;

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return root;
    }
}
