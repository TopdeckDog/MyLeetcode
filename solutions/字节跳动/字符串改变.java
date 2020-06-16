package 字节跳动;

// 思路:滑动窗口,将字符串转换成[a1,b1,a2,b2,...]的形式,然后滑动窗口的看最大长度
import java.util.*;
public class 字符串改变 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String str = sc.next();
        ArrayList<Integer> indexs = new ArrayList<>();
        int lastIndex = 0;
        for (int i = 1; i <= str.length(); i++) {
            if (i == str.length()) {
                indexs.add(i - lastIndex);
            } else if (str.charAt(i) != str.charAt(i-1)) {
                indexs.add(i - lastIndex);
                lastIndex = i;
            }
        }
        // 开始滑动窗口
        int res1 = maxContinueChar(indexs, m);
        for (int i = 1; i < indexs.size(); i += 2) {
            int tmp = indexs.get(i);
            indexs.set(i, indexs.get(i - 1));
            indexs.set(i - 1, tmp);
        }
        int res2 = maxContinueChar(indexs, m);
        System.out.println(Math.max(res1, res2));

    }

    public static int maxContinueChar(ArrayList<Integer> indexs, int m ) {
        int l = 0; int r = 1;
        int res = 0;
        while (l < r) {
            int continueChars = indexs.get(l);
            while (r < indexs.size() && m - indexs.get(r) >= 0) {
                m -= indexs.get(r);
                continueChars += indexs.get(r);
                if (r + 1 < indexs.size()) continueChars += indexs.get(r + 1);
                r += 2;
            }
            // 表示要么指针已经走到尽头了,要么m次数不够了需要将窗口向右滑动
            res = Math.max(res, continueChars);
            // System.out.format("continueChars is %d, l %d, r %d\n", continueChars, l , r);
            if (r > l + 1) {  // 只有r指针大于l + 1说明这一个是当时填充了的
                continueChars -= indexs.get(l);
                m += indexs.get(l + 1);
            }
            l += 2;
        }
        return res;
    }
}
