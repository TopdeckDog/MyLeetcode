package 字节跳动;

import java.util.*;

public class 手串艺人 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int c = sc.nextInt();
            ArrayList<Integer>[] colorBucket = new ArrayList[c+1];
            for (int color = 0; color <= c; color++) {
                colorBucket[color] = new ArrayList<Integer>();
            }
            HashSet<Integer> problemColors = new HashSet<>();
            for (int i = 0; i < n; i++) {  // i为当前遍历的串珠的位置
                int colorNums = sc.nextInt();
                for (int j = 0; j < colorNums; j++) {
                    // 将颜色加入到颜色桶中
                    int color = sc.nextInt();
                    if (!colorBucket[color].isEmpty()) {
                        int lastPos = colorBucket[color].get(colorBucket[color].size() - 1);
                        if (i - lastPos < m) problemColors.add(color);
                    }
                    colorBucket[color].add(i); // i为当前串珠的位置
                }
            }
            // 最后再找一下有没有首尾距离小于m
            for (int color = 1; color <= c; color++) {
                if (colorBucket[color].size() < 2) {
                    continue;
                }
                if (colorBucket[color].get(0) + n - colorBucket[color].get(colorBucket[color].size() - 1) < m) {
                    problemColors.add(color);
                }
            }
            System.out.println(problemColors.size());
        }
    }
}
