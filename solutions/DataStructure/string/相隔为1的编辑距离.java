package DataStructure.string;

public class 相隔为1的编辑距离 {
    public boolean isOneEditDistance(String s, String t) {
        // 思路:双指针、
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        if (s.length() == t.length()) {
            int diffCnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    diffCnt++;
                }
            }
            return diffCnt == 1;
        }
        int i1 = 0;
        int i2 = 0;
        s = s + "!";
        t = t + "!";
        while (i1 < s.length() && i2 < t.length()) {
            //System.out.format("c1 %s, c2 %s\n", s.charAt(i1), t.charAt(i2));
            if (s.charAt(i1) == t.charAt(i2)) {
                i1++;
                i2++;
            } else {
                if (s.length() > t.length()) {
                    i1++;
                } else {
                    i2++;
                }
            }
        }
        //System.out.format("i1 = %d, i2=%d\n", i1, i2);
        return i1 == s.length() && i2 == t.length();
    }

    // 更好的解法：
    // 技巧1:当两个参数对称时,可以再次利用该函数,将两个参数互换位置
    // 技巧2:不用一个一个比较,只需要找到不同的字符点,然后比较前后字符串是否相等
    public boolean isOneEditDistance2(String s, String t) {

        int ns = s.length();
        int nt = t.length();

        // Ensure that s is shorter than t.
        if (ns > nt)
            return isOneEditDistance(t, s);

        // The strings are NOT one edit away distance
        // if the length diff is more than 1.
        if (nt - ns > 1)
            return false;

        for (int i = 0; i < ns; i++)
            if (s.charAt(i) != t.charAt(i))
                // if strings have the same length
                if (ns == nt)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                    // if strings have different lengths
                else
                    return s.substring(i).equals(t.substring(i + 1));

        // If there is no diffs on ns distance
        // the strings are one edit away only if
        // t has one more character.
        return (ns + 1 == nt);
    }
}
