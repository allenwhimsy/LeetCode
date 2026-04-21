package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 177. 第N高的薪水
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
 * 编写 SQL 查询获取第 N 高的薪水（去重后）。如果不存在第 N 高的薪水，返回 null。
 *
 * SQL 原句：
 * SELECT (SELECT DISTINCT salary FROM Employee ORDER BY salary DESC LIMIT 1 OFFSET N-1) AS NthHighestSalary;
 *
 * Java 模拟实现：
 * 方法一：去重+排序（推荐）
 * 使用 TreeSet 去重降序排列，然后取第 N-1 个
 * 时间复杂度 O(n log n)，空间复杂度 O(n)
 *
 * 方法二：流处理 + 排序
 */
public class P0177_NthHighestSalary {

    public static class Employee {
        int id;
        int salary;
        Employee(int id, int salary) {
            this.id = id;
            this.salary = salary;
        }
    }

    /**
     * 查询第 N 高的薪水（去重后），不存在则返回 null
     */
    public Integer nthHighestSalary(List<Employee> employees, int N) {
        if (N < 1) return null;
        // TreeSet 去重并降序排列
        Set<Integer> set = new TreeSet<>(Comparator.reverseOrder());
        for (Employee e : employees) {
            set.add(e.salary);
        }
        if (set.size() < N) return null;
        Iterator<Integer> it = set.iterator();
        int count = 1;
        while (count < N) {
            it.next();
            count++;
        }
        return it.next();
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, 100),
            new Employee(2, 200),
            new Employee(3, 300),
            new Employee(4, 400)
        );
        P0177_NthHighestSalary solution = new P0177_NthHighestSalary();
        System.out.println(solution.nthHighestSalary(employees, 1));  // 400
        System.out.println(solution.nthHighestSalary(employees, 2));  // 300
        System.out.println(solution.nthHighestSalary(employees, 3));  // 200
        System.out.println(solution.nthHighestSalary(employees, 5));  // null
    }
}
