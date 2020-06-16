package DataStructure.tree;

import javax.swing.tree.TreeNode;

public class 子树的最大平均值 {
    public double maximumAverageSubtree(TreeNode root) {
        // 思路:分治法,要能分治,需要算出每个子树的总和、子树中所有节点个数即可
        int[] data = new int[2];
        return maximunSubtree(root, data);
    }

    private double maximunSubtree(TreeNode node, int[] data) {
        if (node == null) {
            return 0.0;
        }
        int[] data1 = new int[2];
        double leftAverage = maximunSubtree(node.left, data1);
        int[] data2 = new int[2];
        double rightAverage = maximunSubtree(node.right, data2);
        data[0] = data2[0] + data1[0] + 1;  // 整个子树结点个数
        data[1] = data2[1] + data1[1] + node.val;  // 整个子树的总和
        double average = data[1] / (double)data[0];
        return Math.max(Math.max(leftAverage, rightAverage), average);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        子树的最大平均值 solution = new 子树的最大平均值();
        TreeNode node = new TreeNode(2);
        node.right = new TreeNode(1);
        solution.maximumAverageSubtree(node);
    }
}


