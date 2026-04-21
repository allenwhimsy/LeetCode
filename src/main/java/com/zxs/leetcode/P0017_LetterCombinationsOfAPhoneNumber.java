package com.zxs.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【LeetCode 第 17 题】电话号码的字母组合
 *
 * <p>题目描述：
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 数字到字母的映射（像老式电话键盘）如下：
 * 2: abc    3: def
 * 4: ghi    5: jkl    6: mno
 * 7: pqrs   8: tuv    9: wxyz
 *
 * <p>示例：
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * <p>约束条件：
 * - 0 <= digits.length <= 4
 * - digits[i] 是范围 ['2','9'] 的数字字符
 *
 * <p>解题思路：
 * 【方法一：回溯（推荐）】O(3^n ~ 4^n)
 * - 用递归/回溯枚举所有组合
 * - 每次选取一个数字对应的字母，继续递归处理下一个数字
 *
 * 【方法二：队列迭代】O(3^n ~ 4^n)
 * - 从左到右依次处理每个数字，用队列维护当前组合
 *
 * <p>推荐方法一回溯，代码简洁易懂。
 *
 * @Author 郑晓胜
 */
public class P0017_LetterCombinationsOfAPhoneNumber {

    // 数字到字母的映射表
    private static final Map<Character, String> digitToLetters = new HashMap<>();

    static {
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");
    }

    /**
     * 回溯算法生成所有字母组合
     *
     * @param digits 数字字符串
     * @return 所有可能的字母组合列表
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.isEmpty()) {
            return result;
        }

        // 路径（当前组合）
        StringBuilder path = new StringBuilder();

        // 开始回溯
        backtrack(digits, 0, path, result);

        return result;
    }

    /**
     * 回溯函数
     *
     * @param digits  原始数字字符串
     * @param index    当前处理的数字索引
     * @param path     当前已构建的路径
     * @param result   结果集
     */
    private void backtrack(String digits, int index, StringBuilder path, List<String> result) {
        // 终止条件：处理完所有数字
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }

        // 获取当前数字对应的字母串
        char digit = digits.charAt(index);
        String letters = digitToLetters.get(digit);

        // 遍历当前数字的所有可能字母
        for (int i = 0; i < letters.length(); i++) {
            // 做选择
            path.append(letters.charAt(i));
            // 递归处理下一个数字
            backtrack(digits, index + 1, path, result);
            // 撤销选择（回溯）
            path.deleteCharAt(path.length() - 1);
        }
    }

    // ====== 测试代码 ======
    public static void main(String[] args) {
        P0017_LetterCombinationsOfAPhoneNumber solution = new P0017_LetterCombinationsOfAPhoneNumber();

        // 测试用例 1
        System.out.println("测试1: " + solution.letterCombinations("23"));

        // 测试用例 2
        System.out.println("测试2: " + solution.letterCombinations(""));

        // 测试用例 3
        System.out.println("测试3: " + solution.letterCombinations("2"));

        // 测试用例 4
        System.out.println("测试4: " + solution.letterCombinations("79"));
    }
}
