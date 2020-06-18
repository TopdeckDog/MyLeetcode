package Algorithm.divide;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class split_array_with_equal_sum {

    // 如下是我的两种错误解法
    // 测试用例:
    // 1.小于7:[1,1,1,1,1]
    // 2.无法找到满足条件的数组:[3,8,2,4,7,1,11,3,1]
    // 3.找得到满足条件的数组:[3,8,2,4,7,1,11,3,1,10]
    // 4.因为负数和0的出现导致相同总和的子数组有多种情况:[1,2,1,3,0,0,2,2,1,3,3]、[1,2,3,4,6,5,-5,10,-1,-2,5,4,4,-1,7]
    // (因为这种测试用例导致我思路有问题,无法贪心,因为后面仍然可能存在总和相同的子数组)
    public boolean splitArray1(int[] nums) {
        // 思路1:贪心,先确定第1位,后面长度就固定了
        // 测试用例
        // 1.小于7:[1,1,1,1,1]
        // 2.无法找到满足条件的数组:[3,8,2,4,7,1,11,3,1,10]
        // 3.找得到满足条件的数组:[]
        if (nums.length < 7) {
            return false;
        }
        // 求和数组
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1] + nums[i];
        }
        // 先确定i,然后jk的位置就确定了
        for (int i = 1; i < nums.length - 5; i++) {
            //System.out.println(i);
            int sum_i = sum[i-1];
            // 计算j的位置
            int j = i + 2;
            while (j < nums.length - 3) {
                if (sum[j-1] - sum[i] >= sum_i) {
                    //System.out.println(j);
                    break;
                }
                j++;
            }
            if (sum[j-1] - sum[i] != sum_i) {
                continue;
            }
            // 计算k的位置
            int k = j + 2;
            while (k < nums.length - 1) {
                if (sum[k-1] - sum[j] >= sum_i) {
                    //System.out.println(k);
                    break;
                }
                k++;
            }
            if (sum[k-1] - sum[j] != sum_i) {
                continue;
            }
            // 最终校验
            if (sum[nums.length - 1] - sum[k] == sum_i) {
                return true;
            }
        }
        return false;
    }

    public boolean splitArray2(int[] nums) {
        // 思想:贪心,先确定第1位,后面长度就固定了,考虑多个0的情况
        // 测试用例
        // 1.小于7:[1,1,1,1,1]
        // 2.无法找到满足条件的数组:[3,8,2,4,7,1,11,3,1]
        // 3.找得到满足条件的数组:[3,8,2,4,7,1,11,3,1,10]
        if (nums.length < 7) {
            return false;
        }
        // 求和数组
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1] + nums[i];
        }
        // 先确定i,然后jk的位置就确定了
        for (int i = 1; i < nums.length - 5; i++) {
            System.out.format("i is %d\n", i);
            int sum_i = sum[i-1];
            // 计算j的位置
            for (int j = i + 2; j < nums.length - 3; j++) {
                if (sum[j-1] - sum[i] < sum_i) {
                    continue;
                } else if (sum[j-1] - sum[i] == sum_i) {
                    // 计算k的位置
                    System.out.format("j is %d\n", j);
                    for (int k = j + 2; k < nums.length - 1; k++) {
                        if (sum[k-1] - sum[j] < sum_i) {
                            continue;
                        } else if (sum[k-1] - sum[j] == sum_i) {
                            System.out.format("k is %d\n", k);
                            // 最终校验
                            if (sum[nums.length - 1] - sum[k] == sum_i) {
                                return true;
                            }
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }

    // 思路1:hashset+累计和剪枝
    // (1)遍历每一个i,j,k可能的情况
    // (2)用累计和快速计算某个数组区间的累计和
    // (3)当第一个和第二个累计和不相等则剪枝掉
    // (4)用类似twoSum的思想,先固定j,先在[0,j]区间找能划分该区间的i,通过hashset存储相等的长度,以同样的手段在[j,n-1]找划分该区间的k,然后判断相等的长度是否也能划分[0,j]
    public boolean splitArray(int[] nums) {
        if (nums.length < 7) {
            return false;
        }
        // 求和数组
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i-1] + nums[i];
        }

        for (int j = 3; j < nums.length - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (sum[i-1] == sum[j-1] - sum[i]) {
                    set.add(sum[i-1]);
                }
            }

            for (int k = j + 2; k < nums.length - 1; k++) {
                if ((sum[sum.length - 1] - sum[k] == sum[k-1] - sum[j]) && (set.contains(sum[sum.length - 1] - sum[k]))) {
                    return true;
                }
            }
        }
        return false;
    }


}
