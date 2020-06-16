package 剑指offer;

public class 把字符串转换成整数 {
    public static int StrToInt(String str) {
        long res = 0;
        if (str == null || str.length() == 0) {  // 空字符串和null返回0
            return 0;
        }
        int flag = 0;
        if (str.charAt(0) == '+') {
            flag = 1;
        } else if (str.charAt(0) == '-') {
            flag = -1;
        }
        str = (flag == 0) ? str : str.substring(1, str.length());  // 截断符号
        int firstNum = 0;
        while (firstNum < str.length()) {
            if (str.charAt(firstNum) != '0') {
                break;
            }
            firstNum++;
        }
        str = str.substring(firstNum, str.length());  // 截断0
        if (str.length() > 10) {
            return 0;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0') {
                return 0;
            }
            res = res * 10 + (str.charAt(i) - '0');
        }
        res = (flag == -1) ? res * -1 : res;
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        System.out.format("res = %d", StrToInt("123"));
    }
}
