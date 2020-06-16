package 常见算法.search;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(BinarySearchMax(new int[]{1,2,3,3,5},  8));
        Solution s = new Solution();
        int[] res = s.searchRange(new int[]{5,7,7,8,8,10}, 8);


    }
    public static int BinarySearchMax(int[] array, int target) {
        int left = 0;
        int right = array.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] <= target)
                left = mid + 1;
            else
                right = mid;  // 这里改成right = mid, 否则会漏一些数据
        }
        if (right - 1 >= 0 && array[right - 1] == target) {  // 最终的结果应该是right - 1  // 考虑right越界
            return right - 1;
        }
        return -1;
    }

    static class Solution {
        public int[] searchRange(int[] nums, int target) {
            int begin = binarySearch(nums, target);
            int beginIndex = (begin == nums.length) ? -1 : begin;
            int endIndex = (begin == -1) ?  -1 : binarySearch(nums, target + 1) - 1;
            return new int[]{beginIndex, endIndex};
        }

        // 二分搜索求>= a的
        public int binarySearch(int nums[], int target) {
            int l =0;
            int r= nums.length;
            while (l < r) {
                int mid = l + ((r - l) >>> 1);
                System.out.format("%d %d %d\n", (r - l) >>> 1, l, mid);
                if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return r;
        }
    }
}
