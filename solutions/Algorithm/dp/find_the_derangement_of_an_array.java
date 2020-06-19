package Algorithm.dp;

public class find_the_derangement_of_an_array {
    public int findDerangement(int n) {
        // 思路:动态规划,状态转移方程dp[i]=(dp[i-1]+dp[i-2])*i
        if (n == 1) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int bigNum = (int)Math.pow(10, 9) + 7;
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n; i++) {
            dp[i] = (int)((long)(dp[i-1] + dp[i-2]) * i % bigNum);
        }
        return dp[n-1];
    }

    // 细节优化
    // 1.dp由于只和前两项有关,因而时间复杂度可以从O(N)变成O(1)
}
