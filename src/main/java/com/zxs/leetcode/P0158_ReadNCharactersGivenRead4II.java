package com.zxs.leetcode;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 158. Read N Characters Given Read4 II - Call multiple times
 *
 * 与157题类似，但 read 函数会被多次调用，且需要支持回读（buffering）机制。
 * 因为多次调用时，上次 read4 读取但未使用的字符需要被缓存起来供下次使用。
 *
 * 示例：
 * File content: "abcdc"
 * read(buff, 4) -> read4 读到 "abcd"，复制 4 个到 buff，buffer 剩余 1 个字符 'c'
 * read(buff, 4) -> buffer 有 'c'，先读缓存，再调用 read4 读到 "cde"，复制 3 个
 *
 * 提示：
 * 需要使用一个缓冲区（队列）来暂存 read4 读取但未使用的字符
 *
 * 解题思路：
 * 方法一：缓存队列（推荐）
 * 用一个队列缓存 read4 读取但未使用的字符
 * 每次 read 调用时：
 *   1. 先从缓存队列中取字符
 *   2. 缓存空时，调用 read4 补充
 *   3. 读取到 n 个字符或文件结束为止
 * 时间复杂度 O(n)，空间复杂度 O(4) = O(1)
 */
public class P0158_ReadNCharactersGivenRead4II {

    // 模拟的外部文件内容（用于测试）
    private static final String FILE_CONTENT = "abcdcdefghij";
    private int filePointer = 0;

    // 缓冲区，暂存 read4 读取但未使用的字符
    private char[] buffer = new char[4];
    private int bufferCount = 0;  // buffer 中有效字符数
    private int bufferPos = 0;   // 当前读取位置

    /**
     * 模拟 read4 API
     */
    private int read4(char[] buf4) {
        int count = 0;
        while (count < 4 && filePointer < FILE_CONTENT.length()) {
            buf4[count++] = FILE_CONTENT.charAt(filePointer++);
        }
        return count;
    }

    /**
     * 从缓存或文件中读取 n 个字符
     */
    public int read(char[] buf, int n) {
        int totalRead = 0;
        int remaining = n;

        while (remaining > 0) {
            // 优先从缓冲区读取
            if (bufferPos < bufferCount) {
                buf[totalRead++] = buffer[bufferPos++];
                remaining--;
            } else {
                // 缓冲区空，重新调用 read4 填充
                bufferCount = read4(buffer);
                bufferPos = 0;
                if (bufferCount == 0) break;  // 文件已读完
            }
        }
        return totalRead;
    }

    public static void main(String[] args) {
        P0158_ReadNCharactersGivenRead4II solution = new P0158_ReadNCharactersGivenRead4II();

        // 测试多次调用
        char[] buf = new char[10];
        int n1 = solution.read(buf, 4);
        System.out.println("第1次读取 " + n1 + " 个字符: " + new String(buf, 0, n1));

        int n2 = solution.read(buf, 4);
        System.out.println("第2次读取 " + n2 + " 个字符: " + new String(buf, 0, n2));

        int n3 = solution.read(buf, 10);
        System.out.println("第3次读取 " + n3 + " 个字符: " + new String(buf, 0, n3));
    }
}
