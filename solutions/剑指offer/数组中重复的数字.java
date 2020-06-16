package 剑指offer;

import java.util.HashMap;
import java.util.Map;

public class 数组中重复的数字 {
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    // 思路:
    // 1.映射存储重复次数,两次遍历
    // 测试用例:空数组、无重复数组、重复数组、
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int number : numbers) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }
        for (int number : numbers) {
            if (map.get(number) > 1) {
                duplication[0] = number;
                return true;
            }
        }
        return false;
    }
}
