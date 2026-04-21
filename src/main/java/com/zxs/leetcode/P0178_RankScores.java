package com.zxs.leetcode;

import java.util.*;

/**
 * @Author 郑晓胜
 * @Date 2026/4/21
 * @Link
 * @Description 178. 分数排名
 *
 * SQL 题目：使用 Java 模拟 SQL 查询
 *
 * 题目描述：
 * Scores 表:
 * | Column Name | Type |
 * |-------------|------|
 * | id          | int  |
 * | score       | decimal |
 *
 * 编写 SQL 查询，将分数从高到低排列，并给出对应的排名。
 * 分数相同的排名相同（dense rank，不跳跃）。
 *
 * SQL 原句（MySQL）：
 * SELECT Score,
 *        DENSE_RANK() OVER (ORDER BY Score DESC) AS Rank
 * FROM Scores;
 *
 * Java 模拟实现：
 * 方法一：去重排序+计数（推荐）
 * 将所有不重复的分数降序排列
 * 为每个分数分配排名（相同分数相同排名）
 * 时间复杂度 O(n log n)，空间复杂度 O(n)
 */
public class P0178_RankScores {

    public static class Score {
        int id;
        double score;
        Score(int id, double score) {
            this.id = id;
            this.score = score;
        }
    }

    public static class RankedScore {
        double score;
        int rank;
        RankedScore(double score, int rank) {
            this.score = score;
            this.rank = rank;
        }
    }

    /**
     * 查询分数排名（dense rank，相同分数同排名）
     */
    public List<RankedScore> rankScores(List<Score> scores) {
        // 1. 去重并降序排列所有分数
        List<Double> distinctScores = new ArrayList<>();
        Set<Double> seen = new HashSet<>();
        for (Score s : scores) {
            if (!seen.contains(s.score)) {
                seen.add(s.score);
                distinctScores.add(s.score);
            }
        }
        Collections.sort(distinctScores, Collections.reverseOrder());

        // 2. 建立分数到排名的映射
        Map<Double, Integer> scoreToRank = new HashMap<>();
        int rank = 1;
        for (Double d : distinctScores) {
            scoreToRank.put(d, rank++);
        }

        // 3. 为每个原始记录分配排名
        List<RankedScore> result = new ArrayList<>();
        for (Score s : scores) {
            result.add(new RankedScore(s.score, scoreToRank.get(s.score)));
        }
        return result;
    }

    public static void main(String[] args) {
        List<Score> scores = Arrays.asList(
            new Score(1, 4.00),
            new Score(2, 3.85),
            new Score(3, 4.00),
            new Score(4, 3.65),
            new Score(5, 4.00),
            new Score(6, 3.65)
        );
        P0178_RankScores solution = new P0178_RankScores();
        List<RankedScore> result = solution.rankScores(scores);
        System.out.println("Score | Rank");
        for (RankedScore rs : result) {
            System.out.printf("%.2f  | %d%n", rs.score, rs.rank);
        }
    }
}
