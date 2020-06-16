package 面试思路;

import java.util.*;

public class 多路归并排序 {
    public static void main(String[] args) {
        ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(new Integer[]{1,2, 3,5,7,9}));
        ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(new Integer[]{-8, -7, -6, -5, -3 }));
        ArrayList<Integer> arr3 = new ArrayList<>(Arrays.asList(new Integer[]{5,7,9}));
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<>();
        arrays.add(arr1);
        arrays.add(arr2);
        arrays.add(arr3);
        ArrayList<Integer> res = mergeSort(arrays);
        for (Integer ele : res) {
            System.out.println(ele);
        }


    }

    public static ArrayList<Integer> mergeSort(ArrayList<ArrayList<Integer>> arrays) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return arrays.get(o1.index).get(o1.value) - arrays.get(o2.index).get(o2.value);
            }
        });
        for (int i = 0; i < arrays.size(); i++) {
            pq.offer(new Node(i, 0));
        }
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.value + 1< arrays.get(node.index).size()) {
                pq.offer(new Node(node.index, node.value + 1));
            }
            res.add(arrays.get(node.index).get(node.value));
        }
        return res;
    }
}

class Node {
    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
    int index;
    int value;
}
