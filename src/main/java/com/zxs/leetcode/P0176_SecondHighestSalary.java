package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 176. 第二高的薪水
 *
 * SQL 题目：使用 Java 模拟 SQL 查询
 *
 * 题目描述：
 * Employee 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | salary      | int  |
 *
 * 编写 SQL 查询获取 Employee 表中第二高的薪水。如果不存在第二高的薪水，查询结果返回 null。
 *
 * SQL 原句：
 * SELECT (SELECT DISTINCT salary FROM Employee ORDER BY salary DESC LIMIT 1 OFFSET 1) AS SecondHighestSalary;
 * 或使用 IFNULL：
 * SELECT IFNULL((SELECT DISTINCT salary FROM Employee ORDER BY salary DESC LIMIT 1 OFFSET 1), NULL) AS SecondHighestSalary;
 *
 * Java 模拟实现：
 * 方法一：去重+排序（推荐）
 * 使用 TreeSet 去重并排序，取第二大值
 * 时间复杂度 O(n log n)，空间复杂度 O(n)
 *
 * 方法二：一次遍历
 * 维护最大和第二大值
 * 时间复杂度 O(n)，空间复杂度 O(1)
 */
public class P0176_SecondHighestSalary {

    public static class Employee {
        int id;
        int salary;
        Employee(int id, int salary) {
            this.id = id;
            this.salary = salary;
        }
    }

    /**
     * 查询第二高的薪水，不存在则返回 null
     */
    public Integer secondHighestSalary(List<Employee> employees) {
        // 方法一：TreeSet 去重排序
        Set<Integer> set = new TreeSet<>(Comparator.reverseOrder());
        for (Employee e : employees) {
            set.add(e.salary);
        }
        if (set.size() < 2) return null;
        Iterator<Integer> it = set.iterator();
        it.next();  // 跳过第一高
        return it.next();
    }

    /**
     * 方法二：一次遍历
     */
    public Integer secondHighestSalaryV2(List<Employee> employees) {
        if (employees == null || employees.size() < 2) return null;
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        Set<Integer> seen = new HashSet<>();  // 跳过重复
        for (Employee e : employees) {
            if (seen.contains(e.salary)) continue;
            seen.add(e.salary);
            if (e.salary > first) {
                second = first;
                first = e.salary;
            } else if (e.salary > second) {
                second = e.salary;
            }
        }
        return second == Integer.MIN_VALUE ? null : second;
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, 100),
            new Employee(2, 200),
            new Employee(3, 300)
        );
        P0176_SecondHighestSalary solution = new P0176_SecondHighestSalary();
        System.out.println(solution.secondHighestSalary(employees));     // 200
        System.out.println(solution.secondHighestSalaryV2(employees));    // 200

        List<Employee> employees2 = Arrays.asList(
            new Employee(1, 100),
            new Employee(2, 100)
        );
        System.out.println(solution.secondHighestSalary(employees2));     // null
    }
}
