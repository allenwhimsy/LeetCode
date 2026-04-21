package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 196. 删除重复的电子邮箱
 *
 * SQL 题目：使用 Java 模拟 SQL DELETE 操作
 *
 * 题目描述：
 * Person 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | email       | varchar |
 *
 * 编写 SQL DELETE 语句，删除所有重复的电子邮箱，
 * 只保留 id 最小的那个（即保留每个邮箱的最小 id 行）。
 *
 * SQL 原句（MySQL）：
 * DELETE p1 FROM Person p1, Person p2
 * WHERE p1.email = p2.email AND p1.id > p2.id;
 *
 * Java 模拟实现：
 * 方法一：先查后删（推荐）
 * 1. 按 email 分组，取每个邮箱的最小 id
 * 2. 删除所有 id 不在最小 id 集合中的记录
 * 时间复杂度 O(n)，空间复杂度 O(n)
 */
public class P0196_DeleteDuplicateEmails {

    public static class Person {
        int id;
        String email;
        Person(int id, String email) {
            this.id = id;
            this.email = email;
        }
        @Override
        public String toString() {
            return id + " " + email;
        }
    }

    /**
     * 删除重复邮箱，保留 id 最小的记录
     * @param persons 原始 Person 表
     * @return 删除重复后的 Person 列表
     */
    public List<Person> deleteDuplicateEmails(List<Person> persons) {
        // 1. 找出每个邮箱的最小 id
        Set<Integer> keepIds = new HashSet<>();
        Map<String, Integer> minIdByEmail = new HashMap<>();
        for (Person p : persons) {
            if (!minIdByEmail.containsKey(p.email) || p.id < minIdByEmail.get(p.email)) {
                minIdByEmail.put(p.email, p.id);
            }
        }
        keepIds.addAll(minIdByEmail.values());

        // 2. 过滤：只保留 id 在 keepIds 中的记录
        List<Person> result = new ArrayList<>();
        for (Person p : persons) {
            if (keepIds.contains(p.id)) {
                result.add(p);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
            new Person(1, "john@example.com"),
            new Person(2, "bob@example.com"),
            new Person(3, "john@example.com"),
            new Person(4, "john@example.com"),
            new Person(5, "alice@example.com")
        );
        P0196_DeleteDuplicateEmails solution = new P0196_DeleteDuplicateEmails();
        for (Person p : solution.deleteDuplicateEmails(persons)) {
            System.out.println(p);
        }
        // 保留 id=1, 2, 5
    }
}
