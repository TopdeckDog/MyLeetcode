package 面试思路;

import java.util.LinkedList;

public class 倒序數字 {
    public static void main(String[] args) {
        System.out.println(reverseNum(-1255123));
    }

    public static int reverseNum (int num) {
        boolean negFlag = (num >= 0);
        if (!negFlag) num = num * -1;
        LinkedList<Integer> queue = new LinkedList<>();
        while (num > 0) {
            queue.offer(num % 10);
            num /= 10;
        }
        while (!queue.isEmpty()) {
            num = num * 10 + queue.poll();
        }
        return negFlag ? num : -num;
    }
}
