package 字节跳动;

import java.util.*;

public class 球队比赛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            long n = sc.nextInt();
            long k = sc.nextInt();
            long d1 = sc.nextInt();
            long d2 = sc.nextInt();
            // q1 > q2, q3 > q2
            if (d1 >= 0 && d2 >= 0) {
                long score = (n - k - 2 * d1 - d2);

                System.out.println("yes");
            } else {
                System.out.println("no");
            }

        }
    }
}
