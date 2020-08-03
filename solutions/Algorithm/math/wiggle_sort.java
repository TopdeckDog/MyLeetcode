package Algorithm.math;

import java.util.*;

public class wiggle_sort {
    // 思路
    // 1.先排序后,前半段和后半段混合插入,时间复杂度O(nlogn)
    // 2.数学法,遇到不符合规则的则交换当前元素和下一个元素,时间复杂度O(n)
    // 测试用例:
    // 1.异常数据(空数组, null)
    // 2.线性(递增或递减)
    // 3.先升后降或先降后升
    // 4.有升有降
    // 细节优化:
    // 1.若使用排序的方式可以不用再分配一个数组,可以在原数组上进行交换
    public void wiggleSort_final(int[] nums) {
        if (nums == null) {
            return;
        }
        boolean esc = true;
        int i = 0;
        while (i < nums.length - 1) {
            if ((nums[i] > nums[i+1] && esc) || (nums[i] < nums[i+1] && !esc)) {
                int tmp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = tmp;
            }
            i++;
            esc = !esc;
        }
    }

    public void wiggleSort_my(int[] nums) {

        Arrays.sort(nums);
        int[] sorted = Arrays.copyOf(nums, nums.length);
        int l = 0;
        int r = (nums.length + 1) / 2;
        int i = 0;
        while (i < nums.length) {
            if (l < (nums.length + 1) / 2) {
                nums[i++] = sorted[l++];
            }
            if (r < nums.length) {
                nums[i++] = sorted[r++];
            }
        }
    }
}