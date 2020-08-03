package DataStructure.list;

class design_phone_directory {
    // 思路:利用bool数组记录它是否被分配,利用链表分配号码
    // 测试用例:
    // 1.异常场景(多次释放,初始化时传入异常值)
    // 2.都分配完了在请求分配
    // 3.分配好了的被释放了
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    private boolean[] allocFlag;
    private Node dummyNode;
    public design_phone_directory(int maxNumbers) {
        allocFlag = new boolean[maxNumbers];
        dummyNode = new Node(-1);
        Node tmp = dummyNode;
        for (int i = 0; i < maxNumbers; i++) {
            tmp.next = new Node(i);
            tmp = tmp.next;
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (dummyNode.next == null) {
            return -1;
        }

        int num = dummyNode.next.num;
        allocFlag[num] = true;
        dummyNode.next = dummyNode.next.next;
        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !allocFlag[number];
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (number >= allocFlag.length || !allocFlag[number]) {
            return;
        }

        allocFlag[number] = false;
        Node tmp = dummyNode.next;
        dummyNode.next = new Node(number);
        dummyNode.next.next = tmp;
    }

    class Node {
        public int num;
        public Node next;
        public Node(int num) {
            this.num = num;
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
