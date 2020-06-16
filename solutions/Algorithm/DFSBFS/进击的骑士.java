package Algorithm.DFSBFS;
import java.util.*;


public class 进击的骑士 {
    public int minKnightMoves(int x, int y) {
        // 思路: BFS，定义好边界和用hashmap存储最近的路径长度
        int x_max = Math.max(x + 2, 2);
        int x_min = Math.min(x - 2, -2);
        int y_max = Math.max(y + 2, 2);
        int y_min = Math.min(y - 2, -2);
        int[][] direction = new int[][]{{1,2}, {1,-2}, {-1,2}, {-1,-2}, {2,1}, {2,-1}, {-2,1}, {-2,-1}};
        if (x == 0 && y == 0) {
            return 0;
        }

        HashMap<Pos, Integer> pathes = new HashMap<>();
        Queue<Pos> poses = new LinkedList<Pos>();
        poses.offer(new Pos(0, 0));
        pathes.put(new Pos(0, 0), 0);
        while (!poses.isEmpty()) {
            Pos pos = poses.poll();
            for (int i = 0; i < direction.length; i++) {
                int next_x = pos.x + direction[i][0];
                int next_y = pos.y + direction[i][1];
                Pos next_pos = new Pos(next_x, next_y);
                if (next_x == x && next_y == y) {
                    return pathes.get(pos) + 1;
                }
                if (next_x <= x_max && next_x >= x_min && next_y >= y_min && next_y <= y_max && !pathes.containsKey(next_pos)) {
                    poses.offer(next_pos);
                    pathes.put(next_pos, pathes.get(pos) + 1);
                }
            }
        }
        return -1;
    }

    // 优化思路:
    // 思路1:
    // 1.如果不限定边界,那么遍历的次数为pow(8, n),n为最终的路径长度,从而可以只限定住从(0, 0)到(x,y)的一个大概范围内搜索
    // 2.镜像思路,无论在哪个坐标的点都可以换算成为到第一象限的点,这样方便坐标映射
    // 3.直接计算到目标点曼哈顿距离小于4的点的距离,这之前不会拐弯,需要不断缩小与目标点的距离,直到一定距离后才考虑拐弯等问题

    public int minKnightMoves_optimization(int x, int y) {
        boolean[][] visited = new boolean[601][601]; // 坐标映射
        LinkedList<Node> queue = new LinkedList<Node>();
        int[][] dir = {{1,2},{2,1},{-1,2},{1,-2},{-1,-2},{-2,-1},{-2,1},{2,-1}};
        queue.add(new Node(0, 0, 0));
        int abs = Math.abs(x) + Math.abs(y);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.x == x && node.y == y) {
                return node.step;
            }
            if (visited[node.x + 300][node.y + 300]) {
                continue;
            }
            visited[node.x + 300][node.y + 300] = true;
            int num = getDis(node.x, node.y, x, y);
            for (int i = 0; i < 8; i++) {
                int tmpX = node.x + dir[i][0];
                int tmpY = node.y + dir[i][1];
                // 贪心算法，减枝
                if (num > getDis(tmpX, tmpY, x, y) || abs < 4) {
                    Node tmp = new Node(tmpX, tmpY, node.step + 1);
                    queue.add(tmp);
                }
            }
        }
        return 0;
    }
    private int getDis(int srcX, int srcY, int dstX, int dstY) { // 计算曼哈顿距离
        return Math.abs(srcX - dstX) + Math.abs(srcY - dstY);
    }

    class Node {
        int x;
        int y;
        int step;
        Node (int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    class Pos {
        public int x;
        public int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int hashCode() {
            return (x << 16) | (y << 16 >> 16);
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x &&
                    y == pos.y;
        }
    }


    public static void main(String[] args) {
        进击的骑士 solution = new 进击的骑士();
        solution.minKnightMoves_optimization(1000, 1000);
    }
}


