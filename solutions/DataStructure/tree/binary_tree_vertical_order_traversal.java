package DataStructure.tree;

import javafx.util.Pair;

import java.util.*;

// https://leetcode-cn.com/problems/binary-tree-vertical-order-traversal/
public class binary_tree_vertical_order_traversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // 思路:
        // (1)先获取深度,最终不同的垂直方向个数为2*d-1个
        // (2)再层次遍历,依次将数据加入垂直列表中
        // (3)可能有的垂直列表中没有值,最后排除空list
        // 测试用例:
        // 1.空树:[]
        // 2.某个位置存在空:[2,null,3]
        // 3.右边子树先于左子树:[2,1,3,null,null,4]
        int depth = depth(root);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < 2 * depth - 1; i ++) {
            res.add(new ArrayList<Integer>());
        }

        Queue<PosNode> queue = new LinkedList<>();
        queue.offer(new PosNode(root, depth - 1));
        while (!queue.isEmpty()) {
            PosNode pNode = queue.poll();
            if (pNode.node == null) {
                continue;
            }
            List<Integer> pos = res.get(pNode.pos);
            pos.add(pNode.node.val);
            res.set(pNode.pos, pos);
            queue.offer(new PosNode(pNode.node.left, pNode.pos - 1));
            queue.offer(new PosNode(pNode.node.right, pNode.pos + 1));
        }

        // 删除空列表
        for (Iterator<List<Integer>> it = res.iterator(); it.hasNext();) {
            if (it.next().isEmpty()) {
                it.remove();
            }
        }
        return res;


    }

    public void levelOrder(TreeNode node, int vertical, List<List<Integer>> res) {
        if (node == null) {
            return;
        }
        List<Integer> pos = res.get(vertical);
        pos.add(node.val);
        res.set(vertical, pos);
        levelOrder(node.left, vertical - 1, res);
        levelOrder(node.left, vertical - 1, res);
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class PosNode {
        TreeNode node;
        int pos;

        public PosNode(TreeNode node, int pos) {
            this.node = node;
            this.pos = pos;
        }
    }

    // 细节优化:
    // 1.直接用哈希表存储一条垂直线上的元素,代替数组,避免因提前分配数组大小而需要深度遍历树获取深度,减少时间复杂度
    // 2.用Pair简化代码行数,当定义一个很简单不需要函数的类时用Pair代替
}
