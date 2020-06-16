package Algorithm.DFSBFS;
import java.util.*;

public class 加权嵌套序列和II {
    interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        // 思路:先求出最大深度,对其进行层次遍历,BFS
        // 测试用例:
        // 1.空list,[]
        // 2.nestedList中有空, [[], 1]
        // 3.多级嵌套, [1,[4,[6]]]
        // 4.不平衡嵌套 [[1,1],1,[1,[1]]]
        if (nestedList == null) {
            return 0;
        }
        int depth = depth(nestedList);
        // System.out.println(depth);
        Queue<Node> queue = new LinkedList<Node>();
        for (NestedInteger nest : nestedList) {
            queue.offer(new Node(nest, depth));
        }
        int res = 0;  // 结果值
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.nestedList == null) {
                continue;
            }
            if (node.nestedList.isInteger()) {
                //System.out.println(res);
                res += node.nestedList.getInteger() * node.depth;
            } else {
                for (NestedInteger nest : node.nestedList.getList()) {
                    queue.offer(new Node(nest, node.depth - 1));
                }
            }
        }
        return res;
    }

    public int depth(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) { // 当为空或者列表里没东西都算作无深度
            return 0;
        }

        int maxDepth = 1;
        for (NestedInteger nest : nestedList) {
            if (nest == null) {
                continue;
            }
            if (!nest.isInteger()) {
                maxDepth = Math.max(depth(nest.getList()) + 1, maxDepth);
            }
        }
        return maxDepth;
    }

    class Node {
        public NestedInteger nestedList;
        public int depth;
        public Node(NestedInteger nestedList, int depth) {
            this.nestedList = nestedList;
            this.depth = depth;
        }
    }

    // 拓展思路1:
    // 1.使用递归(dfs)来代替bfs,而且还不用记录深度,递归的特性决定了可以传递深度
    // 2.综上分析,空间复杂度O(d)(d为深度),时间复杂度O(2n)
    // 拓展思路2:
    // 1.层次遍历,每层的值加在下一层上计算,变相的相当于每一层计算了(depth-当前层)的次数,这样可以只遍历一次
    // 2.综上分析,空间换时间,因为要额外的空间将列表扁平化,空间复杂度O(n),时间复杂度O(n)
    public int depthSumInverse_optimization(List<NestedInteger> nestedList) {
        int res = 0;
        int levelSum = 0;
        List<NestedInteger> levelList = nestedList;
        while (!levelList.isEmpty()) {
            List<NestedInteger> nextLevelList = new ArrayList<>();
            for (NestedInteger nest : levelList) {
                if (nest.isInteger()) {
                    levelSum += nest.getInteger();
                } else {
                    nextLevelList.addAll(nest.getList());
                }
            }
            levelList = nextLevelList;
            res += levelSum;
        }
        return res;
    }

}
