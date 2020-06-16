package 其他公司;

// 思路:用栈存储，遇到|和[就入栈，遇到]就弹栈
import java.util.*;
public class 压缩算法 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String compress = sc.next();
        Deque<String> dq = new ArrayDeque<>();
        System.out.println("???");
        int i = 0;
        while (i < compress.length()) {
            if (compress.charAt(i) <= 'Z' && compress.charAt(i) >= 'A') {
                int wordEnd1 = (compress.indexOf("[", i) == -1) ? compress.length() : compress.indexOf("[", i);
                int wordEnd2 = (compress.indexOf("]", i) == -1) ? compress.length() : compress.indexOf("]", i);;
                int wordEnd = Math.min(wordEnd1, wordEnd2);
                dq.offerLast(compress.substring(i, wordEnd));
                System.out.println(compress.substring(i, wordEnd));
                i = wordEnd;
            } else if (compress.charAt(i) <= '9' && compress.charAt(i) >= '0') {
                int numEnd = compress.indexOf("|", i);
                dq.offerLast(compress.substring(i, numEnd));
                System.out.println(compress.substring(i, numEnd));
                i = numEnd;

            } else if (compress.charAt(i) == ']') {
                String str = dq.pollLast();
                int num = Integer.parseInt(dq.pollLast());
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    sb.append(str);
                }
                while (!dq.isEmpty() && (dq.peekLast().charAt(0) >= 'A' && dq.peekLast().charAt(0) <= 'Z')) {
                    sb.insert(0, dq.pollLast());
                }
                dq.offerLast(sb.toString());
                System.out.println(dq.peekLast());
                i++;
            } else {  // 这种情况就是[或者|
                i++;
                continue;
            }
        }

        StringBuilder res = new StringBuilder();
        while (!dq.isEmpty()) {
            res.append(dq.pollFirst());
        }
        System.out.println(res.toString());
    }
}