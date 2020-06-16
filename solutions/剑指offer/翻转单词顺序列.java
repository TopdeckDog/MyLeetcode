package 剑指offer;

public class 翻转单词顺序列 {
    // 思路:
    // 1.分割字符串,反序遍历输出
    // 测试用例:
    // 正常字符串、空字符串、空格字符串
    public static String ReverseSentence(String str) {
        if (str == null) {
            return null;
        }
        if (str.trim().equals("")) {
            return str;
        }
        String[] words = str.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            res.append(words[i]);
            res.append(" ");
        }
        return res.substring(0, res.length() - 1);
    }

}
