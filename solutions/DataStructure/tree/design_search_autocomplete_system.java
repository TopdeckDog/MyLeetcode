package DataStructure.tree;

import java.util.*;

public class design_search_autocomplete_system {
    // 思路:以下以n代表句子个数,m代表句子长度
    // 1.前缀树,空间复杂度O(nm)(采用数组或map),每一次搜索得到联想结果时间复杂度O(nlogn)(前缀所在节点的子树遍历，然后用优先队列进行排序))
    // 2.用hashmap<prefeix, List<sentence>>存某一个前缀对应的所有句子,空间复杂度O(nm),每一次搜索得到联想结果时间复杂度O(n)
    // 测试用例:
    // 1.异常场景:(多次输入#, 输入中string有null)
    // 2.某个字符串为另一个字符串的子串()
    // 3.多个字符串前缀相同
    private Map<String, TreeSet<SentenceHeat>> prefixMap;
    private StringBuffer historyInput;
    private static int TOPN = 3;
    public design_search_autocomplete_system(String[] sentences, int[] times) {
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

class design_search_autocomplete_system_final {
    private Trie trie;
    public design_search_autocomplete_system_final(String[] sentences, int[] times) {
        trie = new Trie();
        int n = sentences.length;
        for (int i = 0; i < n; ++i)
            trie.insert(sentences[i], times[i]);
    }

    public List<String> input(char c) {
        return trie.searchChar(c);
    }

    class Trie {
        private TrieNode root;
        private TrieNode current;           // 在插入过程中的当前节点，一句话插完后重置为root
        private PriorityQueue<TrieNode> pq; // 小顶堆，自定义比较函数，用来保存排在前3的句子
        private StringBuilder sb;           // 单个字符输入时，保存已输入字符

        public Trie() {
            root = new TrieNode();
            current = root;
            Comparator<TrieNode> comparator = new Comparator<TrieNode>() {
                public int compare(TrieNode node1, TrieNode node2) {
                    if (node1.times != node2.times)
                        return node1.times - node2.times;
                    return node2.sentence.compareTo(node1.sentence);
                }
            };
            pq = new PriorityQueue(4, comparator);
            sb = new StringBuilder();
        }

        public void insert(String sentence, int times) {
            int n = sentence.length();
            for (int i = 0; i < n; ++i)
                insertNewChar(sentence.charAt(i));
            current.sentence = sentence;
            current.times += times;
            current = root;
        }

        // 插入新字符，并更新current
        // 之前前缀没有该字符，返回true
        private boolean insertNewChar(char c) {
            int charNo = c == ' ' ? 26 : c - 'a';
            if (current.children[charNo] != null) {
                current = current.children[charNo];
                return false;
            }
            current.children[charNo] = new TrieNode();
            current = current.children[charNo];
            return true;
        }

        // 在当前节点中搜索字符，如果为终止符，则结束该句搜索过程
        // 否则，如果插入字符之前没有该前缀，则返回空列表
        // 否则，从该字符所在的点开始，搜索所有句子，并找出其中3个最大的
        public List<String> searchChar(char c) {
            List<String> result = new LinkedList();
            if (c == '#') {
                current.sentence = sb.toString();
                ++current.times;
                current = root;
                sb.delete(0, sb.length());
                return result;
            }

            sb.append(c);

            if (insertNewChar(c))
                return result;

            searchSentence(current);
            while (pq.peek() != null)
                result.add(0, pq.poll().sentence);

            return result;
        }

        // 从node点开始向下寻找句子，保存在优先队列里，超过3个弹出
        private void searchSentence(TrieNode node) {
            if (node.sentence != null) {
                pq.offer(node);
                if (pq.size() > 3)
                    pq.poll();
            }
            for (TrieNode child: node.children) {
                if (child != null)
                    searchSentence(child);
            }
        }
    }

    class TrieNode {
        public TrieNode[] children;
        public String sentence;     // 如果该点为句子终点，保存整个句子
        public int times;           // 如果该点为句子终点，记录句子热度

        public TrieNode() {
            times = 0;
            children = new TrieNode[27];
            sentence = null;
        }
    }

}





