package Algorithm.dp;
import java.util.*;

// https://leetcode-cn.com/problems/longest-repeating-substring/

public class longest_repeating_substring {
    // 思路:
    // 1.动态规划,dp[i] = ①x + 1(若尾字符串出现在前面,x<=dp[i-1])  ③0, 其中dp[i]表示重复子串在末尾的最长重复子串长度
    // 时间复杂度O(nm),m取决于dp[i]的平均大小
    // 2.二分查找+字符串哈希, 分解为两个子问题:①通过二分查找判断可能存在最长子串的长度,时间复杂度o(logn)
    // ②对于指定的枚举长度,判断字符串中是否存在相应长度的两个字符串，时间复杂度o(n)(利用Rabin-Karp算法快速判断相邻字符串哈希)
    // 测试用例:
    //
    public int longestRepeatingSubstring_1(String S) {
        int[] dp = new int[S.length()];
        int res = 0;
        for (int i = 1; i < S.length(); i++) {
            int length = dp[i-1];
            while (length >= 0) {
                if (S.substring(0, i).contains(S.substring(i-length, i+1))) {
                    dp[i] = length + 1;
                    break;
                }
                length--;
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int search(int L, int a, long modulus, int n, int[] nums) {
        // compute the hash of string S[:L]
        long h = 0;
        for (int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;

        // already seen hashes of strings of length L
        HashSet<Long> seen = new HashSet();
        seen.add(h);
        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for(int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[start + L - 1]) % modulus;
            if (seen.contains(h)) return start;
            seen.add(h);
        }
        return -1;
    }

    public int longestRepeatingSubstring_2(String S) {
        int n = S.length();
        // convert string to array of integers
        // to implement constant time slice
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) nums[i] = (int)S.charAt(i) - (int)'a';
        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 24);

        // binary search, L = repeating string length
        int left = 1, right = n;
        int L;
        while (left <= right) {
            L = left + (right - left) / 2;
            if (search(L, a, modulus, n, nums) != -1) left = L + 1;
            else right = L - 1;
        }

        return left - 1;
    }
}
