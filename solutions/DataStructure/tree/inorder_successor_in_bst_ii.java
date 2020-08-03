package DataStructure.tree;


public class inorder_successor_in_bst_ii {
    // 思路:分类法,对于没有右子树的节点,中序后继为它的父节点;对于有右子树的节点，中继后续为它的右子树贪心的寻找左子节点
    // 测试用例:
    // 1.异常场景(空节点)
    // 2.叶子节点
    // 3.只有左子树
    // 4.只有右子树
    // 5.左右子树都有
    public Node inorder_successor_in_bst_ii(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            Node tmp = node.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            return tmp;
        }

        Node tmp = node.parent;
        while (tmp != null && tmp.val < node.val) {
            tmp = tmp.parent;
        }
        return tmp;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}