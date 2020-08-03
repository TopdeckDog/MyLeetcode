package Algorithm.math;

class find_permutation {
    // *****没思路
    // 思路:数学归纳法,低于连续的I,以升序排列为最小,对于连续的D,以降序排列为最小
    // 测试用例:
    // 1.异常用例(空,unll)
    // 2.特殊用例(单长度, D, I)
    // 3.先升后降(IDDD)
    // 4.先降后升(DDDII)
    // 5.曲折的升降(DIDIIDIDID)
    // 优化点:
    // 1.找连续区间可用双重循环,第二层循环在遇到D时开始,没有D时结束
    // 2.不用将其s变成char数组,用到时自然charAt,节省空间复杂度
    public int[] findPermutation_final(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int[] res = new int[s.length() + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }

        int i = 1;
        while (i <= s.length()) {
            int j = i;
            while (i <= s.length() && s.charAt(i - 1) == 'D') {
                i++;
            }
            reverseArray(res, j - 1, i - 1);
            i++;
        }
        return res;
    }

    public int[] findPermutation_my(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        char[] relations = s.toCharArray();
        int[] res = new int[relations.length + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }

        int DBegin = -1;
        for (int i = 0; i < relations.length; i++) {
            if (relations[i] == 'I') {
                if (DBegin != -1) {
                    reverseArray(res, DBegin, i);
                    DBegin = -1;
                }
            } else {
                if (DBegin == -1) {
                    DBegin = i;
                }
            }
        }
        if (DBegin != -1) {
            reverseArray(res, DBegin, relations.length);
        }
        return res;
    }

    private void reverseArray(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }
}
