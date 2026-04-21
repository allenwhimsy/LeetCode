package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 180. 连续出现的数字
 *
 * SQL 题目：使用 Java 模拟 SQL 查询
 *
 * 题目描述：
 * Logs 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | num         | varchar |
 *
 * 编写 SQL 查询，查找所有连续出现至少三次的数字。
 *
 * SQL 原句（MySQL）：
 * SELECT DISTINCT l1.Num AS ConsecutiveNums
 * FROM Logs l1, Logs l2, Logs l3
 * WHERE l1.Id = l2.Id - 1
 *   AND l2.Id = l3.Id - 1
 *   AND l1.Num = l2.Num
 *   AND l2.Num = l3.Num;
 *
 * Java 模拟实现：
 * 方法一：窗口比较（推荐）
 * 将 Logs 按 id 排序
 * 用三个指针滑动窗口，检查连续三个是否相等
 * 时间复杂度 O(n)，空间复杂度 O(1)
 *
 * 方法二：哈希表计数
 * 记录每个数连续出现的次数，超过3次则加入结果
 */
public class P0180_ConsecutiveNumbers {

    public static class Log {
        int id;
        String num;
        Log(int id, String num) {
            this.id = id;
            this.num = num;
        }
    }

    /**
     * 查找所有连续出现至少三次的数字
     */
    public List<String> consecutiveNums(List<Log> logs) {
        if (logs == null || logs.size() < 3) return new ArrayList<>();

        // 按 id 排序
        logs.sort(Comparator.comparingInt(l -> l.id));

        Set<String> result = new LinkedHashSet<>();  // 用 LinkedHashSet 保持插入顺序
        for (int i = 0; i < logs.size() - 2; i++) {
            if (logs.get(i).num.equals(logs.get(i + 1).num) &&
                logs.get(i + 1).num.equals(logs.get(i + 2).num)) {
                result.add(logs.get(i).num);
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        List<Log> logs = Arrays.asList(
            new Log(1, "1"),
            new Log(2, "1"),
            new Log(3, "1"),
            new Log(4, "2"),
            new Log(5, "1"),
            new Log(6, "1"),
            new Log(7, "1")
        );
        P0180_ConsecutiveNumbers solution = new P0180_ConsecutiveNumbers();
        System.out.println(solution.consecutiveNums(logs)); // ["1"]
    }
}
