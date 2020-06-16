package 剑指offer;

public class 左旋转字符串 {
    // 思路:
    // 1.找到分割点,然后进行分割后再拼接
    public String LeftRotateString(String str,int n) {
        if (str.length() == 0) {
            return "";
        }
        int splitPos = n % str.length();
        String left = str.substring(0, splitPos);
        String right = str.substring(splitPos, str.length());
        return right + left;
    }
}
