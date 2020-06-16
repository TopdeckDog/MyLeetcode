package 字节跳动;

import java.util.*;

public class i号房重新分配 {
    // 1.先找到i好房2.i号房之前的和i号房之后的分开处理
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        long[] p = new long[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextLong();
        }
        rebalanceBeforePerson(n, x - 1, p);  // 房间从1开始的
    }

    public static void rebalanceBeforePerson(int n, int x, long[] p) {
        long minPerson = Long.MAX_VALUE;
        for (long person : p) {
            if (person < minPerson) {
                minPerson = person;
            }
        }
        ArrayList<Integer> minPersonIndex = new ArrayList<>();
        int reassignIndex = -1;  // 之前重分配的i号房
        for (int i = x + 1; i <= x + n; i++) {
            if (p[i % n] == minPerson) {
                minPersonIndex.add(i % n);
            }
        }
        reassignIndex = minPersonIndex.get(minPersonIndex.size() - 1);  // reassignIndex为x之前人数的最小的一个房间
        // System.out.format("reassignIndex is %d\n", reassignIndex);
        // 开始将之前分配的拿回来
        int assignedPerson = 0;
        // 将分配的人数补齐到刚好分发了t次
        if (x > reassignIndex) {
            for (int i = reassignIndex + 1; i <= x; i++) {
                p[i]--;
                assignedPerson += 1;
            }
        } else if (x < reassignIndex) {
            for (int i = x + 1; i <= reassignIndex; i++) {
                p[i]++;
                assignedPerson -= 1;
            }
        }
        // 刚好分发的t次是
        long assignedTimes = p[reassignIndex];
        p[reassignIndex] = assignedPerson + (n + 1) * assignedTimes;  // 这里加上n + 1是因为后面还要减去一个
        for (int i = 0; i < n; i ++) {
            p[i] -= assignedTimes;
            System.out.format("%d ", p[i]);
        }

    }
}
