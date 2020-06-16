package 其他公司;

// 思路1:暴力破解,循环多次,用一个数组表示最开始第i个个人的手绢现在在谁的手上,时间复杂度O(kn),空间复杂度O(n),k为最终结果
// 思路2:快慢指针,判断链表是否成环,然后找到所有环中最小的那个环,时间复杂度O(n),空间复杂度O(n)
import java.util.*;
public class 丢手绢 {
    // 暴力版本
    public static void main1(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] T = new int[n];
        int[] Q = new int[n]; // 代表Q[i]第一轮为i的手绢在当前的所属人为Q[i]
        for (int i = 0; i < n; i++) {
            T[i] = sc.nextInt() - 1;
            Q[i] = i;
        }
        int res = 0;
        boolean flag = true;
        while (flag) {
            res += 1;
            for (int i = 0; i < n; i++) {
                Q[i] = T[Q[i]];
                if (Q[i] == i) {
                    flag = false;
                }
            }
        }
        System.out.println(res);
    }

    // 快慢指针版本
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] T = new int[n];
        for (int i = 0; i < n; i++) {
            T[i] = sc.nextInt() - 1;
        }
        ArrayList<Integer> loopEntries = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            boolean[] visited2 = new boolean[n];
            int j = i;
            while (false == visited[j]) {
                visited[j] = true;
                visited2[j] = true;
                j = T[j];
            }
            // 只有这次遍历造成的环才是新的环
            if (visited2[j]) {
                loopEntries.add(j);
            }
        }
        // 对于所有的成环,找出最小环
        int res = n;
        for (Integer loopEntry : loopEntries) {
            int fast = T[T[loopEntry]];
            int slow = T[loopEntry];
            int loopSize = 1;
            while (fast != slow) {
                fast = T[T[fast]];
                slow = T[slow];
                loopSize++;
            }
            res = Math.min(res, loopSize);
        }
        System.out.println(res);
    }

    // 快慢指针优化版本:
    // 优化点1:去掉所有不构成环的顶点:关键在用入度进行去除,因为去除后就直接判断这些全部构成环的结点即可,并不会产生多次判断环的情况

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] T = new int[n];
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            T[i] = sc.nextInt() - 1;
            inDegree[T[i]] += 1;
        }

        // 去除不成环的结点
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (0 == inDegree[i]) {
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int node = stack.pop();
            inDegree[node] -= 1;
            inDegree[T[node]] -= 1;
            if (0 == inDegree[T[node]]) {
                stack.push(T[node]);
            }
        }

        // 对于所有的成环,找出最小环
        int res = n;
        for (int i = 0; i < n; i++) {
            if (-1 == inDegree[i]) {
                continue;
            } else {
                int start = i;
                int loopSize = 1;
                while (start != T[i]) {
                    i = T[i];
                    inDegree[i] = -1;
                    loopSize += 1;
                }
                res = Math.min(res, loopSize);
            }


        }
        System.out.println(res);
    }

}