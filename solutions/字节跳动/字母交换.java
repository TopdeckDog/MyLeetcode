package 字节跳动;

import java.util.*;

public class 字母交换 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            final int swapCount = sc.nextInt();
            ArrayList<Integer>[] letterPos = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                letterPos[i] = new ArrayList<>();
            }
            // 填充某个字母的的位置索引
            for (int i = 0; i < str.length(); i++) {
                letterPos[str.charAt(i) - 'a'].add(i);
            }
            int maxContinueCount = 1;
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < letterPos[i].size(); j++) {
                    maxContinueCount = Math.max(continueCount(letterPos[i], j, swapCount), maxContinueCount);
                }
            }
            System.out.println(maxContinueCount);
        }

    }

    public static int continueCount(ArrayList<Integer> indexs, int centerPos, int maxSwapCount) {
        int l = centerPos - 1;
        int r = centerPos + 1;
        int curSwapCount = 0;
        while (curSwapCount < maxSwapCount) {
            if (r < indexs.size() - 1 && l >= 0) {
                int rMove = indexs.get(r) - indexs.get(centerPos) - (r - centerPos); // r需要移动的次数
                int lMove = indexs.get(centerPos) - indexs.get(l) - (centerPos - l);  // l需要移动的次数
                if (rMove > lMove) {
                    curSwapCount += lMove;
                    l--;
                } else {
                    curSwapCount += rMove;
                    r++;
                }
            } else if (l >= 0) {
                int lMove = indexs.get(centerPos) - indexs.get(l) - (centerPos - l);  // l需要移动的次数
                curSwapCount += lMove;
                l--;
            } else if (r < indexs.size() - 1) {
                int rMove = indexs.get(r) - indexs.get(centerPos) - (r - centerPos); // r需要移动的次数
                curSwapCount += rMove;
                r++;
            } else {
                break;
            }
        }
        return r - l - 1;
    }
}

