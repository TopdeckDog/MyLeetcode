package Algorithm.divide;

public class 分享巧克力 {
    // 第一遍做没做出来！！！！

    // 思路:二分 + 贪心
    // 二分查找分块大小，当无法划分成K块时将分块大小变小，当可以划分成K块时将分块大小变大，查找过程中以贪心算法判断是否可划分
    public int maximizeSweetness(int[] sweetness, int K) {
        int sum=0,ans=0;
        for(int i=0;i<sweetness.length;i++)
            sum+=sweetness[i];
        if(K==0) return sum;
        int l=0,r=sum/K+1;
        while(l<=r)
        {
            int mid=(l+r)/2;
            if(check(sweetness,mid,K))
            {
                ans=mid;
                l=mid+1;
            }
            else
                r=mid-1;
        }
        return ans;
    }

    private boolean check(int[] sweetness,int sum,int k)
    {
        int nowsum=0,num=0;
        for(int i=0;i<sweetness.length;i++)
        {
            nowsum+=sweetness[i];
            if(nowsum>=sum)
            {
                num++;
                nowsum=0;
            }
        }
        return num>=k+1;
    }
}
