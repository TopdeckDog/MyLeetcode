package Algorithm.DFSBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 最佳的碰头地点 {
    public int minTotalDistance(int[][] grid) {
        // 思路1:暴力破解,时间复杂度O(n*m)
        // 测试用例:
        // 1,
        List<int[]> homes = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) homes.add(new int[]{i, j});
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int dis = 0;
                for (int[] home : homes) {
                    dis += (Math.abs(home[0] - i) + Math.abs(home[1] - j));
                }
                res =  Math.min(dis, res);
            }
        }
        return res;
    }

    // 优化思路
    // 1.利用多个点的曼哈顿距离优化等于寻找这些点的中位数,二维向量转换为两个一维向量的优化
    // 2.按顺序收集home坐标,这样就用两次遍历的结果让home坐标不用排序
    // 3.不用关心最终汇聚点,因为到左右两边的点距离定长

    public int minTotalDistance_op(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) rows.add(i);
            }
        }

        List<Integer> cols = new ArrayList<>();
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) cols.add(j);
            }
        }

        return minDistance1D(rows) + minDistance1D(cols);
    }

    public int minDistance1D(List<Integer> points) {
        int distance = 0;
        int i = 0, j = points.size() - 1;
        // 不用关注最终汇聚点
        while (i < j) {
            distance += (points.get(j) - points.get(i));
            i++;
            j--;
        }
        return distance;
    }

    public static void main(String[] args) {
        最佳的碰头地点 s = new 最佳的碰头地点();
        s.minTotalDistance(new int[][]{{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}});
    }
}
