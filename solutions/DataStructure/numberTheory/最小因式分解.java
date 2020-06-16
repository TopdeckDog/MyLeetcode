package DataStructure.numberTheory;

import java.util.ArrayList;
import java.util.List;

public class 最小因式分解 {
    public int smallestFactorization(int a) {
        // 思路:①先分解10以内的质因子,按照质因子大小排序②贪心的选择质因子,使得相乘起来小于10的质因子个数最少
        // 测试用例:
        // 1.只有一个数:1 、2、 5、 7
        // 2.超过32位有符号整数:48828125
        // 3.不存在这样的结果:13
        // 4.存在多种选择: 24、 270
        if (a == 1) return 1;
        if (a <= 0) return 0;
        int[] factor = new int[]{9,8,7,6,5,4,3,2};
        List<Integer> factors = new ArrayList<>();
        for (int i = 0; i < factor.length; i++) {
            while (a % factor[i] == 0) {
                a /= factor[i];
                factors.add(factor[i]);
            }
        }
        // 质因子分为后不能被整除则说明存在大于10的质因子
        if (a != 1) {
            return 0;
        }
        factors.sort((o1, o2) -> o1 - o2);
        if (factors.size() > 10) {
            return 0;
        }
        long res = 0;
        for (int i = 0; i < factors.size(); i++) {
            res *= 10;
            res += factors.get(i);
        }
        return res > Integer.MAX_VALUE ? 0 : (int)res;
    }

    // 错误次数:1
    // 1.贪心算法有问题,例如对于24,38比46小,但是按照贪心算得是46

    // 细节优化:
    // 1.将结果放在质因数分解里面,时间复杂度减少一半(因为少了一遍遍历)、
    public int smallestFactorization_op(int a) {
        if (a == 1) return 1;
        if (a <= 0) return 0;
        long res = 0, base = 1;
        for (int i = 9; i > 1; i--) {
            while (a % i == 0) {
                a /= i;
                res = base * i + res;
                base = 10 * base;
            }
        }
        return a < 2 && res <= Integer.MAX_VALUE ? (int)res : 0;
    }
}
