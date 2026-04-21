package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/clone-graph/
 *
 * 题目：克隆图（Clone Graph）
 *
 * 给你无向连通图中一个节点的引用，请你返回该图的深拷贝。
 * 图中每个节点都包含它的值（int） 和其邻居节点的列表（list[Node]）。
 *
 * 示例 1：
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 *
 * 约束：
 * - 节点数范围 [0, 100]
 * - 1 <= Node.val <= 100
 * - 节点值唯一
 * - 无向图是连通的，可以从一个节点访问到所有其他节点
 *
 * 解题思路：
 * 方法一：BFS + 哈希表（推荐）
 *   - 使用 HashMap 记录 原节点 -> 克隆节点 的映射
 *   - BFS 遍历原图，根据映射构建克隆图的边
 *   - 时间 O(N)，空间 O(N)
 *
 * 方法二：DFS + 哈希表
 *   - 递归遍历，时间 O(N)，空间 O(N)
 *
 * 推荐方法：BFS + 哈希表，清晰易懂
 */
public class P0133_CloneGraph {

    public class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int val, ArrayList<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    /**
     * 【推荐解法】BFS + 哈希表
     * 核心：用 HashMap 记录已克隆节点，避免重复克隆
     */
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> map = new HashMap<>(); // 原节点 -> 克隆节点
        Queue<Node> queue = new java.util.LinkedList<>();

        // 克隆起始节点并加入队列
        Node cloneStart = new Node(node.val);
        map.put(node, cloneStart);
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (Node neighbor : cur.neighbors) {
                // 若邻居尚未克隆，先克隆并加入队列
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                // 建立克隆节点的邻居关系
                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return cloneStart;
    }
}
