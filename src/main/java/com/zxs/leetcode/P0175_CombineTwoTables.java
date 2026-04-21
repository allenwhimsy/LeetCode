package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 175. 组合两个表
 *
 * SQL 题目：使用 Java 模拟 SQL 的 LEFT JOIN 操作
 *
 * 题目描述：
 * 表1: Person
 * | Column Name | Type |
 * |-------------|------|
 * | PersonId    | int  |
 * | FirstName   | varchar |
 * | LastName    | varchar |
 *
 * 表2: Address
 * | Column Name | Type |
 * |-------------|------|
 * | AddressId   | int  |
 * | PersonId    | int  |
 * | City        | varchar |
 * | State       | varchar |
 *
 * 题目要求：编写 SQL 查询，返回下述格式的结果：
 * FirstName, LastName, City, State
 * 注意：即使 Address 表中没有人地址信息，也需要返回上述列。
 *
 * SQL 原句：
 * SELECT FirstName, LastName, City, State
 * FROM Person LEFT JOIN Address ON Person.PersonId = Address.PersonId;
 *
 * Java 模拟实现：
 * 方法一：使用 HashMap 缓存地址信息（推荐）
 * 时间复杂度 O(m+n)，空间复杂度 O(m)
 *
 * 方法二：嵌套循环
 * 时间复杂度 O(m*n)，空间复杂度 O(1)
 */
import java.util.*;

public class P0175_CombineTwoTables {

    // 人员表数据结构
    public static class Person {
        int personId;
        String firstName;
        String lastName;
        Person(int personId, String firstName, String lastName) {
            this.personId = personId;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    // 地址表数据结构
    public static class Address {
        int addressId;
        int personId;
        String city;
        String state;
        Address(int addressId, int personId, String city, String state) {
            this.addressId = addressId;
            this.personId = personId;
            this.city = city;
            this.state = state;
        }
    }

    // 模拟查询结果
    public static class PersonAddress {
        String firstName;
        String lastName;
        String city;
        String state;
        PersonAddress(String firstName, String lastName, String city, String state) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.city = city;
            this.state = state;
        }
        @Override
        public String toString() {
            return firstName + " " + lastName + " | " + city + " | " + state;
        }
    }

    /**
     * 模拟 SELECT Person.FirstName, Person.LastName, Address.City, Address.State
     * FROM Person LEFT JOIN Address ON Person.PersonId = Address.PersonId
     */
    public List<PersonAddress> leftJoin(Person[] persons, Address[] addresses) {
        List<PersonAddress> result = new ArrayList<>();
        // 1. 将地址表按 personId 建 HashMap
        Map<Integer, Address> addrMap = new HashMap<>();
        for (Address addr : addresses) {
            addrMap.put(addr.personId, addr);
        }
        // 2. 遍历人员表，左连接地址
        for (Person p : persons) {
            Address addr = addrMap.get(p.personId);
            if (addr != null) {
                result.add(new PersonAddress(p.firstName, p.lastName, addr.city, addr.state));
            } else {
                result.add(new PersonAddress(p.firstName, p.lastName, null, null));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Person[] persons = {
            new Person(1, "John", "Snow"),
            new Person(2, "Jane", "Doe"),
            new Person(3, "Jack", "Hill")
        };
        Address[] addresses = {
            new Address(1, 1, "New York", "NY"),
            new Address(2, 3, "Los Angeles", "CA")
        };

        P0175_CombineTwoTables solution = new P0175_CombineTwoTables();
        List<PersonAddress> result = solution.leftJoin(persons, addresses);
        for (PersonAddress pa : result) {
            System.out.println(pa);
        }
    }
}
