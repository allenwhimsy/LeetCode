package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 184. 部门工资最高的员工
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
 * 编写 SQL 查询，返回每个部门中薪资最高的员工信息。
 * 如果同一部门有多个最高薪资员工，全部返回。
 *
 * SQL 原句（MySQL）：
 * SELECT d.name AS Department, e.name AS Employee, e.salary AS Salary
 * FROM Employee e
 * JOIN Department d ON e.departmentId = d.id
 * WHERE e.salary = (
 *     SELECT MAX(salary) FROM Employee WHERE departmentId = e.departmentId
 * );
 *
 * Java 模拟实现：
 * 方法一：两步聚合（推荐）
 * 1. 按部门聚合，找出每个部门的最高薪资
 * 2. JOIN 员工表和部门表，筛选出薪资等于部门最高薪资的员工
 * 时间复杂度 O(m+n)，空间复杂度 O(n)
 */
public class P0184_DepartmentHighestSalary {

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

    public List<Result> departmentHighestSalary(List<Employee> employees, List<Department> departments) {
        // 1. 找出每个部门的最高薪资
        Map<Integer, Integer> maxSalaryByDept = new HashMap<>();
        for (Employee e : employees) {
            maxSalaryByDept.put(e.departmentId,
                Math.max(e.salary, maxSalaryByDept.getOrDefault(e.departmentId, Integer.MIN_VALUE)));
        }

        // 2. 建立部门 id -> 名称 的映射
        Map<Integer, String> deptNames = new HashMap<>();
        for (Department d : departments) {
            deptNames.put(d.id, d.name);
        }

        // 3. 筛选出薪资等于部门最高薪资的员工
        List<Result> result = new ArrayList<>();
        for (Employee e : employees) {
            if (e.salary == maxSalaryByDept.getOrDefault(e.departmentId, Integer.MIN_VALUE)) {
                String deptName = deptNames.get(e.departmentId);
                result.add(new Result(deptName, e.name, e.salary));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Joe", 70000, 1),
            new Employee(2, "Jim", 90000, 1),
            new Employee(3, "Carol", 80000, 2),
            new Employee(4, "Paul", 80000, 2)
        );
        List<Department> departments = Arrays.asList(
            new Department(1, "IT"),
            new Department(2, "Sales")
        );
        P0184_DepartmentHighestSalary solution = new P0184_DepartmentHighestSalary();
        for (Result r : solution.departmentHighestSalary(employees, departments)) {
            System.out.println(r);
        }
    }
}
