package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 185. 部门工资前三高的所有员工
 *
 * SQL 题目：使用 Java 模拟 SQL 查询
 *
 * 题目描述：
 * Employee 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | name        | varchar |
 * | salary      | int  |
 * | departmentId| int  |
 *
 * Department 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | name        | varchar |
 *
 * 编写 SQL 查询，获取部门工资前三高的员工。
 * 如果有并列，跳过后续排名（例如 1,1,1,4）。
 *
 * SQL 原句（MySQL 8.0+）：
 * SELECT d.name AS Department, e.name AS Employee, e.salary AS Salary
 * FROM Employee e
 * JOIN Department d ON e.departmentId = d.id
 * WHERE (
 *     SELECT COUNT(DISTINCT salary) FROM Employee
 *     WHERE departmentId = e.departmentId AND salary > e.salary
 * ) < 3
 * ORDER BY d.name, e.salary DESC;
 *
 * Java 模拟实现：
 * 方法一：分区排序 + 计数（推荐）
 * 按部门分组，每个部门内按薪资降序排序
 * 为每个员工计算其薪资排名（跳过并列）
 * 筛选排名前三的员工
 * 时间复杂度 O(n log n)，空间复杂度 O(n)
 */
public class P0185_DepartmentTopThreeSalaries {

    public static class Employee {
        int id;
        String name;
        int salary;
        int departmentId;
        Employee(int id, String name, int salary, int departmentId) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.departmentId = departmentId;
        }
    }

    public static class Department {
        int id;
        String name;
        Department(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Result {
        String department;
        String employee;
        int salary;
        Result(String department, String employee, int salary) {
            this.department = department;
            this.employee = employee;
            this.salary = salary;
        }
        @Override
        public String toString() {
            return department + " | " + employee + " | " + salary;
        }
    }

    public List<Result> departmentTopThreeSalaries(List<Employee> employees, List<Department> departments) {
        // 1. 建立部门 id -> 名称映射
        Map<Integer, String> deptNames = new HashMap<>();
        for (Department d : departments) {
            deptNames.put(d.id, d.name);
        }

        // 2. 按部门分组
        Map<Integer, List<Employee>> deptEmployees = new HashMap<>();
        for (Employee e : employees) {
            deptEmployees.computeIfAbsent(e.departmentId, k -> new ArrayList<>()).add(e);
        }

        // 3. 每个部门内计算排名（跳过并列）
        List<Result> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Employee>> entry : deptEmployees.entrySet()) {
            List<Employee> emps = entry.getValue();
            // 按薪资降序排序
            emps.sort((a, b) -> b.salary - a.salary);
            int rank = 1;
            for (int i = 0; i < emps.size() && rank <= 3; i++) {
                result.add(new Result(deptNames.get(entry.getKey()), emps.get(i).name, emps.get(i).salary));
                // 如果下一个薪资不同，rank 更新
                if (i + 1 < emps.size() && emps.get(i + 1).salary != emps.get(i).salary) {
                    rank = i + 2;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Joe", 85000, 1),
            new Employee(2, "Henry", 80000, 1),
            new Employee(3, "Sam", 60000, 1),
            new Employee(4, "Max", 90000, 1),
            new Employee(5, "Janet", 69000, 1),
            new Employee(6, "Randy", 85000, 1),
            new Employee(7, "Will", 70000, 1)
        );
        List<Department> departments = Collections.singletonList(new Department(1, "IT"));
        P0185_DepartmentTopThreeSalaries solution = new P0185_DepartmentTopThreeSalaries();
        for (Result r : solution.departmentTopThreeSalaries(employees, departments)) {
            System.out.println(r);
        }
    }
}
