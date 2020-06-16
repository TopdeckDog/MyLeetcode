package 其他公司;

// 快手的题
// 式子左右边化成ax+b的形式,然后求解
import java.util.*;
public class 解方程 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String experssion = sc.next();
        String leftE = experssion.split("=")[0];
        String rightE = experssion.split("=")[1];
        int[] leftPara = new int[2];
        int[] rightPara = new int[2];
        parseExpression(leftE, leftPara);
        parseExpression(rightE, rightPara);
        if (leftPara[0] == rightPara[0]) {
            System.out.println(-1);
        } else {
            System.out.println((rightPara[1] - leftPara[1]) / (leftPara[0] - rightPara[0]));
        }

    }

    public static void parseExpression(String expression, int[] para) {
        int a1 = 0;
        int b1 = 0;
        char lastModify = '+';
        for (int i = 0; i < expression.length(); i++) {
            int tmp = 1;  // C
            boolean x = false;
            while (i < expression.length() && expression.charAt(i) != '+' && expression.charAt(i) != '-') { // 判断一个表达式
                if (expression.charAt(i) <= '9' && expression.charAt(i) >= '0') {
                    // 找到下一个数字
                    int numEndIndex = findNextNum(expression, i);
                    int num = Integer.parseInt(expression.substring(i, numEndIndex));
                    if (tmp == 1) {
                        tmp = (lastModify == '+') ? num : (-1) * num;
                    } else {
                        tmp *= num;
                    }
                    i = numEndIndex - 1;  // 这里设置成数字的尾巴因为for循环又会++
                } else if (expression.charAt(i) == 'X') {
                    x = true;
                }
                // 乘法
                i++;

            }
            // 根据x将其加到a还是b
            if (x) {
                a1 += tmp;
            } else {
                b1 += tmp;
            }
            // 存储下一个字符的正负
            if (i < expression.length()) {
                lastModify = expression.charAt(i);
            }
        }
        para[0] = a1;
        para[1] = b1;
    }

    public static int findNextNum(String str, int index) {
        while (index < str.length()) {
            if (str.charAt(index) > '9' || str.charAt(index) < '0') {
                break;
            }
            index++;
        }
        return index;
    }

}
