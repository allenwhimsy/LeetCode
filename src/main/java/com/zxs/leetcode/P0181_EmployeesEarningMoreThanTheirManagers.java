package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 181. 超过经理收入的员工
 *
 * SQL 题目：使用 Java 模拟 SQL 查询
 *
 * 题目描述：
 * Employee 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | name        | varchar |
 * | managerId   | int  |
 * | salary      | int  |
 *
 * 编写 SQL 查询，获取收入比经理高的员工姓名。
 *
 * SQL 原句：
 * SELECT e1.name AS Employee
 * FROM Employee e1
 * JOIN Employee e2 ON e1.managerId = e2.id
 * WHERE e1.salary > e2.salary;
 *
 * Java 模拟实现：
 * 方法一：双表连接（推荐）
 * 将 Employee 表与其自身进行等值连接（managerId = id）
 * 筛选出 salary > managerSalary 的记录
 * 时间复杂度 O(n^2)（朴素）或 O(n)（HashMap）
 */
public class P0181_EmployeesEarningMoreThanTheirManagers {

    public static class Employee {
        int id;
        String name;
        Integer managerId;  // 经理的 id，null 表示没有经理
        int salary;
        Employee(int id, String name, Integer managerId, int salary) {
            this.id = id;
            this.name = name;
            this.managerId = managerId;
            this.salary = salary;
        }
    }

    /**
     * 查询收入比经理高的员工姓名
     */
    public List<String> findEmployees(List<Employee> employees) {
        // 1. 用 HashMap 建立 id -> Employee 的映射（经理信息）
        Map<Integer, Employee> managerMap = new HashMap<>();
        for (Employee e : employees) {
            managerMap.put(e.id, e);
        }

        // 2. 遍历员工，找到比经理收入高的
        List<String> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.managerId != null) {
                Employee manager = managerMap.get(e.managerId);
                if (manager != null && e.salary > manager.salary) {
                    result.add(e.name);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Joe", 3, 70000),
            new Employee(2, "Henry", 4, 80000),
            new Employee(3, "Sam", null, 60000),
            new Employee(4, "Max", null, 90000)
        );
        P0181_EmployeesEarningMoreThanTheirManagers solution = new P0181_EmployeesEarningMoreThanTheirManagers();
        System.out.println(solution.findEmployees(employees)); // ["Joe"]
    }
}
