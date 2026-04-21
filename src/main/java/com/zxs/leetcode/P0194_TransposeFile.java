package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 194. 转置文件
 *
 * Shell 题目：使用 Java 实现文件转置
 *
 * 题目描述：
 * 给定一个文件 file.txt，其内容格式为 CSV（逗号分隔），
 * 将其行列转置后输出。
 * 即原文件的列变成行，行变成列。
 *
 * Bash 原句：
 * awk -F',' '{for (i=1;i<=NF;i++) a[NR,i]=$i; if (NF>max) max=NF}
 *            END {for (i=1;i<=max;i++) {str=""; for (j=1;j<=NR;j++) str=str a[j,i] (j==NR?"":","); print str}}' file.txt
 *
 * 假设 file.txt 内容：
 * name,position,age
 * John,Engineer,28
 * Alice,Designer,30
 *
 * 转置后：
 * name,John,Alice
 * position,Engineer,Designer
 * age,28,30
 *
 * 解题思路：
 * 方法一：二维数组存储（推荐）
 * 先将文件内容读取为二维列表（每行按逗号分割）
 * 然后按列遍历，拼接为新的行
 * 时间复杂度 O(m*n)，空间复杂度 O(m*n)
 */
public class P0194_TransposeFile {

    /**
     * 将 CSV 内容行列转置
     * @param lines 每行内容（已按逗号分割）
     * @return 转置后的行列表
     */
    public List<String> transpose(List<String[]> rows) {
        if (rows == null || rows.isEmpty()) return new ArrayList<>();

        int colCount = rows.get(0).length;  // 列数（原行数）
        int rowCount = rows.size();          // 行数（原列数）

        List<String> result = new ArrayList<>();
        // 按列遍历
        for (int col = 0; col < colCount; col++) {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < rowCount; row++) {
                sb.append(rows.get(row)[col]);
                if (row < rowCount - 1) sb.append(",");
            }
            result.add(sb.toString());
        }
        return result;
    }

    /**
     * 简化版：直接处理字符串数组
     */
    public List<String> transposeRaw(List<String[]> rows) {
        return transpose(rows);
    }

    public static void main(String[] args) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"name", "position", "age"});
        rows.add(new String[]{"John", "Engineer", "28"});
        rows.add(new String[]{"Alice", "Designer", "30"});

        P0194_TransposeFile solution = new P0194_TransposeFile();
        for (String line : solution.transpose(rows)) {
            System.out.println(line);
        }
        // 输出:
        // name,John,Alice
        // position,Engineer,Designer
        // age,28,30
    }
}
