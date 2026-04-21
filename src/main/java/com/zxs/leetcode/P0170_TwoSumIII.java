package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 170. 两数之和 III - 数据结构设计
 *
 * 设计一个接收整数流的类，并支持以下操作：
 * - add(number): 向数据结构中插入一个数
 * - find(value): 找出是否存在任意一对数字，使它们的和等于 value
 *
 * 注意：此题可能不在国内版 LeetCode 中
 *
 * 示例：
 * add(1); add(3); add(5);
 * find(4) -> true (1 + 3 = 4)
 * find(7) -> false (没有两个数的和等于 7)
 *
 * 提示：
 * - 可以假设所有 add 和 find 操作中传入的 value 不会超过 2^30
 * - 最多进行 10^5 次 add 和 find 操作
 *
 * 解题思路：
 * 方法一：哈希表（推荐）
 * 用 HashSet 存储所有已加入的数字
 * 每次 find 时，遍历 HashSet，检查 target - num 是否存在
 * 注意处理负数的情况（不能用排序数组的双指针）
 * add: O(1)，find: O(n)，空间: O(n)
 *
 * 方法二：平衡二叉树（有序结构）
 * 用 TreeSet 存储，每次 find 用二分查找
 * add: O(log n)，find: O(n log n)
 */
import java.util.*;

public class P0170_TwoSumIII {

    private Map<Integer, Integer> countMap = new HashMap<>();  // 数字 -> 出现次数

    /** 向数据结构中插入一个数 */
    public void add(int number) {
        countMap.put(number, countMap.getOrDefault(number, 0) + 1);
    }

    /** 找出是否存在任意一对数字，使它们的和等于 value */
    public boolean find(int value) {
        for (Integer num : countMap.keySet()) {
            int complement = value - num;
            Integer complementCount = countMap.get(complement);
            if (complementCount != null) {
                // 如果 complement == num，需要出现至少2次
                if (complement == num && complementCount >= 2) {
                    return true;
                }
                // 如果 complement != num，直接返回 true
                if (complement != num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        P0170_TwoSumIII twoSum = new P0170_TwoSumIII();
        twoSum.add(1);
        twoSum.add(3);
        twoSum.add(5);
        System.out.println(twoSum.find(4));  // true (1+3)
        System.out.println(twoSum.find(7));  // false
        twoSum.add(3);
        System.out.println(twoSum.find(6));  // true (3+3)
    }
}
