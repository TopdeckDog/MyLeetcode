package 字节跳动;

        import java.util.*;

public class 用户喜好 {
    // 用hashmap存储喜好值为k的用户,里面再按照用户id排序
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int n = sc.nextInt();
            Map<Integer, ArrayList<Integer>> likes = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                int likeValue = sc.nextInt();
                ArrayList<Integer> userlike = likes.getOrDefault(likeValue, new ArrayList<Integer>());
                userlike.add(i);
                likes.put(likeValue, userlike);
            }
            int q = sc.nextInt();
            for (int i = 0; i < q; i++) {
                queryLike(sc.nextInt(), sc.nextInt(), likes.get(sc.nextInt()));
            }
        }
    }

    public static void queryLike(int beginUser, int endUser, ArrayList<Integer> userlike) {
        // 找到set中[beginUser,endUser]的数目
        if (userlike == null) {
            System.out.println(0);
            return;
        }
        int beginIndex = binarySearch(userlike, beginUser);
        int endIndex = binarySearch(userlike, endUser);
        System.out.println(endIndex - beginIndex);
    }

    public static int binarySearch(ArrayList<Integer> array, int target) {
        // 找到第一个>=target的位置,找不到则返回array.size()
        int l = 0; int r = array.size();
        while (l < r) {
            int mid = l + (r - l) >>> 1;
            if (array.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}
