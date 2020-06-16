package DataStructure.set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class 最短单词距离II {
    private Map<String, List<Integer>> posMap = new HashMap<>();
    public 最短单词距离II(String[] words) {
        for (int i = 0; i < words.length; i++) {
            List<Integer> pos = posMap.getOrDefault(words[i], new ArrayList<>());
            pos.add(i);
            posMap.put(words[i], pos);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> pos1 = posMap.get(word1);
        List<Integer> pos2 = posMap.get(word2);
        int shortestDis = 0;
        int i = 0; int j = 0;
        while (i < pos1.size() || j < pos2.size()) {
            shortestDis = Math.min(shortestDis, Math.abs(pos1.get(i) - pos2.get(j)));

            if (i < pos1.size() - 1 && j < pos2.size() - 1) {
                // 值小的先移动
                if (pos1.get(i) < pos2.get(j)) {
                    i++;
                } else {
                    j++;
                }
            } else if ((i == pos1.size() - 1) && (j < pos2.size() - 1)) {
                j++;
            } else if ((j == pos2.size() - 1) && (i < pos1.size() - 1)) {
                i++;
            } else {
                i++;
                j++;
            }
        }
        return shortestDis;
    }

    // 细节:
    // 1.只要某个列表到底了,后序就可以不用再遍历了,因为它附近的左右两个数都试过了
    public int shortest_optimization(String word1, String word2) {
        List<Integer> pos1 = posMap.get(word1);
        List<Integer> pos2 = posMap.get(word2);
        int shortestDis = 0;
        int i = 0; int j = 0;
        while (i < pos1.size() && j < pos2.size()) {
            shortestDis = Math.min(shortestDis, Math.abs(pos1.get(i) - pos2.get(j)));
            if (pos1.get(i) < pos2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return shortestDis;
    }
}
