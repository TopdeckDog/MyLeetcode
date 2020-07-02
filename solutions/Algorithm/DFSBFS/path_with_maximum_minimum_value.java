package Algorithm.DFSBFS;

// https://leetcode-cn.com/problems/path-with-maximum-minimum-value/

import javafx.util.Pair;

import java.util.PriorityQueue;
import java.util.Queue;

public class path_with_maximum_minimum_value {
    public int maximumMinimumPath(int[][] A) {
        // 思路:bfs,用数组标记是否访问过,用最大堆选取优选路径
        // 测试用例:
        // 1.空数组,空指针
        // 2.长度为0
        // 3.长度为1
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[A.length][A[0].length];
        int [][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<Pair<Integer, Integer>> heap = new PriorityQueue<>((a, b) -> A[b.getKey()][b.getValue()] - A[a.getKey()][a.getValue()]);
        heap.offer(new Pair<Integer, Integer>(A.length - 1, A[0].length - 1));
        visited[A.length - 1][A[0].length - 1] = true;
        int path = A[0][0];

        while (!heap.isEmpty()) {
            Pair<Integer, Integer> node = heap.poll();
            path = Math.min(path, A[node.getKey()][node.getValue()]);
            for (int i = 0; i < directions.length; i++) {
                int x = node.getKey() + directions[i][0];
                int y = node.getValue() + directions[i][1];
                if (x == 0 && y == 0) {
                    return path;
                }
                if (0 <= x && x < A.length && 0 <= y && y < A[0].length && !visited[x][y]) {
                    heap.offer(new Pair<Integer, Integer>(x, y));
                    visited[x][y] = true;
                }
            }
        }
        return path;
    }
}
