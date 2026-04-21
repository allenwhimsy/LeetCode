package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 182. 查找重复的电子邮箱
 *
 * SQL 题目：使用 Java 模拟 SQL 查询
 *
 * 题目描述：
 * Person 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | email       | varchar |
 *
 * 编写 SQL 查询，查找所有重复的电子邮箱。
 *
 * SQL 原句（MySQL）：
 * SELECT email
 * FROM Person
 * GROUP BY email
 * HAVING COUNT(*) > 1;
 *
 * Java 模拟实现：
 * 方法一：HashMap 计数（推荐）
 * 统计每个邮箱出现的次数，返回次数 > 1 的邮箱
 * 时间复杂度 O(n)，空间复杂度 O(n)
 *
 * 方法二：HashSet
 * 第一遍将邮箱加入 HashSet，第二遍如果已存在则加入结果集
 * 时间复杂度 O(n)，空间复杂度 O(n)
 */
public class P0182_DuplicateEmails {

    public static class Person {
        int id;
        String email;
        Person(int id, String email) {
            this.id = id;
            this.email = email;
        }
    }

    /**
     * 查找所有重复的电子邮箱
     */
    public List<String> findDuplicateEmails(List<Person> persons) {
        Map<String, Integer> countMap = new HashMap<>();
        for (Person p : persons) {
            countMap.put(p.email, countMap.getOrDefault(p.email, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
            new Person(1, "a@test.com"),
            new Person(2, "b@test.com"),
            new Person(3, "a@test.com"),
            new Person(4, "c@test.com"),
            new Person(5, "b@test.com")
        );
        P0182_DuplicateEmails solution = new P0182_DuplicateEmails();
        System.out.println(solution.findDuplicateEmails(persons)); // ["a@test.com", "b@test.com"]
    }
}
