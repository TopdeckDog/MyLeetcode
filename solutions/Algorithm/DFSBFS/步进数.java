package Algorithm.DFSBFS;
// https://leetcode-cn.com/problems/stepping-numbers/

import java.util.ArrayList;
import java.util.List;

public class 步进数 {
    public List<Integer> countSteppingNumbers(int low, int high) {
        // 思路:对于low,high范围内的每一个数判断它是否是步进数,时间复杂度O(k),k为最终解法的个数
        // 测试用例:
        // 1.low = high
        // 2.跨不同的位数
        // 3.无结果
        int lowDigit = digitNum(low);
        int highdDigit = digitNum(high);
        List<Integer> res = new ArrayList<Integer>();
        int[] limit = new int[]{low, high};
        for (int i = lowDigit; i <= highdDigit; i++) {
            steppingNumbers(res, 0, i, limit);
        }
        return res;
    }

    public int digitNum(int num) {
        int digit = 0;
        while (num > 0) {
            num /= 10;
            digit += 1;
        }
        return digit;
    }

    public void steppingNumbers(List<Integer> res, int curTotalNum, int leftDigit, int[] limit) {
        if (leftDigit == 0) {
            if (limit[0] <= curTotalNum && curTotalNum <= limit[1]) {
                res.add(curTotalNum);
            }
            return;
        }

        if (curTotalNum == 0) {
            for (int i = 1; i < ((leftDigit == 10) ? 2 : 10) ; i++) {
                steppingNumbers(res, i, leftDigit - 1, limit);
            }
        } else {
            int lastNum = curTotalNum % 10;
            int[] curNum = new int[]{lastNum - 1, lastNum + 1};
            for (int num : curNum) {
                if (0 <= num && num < 10) {
                    steppingNumbers(res, curTotalNum * 10 + num, leftDigit - 1, limit);
                }
            }
        }
    }

    // 错误次数:1
    // 未考虑测试用例:整数溢出,[0, 1000000000]

    // 思路优化:
    // 思路1:
    // 1.因为结果数不大,直接从0到9加入队列或栈,然后使用BFS或DFS进行筛选,最后排序,损失时间复杂度,但是代码量小
    public static void main(String[] args) {
        步进数 s = new 步进数();
        System.out.println(s.countSteppingNumbers(0, 1000000000).size());
    }
}
