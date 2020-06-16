package 剑指offer;

public class 孩子们的游戏 {
    // 思路:
    // 1.用链表模拟,当链表中只剩一个元素时返回
    // 2.数学法:TODO
    public int LastRemaining_Solution(int n, int m) {
        ListNode dummyHead = new ListNode(-1);
        ListNode tmp = dummyHead;
        for (int i = 0; i < n; i ++) {
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        tmp.next = dummyHead.next;
        while (dummyHead.next != dummyHead && dummyHead.next != null) {
            for (int j = 0; j < m - 1; j++) {
                dummyHead = dummyHead.next;
            }
            tmp = dummyHead.next;
            dummyHead.next = dummyHead.next.next;
            tmp = null;
        }
        return dummyHead.val;
    }
}

class ListNode {
    ListNode(int val) { this.val = val;}
    int val;
    ListNode next;
}
