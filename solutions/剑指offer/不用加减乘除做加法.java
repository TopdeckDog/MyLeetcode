package 剑指offer;

public class 不用加减乘除做加法 {
    // 思路:
    // 1.巧妙应用位运算:两数异或等于不求进位的和,两数与再左移等于进位和
    public int Add(int num1,int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return  num1;
    }
}
