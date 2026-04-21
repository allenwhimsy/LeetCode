package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 183. 从不订购的客户
 *
 * SQL 题目：使用 Java 模拟 SQL 查询
 *
 * 题目描述：
 * Customers 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | name        | varchar |
 *
 * Orders 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | customerId  | int  |
 *
 * 编写 SQL 查询，返回所有从未下单的客户姓名。
 *
 * SQL 原句（MySQL）：
 * SELECT c.name AS Customers
 * FROM Customers c
 * LEFT JOIN Orders o ON c.id = o.customerId
 * WHERE o.customerId IS NULL;
 * -- 或使用子查询：
 * SELECT c.name AS Customers
 * FROM Customers c
 * WHERE c.id NOT IN (SELECT customerId FROM Orders);
 *
 * Java 模拟实现：
 * 方法一：HashSet 差集（推荐）
 * 将所有下过单的 customerId 存入 HashSet
 * 遍历所有客户，筛选出不在该集合中的
 * 时间复杂度 O(m+n)，空间复杂度 O(m)
 */
public class P0183_CustomersWhoNeverOrder {

    public static class Customer {
        int id;
        String name;
        Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Order {
        int id;
        int customerId;
        Order(int id, int customerId) {
            this.id = id;
            this.customerId = customerId;
        }
    }

    /**
     * 返回所有从未下单的客户姓名
     */
    public List<String> findNeverOrderedCustomers(List<Customer> customers, List<Order> orders) {
        // 1. 收集所有下过单的 customerId
        Set<Integer> orderedCustomerIds = new HashSet<>();
        for (Order o : orders) {
            orderedCustomerIds.add(o.customerId);
        }

        // 2. 过滤出从未下单的客户
        List<String> result = new ArrayList<>();
        for (Customer c : customers) {
            if (!orderedCustomerIds.contains(c.id)) {
                result.add(c.name);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Customer> customers = Arrays.asList(
            new Customer(1, "Joe"),
            new Customer(2, "Henry"),
            new Customer(3, "Sam"),
            new Customer(4, "Max")
        );
        List<Order> orders = Arrays.asList(
            new Order(1, 3),
            new Order(2, 1)
        );
        P0183_CustomersWhoNeverOrder solution = new P0183_CustomersWhoNeverOrder();
        System.out.println(solution.findNeverOrderedCustomers(customers, orders)); // ["Henry", "Max"]
    }
}
