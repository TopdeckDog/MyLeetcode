

import java.util.*;

public class Api {
    public static void main(String[] args) {
        String path = "/home/////";
        System.out.println(path.replaceAll("//*", "/"));
        //showArray();

        // 2.Collections

        //showString();


    }

    public static void showArray() {
        // 1.数组
        // (1)初始化,注意数组也是对象
        // 动态初始化,系统默认初始化初始值
        int[] array1 = new int[10];
        int[][] array2 = new int[5][5];
        // 静态初始化,用户指定初始化值
        int[] array3 = new int[]{1,2,3};
        int[] array4 = {1,2,3};
        // (2)Arrays类工具
        // 排序,可以自己传入比较器,并且可以指定[begin, end).但是排序器只对引用数据类型有效,基本数据类型无法使用
        Arrays.sort(new Integer[]{1,2,3}, (e1, e2) -> {return e1 - e2;});
        // 填充
        Arrays.fill(array1, 1);
        // 复制,可以指定复制范围
        int[] array5 = Arrays.copyOf(array1, 5);
        int[] array6 = Arrays.copyOfRange(array1, 3,5);
        int[] array7 = array1.clone();
        // 查找,二分精准查找,若有多个则不一定找到的是哪一个,所以一般还需要重写
        int index = Arrays.binarySearch(array1, 1);
        // 转换
    }

    public static void showString() {
        // (1)初始化
        String s1 = "abc";
        String s2 = new String("abc");  // 这里创建了两个对象,一个在常量池中,一个在堆中
        String s3 = new String(new char[]{'a', 'b', 'c'});

        // (2)查找
        // 长度
        int length = s1.length();
        // 索引求字符
        char ch = s1.charAt(1);
        // 根据字符查找位置,还可以指定起始位置和查找方向
        int beginIndex = s1.indexOf("haha", 1);  // 从前往后找,找不到返回-1
        int lastIndex = s1.lastIndexOf("haha", 2);  // 从后向前找,找不到返回-1
        // 求子串[begin, end)
        String subS = s1.substring(0, 2);
        // 求分割,可以用正则表达式
        String[] split = s1.split("[ab]");

        // (3) 判断
        // 忽略大小写的比较
        boolean eq = s1.equalsIgnoreCase(s2);
        // 是否包含
        boolean con = s1.contains("a");
        // 是否以某段字符串开始或者结束
        boolean startWith = s1.startsWith("nihao");
        boolean endWith = s1.startsWith("bye");

        // (4)修改
        // 字符串连接
        String s4 = s1 + s2;
        String s5 = s1.concat(s2);  // 等效于+
        // 大小写转换
        String upperStr = s1.toUpperCase();
        String lowerStr = s1.toLowerCase();
        // 替换,可以替换字符、字符串、正则等
        String replaceStr1 = s1.replace('a', 'b');  // 只能替换字符
        String replaceStr2 = s1.replaceAll("[a-z]", "0");

        // (5)转换
        int strToInt = Integer.parseInt(s1);
        String intToStr = String.valueOf(3);
        char[] charArray = s1.toCharArray();

    }

    public static void print(Object o) {
        System.out.println(o);
    };


}
