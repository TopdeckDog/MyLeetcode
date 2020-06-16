package DataStructure.list;

import java.util.List;
import java.util.ListIterator;

public class 锯齿迭代器 {
    // 思路:用链表连接迭代器，当某一个迭代器遍历到尾部则将其从链表中剔除
    private int length;
    private int pos;
    private Node<ListIterator<Integer>> iterList;

    public 锯齿迭代器(List<Integer> v1, List<Integer> v2) {
        iterList = new Node<ListIterator<Integer>>(v1.listIterator());
        iterList.next = new Node<ListIterator<Integer>>(v2.listIterator());
        iterList.next.next = iterList;
        length = v1.size() + v2.size();
    }

    public int next() {
        if (!hasNext()) {
            return 0;
        }
        while (!iterList.t.hasNext()) {
            iterList = iterList.next;
        }
        int res = iterList.t.next();
        iterList = iterList.next;
        pos += 1;
        return res;
    }

    public boolean hasNext() {
        return pos < length;
    }

    class Node<T> {
        public T t;
        public Node next;

        public Node(T t) {
            this.t = t;
            next = null;
        }
    }

    // 扩展思路
    // 思路1:
    // 1.用arraylist存储不同的向量,另外一个arraylist存储向量中指针遍历的位置
    // 若遍历的位置在向量尾部则跳过该向量的遍历
}
