package 字节跳动;

import java.util.*;

public class 外卖小哥的保温箱 {
    static int sum = 0;
    static int k = 0;
    static ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
        }
        Integer[] indexs = new Integer[n];
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
            indexs[i] = i;
        }
        Arrays.sort(indexs, (o1, o2) -> b[o2] - b[o1]);
        int boxSum = 0;
        while (boxSum < sum) {
            boxSum += b[indexs[k]];
            k++;
        }
        dfs(0, b, indexs, sum, new ArrayList<Integer>());
        int maxAvailableSum = 0;
        for (ArrayList<Integer> boxes : res) {
            int curAvailableSum = 0;
            for (Integer box : boxes) {
                curAvailableSum += a[indexs[box]];
            }
            maxAvailableSum = Math.max(curAvailableSum, maxAvailableSum);
        }
        System.out.format("%d %d", k, sum - maxAvailableSum);

    }

    public static void dfs(int watermark, int[] b, Integer[] indexs, int sum, ArrayList<Integer> box) {
        if (box.size() == k) {
            res.add((ArrayList<Integer>) box.clone());
            return;
        }
        while (k - box.size() < b.length - watermark) {  // 当剩余可添加的个数还充足时
            if (b[indexs[watermark]] * (k - box.size()) < sum) { // 当剩余的怎么也填不满时,就直接返回
                return;
            }
            box.add(watermark);
            dfs(watermark + 1, b, indexs, sum - b[indexs[watermark]], box);
            box.remove(box.size() - 1);
            watermark++;  // 当前水位往后移动
        }
    }
}