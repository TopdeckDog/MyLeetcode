package 字节跳动;

import java.util.*;

public class 机器人跳跃问题 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int h[] = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = sc.nextInt();
        }
        int e = 0;
        for (int i = n - 1; i >= 0; i--) {
            e = (int) Math.ceil((e + h[i]) / 2.0);
        }
        System.out.println(e);
    }
}
