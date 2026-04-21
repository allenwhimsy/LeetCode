package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 197. 上升的温度
 *
 * SQL 题目：使用 Java 模拟 SQL 查询
 *
 * 题目描述：
 * Weather 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | recordDate   | date |
 * | temperature | int  |
 *
 * 编写 SQL 查询，获取与前一条（有更早日期的记录）相比温度上升的所有日期。
 * 即：找到所有 temperature > 前一天 temperature 的记录日期。
 *
 * SQL 原句（MySQL）：
 * SELECT w2.id, w2.recordDate
 * FROM Weather w1
 * JOIN Weather w2 ON TO_DAYS(w2.recordDate) - TO_DAYS(w1.recordDate) = 1
 * WHERE w2.temperature > w1.temperature;
 *
 * Java 模拟实现：
 * 方法一：HashMap 存储日期->温度（推荐）
 * 将日期作为 key 存入 HashMap，快速查找前一天的温度
 * 时间复杂度 O(n)，空间复杂度 O(n)
 */
public class P0197_RisingTemperature {

    public static class WeatherRecord {
        int id;
        String recordDate;  // 格式: "yyyy-MM-dd"
        int temperature;
        WeatherRecord(int id, String recordDate, int temperature) {
            this.id = id;
            this.recordDate = recordDate;
            this.temperature = temperature;
        }
        @Override
        public String toString() {
            return id + " | " + recordDate + " | " + temperature;
        }
    }

    /**
     * 找出温度比前一天高的所有日期记录
     */
    public List<WeatherRecord> risingTemperature(List<WeatherRecord> records) {
        // 按日期排序（题目保证无重复日期）
        records.sort(Comparator.comparing(r -> r.recordDate));

        List<WeatherRecord> result = new ArrayList<>();
        for (int i = 1; i < records.size(); i++) {
            if (records.get(i).temperature > records.get(i - 1).temperature) {
                result.add(records.get(i));
            }
        }
        return result;
    }

    /**
     * HashMap 方案（对应 SQL JOIN 逻辑）
     */
    public List<WeatherRecord> risingTemperatureMap(List<WeatherRecord> records) {
        Map<String, Integer> dateToTemp = new HashMap<>();
        for (WeatherRecord r : records) {
            dateToTemp.put(r.recordDate, r.temperature);
        }

        // 按日期排序（确保顺序正确）
        records.sort(Comparator.comparing(r -> r.recordDate));

        List<WeatherRecord> result = new ArrayList<>();
        for (int i = 1; i < records.size(); i++) {
            String today = records.get(i).recordDate;
            String yesterday = records.get(i - 1).recordDate;

            // 验证日期是否相邻（SQL中日期差为1天）
            if (dateDiff(yesterday, today) == 1
                    && records.get(i).temperature > records.get(i - 1).temperature) {
                result.add(records.get(i));
            }
        }
        return result;
    }

    // 简单计算日期差（假设输入合法，格式为 yyyy-MM-dd）
    private int dateDiff(String d1, String d2) {
        String[] parts1 = d1.split("-");
        String[] parts2 = d2.split("-");
        int days1 = Integer.parseInt(parts1[0]) * 365 + Integer.parseInt(parts1[1]) * 30 + Integer.parseInt(parts1[2]);
        int days2 = Integer.parseInt(parts2[0]) * 365 + Integer.parseInt(parts2[1]) * 30 + Integer.parseInt(parts2[2]);
        return days2 - days1;
    }

    public static void main(String[] args) {
        List<WeatherRecord> records = Arrays.asList(
            new WeatherRecord(1, "2015-01-01", 10),
            new WeatherRecord(2, "2015-01-02", 25),
            new WeatherRecord(3, "2015-01-03", 20),
            new WeatherRecord(4, "2015-01-04", 30)
        );
        P0197_RisingTemperature solution = new P0197_RisingTemperature();
        for (WeatherRecord r : solution.risingTemperature(records)) {
            System.out.println(r);
        }
        // 输出 id=2 和 id=4 的记录
    }
}
