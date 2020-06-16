package 剑指offer;

import java.util.Arrays;

public class 扑克牌顺子 {
    // 思路:
    // 1.排序,遍历,记住万能牌的个数
    // 测试用例:
    // 1.4个王,一个数字
    // 2. 重复数字
    // 3.王不足以填充
    // 4.王足以填充
    // 5.空数组
    public boolean isContinuous(int [] numbers) {
        if (numbers.length == 0) {
            return false;
        }
        Arrays.sort(numbers);
        int godCount = 0;
        boolean isFirst = true;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                godCount++;
                continue;
            }
            if (isFirst) {
                isFirst = false;
                continue;
            }
            if (numbers[i] == numbers[i-1]) {
                return false;
            }
            if (numbers[i] > numbers[i-1]) {
                godCount -= numbers[i] - numbers[i-1] - 1;
            }
        }
        return godCount >= 0;
    }
}
