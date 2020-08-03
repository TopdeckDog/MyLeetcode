import com.sun.org.apache.bcel.internal.classfile.Code;

import java.sql.Timestamp;
import java.util.*;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        AutocompleteSystem solution = new AutocompleteSystem(new String[]{"i love you","island","iroman","i love leetcode"}, new int[]{5,3,2,2});
        List<String> l1 = solution.input('i');
        print(l1);
        List<String> l2 = solution.input(' ');
        print(l2);
        List<String> l3 = solution.input('a');
        print(l3);
        List<String> l4 = solution.input('#');
        print(l4);
        List<String> l5 = solution.input('i');
        List<String> l6 = solution.input(' ');

        List<String> l7 = solution.input('a');

        List<String> l8 = solution.input('#');
        print(l7);
    }

    public static void print(List<String> l) {
        System.out.println("-----------------------------");
        for (String s : l) {
            System.out.println(s);
        }
        System.out.println("-----------------------------");
    }
}

class AutocompleteSystem {
    // 思路:以下以n代表句子个数,m代表句子长度
    // 1.前缀树,空间复杂度O(nm)(采用map),每一次搜索得到联想结果时间复杂度O(nlogn)(对句子结果排序)
    // 2.用hashmap<prefeix, List<sentence>>存某一个前缀对应的所有句子,空间复杂度O(nm),每一次搜索得到联想结果时间复杂度O(n)
    private Map<String, TreeSet<SentenceHeat>> prefixMap;
    private StringBuffer historyInput;
    private static int TOPN = 3;
    public AutocompleteSystem(String[] sentences, int[] times) {
        int length = sentences.length;
        prefixMap = new HashMap<>();
        historyInput = new StringBuffer();
        for (int i = 0; i < length; i++) {
            updateSentence(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            String sentence = historyInput.toString();
            int time = 1;
            TreeSet<SentenceHeat> prefixSentence = prefixMap.get(sentence);
            if (prefixSentence != null) {
                for (SentenceHeat sh : prefixMap.get(sentence)) {
                    if (sh.sentence.equals(sentence)) {
                        time = sh.time + 1;
                    }
                }
            }
            // 更新sentence的热度
            updateSentence(sentence, time);
            historyInput.setLength(0);
            return res;
        }
        historyInput.append(c);
        String prefix = historyInput.toString();
        TreeSet<SentenceHeat> prefixSentence = prefixMap.get(prefix);
        if (prefixSentence == null) { // 这里说明该前缀不存在
            return res;
        }

        Iterator<SentenceHeat> iter = prefixSentence.iterator();
        while (iter.hasNext() && res.size() < TOPN) {
            res.add(iter.next().sentence);
        }
        return res;
    }

    class SentenceHeat {
        public int time;
        public String sentence;
        public SentenceHeat(int time, String sentence) {
            this.time = time;
            this.sentence = sentence;
        }

        @Override
        public int hashCode() {
            return Objects.hash(time, sentence);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SentenceHeat that = (SentenceHeat) o;

            if (time != that.time) return false;
            return sentence.equals(that.sentence);
        }
    }

    private void updateSentence(String sentence, int time) {
        for (int j = 0; j < sentence.length(); j++) {
            String prefix = sentence.substring(0, j+1);
            if (!prefixMap.containsKey(prefix)) {
                prefixMap.put(prefix, new TreeSet<>((o1, o2) -> {
                    if (o1.time != o2.time) {
                        return o2.time - o1.time;
                    }
                    return o1.sentence.compareTo(o2.sentence);
                }));
            }
            prefixMap.get(prefix).remove(new SentenceHeat(time - 1, sentence));
            prefixMap.get(prefix).add(new SentenceHeat(time, sentence));
        }

    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */