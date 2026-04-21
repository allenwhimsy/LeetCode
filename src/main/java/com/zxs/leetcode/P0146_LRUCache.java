package com.zxs.leetcode;

import java.util.LinkedHashMap;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link https://leetcode.cn/problems/lru-cache/
 *
 * 题目：LRU 缓存（LRU Cache）
 *
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存约束的数据结构。
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果 key 存在于缓存中，则返回 key 的值，否则返回 -1
 * void put(int key, int value) 如果 key 已存在，则变更其数据值
 *                          如果 key 不存在，则插入该组「key-value」
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据
 *
 * 示例 1：
 * 输入：["LRUCache","put","put","get","put","get","put","get","get","get"]
 *       [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
 * 输出：[null,null,null,1,null,-1,null,-1,3,4]
 *
 * 约束：
 * - 1 <= capacity <= 3000
 * - 0 <= key <= 10^4
 * - 0 <= value <= 10^5
 * - 最多调用 2 * 10^5 次 get 和 put
 *
 * 解题思路：
 * 方法一：LinkedHashMap（推荐）
 *   - Java 内置 LinkedHashMap 支持访问顺序，覆写 removeEldestEntry
 *   - 代码极简，时间 O(1)，空间 O(capacity)
 *
 * 方法二：哈希表 + 双向链表（标准实现）
 *   - 用 HashMap 存 key->Node，用双向链表维护访问顺序
 *   - 手动实现 get/put，较为繁琐
 *
 * 推荐方法：LinkedHashMap，一行解决
 */
public class P0146_LRUCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public P0146_LRUCache(int capacity) {
        // accessOrder=true 表示按访问顺序（最近访问移至末尾）
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * 【推荐解法】基于 LinkedHashMap
     * 核心：覆写 removeEldestEntry，当大小超过容量时删除最旧元素
     */
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    // 当 size() > capacity 时，删除最久未访问的节点
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
