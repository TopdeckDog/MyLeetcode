package DataStructure.tree;

import java.util.HashMap;
import java.util.Map;

public class path_sum_iv {
    public int pathSum(int[] nums) {
        // 思路1:构建一颗树然后计算根到叶子节点路径之和
        // 思路2:每个节点计算次数为子节点计算次数之和,用前两位数可以表示节点,因而用哈希表存储节点计算次数即可,那么dp[i][j] = dp[i+1][j*2-1] + dp[i+1][j*2],倒序遍历即可
        // 测试用例:
        // 1.空:[]
        // 2.单节点:[115]
        // 3.只有左子树:[113,221]
        // 4.左右子树都有:[113,215,221]
        Map<Integer, Integer> count = new HashMap<>();
        int pathsum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int left = (nums[i] / 100 + 1) * 10 + (nums[i] % 100) / 10 * 2 - 1;
            int right = (nums[i] / 100 + 1) * 10 + (nums[i] % 100) / 10 * 2;
            int node = (nums[i] / 100) * 10 + (nums[i] % 100) / 10;
            int nodeCount = count.getOrDefault(left, 0) + count.getOrDefault(right, 0);
            nodeCount = (nodeCount == 0) ? 1 : nodeCount;
            pathsum += (nums[i] % 10) * nodeCount;
            count.put(node, nodeCount);
        }
        return pathsum;
    }
}
