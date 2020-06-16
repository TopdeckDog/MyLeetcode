package Algorithm.branchConsideration;
import java.util.List;
import java.util.ArrayList;


public class 缺失的区间 {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // 思路:顺序遍历,首为-1,尾为100
        // 测试用例:
        // 1.lower > upper
        // 2.lower = upper
        // 3.每一位都有
        // 4.首尾不等于lower和upper
        List<String> res = new ArrayList<String>();
        long curNum = lower - 1L;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == curNum + 2) {
                res.add(String.valueOf(curNum + 1));
            } else if (nums[i] > curNum + 2) {
                res.add(String.valueOf(curNum + 1) + "->" + String.valueOf(nums[i] - 1));
            }
            curNum = nums[i];
        }
        // 最后的值与upper比较
        if (upper - 1L == curNum) {
            res.add(String.valueOf(curNum + 1));
        } else if (upper - 1L > curNum) {
            res.add(String.valueOf(curNum + 1) + "->" + String.valueOf(upper));
        }
        return res;
    }

    // 错误次数:5次
    // 1.没考虑整数越界,解决办法:转换成long进行处理


    // 更优秀的解法:
    // 1.将添加到结果集的动作封装起来
    public List<String> findMissingRangesOptimization(int[] nums, int lower, int upper) {
        //防止溢出
        long l = (long) lower;
        long u = (long) upper;
        int n = nums.length;
        List<String> res = new ArrayList<>();
        //如果长度为0，直接把l，u加入即可
        if(nums == null || n == 0){
            add(res, l-1, u+1);
            return res;
        }
        add(res, l-1, nums[0]);
        for(int i=1; i<n; i++){
            add(res, nums[i-1], nums[i]);
        }
        add(res, nums[n-1], u+1);
        return res;
    }
    private void add(List<String> res, long l, long r){
        if(l == r || l+1 == r){
            return;
        }else if(l+1 == r-1){
            // res.add(String.valueOf(l+1));
            res.add(String.valueOf(l+1));
        }else{
            // StringBuilder sb = new StringBuilder();
            // sb.append(String.valueOf(l+1));
            // sb.append("->");
            // sb.append(String.valueOf(r-1));
            // res.add(sb.toString());
            res.add((l+1) + "->" + (r-1));
        }
    }
}
