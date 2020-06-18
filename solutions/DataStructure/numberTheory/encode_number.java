package DataStructure.numberTheory;

public class encode_number {
    public String encode(int num) {
        // 思路:找规律,字符串的排列依次是1位,2位,3位,...，对于第n位都有2^(n-1)种选择,前置n位有2^n-1种选择
        // 先找到数字所在区间,再找到它的二进制表达
        // 测试用例
        // 1.0
        // 2.23
        // 3.2147483647
        int n = 0;
        for (int i = 0; i <= 32; i++) {
            if ((long)num + 1 < Math.pow(2, i)) {
                n = i;
                break;
            }
        }
        int x = (int)(num - Math.pow(2, n - 1) + 1);
        String res = "";
        for (int i = n - 2; i >= 0; i--) {
            int binDigit = x / (int)Math.pow(2, i);
            x %= Math.pow(2, i);
            res += binDigit;
        }
        return res;
    }

    // 思路优化:
    // 1.采用补码的形式即可,
    public String encode_op(int num) {
        return Integer.toBinaryString(num + 1).substring(1);
    }

    public static void main(String[] args) {
        encode_number s = new encode_number();
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE + 1));
        System.out.println(s.encode(Integer.MAX_VALUE ));
    }
}
