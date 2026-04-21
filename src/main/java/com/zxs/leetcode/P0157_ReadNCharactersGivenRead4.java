package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 157. 用 Read4 读取 N 个字符
 *
 * 给你一个文件，并且你有四个 API 方法 read4：
 * int read4(char[] buf4);
 * 该 API 会从文件中读取 4 个连续字符，并将它们写入缓冲区数组 buf4 中。
 * 返回实际读取的字符数，可能小于 4 个字符。
 *
 * 请你实现一个函数 int read(char[] buf, int n)，从文件中读取 n 个字符并存入缓冲区 buf。
 *
 * 示例：
 * 假设文件内容为 "abcdefghijk"，调用了 read4 4次：
 * 第一次 read4 -> "abcd"  返回 4
 * 第二次 read4 -> "efgh"  返回 4
 * 第三次 read4 -> "ijkl"  返回 3（文件结束）
 *
 * 提示：
 * 1. 你不能直接操作文件，只能通过 read4 API 读取
 * 2. read4 返回的是实际读取的字符数，可能小于 4
 * 3. read 函数需要读取恰好 n 个字符（或直到文件结束）
 *
 * 解题思路：
 * 方法一：循环读取（推荐）
 * 每次调用 read4 读取最多4个字符，复制到目标缓冲区
 * 记录已读取的字符数和缓冲区中的剩余字符
 * 当已读取达到 n 或文件结束时停止
 * 时间复杂度 O(n)，空间复杂度 O(1)
 */
public class P0157_ReadNCharactersGivenRead4 {

    // 模拟的外部文件内容（用于测试）
    private static final String FILE_CONTENT = "abcdefghijk";
    private int filePointer = 0;

    /**
     * 模拟 read4 API：从模拟文件中读取最多4个字符
     * @param buf4 目标缓冲区
     * @return 实际读取的字符数
     */
    private int read4(char[] buf4) {
        int count = 0;
        while (count < 4 && filePointer < FILE_CONTENT.length()) {
            buf4[count++] = FILE_CONTENT.charAt(filePointer++);
        }
        return count;
    }

    /**
     * 从文件中读取 n 个字符到缓冲区
     * @param buf 目标缓冲区
     * @param n 要读取的字符数
     * @return 实际读取的字符数
     */
    public int read(char[] buf, int n) {
        int totalRead = 0;  // 已读取的总字符数
        char[] buf4 = new char[4];  // read4 的缓冲区

        while (totalRead < n) {
            int bytes = read4(buf4);  // 调用 read4 读取
            if (bytes == 0) break;   // 文件已读完
            // 将读取的字符复制到目标缓冲区
            for (int i = 0; i < bytes && totalRead < n; i++) {
                buf[totalRead++] = buf4[i];
            }
        }
        return totalRead;
    }

    public static void main(String[] args) {
        P0157_ReadNCharactersGivenRead4 solution = new P0157_ReadNCharactersGivenRead4();
        char[] buf = new char[10];
        int n = solution.read(buf, 10);
        System.out.println("读取了 " + n + " 个字符: " + new String(buf, 0, n));
    }
}
