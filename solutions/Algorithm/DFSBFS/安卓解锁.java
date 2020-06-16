package Algorithm.DFSBFS;

public class 安卓解锁 {
    int res;
    int m;
    int n;
    int[] x;
    int[] y;
    public int numberOfPatterns(int m, int n) {
        // 思路:DFS+剪枝,有16个方向
        x =  new int[]{1, -1, 0, 0, 1, 1, -1, -1, 1, 2, -1, -2, 1, 2, -1, -2};
        y =  new int[]{0, 0, -1, 1, 1, -1, 1, -1, 2, 1, 2, 1, -2, -1, -2, -1};
        boolean[][] visited = new boolean[3][3];
        this.m = m;
        this.n = n;
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                visited[i][j] = true;
                dfs(visited, i, j, 1);
                visited[i][j] = false;
            }
        }
        return res;
    }

    public void dfs(boolean[][] visited, int i, int j, int time) {
        if (time <= n && time >= m) {
            res += 1;
        }
        if (time > n) {
            return;  // 当遍历的深度超过了n则停止遍历
        }
        visited[i][j] = true;
        for (int k = 0; k < 16; k++) {
            if (i + x[k] >= 0 && i + x[k] < 3 && j + y[k] >= 0 && j + y[k] < 3 && visited[i+x[k]][j+y[k]] == false) {
                dfs(visited, i + x[k], j + y[k], time + 1);
                visited[i+x[k]][j+y[k]] = false;  // 遍历退出后需要将这个置为没有遍历过
            }
        }
        for (int k = 0; k < 8; k++) {
            if (i + 2 * x[k] >= 0 && i + 2 * x[k] < 3 && j + 2 * y[k] >= 0 && j + 2 * y[k] < 3 &&
                    visited[i+2*x[k]][j+2*y[k]] == false && visited[i+x[k]][j+y[k]] == true){
                dfs(visited, i + 2 * x[k], j + 2 * y[k], time + 1);
                visited[i+2*x[k]][j+2*y[k]] = false;
            }
        }
    }

    public static void main(String[] args) {
        安卓解锁 solution = new 安卓解锁();
        System.out.println(solution.numberOfPatterns(1, 2));
    }
}
