package 字节跳动;


import java.util.Scanner;

/*
给定一个数组序列, 需要求选出一个区间, 使得该区间是所有区间中经过如下计算的值最大的一个：

区间中的最小数 * 区间所有数的和最后程序输出经过计算后的最大值即可，不需要输出具体的区间。如给定序列  [6 2 1]则根据上述公式, 可得到所有可以选定各个区间的计算值:



[6] = 6 * 6 = 36;

[2] = 2 * 2 = 4;

[1] = 1 * 1 = 1;

[6,2] = 2 * 8 = 16;

[2,1] = 1 * 3 = 3;

[6, 2, 1] = 1 * 9 = 9;



从上述计算可见选定区间 [6] ，计算值为 36， 则程序输出为 36。

区间内的所有数字都在[0, 100]的范围内;
 */
public class 区间和区间最小值乘积最大化 {
    // 贪心:对于每个值,向左向右延伸直到遇到比它小的值为止,则可以计算出对于该最小值的最大区间,对每个都这么做
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            System.out.println(maxRangeValue(nums));
        }
    }

    public static int maxRangeValue(int[] nums) {
        int maxV = 0;
        for (int i = 0; i < nums.length; i++) {
            int rangeSum = nums[i];
            int r = i + 1; int l = i - 1;
            while (r < nums.length && nums[r] >= nums[i]) {
                rangeSum += nums[r++];
            }
            while (l >= 0 && nums[l] >= nums[i]) {
                rangeSum += nums[l--];
            }
            int rangeV = rangeSum * nums[i];
            if (rangeV > maxV) {
                maxV = rangeV;
            }
        }
        return maxV;
    }
}
