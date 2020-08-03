package Algorithm.greedy;
import java.util.*;
public class shortest_way_to_form_string {
    // 思路:
    // 1.贪心法,暴力法，对于target从头从source遍历直到找不到为止,时间复杂度O(mn),m为结果次数
    // 2.哈希表/数组+二分查找,由于上述方法需要不厅的遍历source,效率很慢,可以为source的每个字符建立索引,索引后
    // 对于一个字符就是一个有序递增的序列,然后再在这个序列中二分查找,这样比直接遍历要快,空间换时间
    // 测试用例:
    // 1.异常用例(空指针,空串)
    // 2.target包含source没有的字母
    // 3.每次target的串就只能被source的长度为1一个子序列覆盖
    public int shortestWay_final(String source, String target) {
        int n = source.length(), m = target.length();
        List<Integer>[] index = new ArrayList[26];
        for (int i = 0; i < n; i ++)
        {
            char c = source.charAt(i);
            if (index[c - 'a'] == null) index[c - 'a'] = new ArrayList<>();
            index[c - 'a'].add(i);
        }
        int res = 1;
        for (int j = 0, last = -1; j < m; j ++)
        {
            char c  = target.charAt(j);
            if (index[c - 'a'] == null) return -1;
            int x = findNext(index[c - 'a'], last);//找到当前字母在前一个字母下标之后的下标；
            if (x == -1)
            {
                res ++;
                j --;
                last = -1;
            }
            else last = x;
        }
        return res;
    }
    private int findNext(List<Integer> list, int x)
    {
        int l = 0, r = list.size() - 1;
        while(l < r)
        {
            int mid = l + r >> 1;
            if (list.get(mid) > x) r = mid;
            else l = mid + 1;
        }
        return list.get(l) > x ? list.get(l) : -1;
    }

    public int shortestWay_my(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }
        int loop = 0;
        int index = 0;
        while (true) {
            int indexStart = index;
            for (int i = 0; i < source.length(); i++) {
                if (index < target.length() && target.charAt(index) == source.charAt(i)) {
                    index++;
                }
            }
            if (index == indexStart) {
                break;
            }
            loop++;
        }
        return (index == target.length()) ? loop : -1;
    }
}
