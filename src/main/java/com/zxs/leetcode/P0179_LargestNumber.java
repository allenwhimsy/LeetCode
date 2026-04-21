package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 179. 最大数
 *
 * 给定一组非负整数，重新排列它们的顺序，组成最大的整数。
 *
 * 示例 1：
 * 输入: nums = [10,2]
 * 输出: "210"
 *
 * 示例 2：
 * 输入: nums = [3,30,34,5,9]
 * 输出: "9534330"
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 *
 * 解题思路：
 * 方法一：自定义排序（推荐）
 * 关键：比较 (a+b) 和 (b+a) 的字典序
 * 例如：a="3", b="30" -> "330" vs "303" -> 选 "3"
 * a="30", b="3" -> "303" vs "330" -> 选 "3"
 * 使用 String.compareTo 进行字典序比较
 * 时间复杂度 O(n log n)，空间复杂度 O(n)
 *
 * 方法二：Java 内置 API
 * 利用 Java 的 Collections.sort 或 Arrays.sort
 * 时间复杂度同上
 */
import java.util.*;

public class P0179_LargestNumber {

    public String largestNumber(int[] nums) {
        // 将 int 数组转为 String 数组
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        // 自定义比较器：比较 (a+b) 和 (b+a) 的字典序
        Arrays.sort(strs, (a, b) -> {
            String order1 = a + b;
            String order2 = b + a;
            // 降序排列，因此 compareTo 为负时交换顺序
            return order2.compareTo(order1);
        });

        // 如果最大的数字是 "0"，说明所有数字都是0
        if (strs[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        P0179_LargestNumber solution = new P0179_LargestNumber();
        System.out.println(solution.largestNumber(new int[]{10, 2}));          // "210"
        System.out.println(solution.largestNumber(new int[]{3, 30, 34, 5, 9})); // "9534330"
        System.out.println(solution.largestNumber(new int[]{0, 0, 0}));        // "0"
    }
}
